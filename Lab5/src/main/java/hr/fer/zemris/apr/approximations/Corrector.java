package hr.fer.zemris.apr.approximations;

import hr.fer.zemris.apr.math.matrix.IMatrix;

public interface Corrector {

    IMatrix correct(IMatrix x, IMatrix xFuture, IMatrix a, IMatrix b, IMatrix r, double t, double interval, boolean timeDependant);
}
