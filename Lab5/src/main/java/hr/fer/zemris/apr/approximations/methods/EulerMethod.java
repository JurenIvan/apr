package hr.fer.zemris.apr.approximations.methods;

import hr.fer.zemris.apr.approximations.ApproxHistoryRecord;
import hr.fer.zemris.apr.approximations.Approximation;
import hr.fer.zemris.apr.approximations.Predictor;
import hr.fer.zemris.apr.math.matrix.IMatrix;

import java.util.ArrayList;
import java.util.List;

public class EulerMethod implements Approximation, Predictor {

    private final List<ApproxHistoryRecord> history = new ArrayList<>();

    @Override
    public IMatrix approximate(IMatrix x0, IMatrix a, IMatrix b, IMatrix r, double t, double interval, boolean timeDependant) {
        IMatrix x = x0.copy();
        double i;
        history.add(new ApproxHistoryRecord(0, x));
        for (i = interval; i <= t; i += interval) {
            x = x.nAdd(a.nMultiply(x).add(b.nMultiply(r).multiply(timeDependant ? i - interval : 1)).multiply(interval));
            history.add(new ApproxHistoryRecord(i, x));
        }
        return x;
    }

    @Override
    public List<ApproxHistoryRecord> getHistory() {
        return history;
    }

    @Override
    public IMatrix predict(IMatrix x, IMatrix a, IMatrix b, IMatrix r, double t, double interval, boolean timeDependant) {
        return a.nMultiply(x).add(b.nMultiply(r.nMultiply(timeDependant ? t - interval : 1))).multiply(interval).add(x);
    }
}
