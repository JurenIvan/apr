package hr.fer.zemris.apr.optimisations.functions;

import hr.fer.zemris.apr.math.matrix.Matrix;
import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;

import java.util.function.Function;

import static java.lang.Math.pow;

public class Functions {

    public static final Function<IVector, Double> F1 = x -> 100 * (pow(x.get(1) - pow(x.get(0), 2), 2)) + pow(1 - x.get(0), 2);
    public static final Function<IVector, IVector> F1_DERIVATIVE = x -> new Vector(400 * Math.pow(x.get(0), 3) + (2 - 400 * x.get(1)) * x.get(0) - 2, 200 * (x.get(1) - Math.pow(x.get(0), 2)));
    public static final Function<IVector, Matrix> F1_HESSIAN = x -> {
        var matrix = new Matrix(2, 2);

        matrix.set(0, 0, 1200 * Math.pow(x.get(0), 2) - 400 * x.get(1) + 2);
        matrix.set(0, 1, -400 * x.get(0));
        matrix.set(1, 0, -400 * x.get(0));
        matrix.set(1, 1, 200);

        return matrix;
    };

    public static final Function<IVector, Double> F2 = x -> pow(x.get(0) - 4, 2) + 4 * pow(x.get(1) - 2, 2);
    public static final Function<IVector, IVector> F2_DERIVATIVE = x -> new Vector(2 * (x.get(0) - 4), 8 * (x.get(1) - 2));
    public static final Function<IVector, Matrix> F2_HESSIAN = x -> {
        var matrix = new Matrix(2, 2);

        matrix.set(0, 0, 2);
        matrix.set(0, 1, 0);
        matrix.set(1, 0, 0);
        matrix.set(1, 1, 8);

        return matrix;
    };

    public static final Function<IVector, Double> F3 = x -> pow(x.get(0) - 2, 2) + pow(x.get(1) + 3, 2);
    public static final Function<IVector, IVector> F3_DERIVATIVE = x -> new Vector(2 * (x.get(0) - 2), 2 * (x.get(1) + 3));
    public static final Function<IVector, Matrix> F3_HESSIAN = x -> {
        var matrix = new Matrix(2, 2);

        matrix.set(0, 0, 2);
        matrix.set(0, 1, 0);
        matrix.set(1, 0, 0);
        matrix.set(1, 1, 2);

        return matrix;
    };

    public static final Function<IVector, Double> F4 = x -> pow(x.get(0) - 3, 2) + 4 * pow(x.get(1), 2);
    public static final Function<IVector, IVector> F4_DERIVATIVE = x -> new Vector(2 * (x.get(0) - 3), 2 * x.get(1));
    public static final Function<IVector, Matrix> F4_HESSIAN = x -> {
        var matrix = new Matrix(2, 2);

        matrix.set(0, 0, 2);
        matrix.set(0, 1, 0);
        matrix.set(1, 0, 0);
        matrix.set(1, 1, 2);

        return matrix;
    };

}
