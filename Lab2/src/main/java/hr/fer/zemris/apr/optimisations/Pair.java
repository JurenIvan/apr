package hr.fer.zemris.apr.optimisations;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Pair {

    private double first;
    private double second;

    private Pair(double first, double second) {
        this.first = first;
        this.second = second;
    }

    public static Pair of(double first, double second) {
        return new Pair(first, second);
    }

    public double getFirst() {
        return first;
    }

    public void setFirst(double first) {
        this.first = first;
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
    }

    public boolean equals(Pair other, int precision) {
        return abs(first - other.first) < pow(10, -1 * precision) && abs(second - other.second) < pow(10, -1 * precision);
    }
}
