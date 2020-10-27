package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.optimisations.function.DoubleFunction;

public class GoldenCut {

    private static final double GOLDEN_RATIO = 0.5 * (Math.sqrt(5) - 1);

    public static double given(DoubleFunction f, Pair borders, double e) {
        double a = borders.getFirst();
        double b = borders.getSecond();
        double c, d, fc, fd;
        c = b - GOLDEN_RATIO * (b - a);
        d = a + GOLDEN_RATIO * (b - a);
        fc = f.apply(c);
        fd = f.apply(d);
        while ((b - a) > e) {
            if (fc < fd) {
                b = d;
                d = c;
                c = b - GOLDEN_RATIO * (b - a);
                fd = fc;
                fc = f.apply(c);
            } else {
                a = c;
                c = d;
                d = a + GOLDEN_RATIO * (b - a);
                fc = fd;
                fd = f.apply(d);
            }
        }
        borders.setFirst(a);
        borders.setSecond(b);
        return (a + b) / 2;
    }

    public static Pair lambdaMin(DoubleFunction f, double t0, double e) {
        var u = UniModalInterval.of(f, 1, t0);
        GoldenCut.given(f, u, e);
        return u;
    }
}
