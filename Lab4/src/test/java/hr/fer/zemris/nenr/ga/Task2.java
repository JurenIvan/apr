package hr.fer.zemris.nenr.ga;

import hr.fer.zemris.nenr.ga.breeder.SidedAverageBreeder;
import hr.fer.zemris.nenr.ga.evaluator.FunctionEvaluatorDouble;
import hr.fer.zemris.nenr.ga.initializer.PopulationInitializerDouble;
import hr.fer.zemris.nenr.ga.mutator.NormalNoiseMutator;
import hr.fer.zemris.nenr.ga.picker.RouletteWheel;
import hr.fer.zemris.nenr.ga.selection.TournamentCannonSelection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static hr.fer.zemris.nenr.ga.functions.Functions.F6_GENERATOR;
import static hr.fer.zemris.nenr.ga.functions.Functions.F7_GENERATOR;

public class Task2 {

    private final double MIN_VALUE = -50;
    private final double MAX_VALUE = 150;
    private final int MAX_ITERATION_COUNT = 1000000;

    private double[] borders(int dim, double val) {
        var a = new double[dim];
        for (int i = 0; i < dim; i++) {
            a[i] = val;
        }
        return a;
    }

    @Test
    public void f6() {
        for (int i : List.of(1, 3, 6, 10)) {

            var populationInitializer = new PopulationInitializerDouble(100,
                    borders(i, MIN_VALUE),
                    borders(i, MAX_VALUE));

            SidedAverageBreeder breeder = new SidedAverageBreeder(0.8);
            var mutator = new NormalNoiseMutator(1, 0.1);
            var evaluator = new FunctionEvaluatorDouble(F6_GENERATOR.apply(i));

            var picker = new RouletteWheel();   //doest work with random picker
            var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);
            var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, true);

            geneticAlgorithm.train();
            System.out.println(geneticAlgorithm.getFittest());
        }
    }

    @Test
    public void f7() {
        for (int i : List.of(1, 3, 6, 10)) {

            var populationInitializer = new PopulationInitializerDouble(100,
                    borders(i, MIN_VALUE),
                    borders(i, MAX_VALUE));

            SidedAverageBreeder breeder = new SidedAverageBreeder(0.8);
            var mutator = new NormalNoiseMutator(1, 0.1);
            var evaluator = new FunctionEvaluatorDouble(F7_GENERATOR.apply(i));

            var picker = new RouletteWheel();   //doest work with random picker
            var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);
            var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, true);

            geneticAlgorithm.train();
            System.out.println(geneticAlgorithm.getFittest());
        }
    }
}
