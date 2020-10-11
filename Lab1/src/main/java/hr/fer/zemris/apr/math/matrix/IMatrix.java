package hr.fer.zemris.apr.math.matrix;

import hr.fer.zemris.apr.math.matrix.decomposition.DecomposeStrategy;
import hr.fer.zemris.apr.math.matrix.inverse.InverseStrategy;
import hr.fer.zemris.apr.math.vector.IVector;

public interface IMatrix {

    int getRowsCount();

    int getColsCount();

    double get(int row, int column);

    IMatrix set(int row, int column, double value);

    IMatrix copy();

    IMatrix newInstance(int rows, int columns);

    IMatrix nTranspose(boolean liveView);

    IMatrix add(IMatrix other);

    IMatrix nAdd(IMatrix other);

    IMatrix sub(IMatrix other);

    IMatrix nSub(IMatrix other);

    IMatrix nMultiply(IMatrix other);

    double determinant();

    IMatrix subMatrix(int row, int column, boolean liveView);

    IMatrix nInvert();

    double[][] toArray();

    IVector toVector(boolean flag);

    <T> T nDecompose(DecomposeStrategy<T> strategy);

    IMatrix inverse(InverseStrategy strategy);
}
