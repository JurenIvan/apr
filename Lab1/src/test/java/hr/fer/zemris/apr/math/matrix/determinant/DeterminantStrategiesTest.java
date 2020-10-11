package hr.fer.zemris.apr.math.matrix.determinant;

import hr.fer.zemris.apr.math.matrix.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeterminantStrategiesTest {

    @Test
    public void testLUPDeterminant() {

        var inverse = Matrix.parseString("25 5 1 | 64 8 1 | 144 12 1").determinant(DeterminantStrategies.TRIANGULAR_MATRICES);

        assertEquals(-84, inverse, 1e-6);
    }

}