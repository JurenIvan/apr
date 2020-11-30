package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.optimisations.domain.Pair;

import java.util.function.Function;

import static java.lang.Math.sqrt;

public class GoldenCut {

    private static final double GOLDEN_RATIO = 0.5 * (sqrt(5) - 1);

    public static Pair<IVector, IVector> getInterval(Function<IVector, Double> function, Pair<IVector, IVector> borders, double e) {
        IVector a = borders.getFirst();
        IVector b = borders.getSecond();
        IVector bSubA = b.nSub(a);
        IVector bSubANScalarMultiply = bSubA.nScalarMultiply(GOLDEN_RATIO);
        IVector c = b.nSub(bSubANScalarMultiply);
        IVector d = a.nAdd(bSubANScalarMultiply);
        double fc, fd;

        fc = function.apply(c);
        fd = function.apply(d);
        while (b.nSub(a).norm() > e) {
            if (fc < fd) {
                b = d;
                d = c;
                c = b.nSub(b.nSub(a).nScalarMultiply(GOLDEN_RATIO));
                fd = fc;
                fc = function.apply(c);
            } else {
                a = c;
                c = d;
                d = a.nAdd(b.nSub(a).nScalarMultiply(GOLDEN_RATIO));
                fc = fd;
                fd = function.apply(d);
            }
        }
        return new Pair<>(a, b);
    }

    public static IVector getMiddleOfInterval(Function<IVector, Double> f, Pair<IVector, IVector> borders, double e) {
        var r = GoldenCut.getInterval(f, borders, e);
        return r.getSecond().add(r.getFirst()).scalarMultiply(0.5);
    }
}
