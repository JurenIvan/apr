package hr.fer.zemris.nenr.ga.evaluator;

import hr.fer.zemris.nenr.ga.domain.InstanceDouble;

import java.util.function.BiFunction;

public class FunctionEvaluatorDouble implements Evaluator<InstanceDouble> {

    private final BiFunction<Double, Double, Double> errorCollectingFuction;
    private final IFunction function;

    public FunctionEvaluatorDouble(IFunction function) {
        this((expected, actual) -> Math.abs(expected - actual), function);
    }

    public FunctionEvaluatorDouble(BiFunction<Double, Double, Double> errorCollectingFuction, IFunction function) {
        this.function = function;
        this.errorCollectingFuction = errorCollectingFuction;
    }

    public double evaluate(InstanceDouble instance) {
        return errorCollectingFuction.apply(0.0, function.valueAt(instance.getChromosomes()));
    }

    @Override
    public double[] evaluateDoubleValue(InstanceDouble instance) {
        return instance.getChromosomes();
    }
}
