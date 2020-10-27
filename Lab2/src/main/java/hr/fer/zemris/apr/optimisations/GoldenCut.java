package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;

import java.util.function.Function;

public class GoldenCut {

    private static final double GOLDEN_RATIO = 0.5 * (Math.sqrt(5) - 1);

    public static Pair<IVector> given(Function<IVector, Double> f, Pair<IVector> borders, double e, int index) {
        int n = borders.getFirst().getDimension();
        IVector a = borders.getFirst();
        IVector b = borders.getSecond();
        IVector c = b.nSub(b.nSub(a).nScalarMultiply(GOLDEN_RATIO));
        IVector d = a.nAdd(b.nSub(a).nScalarMultiply(GOLDEN_RATIO));
        double fc, fd;

        fc = f.apply(c);
        fd = f.apply(d);
        while (b.get(index) - a.get(index) > e) {
            if (fc < fd) {
                b = d;
                d = c;
                c = b.nSub(Vector.e(n, index, GOLDEN_RATIO * (b.get(index) - a.get(index))));
                fd = fc;
                fc = f.apply(c);
            } else {
                a = c;
                c = d;
                d = a.nAdd(Vector.e(n, index, GOLDEN_RATIO * (b.get(index) - a.get(index))));
                fc = fd;
                fd = f.apply(d);
            }
        }
        return new Pair<>(a, b);
    }

//    } public static Pair<IVector> given(Function<IVector, Double> f, Pair<IVector> borders, double e, int index) {
//        int n=borders.getFirst().getDimension();
//        IVector a = borders.getFirst();
//        IVector b = borders.getSecond();
//        IVector c = b.nSub(b.nSub(a).nScalarMultiply(GOLDEN_RATIO));
//        IVector d = a.nAdd(b.nSub(a).nScalarMultiply(GOLDEN_RATIO));
//        double fc, fd;
//        double va = a.get(index);
//        double vb = b.get(index);
//        double vc = c.get(index);
//        double vd = d.get(index);
//
//        fc = f.apply(c);
//        fd = f.apply(d);
//        while (vb - va > e) {
//            if (fc < fd) {
//                b = d; vb=vd;
//                d = c; vd=vc;
//                c = b.nSub(Vector.e(n,index, GOLDEN_RATIO * (vb-va))); vc=vb-GOLDEN_RATIO*(vb-va);
//                fd = fc;
//                fc = f.apply(c);
//            } else {
//                a = c; va=vc;
//                c = d; vc=vd;
//                d = a.nAdd(Vector.e(n,index, GOLDEN_RATIO * (vb-va))); vd=va+GOLDEN_RATIO*(vb-va);
//                fc = fd;
//                fd = f.apply(d);
//            }
//        }
//        return new Pair<>(a, b);
//    }
}
