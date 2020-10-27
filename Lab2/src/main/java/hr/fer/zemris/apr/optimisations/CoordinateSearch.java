package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.function.AprFunction;

public class CoordinateSearch {

    public static Vector of(AprFunction function, IVector x0, Vector eps, Vector xn) {
//        int n = x0.getDimension();
//        var x = x0.copy();
//        Vector ei = new Vector(new double[n]);
//
//        do {
//            IVector xs = x.copy();
//            for (int i = 0; i < n; i++) {
//                ei.set(i, 1.0);
//                var af = new AprFunction(val -> function.apply(x.get(i) + ei.get(i) * val))
//                x.add(ei.scalarMultiply())
//                ei.set(i, 0.0);
//            }
//        } while (true);
//
        return null;
    }
}
