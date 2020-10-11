package hr.fer.zemris.apr.math.matrix.decomposition;

import hr.fer.zemris.apr.math.matrix.IMatrix;

public class LUPDecompositionResult extends LUDecompositionResult {

    private final IMatrix pMatrix;

    public LUPDecompositionResult(IMatrix data, IMatrix pMatrix) {
        super(data);
        this.pMatrix = pMatrix;
    }

    public IMatrix getPMatrix() {
        return pMatrix;
    }
}
