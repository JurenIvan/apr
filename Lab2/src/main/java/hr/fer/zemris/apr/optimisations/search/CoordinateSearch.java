package hr.fer.zemris.apr.optimisations.search;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.optimisations.GoldenCut;
import hr.fer.zemris.apr.optimisations.UniModalInterval;

import java.util.function.Function;

public class CoordinateSearch implements SearchAlgorithm {

    private final Function<IVector, Double> function;

    public CoordinateSearch(Function<IVector, Double> function) {
        this.function = function;
    }

    public IVector search(IVector x0, double eps) {
        int n = x0.getDimension();
        var x = x0.copy();

        IVector xs;
        do {
            xs = x.copy();
            for (int i = 0; i < n; i++) {
                x = GoldenCut.getMiddleOfInterval(function, UniModalInterval.of(function, 1, x, i), eps, i);
            }
        } while (compareComponents(x, xs, eps));

        return x;
    }

    private boolean compareComponents(IVector a, IVector b, double eps) {
        for (int i = 0; i < a.getDimension(); i++) {
            if (Math.abs(a.get(i) - b.get(i)) > eps) {
                return true;
            }
        }
        return false;
    }
}
