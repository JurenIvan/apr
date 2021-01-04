package hr.fer.zemris.apr.approximations.methods;

import hr.fer.zemris.apr.approximations.ApproxHistoryRecord;
import hr.fer.zemris.apr.approximations.Approximation;
import hr.fer.zemris.apr.math.matrix.IMatrix;
import hr.fer.zemris.apr.math.matrix.Matrix;

import java.util.ArrayList;
import java.util.List;

import static hr.fer.zemris.apr.math.matrix.Matrix.e;
import static hr.fer.zemris.apr.math.matrix.inverse.InverseStrategies.LUP_DECOMPOSITION_INVERSE;

public class InverseEulerMethod implements Approximation {

    private final List<ApproxHistoryRecord> history = new ArrayList<>();

    @Override
    public IMatrix approximate(IMatrix x0, IMatrix a, IMatrix b, IMatrix r, double t, double interval, boolean timeDependant) {
        IMatrix x = x0.copy();
        Matrix e = e(a.getRowsCount(), a.getColsCount());

        var p = a.nMultiply(interval).sub(e).multiply(-1).inverse(LUP_DECOMPOSITION_INVERSE);
        var q = p.nMultiply(interval).nMultiply(b);

        history.add(new ApproxHistoryRecord(0, x));
        for (double i = interval; i <= t; i += interval) {
            x = p.nMultiply(x).add(q.nMultiply(r.nMultiply(timeDependant ? i : 1)));
            history.add(new ApproxHistoryRecord(i, x));
        }
        return x;
    }

    @Override
    public List<ApproxHistoryRecord> getHistory() {
        return history;
    }
}
