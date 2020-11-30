package hr.fer.zemris.apr.optimisations.search;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.optimisations.GoldenCut;
import hr.fer.zemris.apr.optimisations.UniModalInterval;

import java.util.function.Function;

public class GradientSearch implements SearchAlgorithm {

    private final Function<IVector, Double> function;
    private final Function<IVector, IVector> derivation;
    private final boolean useGoldenCut;
    private final int iterationCount;

    public GradientSearch(Function<IVector, Double> function, Function<IVector, IVector> derivation, boolean useGoldenCut, int iterationCount) {
        this.function = function;
        this.derivation = derivation;
        this.useGoldenCut = useGoldenCut;
        this.iterationCount = iterationCount;
    }

    @Override
    public IVector search(IVector x0, double eps) {
        IVector result = x0.copy();
        int remainingIterations = iterationCount;
        for (IVector gradient = derivation.apply(result); gradient.norm() > eps && remainingIterations-- >= 0; gradient = derivation.apply(result)) {

            if (useGoldenCut) {
                result = GoldenCut.getMiddleOfInterval(function, UniModalInterval.of(function, x0, gradient), eps);
            } else {
                result.sub(gradient);
            }

        }

        return result;
    }
}
