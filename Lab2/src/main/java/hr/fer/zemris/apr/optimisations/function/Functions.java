package hr.fer.zemris.apr.optimisations.function;

import hr.fer.zemris.apr.math.vector.IVector;

import java.util.function.Function;

import static java.lang.Math.*;
import static java.util.stream.IntStream.of;

public class Functions {

    public static final Function<IVector, Double> F1 = x -> 100 * (pow(x.get(1) - pow(x.get(0), 2), 2) + pow(1 - x.get(0), 2));
    public static final Function<IVector, Double> F2 = x -> pow(x.get(0) - 4, 2) + 4 * pow(x.get(1), 2);
    public static final Function<IVector, Double> F4 = x -> abs((x.get(0) - x.get(1)) * (x.get(0) + x.get(1))) + sqrt(pow(x.get(0), 2) + pow(x.get(1), 2));

    public static final Function<Integer, Function<IVector, Double>> F3_SUPPLIER = order -> (Function<IVector, Double>) x -> {
        double sum = 0;
        for (int i = 0; i < order; i++) {
            sum += pow(x.get(i) - i, 2);
        }

        return sum;
    };

    public static final Function<Integer, Function<IVector, Double>> F6_SUPPLIER = order -> (Function<IVector, Double>) x -> {
        double sumOfSquares = of(order - 1).mapToDouble(i -> pow(x.get(i), 2)).sum();
        return 0.5 + (pow(sin(sqrt(sumOfSquares)), 2) - 0.5) / pow(1 + 0.001 * sumOfSquares, 2);
    };
}
