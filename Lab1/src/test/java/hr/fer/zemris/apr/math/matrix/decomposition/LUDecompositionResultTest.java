package hr.fer.zemris.apr.math.matrix.decomposition;

import hr.fer.zemris.apr.math.matrix.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LUDecompositionResultTest {

    @Test
    void getLMatrix() {
        var matrix = Matrix.parseString("1 2 3 | 4 5 6 | 7 8 9");
        var lowerMatrix = Matrix.parseString("1 0 0 | 4 1 0 | 7 8 1");
        var lowerMatrixFromDecomposedMatrix = new LUDecompositionResult(matrix).getLMatrix();

        assertEquals(lowerMatrix.toString(), lowerMatrixFromDecomposedMatrix.toString());
    }

    @Test
    void getUMatrix() {
        var matrix = Matrix.parseString("1 2 3 | 4 5 6 | 7 8 9");
        var upperMatrix = Matrix.parseString("1 2 3 | 0 5 6 | 0 0 9");

        assertEquals(upperMatrix.toString(), new LUDecompositionResult(matrix).getUMatrix().toString());
    }

    @Test
    void getLUMatrix() {
        var matrix = Matrix.parseString("1 2 3 | 4 5 6 | 7 8 9");

        assertEquals(matrix.toString(), new LUDecompositionResult(matrix).getLUMatrix().toString());
    }
}