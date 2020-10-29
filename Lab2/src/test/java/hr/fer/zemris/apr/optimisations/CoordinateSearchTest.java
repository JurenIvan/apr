package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.function.AprFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Math.pow;

class CoordinateSearchTest {

    @Test
    void of() {
        AprFunction aprFunction3 = new AprFunction(x -> pow(x.get(0) - 1, 2) + pow(x.get(1) - 2, 2) + pow(x.get(2) - 3, 2));

        var result = CoordinateSearch.of(aprFunction3, Vector.parseSimple("0 0 0"), Vector.parseSimple("0.000001 0.000001 0.000001"));

        Assertions.assertTrue(new Vector(1, 2, 3).equals(result, 3));
    }

    @Test
    void of_1() {
        AprFunction aprFunction3 = new AprFunction(x -> pow(x.get(0) - 1, 2) + pow(x.get(1) - 2, 2) + pow(x.get(2) - 3, 2));

        var result = CoordinateSearch.of(aprFunction3, Vector.parseSimple("1 100 3"), Vector.parseSimple("0.000001 0.000001 0.000001"));

        Assertions.assertTrue(new Vector(1, 2, 3).equals(result, 3));
    }

    @Test
    void of_3() {
        AprFunction aprFunction3 = new AprFunction(x -> pow(x.get(0) - 1, 2) + pow(x.get(1) - 2, 2) + pow(x.get(2) - 3, 2));

        var result = CoordinateSearch.of(aprFunction3, Vector.parseSimple("-13 -12 5"), Vector.parseSimple("0.000001 0.000001 0.000001"));

        Assertions.assertTrue(new Vector(1, 2, 3).equals(result, 3));
    }

}
