package hr.fer.zemris.apr.math.matrix.supstitutions;

import hr.fer.zemris.apr.math.vector.Vector;

public class SubstitutionStrategies {

    public static SubstituteStrategy FORWARD = (lowerMatrix, b) -> {
        int dim = lowerMatrix.getColsCount();
        double[] y = new double[dim];
        for (int i = 0; i < dim; i++) {
            y[i] = b.get(i);
            for (int j = 0; j < i; j++) {
                y[i] -= lowerMatrix.get(i, j) * y[j];
            }
        }

        return new Vector(y);
    };

    public static SubstituteStrategy BACKWARD = (upperMatrix, y) -> {
        int dim = upperMatrix.getColsCount();
        double[] x = new double[dim];
        for (int i = dim - 1; i >= 0; i--) {
            x[i] = y.get(i);
            for (int j = dim - 1; j > i; j--) {
                x[i] -= upperMatrix.get(i, j) * x[j];
            }
            x[i] /= upperMatrix.get(i, i);
        }

        return new Vector(x);
    };
}
