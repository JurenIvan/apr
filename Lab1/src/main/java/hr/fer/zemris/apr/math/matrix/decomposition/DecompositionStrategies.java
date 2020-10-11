package hr.fer.zemris.apr.math.matrix.decomposition;

import hr.fer.zemris.apr.math.matrix.IMatrix;
import hr.fer.zemris.apr.math.matrix.Matrix;

import static java.lang.Double.MIN_VALUE;

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

    public static DecomposeStrategy<LUPDecompositionResult> LUP_DECOMPOSITION = (originalMatrix) -> {
        double e = 1e-4;
        double[][] a = originalMatrix.toArray();
        int colsCount = originalMatrix.getColsCount();
        double[][] p = new double[colsCount][colsCount];
        for (int i = 0; i < colsCount; i++) {
            p[i][i] = 1;
        }

        for (int i = 0; i < colsCount - 1; i++) {
            double maxValue = MIN_VALUE;
            int maxIndex = -1;
            for (int j = i; j < colsCount; j++) {
                double value = Math.abs(a[j][i]);
                if (value > e && value > maxValue) {
                    maxIndex = j;
                    maxValue = value;
                }
            }
            if (maxIndex == -1) throw new IllegalStateException();

            double[] temp = a[i];
            a[i] = a[maxIndex];
            a[maxIndex] = temp;
            temp = p[i];
            p[i] = p[maxIndex];
            p[maxIndex] = temp;

            for (int j = i + 1; j < colsCount; j++) {
                a[j][i] /= a[i][i];
                for (int k = i + 1; k < colsCount; k++) {
                    a[j][k] -= a[j][i] * a[i][k];
                }
            }
        }
        return new LUPDecompositionResult(new Matrix(a.length, a.length, a, true), new Matrix(p.length, p.length, p, true));
    };
}
