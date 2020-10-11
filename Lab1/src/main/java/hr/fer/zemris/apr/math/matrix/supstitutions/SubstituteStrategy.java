package hr.fer.zemris.apr.math.matrix.supstitutions;

import hr.fer.zemris.apr.math.matrix.IMatrix;
import hr.fer.zemris.apr.math.vector.IVector;

public interface SubstituteStrategy {

    IVector substitute(IMatrix matrix, IVector vector);
}
