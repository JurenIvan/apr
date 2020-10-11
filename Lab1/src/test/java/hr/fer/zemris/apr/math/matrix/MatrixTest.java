package hr.fer.zemris.apr.math.matrix;


import hr.fer.zemris.apr.math.matrix.determinant.DeterminantStrategies;
import hr.fer.zemris.apr.math.matrix.inverse.InverseStrategies;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    public void parseString() {
        Matrix matrix = Matrix.parseString("1 2       3|4 5 6        |7 8 9");

        assertEquals(1, matrix.get(0, 0));
        assertEquals(2, matrix.get(0, 1));
        assertEquals(3, matrix.get(0, 2));
        assertEquals(4, matrix.get(1, 0));
        assertEquals(5, matrix.get(1, 1));
        assertEquals(6, matrix.get(1, 2));
        assertEquals(7, matrix.get(2, 0));
        assertEquals(8, matrix.get(2, 1));
        assertEquals(9, matrix.get(2, 2));
    }

    @Test
    public void nTransposeTrue() {
        IMatrix m1 = Matrix.parseString("1 2 3 | 4 5 6");
        IMatrix m2 = m1.nTranspose(true);

        assertEquals(1, m1.get(0, 0));
        assertEquals(2, m1.get(0, 1));
        assertEquals(3, m1.get(0, 2));
        assertEquals(4, m1.get(1, 0));
        assertEquals(5, m1.get(1, 1));
        assertEquals(6, m1.get(1, 2));

        assertEquals(1, m2.get(0, 0));
        assertEquals(4, m2.get(0, 1));
        assertEquals(2, m2.get(1, 0));
        assertEquals(5, m2.get(1, 1));
        assertEquals(3, m2.get(2, 0));
        assertEquals(6, m2.get(2, 1));

        m2.set(2, 1, 9);

        assertEquals(1, m1.get(0, 0));
        assertEquals(2, m1.get(0, 1));
        assertEquals(3, m1.get(0, 2));
        assertEquals(4, m1.get(1, 0));
        assertEquals(5, m1.get(1, 1));
        assertEquals(9, m1.get(1, 2));

        assertEquals(1, m2.get(0, 0));
        assertEquals(4, m2.get(0, 1));
        assertEquals(2, m2.get(1, 0));
        assertEquals(5, m2.get(1, 1));
        assertEquals(3, m2.get(2, 0));
        assertEquals(9, m2.get(2, 1));
    }

    @Test
    public void nTransposeFalse() {
        IMatrix m1 = Matrix.parseString("1 2 3 | 4 5 6 ");
        IMatrix m2 = m1.nTranspose(false);

        assertEquals(1, m1.get(0, 0));
        assertEquals(2, m1.get(0, 1));
        assertEquals(3, m1.get(0, 2));
        assertEquals(4, m1.get(1, 0));
        assertEquals(5, m1.get(1, 1));
        assertEquals(6, m1.get(1, 2));

        assertEquals(1, m2.get(0, 0));
        assertEquals(4, m2.get(0, 1));
        assertEquals(2, m2.get(1, 0));
        assertEquals(5, m2.get(1, 1));
        assertEquals(3, m2.get(2, 0));
        assertEquals(6, m2.get(2, 1));

        m2.set(2, 1, 9);

        assertEquals(1, m1.get(0, 0));
        assertEquals(2, m1.get(0, 1));
        assertEquals(3, m1.get(0, 2));
        assertEquals(4, m1.get(1, 0));
        assertEquals(5, m1.get(1, 1));
        assertEquals(6, m1.get(1, 2));

        assertEquals(1, m2.get(0, 0));
        assertEquals(4, m2.get(0, 1));
        assertEquals(2, m2.get(1, 0));
        assertEquals(5, m2.get(1, 1));
        assertEquals(3, m2.get(2, 0));
        assertEquals(9, m2.get(2, 1));
    }

    @Test
    public void subMatrixTrue() {
        IMatrix m1 = Matrix.parseString("1 2 3 | 4 5 6 | 7 8 9");
        IMatrix m2 = m1.subMatrix(1, 1, true);

        assertEquals(1, m2.get(0, 0));
        assertEquals(3, m2.get(0, 1));
        assertEquals(7, m2.get(1, 0));
        assertEquals(9, m2.get(1, 1));

        m2.set(1, 1, 10);

        assertEquals(1, m1.get(0, 0));
        assertEquals(2, m1.get(0, 1));
        assertEquals(3, m1.get(0, 2));
        assertEquals(4, m1.get(1, 0));
        assertEquals(5, m1.get(1, 1));
        assertEquals(6, m1.get(1, 2));
        assertEquals(7, m1.get(2, 0));
        assertEquals(8, m1.get(2, 1));
        assertEquals(10, m1.get(2, 2));

        assertEquals(1, m2.get(0, 0));
        assertEquals(3, m2.get(0, 1));
        assertEquals(7, m2.get(1, 0));
        assertEquals(10, m2.get(1, 1));
    }

    @Test
    public void subMatrixFalse() {
        IMatrix m1 = Matrix.parseString("1 2 3 | 4 5 6 | 7 8 9");
        IMatrix m2 = m1.subMatrix(1, 1, false);

        assertEquals(1, m2.get(0, 0), 1e-6);
        assertEquals(3, m2.get(0, 1), 1e-6);
        assertEquals(7, m2.get(1, 0), 1e-6);
        assertEquals(9, m2.get(1, 1), 1e-6);

        m2.set(1, 1, 10);

        assertEquals(1, m1.get(0, 0), 1e-6);
        assertEquals(2, m1.get(0, 1), 1e-6);
        assertEquals(3, m1.get(0, 2), 1e-6);
        assertEquals(4, m1.get(1, 0), 1e-6);
        assertEquals(5, m1.get(1, 1), 1e-6);
        assertEquals(6, m1.get(1, 2), 1e-6);
        assertEquals(7, m1.get(2, 0), 1e-6);
        assertEquals(8, m1.get(2, 1), 1e-6);
        assertEquals(9, m1.get(2, 2), 1e-6);

        assertEquals(1, m2.get(0, 0), 1e-6);
        assertEquals(3, m2.get(0, 1), 1e-6);
        assertEquals(7, m2.get(1, 0), 1e-6);
        assertEquals(10, m2.get(1, 1), 1e-6);
    }

    @Test
    public void determinant() {
        IMatrix m1 = Matrix.parseString("10 21 4 | 81 1 2|-2      7 4");
        assertEquals(-4712, m1.determinant(DeterminantStrategies.SUBVIEW_DETERMINANT));
    }

    @Test
    public void determinant2() {
        IMatrix m1 = Matrix.parseString("1 2 | 7 4");
        assertEquals(-10, m1.determinant(DeterminantStrategies.SUBVIEW_DETERMINANT));
    }

    @Test
    public void inverse() {
        IMatrix matrix1 = Matrix.parseString("4 7 | 2 6 ").inverse(InverseStrategies.SUBVIEW_INVERSE);

        assertEquals(0.6, matrix1.get(0, 0), 1e-6);
        assertEquals(-0.7, matrix1.get(0, 1), 1e-6);
        assertEquals(-0.2, matrix1.get(1, 0), 1e-6);
        assertEquals(0.4, matrix1.get(1, 1), 1e-6);
    }

    @Test
    public void inverse2() {
        IMatrix matrix = Matrix.parseString("1 2 3 |0 1 4|5 6 0").inverse(InverseStrategies.SUBVIEW_INVERSE);

        assertEquals(-24, matrix.get(0, 0), 1e-6);
        assertEquals(18, matrix.get(0, 1), 1e-6);
        assertEquals(5, matrix.get(0, 2), 1e-6);
        assertEquals(20, matrix.get(1, 0), 1e-6);
        assertEquals(-15, matrix.get(1, 1), 1e-6);
        assertEquals(-4, matrix.get(1, 2), 1e-6);
        assertEquals(-5, matrix.get(2, 0), 1e-6);
        assertEquals(4, matrix.get(2, 1), 1e-6);
        assertEquals(1, matrix.get(2, 2), 1e-6);
    }

    @Test
    public void multiplication() {
        IMatrix m1 = Matrix.parseString("2 7 4| 2 6 4 | 1 5 7");
        IMatrix m2 = Matrix.parseString("1 2 3| 4 5 6 | 7 8 9");

        IMatrix multiplication = m1.nMultiply(m2);

        assertEquals(58, multiplication.get(0, 0), 1e-6);
        assertEquals(71, multiplication.get(0, 1), 1e-6);
        assertEquals(84, multiplication.get(0, 2), 1e-6);
        assertEquals(54, multiplication.get(1, 0), 1e-6);
        assertEquals(66, multiplication.get(1, 1), 1e-6);
        assertEquals(78, multiplication.get(1, 2), 1e-6);
        assertEquals(70, multiplication.get(2, 0), 1e-6);
        assertEquals(83, multiplication.get(2, 1), 1e-6);
        assertEquals(96, multiplication.get(2, 2), 1e-6);
    }

    @Test
    public void calc() {
        IMatrix a = Matrix.parseString("3 5 | 2 10");
        IMatrix r = Matrix.parseString("2 | 8");
        IMatrix v = a.inverse(InverseStrategies.SUBVIEW_INVERSE).nMultiply(r);

        assertEquals(-1, v.get(0, 0), 1e-6);
        assertEquals(1, v.get(1, 0), 1e-6);
    }

    @Test
    void sub() {
        var m1 = Matrix.parseString("5 4|3 2");
        var m2 = Matrix.parseString("4 2|1 0");

        var result = m1.sub(m2);

        assertEquals(1, result.get(0, 0));
        assertEquals(2, result.get(0, 1));
        assertEquals(2, result.get(1, 0));
        assertEquals(2, result.get(1, 1));

        assertEquals(1, m1.get(0, 0));
        assertEquals(2, m1.get(0, 1));
        assertEquals(2, m1.get(1, 0));
        assertEquals(2, m1.get(1, 1));
    }

    @Test
    void nSub() {
        var m1 = Matrix.parseString("5 4|3 2");
        var m2 = Matrix.parseString("4 2|1 0");

        var result = m1.nSub(m2);

        assertEquals(1, result.get(0, 0));
        assertEquals(2, result.get(0, 1));
        assertEquals(2, result.get(1, 0));
        assertEquals(2, result.get(1, 1));

        assertEquals(5, m1.get(0, 0));
        assertEquals(4, m1.get(0, 1));
        assertEquals(3, m1.get(1, 0));
        assertEquals(2, m1.get(1, 1));
    }

    @Test
    void testEquals() {
        Matrix m1 = Matrix.parseString("1.123456780 123");
        Matrix m2 = Matrix.parseString("1.123456789 123");

        assertTrue(m1.equals(m2, 7));
        assertTrue(m1.equals(m2, 8));
        assertFalse(m1.equals(m2, 9));

        Matrix m3 = Matrix.parseString("1.12345000 123");

        assertEquals(m1, m3);
    }
}
