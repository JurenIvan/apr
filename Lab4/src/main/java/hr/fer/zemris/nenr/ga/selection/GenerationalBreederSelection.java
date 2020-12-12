package hr.fer.zemris.nenr.ga.selection;

import hr.fer.zemris.nenr.ga.breeder.Breeder;
import hr.fer.zemris.nenr.ga.domain.InstanceDouble;
import hr.fer.zemris.nenr.ga.mutator.Mutator;
import hr.fer.zemris.nenr.ga.picker.Picker;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.min;
import static java.util.Comparator.comparing;

/**
 * Selects 2n instances using picker and creates n children which replaces current population.
 */
public class GenerationalBreederSelection implements Selection<InstanceDouble> {

    private final Mutator<InstanceDouble> mutator;
    private final Picker<InstanceDouble> picker;
    private final Breeder<InstanceDouble> breeder;
    private final boolean elitism;

    public GenerationalBreederSelection(Mutator<InstanceDouble> mutator, Picker<InstanceDouble> instancePicker, Breeder<InstanceDouble> breeder, boolean elitism) {
        this.mutator = mutator;
        this.picker = instancePicker;
        this.breeder = breeder;
        this.elitism = elitism;
    }

    @Override
    public void doSelection(List<InstanceDouble> population) {
        picker.configure(population);
        List<InstanceDouble> parents = new ArrayList<>(population.size() * 2);
        for (int i = 0; i < population.size() * 2; i++) {
            parents.add(population.get(picker.pickOne()));
        }
        int populationSize = population.size();
        if (elitism) {
            InstanceDouble best = min(population, comparing(InstanceDouble::getFitness));
            population.clear();
            population.add(best);
            populationSize--;
        } else {
            population.clear();
        }
        for (int i = 0; i < populationSize; i++) {
            var child = breeder.mate(parents.get(2 * i), parents.get(2 * i + 1));
            mutator.mutate(child);
            population.add(child);
        }
    }
}
