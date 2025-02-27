package hr.fer.zemris.apr.optimisations.search;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.domain.Pair;

import java.util.function.Function;

public class HookeJeevesSearch implements SearchAlgorithm {

    private final Function<IVector, Double> function;
    private final double dx;

    public HookeJeevesSearch(Function<IVector, Double> function, double dx) {
        this.function = function;
        this.dx = dx;
    }

    public HookeJeevesSearch(Function<IVector, Double> function) {
        this(function, 1);
    }

    public IVector search(IVector x0, double eps) {
        double dx = this.dx;
        IVector xp = x0.copy();
        IVector xb = x0.copy();

        do {
            var xnAndValue = explore(xp, dx);
            var xn = xnAndValue.getFirst();
            var valueAtXn = xnAndValue.getSecond();
            if (valueAtXn < function.apply(xb)) {
                xp = xn.nScalarMultiply(2).sub(xb);
                xb = xn.copy();
            } else {
                dx *= 0.5;
                xp = xb.copy();
            }
        } while (dx > eps);
        return xb;
    }

    private Pair<IVector, Double> explore(IVector xp, double dx) {
        int n = xp.getDimension();
        IVector x = xp.copy();
        double valueAtX = 0;
        for (int i = 0; i < n; i++) {
            double p = function.apply(x);
            x.add(Vector.e(n, i, dx));
            valueAtX = function.apply(x);
            if (valueAtX > p) {
                x.sub(Vector.e(n, i, dx * 2));
                valueAtX = function.apply(x);
                if (valueAtX > p) {
                    x.add(Vector.e(n, i, dx));
                }
            }
        }
        return new Pair<>(x, valueAtX);
    }
}
