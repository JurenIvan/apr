package hr.fer.zemris.apr.math.matrix;


import hr.fer.zemris.apr.math.matrix.decomposition.DecomposeStrategy;
import hr.fer.zemris.apr.math.matrix.determinant.DeterminantStrategy;
import hr.fer.zemris.apr.math.matrix.inverse.InverseStrategy;
import hr.fer.zemris.apr.math.matrix.solver.EquasionSolver;
import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.views.VectorMatrixView;

import java.util.Arrays;

public abstract class AbstractMatrix implements IMatrix {

    @Override
    public IMatrix nTranspose(boolean liveView) {
        return liveView ? new MatrixTransposeView(this) : new MatrixTransposeView(this.copy());
    }

    @Override
    public IMatrix subMatrix(int row, int column, boolean liveView) {
        return liveView ? new MatrixSubMatrixView(this, column, row) : new MatrixSubMatrixView(this.copy(), column, row);
    }

    @Override
    public IMatrix add(IMatrix other) {
        checkDimensions(other);
        for (int i = 0; i < getRowsCount(); i++)
            for (int j = 0; j < getColsCount(); j++)
                set(i, j, get(i, j) + other.get(i, j));
        return this;
    }

    @Override
    public IMatrix nAdd(IMatrix other) {
        checkDimensions(other);
        return copy().add(other);
    }

    @Override
    public IMatrix sub(IMatrix other) {
        checkDimensions(other);
        for (int i = 0; i < getRowsCount(); i++)
            for (int j = 0; j < getColsCount(); j++)
                set(i, j, get(i, j) - other.get(i, j));
        return this;
    }

    @Override
    public IMatrix nSub(IMatrix other) {
        checkDimensions(other);
        return copy().sub(other);
    }

    @Override
    public IMatrix nMultiply(IMatrix other) {
        checkMultiplicationDimensions(other);
        IMatrix matrix = newInstance(getRowsCount(), other.getColsCount());
        for (int row = 0; row < getRowsCount(); row++) {
            for (int col = 0; col < other.getColsCount(); col++) {
                double cell = 0;
                for (int i = 0; i < other.getRowsCount(); i++)
                    cell += get(row, i) * other.get(i, col);
                matrix.set(row, col, cell);
            }
        }
        return matrix;
    }

    @Override
    public IVector toVector(boolean flag) {
        return flag ? new VectorMatrixView(this) : new VectorMatrixView(this.copy());
    }

    private void checkDimensions(IMatrix other) {
        if (getColsCount() != other.getColsCount() || getRowsCount() != other.getRowsCount())
            throw new UnsupportedOperationException();
    }

    private void checkMultiplicationDimensions(IMatrix other) {
        if (getColsCount() != other.getRowsCount())
            throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return Arrays.deepToString(toArray()).replace("], ", "]\n");
    }

    @Override
    public <T> T nDecompose(DecomposeStrategy<T> strategy) {
        return strategy.decompose(this);
    }

    @Override
    public IMatrix inverse(InverseStrategy strategy) {
        return strategy.inverseOf(this);
    }

    @Override
    public double determinant(DeterminantStrategy strategy) {
        return strategy.calculateDeterminant(this);
    }

    @Override
    public IVector solveSystem(EquasionSolver solver, IVector right) {
        return solver.solve(this, right);
    }
}
