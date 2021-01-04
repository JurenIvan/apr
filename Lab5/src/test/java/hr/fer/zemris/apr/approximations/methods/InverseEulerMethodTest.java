package hr.fer.zemris.apr.approximations.methods;

import hr.fer.zemris.apr.approximations.ApproxHistoryRecord;
import hr.fer.zemris.apr.math.matrix.Matrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InverseEulerMethodTest {

    @Test
    void approximate() {
        var a = Matrix.parseString("0 1 | -200 -102");
        var x = Matrix.parseString("1 | -2");

        var b = Matrix.parseString(" 0 0 | 0 0 ");
        var r = Matrix.parseString(" 0 | 0");

        var euler = new InverseEulerMethod();
        euler.approximate(x, a, b, r, 0.02, 0.01, false);
        var history = euler.getHistory();

        Assertions.assertEquals(new ApproxHistoryRecord(0, Matrix.parseString("1 | -2")), history.get(0));
        Assertions.assertEquals(new ApproxHistoryRecord(0.01, Matrix.parseString("0.980392156862745 | -1.9607843137254901")), history.get(1));
        Assertions.assertEquals(new ApproxHistoryRecord(0.02, Matrix.parseString("0.961168781237985 | -1.9223375624759704")), history.get(2));
        Assertions.assertEquals(3, history.size());
    }
}
