package hr.fer.zemris.apr.approximations;

import hr.fer.zemris.apr.math.matrix.IMatrix;

public class ApproxHistoryRecord {
    private final double t;
    private final IMatrix state;

    public ApproxHistoryRecord(double t, IMatrix state) {
        this.t = t;
        this.state = state;
    }

    public double getT() {
        return t;
    }

    public IMatrix getState() {
        return state;
    }

    @Override
    public String toString() {
        return "ApproxHistoryRecord{" +
                "t=" + t +
                ", state=\n" + state +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApproxHistoryRecord that = (ApproxHistoryRecord) o;

        if (Math.abs(that.t - t) > 1e-6) return false;
        return state.equals(that.state, 6);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(t);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + state.hashCode();
        return result;
    }
}
