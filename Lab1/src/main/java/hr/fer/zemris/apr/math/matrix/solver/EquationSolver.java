package hr.fer.zemris.apr.math.matrix.solver;

import hr.fer.zemris.apr.math.matrix.IMatrix;
import hr.fer.zemris.apr.math.vector.IVector;

public interface EquationSolver {

    IVector solve(IMatrix matrix, IVector right);
}
