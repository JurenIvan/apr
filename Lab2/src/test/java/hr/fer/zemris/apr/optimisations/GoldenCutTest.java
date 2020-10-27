package hr.fer.zemris.apr.optimisations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GoldenCutTest {

    @Test
    void givenTest1() {
        Pair borders = Pair.of(2, 8);
        double middle = GoldenCut.given(x -> Math.pow(x - 4, 2), borders, 1);

        Assertions.assertTrue(borders.equals(Pair.of(3.416407864, 4.291796067), 6));
        Assertions.assertEquals((3.416407864 + 4.291796067) / 2, middle, 1e-6);
    }
}
