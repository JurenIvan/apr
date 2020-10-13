package hr.fer.zemris.apr.math;

import hr.fer.zemris.apr.math.matrix.Matrix;
import hr.fer.zemris.apr.math.matrix.decomposition.DecompositionStrategies;
import hr.fer.zemris.apr.math.vector.Vector;
import org.junit.jupiter.api.Test;

import static hr.fer.zemris.apr.math.matrix.determinant.DeterminantStrategies.TRIANGULAR_MATRICES;
import static hr.fer.zemris.apr.math.matrix.inverse.InverseStrategies.LUP_DECOMPOSITION_INVERSE;
import static hr.fer.zemris.apr.math.matrix.solver.EquasionSolvers.LUP_EQUATION_SOLVER;
import static hr.fer.zemris.apr.math.matrix.solver.EquasionSolvers.LU_EQUATION_SOLVER;
import static org.junit.jupiter.api.Assertions.*;

public class Demo {

    @Test
    public void zadatak01() {
        var matrix = Matrix.parseString("5 2.22");

        assertNotEquals("1.6666666666666666", String.valueOf(matrix.get(0, 0) / 3).substring(0, 18));
        assertEquals(5.0 / 3, matrix.get(0, 0) / 3, 1e-10);
    }

    @Test
    public void zadatak02() {
        var matrix = Matrix.parseString("3 9 6 | 4 12 12 | 1 -1 1");
        var right = Vector.parseSimple("12 12 1");

        var expectedSolution = Vector.parseSimple("3 1 -1");
        assertEquals(expectedSolution, matrix.solveSystem(LUP_EQUATION_SOLVER, right));
        assertThrows(IllegalStateException.class, () -> matrix.solveSystem(LU_EQUATION_SOLVER, right));
    }

    @Test
    public void zadatak03() {
        var matrix = Matrix.parseString("1 2 3 | 4 5 6 | 7 8 9");

        var luDecomposed = matrix.nDecompose(DecompositionStrategies.LU_DECOMPOSITION);
        assertEquals(Matrix.parseString("1 2 3 | 4 -3 -6 | 7 2 0"), luDecomposed.getLUMatrix());

        var lupDecomposed = matrix.nDecompose(DecompositionStrategies.LUP_DECOMPOSITION);
        assertEquals(Matrix.parseString("7 8 9| 0.142857 0.857142 1.7142857 | 0.571428 0.5 0"), lupDecomposed.getLUMatrix());

        var right = Vector.parseSimple("8 20 32");
        var expectedSolution = Vector.parseSimple("1 2 1");

        assertThrows(IllegalStateException.class, () -> matrix.solveSystem(LUP_EQUATION_SOLVER, right));
        assertThrows(IllegalStateException.class, () -> matrix.solveSystem(LU_EQUATION_SOLVER, right));
    }

    @Test
    public void zadatak04() {
        var matrix = Matrix.parseString("0.000001 3000000 2000000|1000000 2000000 3000000|2000000 1000000 2000000");
        var right = Vector.parseSimple("12000000.000001 14000000 10000000");


        var expectedLUPSolution = Vector.parseSimple("1 2 3");
        assertEquals(expectedLUPSolution, matrix.solveSystem(LUP_EQUATION_SOLVER, right));
        var expectedLUSolution = Vector.parseSimple("1.00024044 1.9993176 3.0010235");
        assertEquals(expectedLUSolution, matrix.solveSystem(LU_EQUATION_SOLVER, right));
    }

    @Test
    public void zadatak05() {
        var matrix = Matrix.parseString("0 1 2 | 2 0 3 | 3 5 1");
        var right = Vector.parseSimple("6 9 3");

        var expectedLUPSolution = Vector.parseSimple("0 0 3");
        assertEquals(expectedLUPSolution, matrix.solveSystem(LUP_EQUATION_SOLVER, right));

        assertThrows(IllegalStateException.class, () -> matrix.solveSystem(LU_EQUATION_SOLVER, right));
    }

    @Test
    public void zadatak06() {
        var matrix = Matrix.parseString("4000000000 1000000000 3000000000 | 4 2 7 | 0.0000000003 0.0000000005 0.0000000002");
        var right = Vector.parseSimple("9000000000 15 0.0000000015");

        var result = matrix.solveSystem(LUP_EQUATION_SOLVER, right);
        assertTrue(Vector.parseSimple("1 2 1").equals(result, 6));
    }

    @Test
    public void zadatak07() {
        var matrix = Matrix.parseString("1 2 3|4 5 6|7 8 9");

        assertThrows(IllegalStateException.class, () -> matrix.inverse(LUP_DECOMPOSITION_INVERSE));
    }

    @Test
    public void zadatak08() {
        var matrix = Matrix.parseString("4 -5 -2|5 -6 -2|-8 9 3");
        var expected = Matrix.parseString("0 -3 -2 | 1 -4 -2| -3 4 1");
        assertEquals(expected, matrix.inverse(LUP_DECOMPOSITION_INVERSE));
    }

    @Test
    public void zadatak09() {
        var matrix = Matrix.parseString("4 -5 -2|5 -6 -2|-8 9 3");

        assertEquals(1, matrix.determinant(TRIANGULAR_MATRICES));
    }

    @Test
    public void zadatak10() {
        var matrix = Matrix.parseString("3 9 6|4 12 12 |1 -1 1");

        assertEquals(48, matrix.determinant(TRIANGULAR_MATRICES));
    }
}
