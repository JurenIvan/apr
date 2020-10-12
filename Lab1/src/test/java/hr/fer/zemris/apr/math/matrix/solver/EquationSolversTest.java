package hr.fer.zemris.apr.math.matrix.solver;

import hr.fer.zemris.apr.math.matrix.Matrix;
import hr.fer.zemris.apr.math.vector.Vector;
import org.junit.jupiter.api.Test;

import static hr.fer.zemris.apr.math.matrix.solver.EquasionSolvers.LUP_EQUATION_SOLVER;
import static hr.fer.zemris.apr.math.matrix.solver.EquasionSolvers.LU_EQUATION_SOLVER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EquationSolversTest {

    @Test
    public void LUPEquationSolver() {

        var matrix = Matrix.parseString("3 2 1 | 1 2 2 | 4 3 4");
        var vector = Vector.parseSimple("4 3 8");

        var x = matrix.solveSystem(LUP_EQUATION_SOLVER, vector);

        assertEquals(1, x.get(0));
        assertEquals(0, x.get(1));
        assertEquals(1, x.get(2));
    }

    @Test
    public void LUEquationSolver() {

        var matrix = Matrix.parseString("3 2 1 | 1 2 2 | 4 3 4");
        var vector = Vector.parseSimple("4 3 8");

        var x = matrix.solveSystem(LU_EQUATION_SOLVER, vector);

        assertEquals(1, x.get(0));
        assertEquals(0, x.get(1));
        assertEquals(1, x.get(2));
    }

}