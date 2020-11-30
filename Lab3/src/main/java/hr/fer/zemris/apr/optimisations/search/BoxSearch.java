package hr.fer.zemris.apr.optimisations.search;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.domain.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BoxSearch implements SearchAlgorithm {

    private final Function<IVector, Double> function;
    private final List<Function<IVector, Double>> gi;
    private final Function<Integer, Pair<Double, Double>> borderConstrains;

    public BoxSearch(Function<IVector, Double> function, List<Function<IVector, Double>> gi, Function<Integer, Pair<Double, Double>> borders) {
        this.function = function;
        this.gi = gi;
        this.borderConstrains = borders;
    }

    @Override
    public IVector search(IVector x0, double eps) {
        int n = x0.getDimension();
        checkStartingPoint(x0.copy());
        List<Pair<IVector, Double>> simplex = generatePoints(x0);

        IVector xc;
        do {
            Pair<Integer, Integer> worstAndAlmostWorst = findTwoWorstPoints(simplex);
            xc = calculateCentroid(simplex, worstAndAlmostWorst.getFirst());
            var xr = reflexion(xc, simplex.get(worstAndAlmostWorst.getFirst()).getFirst());

            for (int i = 0; i < n; i++) {
                checkBorderCondition(xr, i);
            }
            while (gi.stream().anyMatch(e -> e.apply(xr) < 0)) {
                xr.add(xc).nScalarMultiply(0.5);
            }
            var xrValue = function.apply(xr);
            if (xrValue > simplex.get(worstAndAlmostWorst.getSecond()).getSecond()) {
                xr.add(xc).nScalarMultiply(0.5);
            }
            simplex.set(worstAndAlmostWorst.getFirst(), new Pair<>(xr, xrValue));

        } while (checkCondition(simplex, xc, eps));

        return xc;
    }

    private boolean checkCondition(List<Pair<IVector, Double>> evaluated, IVector xc, double eps) {
        double sum = 0;
        double valueAtXc = function.apply(xc);
        for (Pair<IVector, Double> iVectorDoublePair : evaluated) {
            sum += Math.pow(iVectorDoublePair.getSecond() - valueAtXc, 2);
        }
        return Math.sqrt(sum / evaluated.size()) > eps;
    }

    private void checkBorderCondition(IVector xr, int i) {
        var borders = borderConstrains.apply(i);
        if (xr.get(i) < borders.getFirst()) xr.set(i, borders.getFirst());
        if (xr.get(i) > borders.getSecond()) xr.set(i, borders.getSecond());
    }

    private IVector reflexion(IVector xc, IVector xh) {
        return xc.nScalarMultiply(3).sub(xh.nScalarMultiply(2));
    }

    private Pair<Integer, Integer> findTwoWorstPoints(List<Pair<IVector, Double>> simplex) {
        double worst1 = -Double.MAX_VALUE;
        int worst1Index = -1;
        double worst2 = -Double.MAX_VALUE;
        int worst2Index = -1;

        for (int i = 0; i < simplex.size(); i++) {
            Pair<IVector, Double> point = simplex.get(i);
            if (point.getSecond() > worst1) {
                worst2 = worst1;
                worst2Index = worst1Index;
                worst1 = simplex.get(i).getSecond();
                worst1Index = i;
            } else if (point.getSecond() > worst2) {
                worst2 = point.getSecond();
                worst2Index = i;
            }
        }

        return new Pair<>(worst1Index, worst2Index);
    }

    private List<Pair<IVector, Double>> generatePoints(IVector xc) {
        int n = xc.getDimension();
        List<Pair<IVector, Double>> simplex = new ArrayList<>();
        simplex.add(new Pair<>(xc, function.apply(xc)));

        for (int i = 0; i < 2 * n - 1; i++) {
            IVector xt = randomPoint(xc);
            simplex.add(new Pair<>(xt, function.apply(xt)));
            xc = calculateCentroid(simplex, -1);
        }
        return simplex;
    }

    private IVector calculateCentroid(List<Pair<IVector, Double>> simplex, int excluded) {
        IVector xc = new Vector(simplex.get(0).getFirst().getDimension());
        for (int i = 0; i < simplex.size(); i++) {
            if (i == excluded) continue;
            xc.add(simplex.get(i).getFirst());
        }
        xc = xc.nScalarMultiply(1.0 / simplex.size());
        return xc;
    }

    private IVector randomPoint(IVector xc) {
        int n = xc.getDimension();
        var point = new Vector(n);

        for (int i = 0; i < n; i++) {
            var bc = borderConstrains.apply(i);
            point.set(i, bc.getFirst() + Math.random() * (bc.getSecond() - bc.getFirst()));
        }

        while (gi.stream().anyMatch(e -> e.apply(point) < 0)) {
            point.add(xc).nScalarMultiply(0.5);
        }

        return xc;
    }

    private void checkStartingPoint(IVector x0) {
        for (int i = 0; i < x0.getDimension(); i++) {
            var border = borderConstrains.apply(i);
            if (x0.get(i) < border.getFirst() || x0.get(i) > border.getSecond())
                throw new IllegalStateException("Starting point should fullfill borders conditions");
        }

        for (Function<IVector, Double> iVectorDoubleFunction : gi) {
            if (iVectorDoubleFunction.apply(x0) < 0)
                throw new IllegalStateException("Starting point should fulfill inequality conditions");
        }
    }

}
