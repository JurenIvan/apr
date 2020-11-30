package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.functions.CountingFunction;
import hr.fer.zemris.apr.optimisations.search.GradientSearch;
import org.junit.jupiter.api.Test;

import static hr.fer.zemris.apr.optimisations.functions.Functions.F3;
import static hr.fer.zemris.apr.optimisations.functions.Functions.F3_DERIVATIVE;

class Demo {

    @Test
    void task1_1() {
        var f3 = new CountingFunction<>(F3);
        var f3Derivative = new CountingFunction<>(F3_DERIVATIVE);
        var gs = new GradientSearch(f3, f3Derivative, true, 10000);

        IVector result = gs.search(new Vector(-1.9, 2.0), 1e-6);

        System.out.println(result);
        System.out.println(f3.getCounter());
        System.out.println(f3Derivative.getCounter());
    }

    @Test
    void task1_2() {
        var f3 = new CountingFunction<>(F3);
        var f3Derivative = new CountingFunction<>(F3_DERIVATIVE);
        var gs = new GradientSearch(f3, f3Derivative, false, 10);

        IVector result = gs.search(new Vector(-1.9, 2.0), 1e-6);

        System.out.println(result);
        System.out.println(f3.getCounter());
        System.out.println(f3Derivative.getCounter());
    }


    @Test
    void task2() {
    }

    @Test
    void task3() {
    }

    @Test
    void task4() {
    }

    @Test
    void task5() {
    }
}
