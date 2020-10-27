package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;

import java.util.function.Function;

public class CoordinateSearch {

    public static Vector of(Function<IVector, Double> function, IVector x0, Vector eps, Vector xn) {
        int n = x0.getDimension();
        var x = x0.copy();
        Vector ei = new Vector(new double[n]);
//
//        IVector xs = x.copy();
//        do {
//            for (int i = 0; i < n; i++) {
//                ei.set(i, 1.0);
////                var af = new AprFunction(val -> function.apply(x.get(i) + ei.get(i) * val))
////                x.add(ei.scalarMultiply())
//                ei.set(i, 0.0);
//            }
//        } while (x.nSub(xs).norm()<);

        return null;
    }
}
