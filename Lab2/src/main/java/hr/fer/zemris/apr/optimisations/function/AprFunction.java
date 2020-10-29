package hr.fer.zemris.apr.optimisations.function;

import hr.fer.zemris.apr.math.vector.IVector;

import java.util.function.Function;

public class AprFunction implements Function<IVector, Double> {

    private final Function<IVector, Double> function;
    private int counter = 0;

    public AprFunction(Function<IVector, Double> function) {
        this.function = function;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public Double apply(IVector in) {
        counter++;
        return function.apply(in);
    }
}
