package hr.fer.zemris.nenr.ga;

import hr.fer.zemris.nenr.ga.breeder.SidedAverageBreederBinary;
import hr.fer.zemris.nenr.ga.evaluator.FunctionEvaluatorBinary;
import hr.fer.zemris.nenr.ga.initializer.PopulationInitializerBinary;
import hr.fer.zemris.nenr.ga.mutator.BinaryMutator;
import hr.fer.zemris.nenr.ga.picker.RandomBinaryPicker;
import hr.fer.zemris.nenr.ga.picker.RouletteWheelBinary;
import hr.fer.zemris.nenr.ga.selection.TournamentNSelection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static hr.fer.zemris.nenr.ga.functions.Functions.F6_GENERATOR;

public class Task5 {

    private final double MIN_VALUE = -50;
    private final double MAX_VALUE = 150;
    private final int MAX_ITERATION_COUNT = 10000;

    private double[] borders(int dim, double val) {
        var a = new double[dim];
        for (int i = 0; i < dim; i++) {
            a[i] = val;
        }
        return a;
    }

    @Test
    public void tournament_with_roulete() {
        var minRange = borders(3, MIN_VALUE);
        var maxRange = borders(3, MAX_VALUE);
        List<List<Double>> tournaments = new ArrayList<>();
        for (int i = 3; i < 20; i++) {
            List<Double> tournament = new ArrayList<>();
            tournaments.add(tournament);
            for (int j = 0; j < 30; j++) {
                var populationInitializer = new PopulationInitializerBinary(100, minRange.length, minRange, maxRange, new int[]{6, 6, 6});
                var evaluator = new FunctionEvaluatorBinary(F6_GENERATOR.apply(3), minRange, maxRange);
                var breeder = new SidedAverageBreederBinary(0.8);
                var mutator = new BinaryMutator(0.01);
                var picker = new RouletteWheelBinary();
                var selector = new TournamentNSelection<>(breeder, evaluator, mutator, picker, 10, i);

                var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, true);
                geneticAlgorithm.train();
                tournament.add(geneticAlgorithm.getFittest().getFitness());
            }
        }
        outputMutationsInAcceptableFormat(tournaments);
    }

    @Test
    public void tournament_with_no_roulete() {
        var minRange = borders(3, MIN_VALUE);
        var maxRange = borders(3, MAX_VALUE);
        List<List<Double>> tournaments = new ArrayList<>();
        for (int i = 3; i < 20; i++) {
            List<Double> tournament = new ArrayList<>();
            tournaments.add(tournament);
            for (int j = 0; j < 30; j++) {
                var populationInitializer = new PopulationInitializerBinary(100, minRange.length, minRange, maxRange, new int[]{6, 6, 6});
                var evaluator = new FunctionEvaluatorBinary(F6_GENERATOR.apply(3), minRange, maxRange);
                var breeder = new SidedAverageBreederBinary(0.8);
                var mutator = new BinaryMutator(0.01);
                var picker = new RandomBinaryPicker();
                var selector = new TournamentNSelection<>(breeder, evaluator, mutator, picker, 10, i);

                var geneticAlgorithm = new GeneticAlgorithm<>(mutator, evaluator, populationInitializer, selector, MAX_ITERATION_COUNT, true);
                geneticAlgorithm.train();
                tournament.add(geneticAlgorithm.getFittest().getFitness());
            }
        }
        outputMutationsInAcceptableFormat(tournaments);
    }

    private void outputMutationsInAcceptableFormat(List<List<Double>> kTournaments) {
        System.out.println(IntStream.range(3, 20).mapToObj(String::valueOf).collect(Collectors.joining(",")));
        for (int i = 0; i < kTournaments.get(0).size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 16; j++) {
                sb.append(kTournaments.get(j).get(i)).append(',');
            }
            sb.append(kTournaments.get(16).get(i));
            System.out.println(sb.toString());
        }
    }
}
