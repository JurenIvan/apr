package hr.fer.zemris.apr.math.matrix.determinant;

import hr.fer.zemris.apr.math.matrix.decomposition.DecompositionStrategies;

public class DeterminantStrategies {

    public static DeterminantStrategy TRIANGULAR_MATRICES = matrix -> {
        var decompositionResult = matrix.nDecompose(DecompositionStrategies.LUP_DECOMPOSITION);
        var dim = decompositionResult.getLMatrix().getColsCount();
        var product = 1.0;
        for (int i = 0; i < dim; i++) {
            product *= decompositionResult.getLUMatrix().get(i, i);
        }

        int displacements = 0;
        for (int i = 0; i < dim; i++) {
            displacements += decompositionResult.getPMatrix().get(i, i) != 1 ? 1 : 0;
        }
        return product * (displacements % 2 == 1 ? 1 : -1);
    };
}
