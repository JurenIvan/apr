package hr.fer.zemris.apr.math;

import hr.fer.zemris.apr.math.matrix.Matrix;

import static hr.fer.zemris.apr.math.matrix.decomposition.DecompositionStrategies.LU_DECOMPOSITION;

public class Main {
    public static void main(String[] args) {

        var m = Matrix.parseString("6 2 10 | 2 3 0 | 0 4 2");
        System.out.println(m);

        var t = m.nDecompose(LU_DECOMPOSITION);

        System.out.println("LU");
        System.out.println(t.getLUMatrix());
        System.out.println("L");
        System.out.println(t.getLMatrix());
        System.out.println("U");
        System.out.println(t.getUMatrix());
    }
}
