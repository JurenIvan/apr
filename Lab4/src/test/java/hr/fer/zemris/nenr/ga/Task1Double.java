package hr.fer.zemris.nenr.ga;

import hr.fer.zemris.nenr.ga.breeder.SidedAverageBreeder;
import hr.fer.zemris.nenr.ga.evaluator.FunctionEvaluatorDouble;
import hr.fer.zemris.nenr.ga.initializer.PopulationInitializerDouble;
import hr.fer.zemris.nenr.ga.mutator.NormalNoiseMutator;
import hr.fer.zemris.nenr.ga.picker.RouletteWheel;
import hr.fer.zemris.nenr.ga.selection.GenerationalBreederSelection;
import hr.fer.zemris.nenr.ga.selection.TournamentCannonSelection;
import org.junit.Test;

import java.util.stream.Collectors;

import static hr.fer.zemris.nenr.ga.Functions.*;

public class Task1Double {

    private final double MIN_VALUE = -50;
    private final double MAX_VALUE = 150;

    @Test
    public void f1() {
        var populationInitializer = new PopulationInitializerDouble(100,
                new double[]{MIN_VALUE, MIN_VALUE},
                new double[]{MAX_VALUE, MAX_VALUE});
        var evaluator = new FunctionEvaluatorDouble(F1);

        var breeder = new SidedAverageBreeder(0.5);
        var mutator = new NormalNoiseMutator(0.5, 0.01);

        var picker = new RouletteWheel();   //doest work with random picker
        var selector = new GenerationalBreederSelection<>(mutator, picker, breeder, true);
        var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, 15000, true);

        geneticAlgorithm.train();
        System.out.println(geneticAlgorithm.getFittest());
        System.out.println(geneticAlgorithm.getHistory().stream().map(GeneticAlgorithm.GeneticAlgorithmHistory::toString).collect(Collectors.joining("\n")));
    }

    @Test
    public void f3() {
        var populationInitializer = new PopulationInitializerDouble(100,
                new double[]{MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE},
                new double[]{MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE});
        var evaluator = new FunctionEvaluatorDouble(F3_GENERATOR.apply(5));

        var breeder = new SidedAverageBreeder(0.5);
        var mutator = new NormalNoiseMutator(0.5, 0.01);

        var picker = new RouletteWheel();   //doest work with random picker
        var selector = new GenerationalBreederSelection<>(mutator, picker, breeder, true);
        var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, 5000, true);

        geneticAlgorithm.train();
        System.out.println(geneticAlgorithm.getFittest());
        System.out.println(geneticAlgorithm.getHistory().stream().map(GeneticAlgorithm.GeneticAlgorithmHistory::toString).collect(Collectors.joining("\n")));
    }

    @Test
    public void f6() {
        var populationInitializer = new PopulationInitializerDouble(1000,
                new double[]{MIN_VALUE, MIN_VALUE},
                new double[]{MAX_VALUE, MAX_VALUE});

        SidedAverageBreeder breeder = new SidedAverageBreeder(0.8);
        var mutator = new NormalNoiseMutator(1, 0.1);
        var evaluator = new FunctionEvaluatorDouble(F6_GENERATOR.apply(2));

        var picker = new RouletteWheel();   //doest work with random picker
        var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);
        var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, 5000, true);

        geneticAlgorithm.train();
        System.out.println(geneticAlgorithm.getFittest());
        System.out.println(geneticAlgorithm.getHistory().stream().map(GeneticAlgorithm.GeneticAlgorithmHistory::toString).collect(Collectors.joining("\n")));
    }

    @Test
    public void f7() {
        var populationInitializer = new PopulationInitializerDouble(1000,
                new double[]{MIN_VALUE, MIN_VALUE},
                new double[]{MAX_VALUE, MAX_VALUE});

        SidedAverageBreeder breeder = new SidedAverageBreeder(0.8);
        var mutator = new NormalNoiseMutator(1, 0.1);
        var evaluator = new FunctionEvaluatorDouble(F7_GENERATOR.apply(2));

        var picker = new RouletteWheel();   //doest work with random picker
        var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);
        var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, 5000, true);

        geneticAlgorithm.train();
        System.out.println(geneticAlgorithm.getFittest());
        System.out.println(geneticAlgorithm.getHistory().stream().map(GeneticAlgorithm.GeneticAlgorithmHistory::toString).collect(Collectors.joining("\n")));
    }
}
