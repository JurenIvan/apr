package hr.fer.zemris.apr.math.matrix.supstitutions;

import hr.fer.zemris.apr.math.matrix.Matrix;
import hr.fer.zemris.apr.math.vector.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubstitutionStrategiesTest {

    @Test
    public void testForwardSubstitution() {
        var m = Matrix.parseString("1 0 0 | 0.25 1 0 | 0.75 -0.2 1");
        var v = Vector.parseSimple("8 3 4");

        var y = SubstitutionStrategies.FORWARD.substitute(m, v);
        assertEquals(Vector.parseSimple("8 1 -1.8"), y);
    }

    @Test
    public void testBackwardSubstitution() {
        var m = Matrix.parseString("4 3 4 | 0 1.25 1 | 0 0 -1.8");
        var v = Vector.parseSimple("8 1 -1.8");

        var x = SubstitutionStrategies.BACKWARD.substitute(m, v);

        assertEquals(Vector.parseSimple("1 0 1"), x);
    }

}