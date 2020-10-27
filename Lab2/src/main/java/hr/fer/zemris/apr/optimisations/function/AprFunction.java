package hr.fer.zemris.apr.optimisations.function;

public class AprFunction implements DoubleFunction {

    private final DoubleFunction function;
    private int counter = 0;

    public AprFunction(DoubleFunction function) {
        this.function = function;
    }

    public int getCounter() {
        return counter;
    }

    public double apply(double in) {
        counter++;
        return function.apply(in);
    }
}
