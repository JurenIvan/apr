package hr.fer.zemris.apr.approximations.methods;

import hr.fer.zemris.apr.approximations.ApproxHistoryRecord;
import hr.fer.zemris.apr.math.matrix.Matrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EulerMethodTest {

    @Test
    void approximate() {
        var a = Matrix.parseString("0 1 | -10 -5");
        var x = Matrix.parseString("1 | 0");

        var b = Matrix.parseString("0 0 | 0 0 ");
        var r = Matrix.parseString("0 | 0");


        var euler = new EulerMethod();
        euler.approximate(x, a, b, r, 0.8, 0.4, false);
        var history = euler.getHistory();

        Assertions.assertEquals(new ApproxHistoryRecord(0, Matrix.parseString("1 | 0")), history.get(0));
        Assertions.assertEquals(new ApproxHistoryRecord(0.4, Matrix.parseString("1 | -4")), history.get(1));
        Assertions.assertEquals(new ApproxHistoryRecord(0.8, Matrix.parseString("-0.6 | 0")), history.get(2));
        Assertions.assertEquals(3, history.size());
    }
}
