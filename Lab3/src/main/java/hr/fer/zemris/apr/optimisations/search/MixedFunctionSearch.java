package hr.fer.zemris.apr.optimisations.search;

import hr.fer.zemris.apr.math.vector.IVector;

import java.util.List;
import java.util.function.Function;

import static java.lang.Math.pow;

public class MixedFunctionSearch implements SearchAlgorithm {

    private final SearchAlgorithm innerSearchAlgorithm;
    private final WrappedFunction masterFunction;
    private final List<Function<IVector, Double>> gi;

    public MixedFunctionSearch(Function<IVector, Double> function, List<Function<IVector, Double>> gi, List<Function<IVector, Double>> hi, double tInitial) {
        this.gi = gi;
        this.masterFunction = new WrappedFunction() {

            {
                setT(tInitial);
            }

            @Override
            public Double apply(IVector x) {
                return function.apply(x) - 1.0 / t * gi.stream().mapToDouble(e -> Math.log(e.apply(x))).sum() + t * hi.stream().mapToDouble(e -> pow(e.apply(x), 2)).sum();
            }
        };
        this.innerSearchAlgorithm = new HookeJeevesSearch(masterFunction, 0.5);
    }

    @Override
    public IVector search(IVector x0, double eps) {
        Function<IVector, Double> innerFunk = x1 -> -gi.stream().mapToDouble(e -> e.apply(x1)).map(e -> Math.min(0, e)).sum();

        IVector last;
        if (innerFunk.apply(x0) > 0)
            last = new HookeJeevesSearch(innerFunk).search(x0, eps);
        else
            last = x0;

        int iterationCount = 10000;
        while (iterationCount-- > 0) {
            var result = innerSearchAlgorithm.search(last, eps);
            if (checkCondition(result, last, eps)) return result;
            masterFunction.setT(masterFunction.getT() * 10);
            last = result;
        }
        return last;
    }

    private boolean checkCondition(IVector result, IVector last, double eps) {
        for (int i = 0; i < result.getDimension(); i++) {
            if (Math.abs(result.get(i) - last.get(i)) > eps) return false;
        }
        return true;
    }

    private abstract static class WrappedFunction implements Function<IVector, Double> {
        protected double t;

        public double getT() {
            return t;
        }

        public void setT(double t) {
            this.t = t;
        }
    }
}
