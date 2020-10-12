package hr.fer.zemris.apr.math.matrix.solver;

import static hr.fer.zemris.apr.math.matrix.decomposition.DecompositionStrategies.LUP_DECOMPOSITION;
import static hr.fer.zemris.apr.math.matrix.decomposition.DecompositionStrategies.LU_DECOMPOSITION;
import static hr.fer.zemris.apr.math.matrix.supstitutions.SubstitutionStrategies.BACKWARD;
import static hr.fer.zemris.apr.math.matrix.supstitutions.SubstitutionStrategies.FORWARD;

public class EquasionSolvers {

    public static final EquationSolver LUP_EQUATION_SOLVER = (matrix, vectorRight) -> {
        var result = matrix.nDecompose(LUP_DECOMPOSITION);
        var y = FORWARD.substitute(result.getLMatrix(), result.getPMatrix().nMultiply(vectorRight.toColumnMatrix(true)).toVector(true));
        return BACKWARD.substitute(result.getUMatrix(), y);
    };

    public static final EquationSolver LU_EQUATION_SOLVER = (matrix, vectorRight) -> {
        var result = matrix.nDecompose(LU_DECOMPOSITION);
        var y = FORWARD.substitute(result.getLMatrix(), vectorRight);
        return BACKWARD.substitute(result.getUMatrix(), y);
    };
}
