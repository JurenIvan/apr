package hr.fer.zemris.apr.optimisations.search;

import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.function.AprFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Math.pow;

class SimplexSearchTest {

    @Test
    void Simplex_test_1() {
        AprFunction aprFunction3 = new AprFunction(x -> pow(x.get(0) - 1, 2) + pow(x.get(1) - 2, 2) + pow(x.get(2) - 3, 2));

        var simplex = new SimplexSearch(aprFunction3);
        var result = simplex.search(Vector.parseSimple("0 0 0"), 1e-6);

        Assertions.assertTrue(new Vector(1, 2, 3).equals(result, 3));
    }

    @Test
    void Simplex_test_2() {
        AprFunction aprFunction3 = new AprFunction(x -> pow(x.get(0) - 1, 2) + pow(x.get(1) - 2, 2) + pow(x.get(2) - 3, 2));

        var simplex = new SimplexSearch(aprFunction3);
        var result = simplex.search(Vector.parseSimple("-23 2 4"), 1e-6);

        Assertions.assertTrue(new Vector(1, 2, 3).equals(result, 3));
    }

    @Test
    void Simplex_test_3() {
        AprFunction aprFunction3 = new AprFunction(x -> pow(x.get(0) - 1, 2) + pow(x.get(1) - 2, 2) + pow(x.get(2) - 3, 2));

        var simplex = new SimplexSearch(aprFunction3);
        var result = simplex.search(Vector.parseSimple("100 100 10"), 1e-6);

        Assertions.assertTrue(new Vector(1, 2, 3).equals(result, 3));
    }
}
