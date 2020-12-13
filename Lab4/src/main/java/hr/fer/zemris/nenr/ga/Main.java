package hr.fer.zemris.nenr.ga;

import hr.fer.zemris.nenr.ga.breeder.SidedAverageBreeder;
import hr.fer.zemris.nenr.ga.evaluator.FunctionEvaluatorDouble;
import hr.fer.zemris.nenr.ga.evaluator.IFunction;
import hr.fer.zemris.nenr.ga.initializer.PopulationInitializerDouble;
import hr.fer.zemris.nenr.ga.mutator.NormalNoiseMutator;
import hr.fer.zemris.nenr.ga.picker.RouletteWheel;
import hr.fer.zemris.nenr.ga.selection.GenerationalBreederSelection;

import java.util.function.Function;
import java.util.stream.Collectors;

import static hr.fer.zemris.nenr.ga.GeneticAlgorithm.GeneticAlgorithmHistory;
import static java.lang.Math.*;
import static java.util.stream.IntStream.range;

public class Main {


    public static final IFunction F1 = x -> 100 * pow(x[1] - pow(x[0], 2), 2) + pow(1 - x[0], 2);
    public static final Function<Integer, IFunction> F3_GENERATOR = order -> x -> range(0, order).mapToDouble(i -> Math.pow(x[i] - i, 2)).sum();

    public static final Function<Integer, IFunction> F6_GENERATOR = order -> x -> {
        double sumOfSquares = range(0, order).mapToDouble(i -> pow(x[i], 2)).sum();
        return 0.5 + (pow(sin(sqrt(sumOfSquares)), 2) - 0.5) / pow(1 + 0.001 * sumOfSquares, 2);
    };

    public static final Function<Integer, IFunction> F7_GENERATOR = order -> x -> {
        double sumOfSquares = range(0, order).mapToDouble(i -> pow(x[i], 2)).sum();
        return Math.pow(sumOfSquares, 0.25) * (1 + Math.pow(Math.sin(50 * Math.pow(sumOfSquares, 0.1)), 2));
    };

    public static void main(String[] args) {

        var populationInitializer = new PopulationInitializerDouble(100, new double[]{-4, -4}, new double[]{4, 4});
        var evaluator = new FunctionEvaluatorDouble(F1);

        var breeder = new SidedAverageBreeder(0.5);
        var mutator = new NormalNoiseMutator(0.5, 0.01);

        var picker = new RouletteWheel();   //doest work with random picker
        var selector = new GenerationalBreederSelection<>(mutator, picker, breeder, true);
        var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, 10000, true);

        geneticAlgorithm.train();
        System.out.println(geneticAlgorithm.getFittest());
        System.out.println(geneticAlgorithm.getHistory().stream().map(GeneticAlgorithmHistory::toString).collect(Collectors.joining("\n")));
    }
}
