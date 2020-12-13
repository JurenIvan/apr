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

    private static double[] transformIntoDoubleRepresentation(boolean[][] chromosomes, double[] minValue, double[] maxValue) {
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

    @Override
    public double evaluate(InstanceBinary instance) {
        var value = transformIntoDoubleRepresentation(instance.getChromosomes(), minValue, maxValue);
        return errorCollectingFunction.apply(0.0, function.valueAt(value));
    }

    @Override
    public double[] evaluateDoubleValue(InstanceBinary instance) {
        return transformIntoDoubleRepresentation(instance.getChromosomes(), minValue, maxValue);
    }
}
