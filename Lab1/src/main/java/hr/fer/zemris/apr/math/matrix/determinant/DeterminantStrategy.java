package hr.fer.zemris.apr.math.matrix.determinant;

import hr.fer.zemris.apr.math.matrix.IMatrix;

public interface DeterminantStrategy {

    double calculateDeterminant(IMatrix abstractMatrix);
}
