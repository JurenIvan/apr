package hr.fer.zemris.apr.approximations.methods;

import hr.fer.zemris.apr.approximations.ApproxHistoryRecord;
import hr.fer.zemris.apr.approximations.Approximation;
import hr.fer.zemris.apr.approximations.Corrector;
import hr.fer.zemris.apr.math.matrix.IMatrix;
import hr.fer.zemris.apr.math.matrix.Matrix;

import java.util.ArrayList;
import java.util.List;

import static hr.fer.zemris.apr.math.matrix.Matrix.e;
import static hr.fer.zemris.apr.math.matrix.inverse.InverseStrategies.LUP_DECOMPOSITION_INVERSE;

public class TrapezeMethod implements Approximation, Corrector {

    private final List<ApproxHistoryRecord> history = new ArrayList<>();

    @Override
    public IMatrix approximate(IMatrix x0, IMatrix a, IMatrix b, IMatrix r, double tMax, double t, boolean timeDependant) {
        IMatrix x = x0.copy();
        Matrix e = e(a.getRowsCount(), a.getColsCount());

        var invers = a.nMultiply(t / 2).sub(e).multiply(-1).inverse(LUP_DECOMPOSITION_INVERSE);
        var R = invers.nMultiply(a.nMultiply(t / 2).add(e));
        var s = invers.nMultiply(t / 2).nMultiply(b);

        history.add(new ApproxHistoryRecord(0, x));
        for (double i = t; i <= tMax; i += t) {
            x = R.nMultiply(x).add(s.nMultiply(r.nMultiply(timeDependant ? i - t : 1).add(r.nMultiply(timeDependant ? i : 1))));
            history.add(new ApproxHistoryRecord(i, x));
        }
        return x;
    }

    @Override
    public List<ApproxHistoryRecord> getHistory() {
        return history;
    }


    @Override
    public IMatrix correct(IMatrix x, IMatrix xFuture, IMatrix a, IMatrix b, IMatrix r, double trenutniTrenutak, double duzinaFuckingPomaka, boolean timeDependant) {
        return x.nAdd(
                a.nMultiply(x).add(b.nMultiply(r.nMultiply(timeDependant ? trenutniTrenutak - duzinaFuckingPomaka : 1)))
                        .add(a.nMultiply(xFuture).add(b.nMultiply(r.nMultiply(timeDependant ? trenutniTrenutak : 1))))
                        .multiply(duzinaFuckingPomaka / 2)
        );
    }
}
