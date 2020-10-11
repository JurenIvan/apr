package hr.fer.zemris.apr.math.matrix.decomposition;

import hr.fer.zemris.apr.math.matrix.IMatrix;

public class LUDecompositionResult {

    protected final IMatrix data;
    protected IMatrix lMatrix;
    protected IMatrix uMatrix;

    public LUDecompositionResult(IMatrix data) {
        this.data = data;
    }

    public IMatrix getLMatrix() {
        if (lMatrix == null)
            lMatrix = calculateLMatrix();
        return lMatrix;
    }

    public IMatrix getUMatrix() {
        if (uMatrix == null)
            uMatrix = calculateUMatrix();
        return uMatrix;
    }

    private IMatrix calculateLMatrix() {
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

    private IMatrix calculateUMatrix() {
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
