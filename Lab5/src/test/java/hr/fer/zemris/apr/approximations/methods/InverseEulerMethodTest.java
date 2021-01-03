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

        var euler = new InverseEulerMethod(x, a, b, b, 0.02, 0.01);
        euler.approximate();
        var history = euler.getHistory();

        Assertions.assertEquals(new ApproxHistoryRecord(0, Matrix.parseString("1 | -2")), history.get(0));
        Assertions.assertEquals(new ApproxHistoryRecord(0.01, Matrix.parseString("0.98 | -1.96 ")), history.get(1));
        Assertions.assertEquals(new ApproxHistoryRecord(0.02, Matrix.parseString("0.9604 | -1.9208")), history.get(2));
        Assertions.assertEquals(3, history.size());
    }
}
