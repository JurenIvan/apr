package hr.fer.zemris.apr.math.matrix.inverse;

import hr.fer.zemris.apr.math.matrix.IMatrix;
import hr.fer.zemris.apr.math.matrix.Matrix;
import hr.fer.zemris.apr.math.matrix.decomposition.DecompositionStrategies;
import hr.fer.zemris.apr.math.matrix.determinant.DeterminantStrategies;
import hr.fer.zemris.apr.math.vector.Vector;

import static hr.fer.zemris.apr.math.matrix.supstitutions.SubstitutionStrategies.BACKWARD;
import static hr.fer.zemris.apr.math.matrix.supstitutions.SubstitutionStrategies.FORWARD;

public class InverseStrategies {

    public static final InverseStrategy LUP_DECOMPOSITION_INVERSE = matrix -> {
        if (matrix.getRowsCount() != matrix.getColsCount()) throw new IllegalStateException();

        var dim = matrix.getColsCount();
        var a = new double[dim][dim];
        var lupDecompositionResult = matrix.nDecompose(DecompositionStrategies.LUP_DECOMPOSITION);
        var LPMatrix = lupDecompositionResult.getLMatrix();
        var UPMatrix = lupDecompositionResult.getUMatrix();
        var transposed = lupDecompositionResult.getPMatrix().nTranspose(false).toArray();

        for (int i = 0; i < dim; i++) {
            var z = FORWARD.substitute(LPMatrix, new Vector(transposed[i]));
            var x = BACKWARD.substitute(UPMatrix, z);

            for (int j = 0; j < x.getDimension(); j++) {
                a[j][i] = x.get(j);
            }
        }
        return new Matrix(dim, dim, a, true);
    };

    public static final InverseStrategy SUBVIEW_INVERSE = matrix -> {
        double determinant = 1 / matrix.determinant(DeterminantStrategies.SUBVIEW_DETERMINANT);
        IMatrix transposed = matrix.nTranspose(false);
        for (int i = 0; i < matrix.getRowsCount(); i++) {
            for (int j = 0; j < matrix.getColsCount(); j++) {
                transposed.set(i, j, Math.pow(-1, i + j) * determinant * matrix.subMatrix(i, j, true).determinant(DeterminantStrategies.SUBVIEW_DETERMINANT));
            }
        }
        return transposed.nTranspose(true);
    };
}

