package hr.fer.zemris.apr.math.matrix.decomposition;

import hr.fer.zemris.apr.math.matrix.IMatrix;

@FunctionalInterface
public interface DecomposeStrategy<T> {

    T decompose(IMatrix matrix);
}
