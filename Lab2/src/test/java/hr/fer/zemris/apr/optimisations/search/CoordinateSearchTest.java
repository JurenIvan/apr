package hr.fer.zemris.apr.optimisations.search;

import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.function.AprFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Math.pow;

class CoordinateSearchTest {

    @Test
    void coordinateSearchTest1() {
        AprFunction aprFunction3 = new AprFunction(x -> pow(x.get(0) - 1, 2) + pow(x.get(1) - 2, 2) + pow(x.get(2) - 3, 2));

        var coordinateSearch = new CoordinateSearch(aprFunction3);
        var result = coordinateSearch.search(Vector.parseSimple("0 0 0"), 1e-6);

        Assertions.assertTrue(new Vector(1, 2, 3).equals(result, 3));
    }

    @Test
    void coordinateSearchTest2() {
        AprFunction aprFunction3 = new AprFunction(x -> pow(x.get(0) - 1, 2) + pow(x.get(1) - 2, 2) + pow(x.get(2) - 3, 2));

        var coordinateSearch = new CoordinateSearch(aprFunction3);
        var result = coordinateSearch.search(Vector.parseSimple("1 100 3"), 1e-6);

        Assertions.assertTrue(new Vector(1, 2, 3).equals(result, 3));
    }

    @Test
    void coordinateSearchTest3() {
        AprFunction aprFunction3 = new AprFunction(x -> pow(x.get(0) - 1, 2) + pow(x.get(1) - 2, 2) + pow(x.get(2) - 3, 2));

        var coordinateSearch = new CoordinateSearch(aprFunction3);
        var result = coordinateSearch.search(Vector.parseSimple("-13 -12 5"), 1e-6);

        Assertions.assertTrue(new Vector(1, 2, 3).equals(result, 3));
    }

}
