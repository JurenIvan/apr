package hr.fer.zemris.nenr.ga.selection;

import hr.fer.zemris.nenr.ga.breeder.Breeder;
import hr.fer.zemris.nenr.ga.domain.InstanceDouble;
import hr.fer.zemris.nenr.ga.evaluator.Evaluator;
import hr.fer.zemris.nenr.ga.mutator.Mutator;
import hr.fer.zemris.nenr.ga.picker.RouletteWheel;

import java.util.List;

import static java.lang.Math.max;

/**
 * Select numberOfTournaments of pairs of parents. For each pair replace worse parent with child if child is better than worse parent.
 */
public class TournamentRouletteSelection implements Selection<InstanceDouble> {

    private final Breeder<InstanceDouble> breeder;
    private final Evaluator<InstanceDouble> evaluator;
    private final Mutator<InstanceDouble> mutator;
    private final int numberOfTournaments;

    public TournamentRouletteSelection(Breeder<InstanceDouble> breeder, Evaluator<InstanceDouble> evaluator, Mutator<InstanceDouble> mutator, int numberOfTournaments) {
        this.breeder = breeder;
        this.evaluator = evaluator;
        this.mutator = mutator;
        this.numberOfTournaments = numberOfTournaments;
    }

    @Override
    public void doSelection(List<InstanceDouble> population) {
        RouletteWheel rouletteWheel = new RouletteWheel();

        for (int i = 0; i < numberOfTournaments; i++) {
            int first = rouletteWheel.pickOne();
            int second = rouletteWheel.pickOne();

            InstanceDouble dad = population.get(first);
            InstanceDouble mom = population.get(second);

            InstanceDouble child = breeder.mate(dad, mom);
            mutator.mutate(child);
            child.setFitness(evaluator.evaluate(child));

            if (child.getFitness() > max(dad.getFitness(), mom.getFitness())) return;
            if (dad.getFitness() > max(child.getFitness(), mom.getFitness())) {
                population.set(first, child);
                continue;
            }
            if (mom.getFitness() > max(dad.getFitness(), child.getFitness())) {
                population.set(second, child);
            }
        }
    }
}
