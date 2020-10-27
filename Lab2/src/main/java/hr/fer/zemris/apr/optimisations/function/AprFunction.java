package hr.fer.zemris.apr.optimisations.function;

import hr.fer.zemris.apr.math.vector.IVector;

import java.util.function.Function;

public class AprFunction implements Function<IVector, IVector> {

    private final Function<IVector, IVector> function;
    private int counter = 0;

    public AprFunction(Function<IVector, IVector> function) {
        this.function = function;
    }

    public int getCounter() {
        return counter;
    }

    public IVector apply(IVector in) {
        counter++;
        return function.apply(in);
    }
}
