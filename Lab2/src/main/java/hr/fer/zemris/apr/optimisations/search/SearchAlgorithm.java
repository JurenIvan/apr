package hr.fer.zemris.apr.optimisations.search;

import hr.fer.zemris.apr.math.vector.IVector;

public interface SearchAlgorithm {

    IVector search(IVector x0, double eps);
}
