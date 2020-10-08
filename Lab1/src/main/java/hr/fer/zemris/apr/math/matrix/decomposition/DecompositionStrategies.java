package hr.fer.zemris.apr.math.matrix.decomposition;

import hr.fer.zemris.apr.math.matrix.IMatrix;

public class DecompositionStrategies {

    public static DecomposeStrategy<LUDecompositionResult> LU_DECOMPOSITION = (originalMatrix) -> {
        IMatrix matrixCopy = originalMatrix.copy();
        int colsCount = originalMatrix.getColsCount();

        for (int i = 0; i < colsCount - 1; i++) {
            for (int j = i + 1; j < colsCount; j++) {
                matrixCopy.set(j, i, matrixCopy.get(j, i) / matrixCopy.get(i, i));
                for (int k = i + 1; k < colsCount; k++) {
                    matrixCopy.set(j, k, matrixCopy.get(j, k) - matrixCopy.get(j, i) * matrixCopy.get(i, k));
                }
            }
        }
        return new LUDecompositionResult(matrixCopy);
    };

    public static DecomposeStrategy<LUDecompositionResult> LUP_DECOMPOSITION = (originalMatrix) -> {
        IMatrix matrixCopy = originalMatrix.copy();
        int colsCount = originalMatrix.getColsCount();

        for (int i = 0; i < colsCount - 1; i++) {
            for (int j = i + 1; j < colsCount; j++) {
                matrixCopy.set(j, i, matrixCopy.get(j, i) / matrixCopy.get(i, i));
                for (int k = i + 1; k < colsCount; k++) {
                    matrixCopy.set(j, k, matrixCopy.get(j, k) - matrixCopy.get(j, i) * matrixCopy.get(i, k));
                }
            }
        }
        return new LUDecompositionResult(matrixCopy);
    };
}
