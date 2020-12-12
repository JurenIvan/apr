package hr.fer.zemris.nenr.ga.selection;

import hr.fer.zemris.nenr.ga.breeder.Breeder;
import hr.fer.zemris.nenr.ga.domain.InstanceDouble;
import hr.fer.zemris.nenr.ga.evaluator.Evaluator;
import hr.fer.zemris.nenr.ga.mutator.Mutator;

import java.util.List;

import static java.lang.Math.max;
import static java.util.Comparator.comparing;

/**
 * Replaces worst n/3 intances with children of best 2/3
 */
public class TournamentSelection implements Selection<InstanceDouble> {

    private final Breeder<InstanceDouble> breeder;
    private final Evaluator<InstanceDouble> evaluator;
    private final Mutator<InstanceDouble> mutator;

    public TournamentSelection(Breeder<InstanceDouble> breeder, Evaluator<InstanceDouble> evaluator, Mutator<InstanceDouble> mutator) {
        this.breeder = breeder;
        this.evaluator = evaluator;
        this.mutator = mutator;
    }

    @Override
    public void doSelection(List<InstanceDouble> population) {
        population.sort(comparing(InstanceDouble::getFitness));
        for (int i = 0; i < population.size() / 2; i++) {
            InstanceDouble dad = population.get(2 * i);
            InstanceDouble mom = population.get(2 * i + 1);

            InstanceDouble child = breeder.mate(dad, mom);
            mutator.mutate(child);
            child.setFitness(evaluator.evaluate(child));
            if (child.getFitness() > max(dad.getFitness(), mom.getFitness())) return;
            if (dad.getFitness() > max(child.getFitness(), mom.getFitness())) population.set(2 * i, child);
            if (mom.getFitness() > max(dad.getFitness(), child.getFitness())) population.set(2 * i + 1, child);
        }
    }
}
