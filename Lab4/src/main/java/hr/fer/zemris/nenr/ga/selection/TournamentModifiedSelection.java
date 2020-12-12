package hr.fer.zemris.nenr.ga.selection;

import hr.fer.zemris.nenr.ga.breeder.Breeder;
import hr.fer.zemris.nenr.ga.domain.InstanceDouble;
import hr.fer.zemris.nenr.ga.evaluator.Evaluator;
import hr.fer.zemris.nenr.ga.mutator.Mutator;

import java.util.List;

import static java.util.Comparator.comparing;

/**
 * Replaces worst ones with children of fist 2/3 only if child is better than one of parents
 */
public class TournamentModifiedSelection implements Selection<InstanceDouble> {

    private final Breeder<InstanceDouble> breeder;
    private final Evaluator<InstanceDouble> evaluator;
    private final Mutator<InstanceDouble> mutator;

    public TournamentModifiedSelection(Breeder<InstanceDouble> breeder, Evaluator<InstanceDouble> evaluator, Mutator<InstanceDouble> mutator) {
        this.breeder = breeder;
        this.evaluator = evaluator;
        this.mutator = mutator;
    }

    @Override
    public void doSelection(List<InstanceDouble> population) {
        population.sort(comparing(InstanceDouble::getFitness));
        int replaced = 1;
        for (int i = 0; i < population.size() / 3; i++) {
            InstanceDouble dad = population.get(2 * i);
            InstanceDouble mom = population.get(2 * i + 1);

            InstanceDouble child = breeder.mate(dad, mom);
            mutator.mutate(child);
            child.setFitness(evaluator.evaluate(child));
            if (child.getFitness() < Math.max(dad.getFitness(), mom.getFitness()))
                population.set(population.size() - replaced++, child);
        }
    }
}
