package hr.fer.zemris.apr.math.matrix.determinant;

import hr.fer.zemris.apr.math.matrix.IMatrix;
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

    public static DeterminantStrategy SUBVIEW_DETERMINANT = new DeterminantStrategy() {
        @Override
        public double calculateDeterminant(IMatrix matrix) {
            if (matrix.getColsCount() == 1)
                return matrix.get(0, 0);
            if (matrix.getColsCount() == 2)
                return matrix.get(0, 0) * matrix.get(1, 1) - matrix.get(1, 0) * matrix.get(0, 1);

            double determinant = 0;
            int sign = 1;
            for (int i = 0; i < matrix.getColsCount(); i++, sign *= -1)
                determinant += sign * matrix.get(0, i) * calculateDeterminant(matrix.subMatrix(0, i, true));

            return determinant;
        }
    };
}
