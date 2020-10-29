package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.domain.Pair;
import org.junit.jupiter.api.Test;

import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GoldenCutTest {

    @Test
    void givenTest1() {
        Pair<IVector, IVector> borders = GoldenCut.getInterval(x -> pow(x.get(0) - 4, 2), new Pair<>(new Vector(2), new Vector(8)), 1, 0);

        assertEquals(borders.getFirst().get(0), 3.416407864, 1e-6);
        assertEquals(borders.getSecond().get(0), 4.291796067, 1e-6);
    }

    @Test
    void givenTest2() {
        IVector vector = GoldenCut.getMiddleOfInterval(x -> pow(x.get(0) - 4, 2), new Pair<>(new Vector(2), new Vector(8)), 1, 0);

        assertEquals(vector.get(0), 3.8541019662496843, 1e-6);
    }
}
