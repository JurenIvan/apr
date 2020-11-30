package hr.fer.zemris.apr.optimisations.search;

import hr.fer.zemris.apr.math.matrix.Matrix;
import hr.fer.zemris.apr.math.matrix.inverse.InverseStrategies;
import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.optimisations.GoldenCut;
import hr.fer.zemris.apr.optimisations.UniModalInterval;

import java.util.List;
import java.util.function.Function;

public class NewtonRaphsonSearch implements SearchAlgorithm {

    private final Function<IVector, Double> function;
    private final Function<IVector, IVector> derivation;
    private final List<Function<IVector, IVector>> hessian;
    private final boolean useGoldenCut;

    public NewtonRaphsonSearch(Function<IVector, Double> function, Function<IVector, IVector> derivation, List<Function<IVector, IVector>> hessian, boolean useGoldenCut) {
        this.function = function;
        this.derivation = derivation;
        this.hessian = hessian;
        this.useGoldenCut = useGoldenCut;
    }

    @Override
    public IVector search(IVector x0, double eps) {
        IVector result = x0.copy();
        for (int i = 0; i < 1000; i++) {
            var gradient = InverseStrategies.LUP_DECOMPOSITION_INVERSE.inverseOf(calculateHessian(hessian, result)).nMultiply(derivation.apply(result).toColumnMatrix(false)).toVector(true);
            if (useGoldenCut)
                result = GoldenCut.getMiddleOfInterval(function, UniModalInterval.of(function, result, gradient), eps);
            else
                result.nSub(gradient);
        }
        return result;
    }

    private Matrix calculateHessian(List<Function<IVector, IVector>> hessian, IVector x0) {
        var matrix = new Matrix(hessian.size(), hessian.size());

        for (int i = 0; i < hessian.size(); i++) {
            var line = hessian.get(i).apply(x0);
            for (int j = 0; j < hessian.size(); j++) {
                matrix.set(i, j, line.get(j));
            }
        }

        return matrix;
    }
}
