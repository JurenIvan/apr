package hr.fer.zemris.apr.approximations.methods;

import hr.fer.zemris.apr.approximations.ApproxHistoryRecord;
import hr.fer.zemris.apr.approximations.Approximation;
import hr.fer.zemris.apr.math.matrix.IMatrix;
import hr.fer.zemris.apr.math.matrix.Matrix;

import java.util.ArrayList;
import java.util.List;

import static hr.fer.zemris.apr.math.matrix.Matrix.e;

public class RungeKuttaMethod implements Approximation {

    private final List<ApproxHistoryRecord> history = new ArrayList<>();

    @Override
    public IMatrix approximate(IMatrix x0, IMatrix a, IMatrix b, IMatrix r, double tMax, double t, boolean timeDependant) {
        IMatrix x = x0.copy();
        Matrix e = e(a.getRowsCount(), a.getColsCount());

        history.add(new ApproxHistoryRecord(0, x));
        for (double i = t; i <= tMax; i += t) {

            double multiplier = timeDependant ? (i - t) + t / 2 : 1;

            var m1 = calculateM(a, b, r, x, timeDependant ? i - t : 1);
            var m2 = calculateM(a, b, r, m1.nMultiply(t / 2).add(x), multiplier);
            var m3 = calculateM(a, b, r, m2.nMultiply(t / 2).add(x), multiplier);
            var m4 = calculateM(a, b, r, m3.nMultiply(t).add(x), i);

            x = x.nAdd(m1.add(m2.multiply(2)).add(m3.multiply(2)).add(m4).multiply(t / 6));

            history.add(new ApproxHistoryRecord(i, x));
        }
        return x;
    }

    public IMatrix calculateM(IMatrix a, IMatrix b, IMatrix r, IMatrix x, double t) {
        return a.nMultiply(x).add(b.nMultiply(r).multiply(t));
    }

    @Override
    public List<ApproxHistoryRecord> getHistory() {
        return history;
    }
}
