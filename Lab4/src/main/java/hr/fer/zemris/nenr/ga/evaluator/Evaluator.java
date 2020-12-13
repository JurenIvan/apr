package hr.fer.zemris.nenr.ga.evaluator;

import hr.fer.zemris.apr.optimisations.functions.CountingFunction;

public interface Evaluator<T> {

    double evaluate(T instance);

    double[] evaluateDoubleValue(T instance);

    CountingFunction<double[], Double> getFunction();
}
