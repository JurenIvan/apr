package hr.fer.zemris.apr.math;

import hr.fer.zemris.apr.math.matrix.Matrix;
import hr.fer.zemris.apr.math.vector.Vector;
import org.junit.jupiter.api.Disabled;
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
    }

    @Test
    @Disabled
    public void zadatak02() {
        var matrix = Matrix.parseString("3 9 6 | 4 12 12 | 1 -1 -1");
        var right = Vector.parseSimple("12 12 1");

        var expectedSolution = Vector.parseSimple("3 1 -1");
        assertEquals(expectedSolution, matrix.solveSystem(LUP_EQUATION_SOLVER, right));
        assertEquals(expectedSolution, matrix.solveSystem(LU_EQUATION_SOLVER, right));
    }

    @Test
    @Disabled
    public void zadatak03() {
        var matrix = Matrix.parseString("1 2 3 | 4 5 6 | 7 8 9");
        var right = Vector.parseSimple("8 20 32");


        var expectedSolution = Vector.parseSimple("1 2 1");
        assertEquals(expectedSolution, matrix.solveSystem(LUP_EQUATION_SOLVER, right));
        assertEquals(expectedSolution, matrix.solveSystem(LU_EQUATION_SOLVER, right));
    }

    @Test
    @Disabled
    public void zadatak04() {
        var matrix = Matrix.parseString("0.000001 3000000 2000000|1000000 2000000 3000000|2000000 1000000 2000000");
        var right = Vector.parseSimple("12000000.000001 14000000 10000000");


        var expectedSolution = Vector.parseSimple("1 2 3");
        assertEquals(expectedSolution, matrix.solveSystem(LUP_EQUATION_SOLVER, right));
        assertEquals(expectedSolution, matrix.solveSystem(LU_EQUATION_SOLVER, right));
    }

    @Test
    public void zadatak05() {

    }

    @Test
    public void zadatak06() {
        var matrix = Matrix.parseString("4000000000 1000000000 3000000000 | 4 2 7 | 0.0000000003 0.0000000005 0.0000000002");
        var right = Vector.parseSimple("9000000000 15 0.0000000015");

        var result = matrix.solveSystem(LUP_EQUATION_SOLVER, right);
        assertTrue(Vector.parseSimple("1 2 1").equals(result, 6));
    }

    @Test
    @Disabled
    public void zadatak07() {
        var matrix = Matrix.parseString("1 2 3|4 5 6|7 8 9");

        assertThrows(Exception.class, () -> matrix.inverse(LUP_DECOMPOSITION_INVERSE));
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
