package hr.fer.zemris.apr.math;

import hr.fer.zemris.apr.math.matrix.Matrix;
import hr.fer.zemris.apr.math.matrix.inverse.InverseStrategies;

public class Main {
    public static void main(String[] args) {
        var m = Matrix.parseString("25 5 1 | 64 8 1 | 144 12 1");

        var inverse = m.inverse(InverseStrategies.LUP_DECOMPOSITION_INVERSE);
        System.out.println(inverse);
    }
}
