package hr.fer.zemris.nenr.ga.evaluator;

import hr.fer.zemris.nenr.ga.domain.InstanceDouble;

import java.util.function.BiFunction;

public class FunctionEvaluatorDouble implements Evaluator<InstanceDouble> {

    private final BiFunction<Double, Double, Double> errorCollectingFuction;
    private final IFunction function;

    public FunctionEvaluatorDouble(IFunction function) {
        this((expected, actual) -> Math.pow(expected - actual, 2), function);
    }

    public FunctionEvaluatorDouble(BiFunction<Double, Double, Double> errorCollectingFuction, IFunction function) {
        this.function = function;
        this.errorCollectingFuction = errorCollectingFuction;
    }

    public double evaluate(InstanceDouble instance) {
        return errorCollectingFuction.apply(instance.getFitness(), function.valueAt(instance.getChromosomes()));
    }
}
