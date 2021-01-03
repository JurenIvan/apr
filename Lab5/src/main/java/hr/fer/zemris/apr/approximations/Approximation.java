package hr.fer.zemris.apr.approximations;

import hr.fer.zemris.apr.math.matrix.IMatrix;

import java.util.List;

public interface Approximation {

    IMatrix approximate();

    List<ApproxHistoryRecord> getHistory();
}
