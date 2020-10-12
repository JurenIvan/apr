package hr.fer.zemris.apr.math.matrix.decomposition;

import hr.fer.zemris.apr.math.matrix.Matrix;
import org.junit.jupiter.api.Test;

import static hr.fer.zemris.apr.math.matrix.decomposition.DecompositionStrategies.LUP_DECOMPOSITION;
import static hr.fer.zemris.apr.math.matrix.decomposition.DecompositionStrategies.LU_DECOMPOSITION;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DecompositionStrategiesTest {

    @Test
    public void LU_DECOMPOSITION_3rows() {
        var m = Matrix.parseString("6 2 10 | 2 3 0 | 0 4 2");
        var decomposed = Matrix.parseString("6 2 10 | 0.3333333333333333 2.3333333333333335 -3.333333333333333 | 0 1.7142857142857142 7.7142857142857135");

        var result = m.nDecompose(LU_DECOMPOSITION);

        assertEquals(decomposed, result.getLUMatrix());
    }

    @Test
    public void LUP_DECOMPOSITION_4rows() {
        var m = Matrix.parseString("4 10 5 -9 | -4 6 6 3 | -8 4 2 6 | -4 8 1 -3");
        var decomposed = Matrix.parseString("-8 4 2 6 | -0.5 12 6 -6 | 0.5 0.3333333333333333 3 2 | 0.5 0.5 -1 -1");

        var result = m.nDecompose(LUP_DECOMPOSITION);

        assertEquals(decomposed, result.getLUMatrix());
    }

    @Test
    public void LUP_DECOMPOSITION_3rows() {
        var result = Matrix.parseString("6 2 10 | 2 3 0 | 0 4 2").nDecompose(LUP_DECOMPOSITION);

        assertEquals(Matrix.parseString("6 2 10 | 0 4 2 | 0.33333 0.58333 -4.5"), result.getLUMatrix());
        assertEquals(Matrix.parseString("6 2 10 | 0 4 2 | 0.33333 0.58333 -4.5"), result.getLUMatrix());
    }
}