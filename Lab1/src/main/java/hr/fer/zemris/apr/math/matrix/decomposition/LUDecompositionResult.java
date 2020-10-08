package hr.fer.zemris.apr.math.matrix.decomposition;

import hr.fer.zemris.apr.math.matrix.IMatrix;

public class LUDecompositionResult {

    private final IMatrix data;

    public LUDecompositionResult(IMatrix data) {
        this.data = data;
    }

    public IMatrix getLMatrix() {
        var lMatrix = data.copy();
        int dimension = lMatrix.getColsCount();
        for (int i = 0; i < dimension; i++) {
            for (int j = i; j < dimension; j++) {
                lMatrix.set(i, j, 0);
            }
        }
        for (int i = 0; i < dimension; i++) {
            lMatrix.set(i, i, 1);
        }
        return lMatrix;
    }

    public IMatrix getUMatrix() {
        var uMatrix = data.copy();
        int dimension = uMatrix.getColsCount();
        for (int i = 1; i < dimension; i++) {
            for (int j = 0; j < i; j++) {
                uMatrix.set(i, j, 0);
            }
        }
        return uMatrix;
    }

    public IMatrix getLUMatrix() {
        return data;
    }
}
