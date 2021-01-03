package hr.fer.zemris.apr.approximations.methods;

import hr.fer.zemris.apr.approximations.ApproxHistoryRecord;
import hr.fer.zemris.apr.approximations.Approximation;
import hr.fer.zemris.apr.math.matrix.IMatrix;

import java.util.ArrayList;
import java.util.List;

public class EulerMethod implements Approximation {

    private final IMatrix x0;
    private final IMatrix a;
    //    private final IMatrix b;
//    private final IMatrix r;
    private final double t;
    private final double interval;
    private final List<ApproxHistoryRecord> history;

    //    public EulerMethod(IMatrix x0, IMatrix a, IMatrix b, IMatrix r, double t, double interval) {
    public EulerMethod(IMatrix x0, IMatrix a, double t, double interval) {
        this.x0 = x0;
        this.a = a;
//        this.b = b;
//        this.r = r;
        this.t = t;
        this.interval = interval;
        history = new ArrayList<>((int) (t / interval) + 1);
    }

    @Override
    public IMatrix approximate() {
        IMatrix x = x0.copy();
        double i;
        history.add(new ApproxHistoryRecord(0, x));
        for (i = interval; i <= t; i += interval) {
            x = x.nAdd(a.nMultiply(x).multiply(interval));
            history.add(new ApproxHistoryRecord(i, x));
        }
        return x;
    }

    @Override
    public List<ApproxHistoryRecord> getHistory() {
        return history;
    }
}
