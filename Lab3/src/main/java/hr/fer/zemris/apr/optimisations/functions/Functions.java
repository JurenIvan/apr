package hr.fer.zemris.apr.optimisations.functions;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;

import java.util.function.Function;

import static java.lang.Math.pow;

public class Functions {

    public static final Function<IVector, Double> F1 = x -> 100 * (pow(x.get(1) - pow(x.get(0), 2), 2) + pow(1 - x.get(0), 2));

    public static final Function<IVector, Double> F2 = x -> pow(x.get(0) - 4, 2) + 4 * pow(x.get(1) - 2, 2);
    public static final Function<IVector, Double> F3 = x -> pow(x.get(0) - 2, 2) + 4 * pow(x.get(1) + 3, 2);
    public static final Function<IVector, Double> F4 = x -> pow(x.get(0) - 3, 2) + 4 * pow(x.get(1), 2);

    public static final Function<IVector, IVector> F3_DERIVATIVE = x -> new Vector(2 * (x.get(0) - 2), 2 * x.get(1));
}
