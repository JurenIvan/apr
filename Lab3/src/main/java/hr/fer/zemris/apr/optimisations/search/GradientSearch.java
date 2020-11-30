package hr.fer.zemris.apr.optimisations.search;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.optimisations.GoldenCut;
import hr.fer.zemris.apr.optimisations.UniModalInterval;

import java.util.function.Function;

public class GradientSearch implements SearchAlgorithm {

    private final Function<IVector, Double> function;
    private final Function<IVector, IVector> derivation;
    private final boolean useGoldenCut;

    public GradientSearch(Function<IVector, Double> function, Function<IVector, IVector> derivation, boolean useGoldenCut) {
        this.function = function;
        this.derivation = derivation;
        this.useGoldenCut = useGoldenCut;
    }

    @Override
    public IVector search(IVector x0, double eps) {
        IVector result = x0.copy();
        for (IVector gradient = derivation.apply(x0); gradient.norm() < eps; gradient = derivation.apply(x0)) {
            if (useGoldenCut) {
                result = GoldenCut.getMiddleOfInterval(function, UniModalInterval.of(function, x0, gradient), eps);
            } else {
                result.add(gradient);
            }
        }

        return result;
    }
}
