package hr.fer.zemris.nenr.ga;

import hr.fer.zemris.nenr.ga.breeder.SidedAverageBreederBinary;
import hr.fer.zemris.nenr.ga.evaluator.FunctionEvaluatorBinary;
import hr.fer.zemris.nenr.ga.initializer.PopulationInitializerBinary;
import hr.fer.zemris.nenr.ga.mutator.BinaryMutator;
import hr.fer.zemris.nenr.ga.picker.RouletteWheelBinary;
import hr.fer.zemris.nenr.ga.selection.TournamentCannonSelection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static hr.fer.zemris.nenr.ga.functions.Functions.F6_GENERATOR;

public class Task4 {

    private final double MIN_VALUE = -50;
    private final double MAX_VALUE = 150;
    private final int MAX_ITERATION_COUNT = 100000;

    private double[] borders(int dim, double val) {
        var a = new double[dim];
        for (int i = 0; i < dim; i++) {
            a[i] = val;
        }
        return a;
    }

    @Test
    public void f6() {
        int bestPopulation = -1;
        double bestMedian = Double.POSITIVE_INFINITY;
        List<List<Double>> populations = new ArrayList<>();
        for (var population : List.of(30, 50, 100, 200, 100)) {
            List<Double> populationDependant = new ArrayList<>();
            populations.add(populationDependant);
            for (int i = 0; i < 50; i++) {
                var minRange = borders(3, MIN_VALUE);
                var maxRange = borders(3, MAX_VALUE);

                var populationInitializer = new PopulationInitializerBinary(population, minRange.length, minRange, maxRange, new int[]{6, 6, 6});
                var evaluator = new FunctionEvaluatorBinary(F6_GENERATOR.apply(3), minRange, maxRange);
                var breeder = new SidedAverageBreederBinary(0.8);
                var mutator = new BinaryMutator(0.01);
                var picker = new RouletteWheelBinary();
                var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);

                var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, true);
                geneticAlgorithm.train();
                populationDependant.add(geneticAlgorithm.getFittest().getFitness());
            }
            var median = median(populationDependant);
            if (median < bestMedian) {
                bestMedian = median;
                bestPopulation = population;
            }
        }
        System.out.println("bestPopulation" + bestPopulation);
        outputInAcceptableFormat(populations);

        bestMedian = Double.POSITIVE_INFINITY;
        double bestMutation = -1;
        List<List<Double>> mutations = new ArrayList<>();
        for (var mutation : List.of(0.01, 0.05, 0.1, 0.02, 0.3, 0.6, 0.9)) {
            List<Double> mutationDependant = new ArrayList<>();
            mutations.add(mutationDependant);
            for (int i = 0; i < 30; i++) {
                var minRange = borders(3, MIN_VALUE);
                var maxRange = borders(3, MAX_VALUE);

                var populationInitializer = new PopulationInitializerBinary(bestPopulation, minRange.length, minRange, maxRange, new int[]{6, 6, 6});
                var evaluator = new FunctionEvaluatorBinary(F6_GENERATOR.apply(3), minRange, maxRange);
                var breeder = new SidedAverageBreederBinary(0.8);
                var mutator = new BinaryMutator(mutation);
                var picker = new RouletteWheelBinary();
                var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);

                var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, true);
                geneticAlgorithm.train();
                mutationDependant.add(geneticAlgorithm.getFittest().getFitness());
            }
            var median = median(mutationDependant);
            if (median < bestMedian) {
                bestMedian = median;
                bestMutation = mutation;
            }
        }

        outputMutationsInAcceptableFormat(mutations);


        System.out.println(bestPopulation);
        System.out.println(bestMutation);

    }

    private double median(List<Double> list) {
        Collections.sort(list);
        double median;
        if (list.size() % 2 == 0)
            median = (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;
        else
            median = list.get(list.size() / 2);
        return median;
    }

    private void outputInAcceptableFormat(List<List<Double>> populations) {
        System.out.println("30,50,100,200,1000");
        for (int i = 0; i < populations.get(0).size(); i++) {
            System.out.println(populations.get(0).get(i) + "," + populations.get(1).get(i) + "," + populations.get(2).get(i) + "," + populations.get(3).get(i) + "," + populations.get(4).get(i));
        }
    }

    private void outputMutationsInAcceptableFormat(List<List<Double>> populations) {
        System.out.println("0.01,0.05,0.1,0.02,0.3,0.6,0.9");
        for (int i = 0; i < populations.get(0).size(); i++) {
            System.out.println(
                    populations.get(0).get(i) + "," +
                            populations.get(1).get(i) + "," +
                            populations.get(2).get(i) + "," +
                            populations.get(3).get(i) + "," +
                            populations.get(4).get(i) + "," +
                            populations.get(5).get(i) + "," +
                            populations.get(6).get(i));
        }
    }
}
