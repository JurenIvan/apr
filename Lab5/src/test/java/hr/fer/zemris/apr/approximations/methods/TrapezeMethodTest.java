package hr.fer.zemris.apr.approximations.methods;

import hr.fer.zemris.apr.approximations.ApproxHistoryRecord;
import hr.fer.zemris.apr.math.matrix.Matrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TrapezeMethodTest {

    @Test
    void approximate() {
        var a = Matrix.parseString("-0.1");
        var x = Matrix.parseString("1");

        var b = Matrix.parseString("0");
        var r = Matrix.parseString("0");


        var trapezeMethod = new TrapezeMethod();
        trapezeMethod.approximate(x, a, b, r, 2, 1, false);
        var history = trapezeMethod.getHistory();

        Assertions.assertEquals(new ApproxHistoryRecord(0, Matrix.parseString("1")), history.get(0));
        Assertions.assertEquals(new ApproxHistoryRecord(1, Matrix.parseString("0.9047619047619047")), history.get(1));
        Assertions.assertEquals(new ApproxHistoryRecord(2, Matrix.parseString(Math.pow(0.9047619047619047, 2) + "")), history.get(2));
        Assertions.assertEquals(3, history.size());
    }
}
