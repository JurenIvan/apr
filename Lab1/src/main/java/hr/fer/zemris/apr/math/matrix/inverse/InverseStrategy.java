package hr.fer.zemris.apr.math.matrix.inverse;

import hr.fer.zemris.apr.math.matrix.IMatrix;

@FunctionalInterface
public interface InverseStrategy {

    IMatrix inverseOf(IMatrix matrix);
}
