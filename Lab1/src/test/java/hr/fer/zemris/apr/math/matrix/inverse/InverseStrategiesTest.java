package hr.fer.zemris.apr.math.matrix.inverse;

import hr.fer.zemris.apr.math.matrix.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InverseStrategiesTest {

    @Test
    public void testLUPDecompositinInverse() {
        var inverse = Matrix.parseString("25 5 1 | 64 8 1 | 144 12 1").inverse(InverseStrategies.LUP_DECOMPOSITION_INVERSE);

        assertEquals(
                "[[0.047619047619047616, -0.08333333333333333, 0.03571428571428572]\n" +
                        "[-0.9523809523809524, 1.4166666666666665, -0.46428571428571436]\n" +
                        "[4.571428571428572, -5.000000000000001, 1.4285714285714288]]", inverse.toString());
    }

}