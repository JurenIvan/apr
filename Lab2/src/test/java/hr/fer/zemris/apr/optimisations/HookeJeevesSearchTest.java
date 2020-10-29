package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.function.AprFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Math.pow;

class HookeJeevesSearchTest {

    @Test
    void hookeJeevesTest1() {
        AprFunction aprFunction3 = new AprFunction(x -> pow(x.get(0) - 1, 2) + pow(x.get(1) - 2, 2) + pow(x.get(2) - 3, 2));

        var simplex = new HookeJeevesSearch(aprFunction3);
        var result = simplex.of(Vector.parseSimple("0 0 0"), 0.5, 0.000001);

        Assertions.assertTrue(new Vector(1, 2, 3).equals(result, 3));
    }

    @Test
    void hookeJeevesTest2() {
        AprFunction aprFunction3 = new AprFunction(x -> pow(x.get(0) - 1, 2) + pow(x.get(1) - 2, 2) + pow(x.get(2) - 3, 2));

        var simplex = new HookeJeevesSearch(aprFunction3);
        var result = simplex.of(Vector.parseSimple("-23 2 4"), 0.5, 0.000001);

        Assertions.assertTrue(new Vector(1, 2, 3).equals(result, 3));
    }

    @Test
    void hookeJeevesTest3() {
        AprFunction aprFunction3 = new AprFunction(x -> pow(x.get(0) - 1, 2) + pow(x.get(1) - 2, 2) + pow(x.get(2) - 3, 2));

        var simplex = new HookeJeevesSearch(aprFunction3);
        var result = simplex.of(Vector.parseSimple("-13 -12 5"), 0.5, 0.000001);

        Assertions.assertTrue(new Vector(1, 2, 3).equals(result, 3));
    }

}
