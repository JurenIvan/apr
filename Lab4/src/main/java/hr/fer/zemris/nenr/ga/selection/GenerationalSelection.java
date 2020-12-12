package hr.fer.zemris.nenr.ga.selection;

import hr.fer.zemris.nenr.ga.domain.InstanceDouble;
import hr.fer.zemris.nenr.ga.mutator.Mutator;

import java.util.List;

import static java.util.Comparator.comparing;

/**
 * Leaves eliminationFactor percentage(n) of Instances and rest replaces with best (n)
 */
public class GenerationalSelection implements Selection<InstanceDouble> {

    private final double eliminationFactor;
    private final Mutator<InstanceDouble> mutator;

    public GenerationalSelection(Mutator<InstanceDouble> mutator, double eliminationFactor) {
        this.eliminationFactor = eliminationFactor;
        this.mutator = mutator;
    }

    @Override
    public void doSelection(List<InstanceDouble> population) {
        population.sort(comparing(InstanceDouble::getFitness));
        int index = 0;
        for (int i = (int) Math.floor(population.size() * eliminationFactor); i < population.size(); i++) {
            var instance = new InstanceDouble(population.get(index++).getChromosomes().clone());
            mutator.mutate(instance);
            population.set(i, instance);
        }
    }
}
