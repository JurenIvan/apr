package hr.fer.zemris.nenr.ga;

import hr.fer.zemris.nenr.ga.breeder.SidedAverageBreederBinary;
import hr.fer.zemris.nenr.ga.evaluator.FunctionEvaluatorBinary;
import hr.fer.zemris.nenr.ga.initializer.PopulationInitializerBinary;
import hr.fer.zemris.nenr.ga.mutator.BinaryMutator;
import hr.fer.zemris.nenr.ga.picker.RouletteWheelBinary;
import hr.fer.zemris.nenr.ga.selection.TournamentCannonSelection;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static hr.fer.zemris.nenr.ga.Functions.*;

public class Task1Binary {

    private final double MIN_VALUE = -4;
    private final double MAX_VALUE = 4;

    @Test
    public void f1() {
        var minRange = new double[]{MIN_VALUE, MIN_VALUE};
        var maxRange = new double[]{MAX_VALUE, MAX_VALUE};

        var populationInitializer = new PopulationInitializerBinary(1000, minRange.length, minRange, maxRange, new int[]{6, 6});
        var evaluator = new FunctionEvaluatorBinary(F1, minRange, maxRange);
        var breeder = new SidedAverageBreederBinary(0.8);
        var mutator = new BinaryMutator(0.1);
        var picker = new RouletteWheelBinary();
        var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);

        var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, 1000, true);

        geneticAlgorithm.train();
        System.out.println(
                geneticAlgorithm.getHistory().stream()
                        .map(e -> "%s\n double representation : %s".formatted(
                                e.toString(),
                                Arrays.stream(evaluator.evaluateDoubleValue(e.getBestInstance()))
                                        .mapToObj(e2 -> String.format("%6.3f", e2))
                                        .collect(Collectors.joining(","))))
                        .collect(Collectors.joining("\n")));
    }

    @Test
    public void f3() {
        var minRange = new double[]{MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE};
        var maxRange = new double[]{MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE};

        var populationInitializer = new PopulationInitializerBinary(100, minRange.length, minRange, maxRange, new int[]{6, 6, 6, 6, 6});
        var evaluator = new FunctionEvaluatorBinary(F3_GENERATOR.apply(5), minRange, maxRange);
        var breeder = new SidedAverageBreederBinary(0.8);
        var mutator = new BinaryMutator(0.001);
        var picker = new RouletteWheelBinary();
        var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);

        var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, 1000, true);

        geneticAlgorithm.train();
        System.out.println(geneticAlgorithm.getFittest());
        System.out.println(
                geneticAlgorithm.getHistory().stream()
                        .map(e -> "%s\n double representation : %s".formatted(
                                e.toString(),
                                Arrays.stream(evaluator.evaluateDoubleValue(e.getBestInstance()))
                                        .mapToObj(e2 -> String.format("%6.3f", e2))
                                        .collect(Collectors.joining(","))))
                        .collect(Collectors.joining("\n")));
    }

    @Test
    public void f6() {
        var minRange = new double[]{MIN_VALUE, MIN_VALUE};
        var maxRange = new double[]{MAX_VALUE, MAX_VALUE};

        var populationInitializer = new PopulationInitializerBinary(100, minRange.length, minRange, maxRange, new int[]{6, 6, 6, 6, 6});
        var evaluator = new FunctionEvaluatorBinary(F6_GENERATOR.apply(2), minRange, maxRange);
        var breeder = new SidedAverageBreederBinary(0.8);
        var mutator = new BinaryMutator(0.001);
        var picker = new RouletteWheelBinary();
        var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);

        var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, 1000, true);

        geneticAlgorithm.train();
        System.out.println(geneticAlgorithm.getFittest());
        System.out.println(
                geneticAlgorithm.getHistory().stream()
                        .map(e -> "%s\n double representation : %s".formatted(
                                e.toString(),
                                Arrays.stream(evaluator.evaluateDoubleValue(e.getBestInstance()))
                                        .mapToObj(e2 -> String.format("%6.3f", e2))
                                        .collect(Collectors.joining(","))))
                        .collect(Collectors.joining("\n")));
    }

    @Test
    public void f7() {
        var minRange = new double[]{MIN_VALUE, MIN_VALUE};
        var maxRange = new double[]{MAX_VALUE, MAX_VALUE};

        var populationInitializer = new PopulationInitializerBinary(100, minRange.length, minRange, maxRange, new int[]{6, 6, 6, 6, 6});
        var evaluator = new FunctionEvaluatorBinary(F7_GENERATOR.apply(2), minRange, maxRange);
        var breeder = new SidedAverageBreederBinary(0.8);
        var mutator = new BinaryMutator(0.001);
        var picker = new RouletteWheelBinary();
        var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);

        var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, 1000, true);

        geneticAlgorithm.train();
        System.out.println(geneticAlgorithm.getFittest());
        System.out.println(geneticAlgorithm.getHistory().stream().map(GeneticAlgorithm.GeneticAlgorithmHistory::toString).collect(Collectors.joining("\n")));
        System.out.println(
                geneticAlgorithm.getHistory().stream()
                        .map(e -> "%s\n double representation : %s".formatted(
                                e.toString(),
                                Arrays.stream(evaluator.evaluateDoubleValue(e.getBestInstance()))
                                        .mapToObj(e2 -> String.format("%6.3f", e2))
                                        .collect(Collectors.joining(","))))
                        .collect(Collectors.joining("\n")));
    }
}
