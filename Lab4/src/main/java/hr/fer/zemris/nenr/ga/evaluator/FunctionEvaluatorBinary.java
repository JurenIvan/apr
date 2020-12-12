package hr.fer.zemris.nenr.ga.evaluator;

import hr.fer.zemris.nenr.ga.domain.InstanceBinary;

import java.util.function.BiFunction;

public class FunctionEvaluatorBinary implements Evaluator<InstanceBinary> {

    private final BiFunction<Double, Double, Double> errorCollectingFunction;
    private final IFunction function;
    private final double[] minValue;
    private final double[] maxValue;

    public FunctionEvaluatorBinary(IFunction function, double[] minValue, double[] maxValue) {
        this((expected, actual) -> Math.pow(expected - actual, 2), function, minValue, maxValue);

    }

    public FunctionEvaluatorBinary(BiFunction<Double, Double, Double> errorCollectingFuction, IFunction function, double[] minValue, double[] maxValue) {
        this.errorCollectingFunction = errorCollectingFuction;
        this.function = function;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public double evaluate(InstanceBinary instance) {
        return errorCollectingFunction.apply(instance.getFitness(), function.valueAt(transformIntoDoubleRepresentation(instance.getChromosomes())));
    }

    private double[] transformIntoDoubleRepresentation(boolean[][] chromosomes) {
        var result = new double[chromosomes.length];

        for (int dimension = 0; dimension < chromosomes.length; dimension++) {
            double value = 0;
            double pow = 1;
            for (boolean b : chromosomes[dimension]) {
                pow /= 2;
                value += b ? pow : 0;
            }

            double min = minValue[dimension];
            double max = maxValue[dimension];
            result[dimension] = min + (max - min) * value;
        }

        return result;
    }
}
