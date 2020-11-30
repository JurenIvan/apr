package hr.fer.zemris.apr.optimisations.search;

import hr.fer.zemris.apr.math.matrix.Matrix;
import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.optimisations.GeneralGoldenCut;
import hr.fer.zemris.apr.optimisations.GeneralUniModalInterval;

import java.util.function.Function;

import static hr.fer.zemris.apr.math.matrix.inverse.InverseStrategies.LUP_DECOMPOSITION_INVERSE;

public class NewtonRaphsonSearch implements SearchAlgorithm {

    private final Function<IVector, Double> function;
    private final Function<IVector, IVector> derivation;
    private final Function<IVector, Matrix> hessian;
    private final boolean useGoldenCut;
    private final int iterationCap;

    public NewtonRaphsonSearch(Function<IVector, Double> function, Function<IVector, IVector> derivation, Function<IVector, Matrix> hessian, boolean useGoldenCut, int iterationCap) {
        this.function = function;
        this.derivation = derivation;
        this.hessian = hessian;
        this.useGoldenCut = useGoldenCut;
        this.iterationCap = iterationCap;
    }

    @Override
    public IVector search(IVector x0, double eps) {
        IVector result = x0.copy();
        for (int i = 0; i < iterationCap; i++) {
            var gradient = LUP_DECOMPOSITION_INVERSE.inverseOf(hessian.apply(result)).nMultiply(derivation.apply(result).toColumnMatrix(false)).toVector(true);
            if (gradient.norm() < eps) return result;
            if (useGoldenCut)
                result = GeneralGoldenCut.getMiddleOfInterval(function, GeneralUniModalInterval.of(function, result, gradient), eps);
            else
                result.nSub(gradient);
        }
        return result;
    }
}
