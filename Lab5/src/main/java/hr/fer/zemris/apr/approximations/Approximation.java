package hr.fer.zemris.apr.approximations;

import hr.fer.zemris.apr.math.matrix.IMatrix;

import java.util.List;

public interface Approximation {

    IMatrix approximate(IMatrix x0, IMatrix a, IMatrix b, IMatrix r, double tMax, double t, boolean timeDependant);

    List<ApproxHistoryRecord> getHistory();
}
