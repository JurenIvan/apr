package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.domain.Pair;

import java.util.function.Function;

import static java.lang.Math.sqrt;

public class GoldenCut {

    private static final double GOLDEN_RATIO = 0.5 * (sqrt(5) - 1);

    public static Pair<IVector, IVector> getInterval(Function<IVector, Double> function, Pair<IVector, IVector> borders, double e, int index) {
        int n = borders.getFirst().getDimension();
        IVector a = borders.getFirst();
        IVector b = borders.getSecond();
        IVector c = b.nSub(b.nSub(a).nScalarMultiply(GOLDEN_RATIO));
        IVector d = a.nAdd(b.nSub(a).nScalarMultiply(GOLDEN_RATIO));
        double fc, fd;

        fc = function.apply(c);
        fd = function.apply(d);
        while (b.get(index) - a.get(index) > e) {
            if (fc < fd) {
                b = d;
                d = c;
                c = b.nSub(Vector.e(n, index, GOLDEN_RATIO * (b.get(index) - a.get(index))));
                fd = fc;
                fc = function.apply(c);
            } else {
                a = c;
                c = d;
                d = a.nAdd(Vector.e(n, index, GOLDEN_RATIO * (b.get(index) - a.get(index))));
                fc = fd;
                fd = function.apply(d);
            }
        }
        return new Pair<>(a, b);
    }

    public static IVector getMiddleOfInterval(Function<IVector, Double> f, Pair<IVector, IVector> borders, double e, int index) {
        var r = GoldenCut.getInterval(f, borders, e, index);
        return r.getSecond().add(r.getFirst()).scalarMultiply(0.5);
    }
}
