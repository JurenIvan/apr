package hr.fer.zemris.apr.approximations.methods;

import hr.fer.zemris.apr.approximations.ApproxHistoryRecord;
import hr.fer.zemris.apr.math.matrix.Matrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RungeKuttaMethodTest {

    @Test
    void approximate() {
        var a = Matrix.parseString("-0.1");
        var x = Matrix.parseString("1");

        var b = Matrix.parseString("0");
        var r = Matrix.parseString("0");


        var rungeKutta = new RungeKuttaMethod();
        rungeKutta.approximate(x, a, b, r, 1, 1, false);
        var history = rungeKutta.getHistory();

        Assertions.assertEquals(new ApproxHistoryRecord(0, Matrix.parseString("1")), history.get(0));
        Assertions.assertEquals(new ApproxHistoryRecord(1, Matrix.parseString("0.9048375")), history.get(1));
        Assertions.assertEquals(2, history.size());
    }
}
