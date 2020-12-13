package hr.fer.zemris.nenr.ga;

import hr.fer.zemris.nenr.ga.breeder.SidedAverageBreeder;
import hr.fer.zemris.nenr.ga.breeder.SidedAverageBreederBinary;
import hr.fer.zemris.nenr.ga.evaluator.FunctionEvaluatorBinary;
import hr.fer.zemris.nenr.ga.evaluator.FunctionEvaluatorDouble;
import hr.fer.zemris.nenr.ga.initializer.PopulationInitializerBinary;
import hr.fer.zemris.nenr.ga.initializer.PopulationInitializerDouble;
import hr.fer.zemris.nenr.ga.mutator.BinaryMutator;
import hr.fer.zemris.nenr.ga.mutator.NormalNoiseMutator;
import hr.fer.zemris.nenr.ga.picker.RouletteWheel;
import hr.fer.zemris.nenr.ga.picker.RouletteWheelBinary;
import hr.fer.zemris.nenr.ga.selection.TournamentCannonSelection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static hr.fer.zemris.nenr.ga.functions.Functions.F6_GENERATOR;
import static hr.fer.zemris.nenr.ga.functions.Functions.F7_GENERATOR;

public class Task3 {

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
        List<Double> fitnessDouble3 = new ArrayList<>();
        List<Double> fitnessDouble6 = new ArrayList<>();
        List<Double> fitnessBinary3 = new ArrayList<>();
        List<Double> fitnessBinary6 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            var populationInitializer = new PopulationInitializerDouble(100,
                    borders(3, MIN_VALUE),
                    borders(3, MAX_VALUE));

            SidedAverageBreeder breeder = new SidedAverageBreeder(0.8);
            var mutator = new NormalNoiseMutator(1, 0.1);
            var evaluator = new FunctionEvaluatorDouble(F6_GENERATOR.apply(3));

            var picker = new RouletteWheel();   //doest work with random picker
            var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);
            var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, false);

            geneticAlgorithm.train();
            fitnessDouble3.add(geneticAlgorithm.getFittest().getFitness());
        }
        for (int i = 0; i < 30; i++) {
            var populationInitializer = new PopulationInitializerDouble(100,
                    borders(6, MIN_VALUE),
                    borders(6, MAX_VALUE));

            SidedAverageBreeder breeder = new SidedAverageBreeder(0.8);
            var mutator = new NormalNoiseMutator(1, 0.1);
            var evaluator = new FunctionEvaluatorDouble(F6_GENERATOR.apply(6));

            var picker = new RouletteWheel();   //doest work with random picker
            var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);
            var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, false);

            geneticAlgorithm.train();
            fitnessDouble6.add(geneticAlgorithm.getFittest().getFitness());
        }
        for (int i = 0; i < 30; i++) {
            var minRange = borders(3, MIN_VALUE);
            var maxRange = borders(3, MAX_VALUE);

            var populationInitializer = new PopulationInitializerBinary(100, minRange.length, minRange, maxRange, new int[]{4, 4, 4});
            var evaluator = new FunctionEvaluatorBinary(F6_GENERATOR.apply(3), minRange, maxRange);
            var breeder = new SidedAverageBreederBinary(0.8);
            var mutator = new BinaryMutator(0.01);
            var picker = new RouletteWheelBinary();
            var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);

            var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, true);
            geneticAlgorithm.train();
            fitnessBinary3.add(geneticAlgorithm.getFittest().getFitness());
        }
        for (int i = 0; i < 30; i++) {
            var minRange = borders(6, MIN_VALUE);
            var maxRange = borders(6, MAX_VALUE);

            var populationInitializer = new PopulationInitializerBinary(100, minRange.length, minRange, maxRange, new int[]{4, 4, 4, 4, 4, 4});
            var evaluator = new FunctionEvaluatorBinary(F6_GENERATOR.apply(6), minRange, maxRange);
            var breeder = new SidedAverageBreederBinary(0.8);
            var mutator = new BinaryMutator(0.01);
            var picker = new RouletteWheelBinary();
            var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);

            var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, true);
            geneticAlgorithm.train();
            fitnessBinary6.add(geneticAlgorithm.getFittest().getFitness());
        }

        outputInAcceptableFormat(fitnessBinary3, fitnessBinary6, fitnessDouble3, fitnessDouble6);

        calculateStatistics(fitnessBinary3, "fitnessBinary3");
        calculateStatistics(fitnessBinary6, "fitnessBinary6");
        calculateStatistics(fitnessDouble3, "fitnessDouble3");
        calculateStatistics(fitnessDouble6, "fitnessDouble6");
    }

    private void outputInAcceptableFormat(List<Double> fitnessBinary3, List<Double> fitnessBinary6, List<Double> fitnessDouble3, List<Double> fitnessDouble6) {
        System.out.println("fitnessBinary3,fitnessBinary6,fitnessDouble3,fitnessDouble6");
        for (int i = 0; i < fitnessBinary3.size(); i++) {
            System.out.println(fitnessBinary3.get(i) + "," + fitnessBinary6.get(i) + "," + fitnessDouble3.get(i) + "," + fitnessDouble6.get(i));
        }
    }

    @Test
    public void f7() {
        List<Double> fitnessDouble3 = new ArrayList<>();
        List<Double> fitnessDouble6 = new ArrayList<>();
        List<Double> fitnessBinary3 = new ArrayList<>();
        List<Double> fitnessBinary6 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            var populationInitializer = new PopulationInitializerDouble(100,
                    borders(3, MIN_VALUE),
                    borders(3, MAX_VALUE));

            SidedAverageBreeder breeder = new SidedAverageBreeder(0.8);
            var mutator = new NormalNoiseMutator(1, 0.1);
            var evaluator = new FunctionEvaluatorDouble(F7_GENERATOR.apply(3));

            var picker = new RouletteWheel();   //doest work with random picker
            var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);
            var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, false);

            geneticAlgorithm.train();
            fitnessDouble3.add(geneticAlgorithm.getFittest().getFitness());
        }
        for (int i = 0; i < 30; i++) {
            var populationInitializer = new PopulationInitializerDouble(100,
                    borders(6, MIN_VALUE),
                    borders(6, MAX_VALUE));

            SidedAverageBreeder breeder = new SidedAverageBreeder(0.8);
            var mutator = new NormalNoiseMutator(1, 0.1);
            var evaluator = new FunctionEvaluatorDouble(F7_GENERATOR.apply(6));

            var picker = new RouletteWheel();   //doest work with random picker
            var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);
            var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, false);

            geneticAlgorithm.train();
            fitnessDouble6.add(geneticAlgorithm.getFittest().getFitness());
        }
        for (int i = 0; i < 30; i++) {
            var minRange = borders(3, MIN_VALUE);
            var maxRange = borders(3, MAX_VALUE);

            var populationInitializer = new PopulationInitializerBinary(100, minRange.length, minRange, maxRange, new int[]{4, 4, 4});
            var evaluator = new FunctionEvaluatorBinary(F7_GENERATOR.apply(3), minRange, maxRange);
            var breeder = new SidedAverageBreederBinary(0.8);
            var mutator = new BinaryMutator(0.01);
            var picker = new RouletteWheelBinary();
            var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);

            var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, true);
            geneticAlgorithm.train();
            fitnessBinary3.add(geneticAlgorithm.getFittest().getFitness());
        }
        for (int i = 0; i < 30; i++) {
            var minRange = borders(6, MIN_VALUE);
            var maxRange = borders(6, MAX_VALUE);

            var populationInitializer = new PopulationInitializerBinary(100, minRange.length, minRange, maxRange, new int[]{4, 4, 4, 4, 4, 4});
            var evaluator = new FunctionEvaluatorBinary(F7_GENERATOR.apply(6), minRange, maxRange);
            var breeder = new SidedAverageBreederBinary(0.8);
            var mutator = new BinaryMutator(0.01);
            var picker = new RouletteWheelBinary();
            var selector = new TournamentCannonSelection<>(breeder, evaluator, mutator, picker, 10);

            var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, true);
            geneticAlgorithm.train();
            fitnessBinary6.add(geneticAlgorithm.getFittest().getFitness());
        }

        outputInAcceptableFormat(fitnessBinary3, fitnessBinary6, fitnessDouble3, fitnessDouble6);

        calculateStatistics(fitnessBinary3, "fitnessBinary3");
        calculateStatistics(fitnessBinary6, "fitnessBinary6");
        calculateStatistics(fitnessDouble3, "fitnessDouble3");
        calculateStatistics(fitnessDouble6, "fitnessDouble6");
    }

    private void calculateStatistics(List<Double> list, String title) {
        System.out.println(title);
        System.out.println("min:    " + list.stream().min(Double::compareTo).get());
        System.out.println("max:    " + list.stream().max(Double::compareTo).get());
        System.out.println("median: " + median(list));
        System.out.println("average:" + average(list));
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

    private double average(List<Double> list) {
        double sum = list.stream().mapToDouble(e -> e).sum();
        return sum / list.size();
    }
}
