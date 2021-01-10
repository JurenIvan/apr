package hr.fer.zemris.apr.approximations;

import hr.fer.zemris.apr.math.matrix.IMatrix;

public interface Predictor {

    IMatrix predict(IMatrix x, IMatrix a, IMatrix b, IMatrix r, double t, double interval, boolean timeDependant);
}
