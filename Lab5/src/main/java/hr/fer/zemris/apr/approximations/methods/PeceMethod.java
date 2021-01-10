package hr.fer.zemris.apr.approximations.methods;

import hr.fer.zemris.apr.approximations.ApproxHistoryRecord;
import hr.fer.zemris.apr.approximations.Approximation;
import hr.fer.zemris.apr.approximations.Corrector;
import hr.fer.zemris.apr.approximations.Predictor;
import hr.fer.zemris.apr.math.matrix.IMatrix;

import java.util.ArrayList;
import java.util.List;

public class PeceMethod implements Approximation {

    private final int q;
    private final Predictor predictor;
    private final Corrector corrector;
    private final List<ApproxHistoryRecord> history = new ArrayList<>();

    public PeceMethod(Predictor predictor, Corrector corrector, int q) {
        this.predictor = predictor;
        this.corrector = corrector;
        this.q = q;
    }

    @Override
    public IMatrix approximate(IMatrix x0, IMatrix a, IMatrix b, IMatrix r, double tMax, double interval, boolean timeDependant) {
        IMatrix x = x0.copy();
        history.add(new ApproxHistoryRecord(0, x));
        for (double i = interval; i <= tMax; i += interval) {
            var prediction = predictor.predict(x, a, b, r, i, interval, timeDependant);
            for (int j = 0; j < q; j++) {
                prediction = corrector.correct(x, prediction, a, b, r, i, interval, timeDependant);
            }
            x = prediction;
            history.add(new ApproxHistoryRecord(i, x));
        }
        return x;
    }

    @Override
    public List<ApproxHistoryRecord> getHistory() {
        return history;
    }
}
