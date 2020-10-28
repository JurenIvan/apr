package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.IVector;

import java.util.function.Function;

public class CoordinateSearch {

    public static IVector of(Function<IVector, Double> function, IVector x0, IVector eps) {
        int n = x0.getDimension();
        var x = x0.copy();

        IVector xs;
        do {
            xs = x.copy();
            for (int i = 0; i < n; i++) {
                x = GoldenCut.avg(function, UniModalInterval.of(function, 1, x, i), eps.get(i), i);
            }
        } while (compareComponents(x, xs, eps));

        return x;
    }

    private static boolean compareComponents(IVector a, IVector b, IVector eps) {
        for (int i = 0; i < eps.getDimension(); i++) {
            if (Math.abs(a.get(i) - b.get(i)) > eps.get(i)) {
                return true;
            }
        }
        return false;
    }
}
