package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GoldenCutTest {

    @Test
    void givenTest1() {
        Pair<IVector> borders = GoldenCut.given(x -> Math.pow(x.get(0) - 4, 2), new Pair<>(new Vector(2), new Vector(8)), 1, 0);

        Assertions.assertEquals(borders.getFirst().get(0), 3.416407864, 1e-6);
        Assertions.assertEquals(borders.getSecond().get(0), 4.291796067, 1e-6);
    }
}
