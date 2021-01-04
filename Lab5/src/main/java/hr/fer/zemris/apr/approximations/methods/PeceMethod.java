package hr.fer.zemris.apr.approximations.methods;

import hr.fer.zemris.apr.approximations.ApproxHistoryRecord;
import hr.fer.zemris.apr.approximations.Approximation;
import hr.fer.zemris.apr.math.matrix.IMatrix;

import java.util.ArrayList;
import java.util.List;

public class PeceMethod implements Approximation {

    private final int q;
    private final Approximation predictor;
    private final Approximation corrector;
    private final List<ApproxHistoryRecord> history = new ArrayList<>();

    public PeceMethod(Approximation predictor, Approximation corrector, int q) {
        this.predictor = predictor;
        this.corrector = corrector;
        this.q = q;
    }

    @Override
    public IMatrix approximate(IMatrix x0, IMatrix a, IMatrix b, IMatrix r, double t, double interval, boolean timeDependant) {
        IMatrix x = x0.copy();
        for (double i = interval; i <= t; i += interval) {
            x = predictor.approximate(x, a, b, r, t, interval, timeDependant);
            for (int j = 0; j < q; j++) {
                x = corrector.approximate(x, a, b, r, t, interval, timeDependant);
            }
        }
        return x;
    }

    @Override
    public List<ApproxHistoryRecord> getHistory() {
        return history;
    }
}
