package hr.fer.zemris.apr.optimisations.search;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.domain.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.Double.MAX_VALUE;

public class SimplexSearch implements SearchAlgorithm {

    private final double alfa;
    private final double beta;
    private final double gamma;
    private final double sigma;
    private final double simplexGeneratingStep;
    private final Function<IVector, Double> function;

    public SimplexSearch(Function<IVector, Double> function, double alfa, double beta, double gamma, double sigma, double simplexGeneratingStep) {
        this.alfa = alfa;
        this.beta = beta;
        this.gamma = gamma;
        this.sigma = sigma;
        this.function = function;
        this.simplexGeneratingStep = simplexGeneratingStep;
    }

    public SimplexSearch(Function<IVector, Double> function) {
        this(function, 1, 0.5, 2, 0.5, 1);
    }

    public IVector search(IVector x0, double eps) {
        int n = x0.getDimension();
        List<Pair<IVector, Double>> evaluated = new ArrayList<>(n);

        IVector xc;
        var minMaxPair = evaluateAndIndexOfMinMaxAndSecondGreatest(evaluated, x0);
        do {
            xc = calculateCentroid(evaluated, minMaxPair.getSecond());

            IVector xr = reflexion(xc, evaluated.get(minMaxPair.getSecond()).getFirst());
            var valueAtXr = function.apply(xr);

            if (valueAtXr < evaluated.get(minMaxPair.getFirst()).getSecond()) {
                IVector xe = expansion(xc, xr);
                var valueAtXe = function.apply(xe);
                if (valueAtXe < evaluated.get(minMaxPair.getFirst()).getSecond()) {
                    evaluated.set(minMaxPair.getSecond(), new Pair<>(xe, valueAtXe));
                } else {
                    evaluated.set(minMaxPair.getSecond(), new Pair<>(xr, valueAtXr));
                }
            } else {
                if (valueAtXr > maxValueExcept(evaluated, minMaxPair.getSecond())) {
                    if (valueAtXr < evaluated.get(minMaxPair.getSecond()).getSecond()) {
                        evaluated.set(minMaxPair.getSecond(), new Pair<>(xr, valueAtXr));
                    }
                    var xk = contract(xc, evaluated.get(minMaxPair.getSecond()).getFirst());
                    var valueAtXk = function.apply(xk);
                    if (valueAtXk < evaluated.get(minMaxPair.getSecond()).getSecond()) {
                        evaluated.set(minMaxPair.getSecond(), new Pair<>(xk, valueAtXk));
                    } else {
                        minMaxPair = reEvaluateAndIndexOfMinMax(evaluated, minMaxPair, n);
                        continue;
                    }
                } else {
                    evaluated.set(minMaxPair.getSecond(), new Pair<>(xr, valueAtXr));
                }
            }
            minMaxPair = checkForMinMax(evaluated);
        } while (checkCondition(evaluated, xc, eps));
        return xc;
    }

    private boolean checkCondition(List<Pair<IVector, Double>> evaluated, IVector xc, double eps) {
        double sum = 0;
        double valueAtXc = function.apply(xc);
        for (int i = 0; i < evaluated.size(); i++) {
            sum += Math.pow(evaluated.get(i).getSecond() - valueAtXc, 2);
        }
        return Math.sqrt(sum) > eps;
    }

    private IVector reflexion(IVector xc, IVector xh) {
        return xc.nScalarMultiply(1 + alfa).sub(xh.nScalarMultiply(alfa));
    }

    private IVector expansion(IVector xc, IVector xr) {
        return xc.nScalarMultiply(1 - gamma).add(xr.nScalarMultiply(gamma));
    }

    private IVector contract(IVector xc, IVector xh) {
        return xc.nScalarMultiply(1 - beta).add(xh.nScalarMultiply(beta));
    }

    private Double maxValueExcept(List<Pair<IVector, Double>> evaluated, Integer indexOfOneToSkip) {
        double max = -MAX_VALUE;
        for (int i = 0; i < evaluated.size(); i++) {
            if (i == indexOfOneToSkip) continue;
            max = Math.max(max, evaluated.get(i).getSecond());
        }
        return max;
    }

    private Pair<Integer, Integer> checkForMinMax(List<Pair<IVector, Double>> evaluated) {
        int indexMin = -1, indexMax = -1;
        double valueMin = MAX_VALUE, valueMax = -MAX_VALUE;
        for (int i = 0; i < evaluated.size(); i++) {
            var valueAtXj = evaluated.get(i).getSecond();
            if (valueAtXj > valueMax) {
                valueMax = valueAtXj;
                indexMax = i;
            }
            if (valueAtXj < valueMin) {
                valueMin = valueAtXj;
                indexMin = i;
            }
        }
        return new Pair<>(indexMin, indexMax);
    }

    private Pair<Integer, Integer> reEvaluateAndIndexOfMinMax(List<Pair<IVector, Double>> evaluated, Pair<Integer, Integer> minMaxPair, int n) {
        int indexMin = -1, indexMax = -1;
        double valueMin = MAX_VALUE, valueMax = -MAX_VALUE;
        var indexOldMin = minMaxPair.getFirst();
        var xl = evaluated.get(indexOldMin).getFirst();
        for (int i = 0; i < n; i++) {
            if (i == indexOldMin) continue;
            var xj = evaluated.get(i).getFirst().add(xl).scalarMultiply(sigma);
            var valueAtXj = function.apply(xj);
            if (valueAtXj > valueMax) {
                valueMax = valueAtXj;
                indexMax = i;
            }
            if (valueAtXj < valueMin) {
                valueMin = valueAtXj;
                indexMin = i;
            }
            evaluated.set(i, new Pair<>(xj, valueAtXj));
        }
        return new Pair<>(indexMin, indexMax);
    }

    private Pair<Integer, Integer> evaluateAndIndexOfMinMaxAndSecondGreatest(List<Pair<IVector, Double>> evaluated, IVector x0) {
        int n = x0.getDimension();
        int indexMin = 0, indexMax = 0;
        var valueAtX0 = function.apply(x0);
        double valueMin = valueAtX0, valueMax = valueAtX0;
        evaluated.add(new Pair<>(x0, valueAtX0));
        for (int i = 1; i <= n; i++) {
            var newPoint = x0.nAdd(Vector.e(n, i - 1, simplexGeneratingStep));
            var valueAtPoint = function.apply(newPoint);
            if (valueAtPoint > valueMax) {
                valueMax = valueAtPoint;
                indexMax = i;
            }
            if (valueAtPoint < valueMin) {
                valueMin = valueAtPoint;
                indexMin = i;
            }
            evaluated.add(new Pair<>(newPoint, valueAtPoint));
        }
        return new Pair<>(indexMin, indexMax);
    }

    private IVector calculateCentroid(List<Pair<IVector, Double>> evaluated, int indexMax) {
        IVector x = new Vector(new double[evaluated.get(0).getFirst().getDimension()]);
        for (int i = 0; i < evaluated.size(); i++) {
            if (i == indexMax) continue;
            x = x.add(evaluated.get(i).getFirst());
        }
        x.scalarMultiply(1.0 / (evaluated.size() - 1));
        return x;
    }

}
