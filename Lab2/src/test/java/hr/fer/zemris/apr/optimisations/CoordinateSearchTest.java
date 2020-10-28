package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.function.AprFunction;
import org.junit.jupiter.api.Test;

class CoordinateSearchTest {

    @Test
    void of() {
        AprFunction aprFunction3 = new AprFunction(x -> Math.pow(x.get(0) - 1, 2) + Math.pow(x.get(1) - 2, 2) + Math.pow(x.get(2) - 3, 2));

        var a = CoordinateSearch.of(aprFunction3, Vector.parseSimple("0 0 0"), Vector.parseSimple("0.001 0.001 0.001"));
        System.out.println(a);
        System.out.println(aprFunction3.getCounter());

    }
}
