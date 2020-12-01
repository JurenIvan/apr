package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.domain.Pair;
import hr.fer.zemris.apr.optimisations.functions.CountingFunction;
import hr.fer.zemris.apr.optimisations.search.BoxSearch;
import hr.fer.zemris.apr.optimisations.search.GradientSearch;
import hr.fer.zemris.apr.optimisations.search.MixedFunctionSearch;
import hr.fer.zemris.apr.optimisations.search.NewtonRaphsonSearch;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static hr.fer.zemris.apr.optimisations.functions.Functions.*;

class DemoLab3 {

    @Test
    void task1_1() {
        var f3 = new CountingFunction<>(F3);
        var f3Derivative = new CountingFunction<>(F3_DERIVATIVE);
        var gs = new GradientSearch(f3, f3Derivative, true, 1000);

        IVector result = gs.search(new Vector(0, 0), 1e-6);

        System.out.println(result);
        System.out.println("f3 evaluations: " + f3.getCounter());
        System.out.println("f3 gradient evaluations: " + f3Derivative.getCounter());
    }

    @Test
    void task1_2() {
        var f3 = new CountingFunction<>(F3);
        var f3Derivative = new CountingFunction<>(F3_DERIVATIVE);
        var gs = new GradientSearch(f3, f3Derivative, false, 1000);

        IVector result = gs.search(new Vector(0, 0), 1e-6);

        System.out.println(result);
        System.out.println("f3 evaluations: " + f3.getCounter());
        System.out.println("f3 gradient evaluations: " + f3Derivative.getCounter());
    }

    @Test
    void task2_Function1_GradientSearch() {
        var f1 = new CountingFunction<>(F1);
        var f1Derivative = new CountingFunction<>(F1_DERIVATIVE);
        var gs = new GradientSearch(f1, f1Derivative, true, 10000);

        IVector result = gs.search(new Vector(-1.9, 2.0), 1e-6);

        System.out.println(result);
        System.out.println("f1 evaluations: " + f1.getCounter());
        System.out.println("f1 gradient evaluations: " + f1Derivative.getCounter());
    }

    @Test
    void task2_Function1_NewtonRaphson() {
        var f1 = new CountingFunction<>(F1);
        var f1Derivative = new CountingFunction<>(F1_DERIVATIVE);
        var f1Hessian = new CountingFunction<>(F1_HESSIAN);
        var gs = new NewtonRaphsonSearch(f1, f1Derivative, f1Hessian, true, 1000);

        IVector result = gs.search(new Vector(-1.9, 2.0), 1e-6);

        System.out.println(result);
        System.out.println("f1 evaluations: " + f1.getCounter());
        System.out.println("f1 gradijent evaluations: " + f1Derivative.getCounter());
        System.out.println("f1 hessian evaluationas: " + f1Hessian.getCounter());
    }

    @Test
    void task2_Function2_GradientSearch() {
        var f2 = new CountingFunction<>(F2);
        var f2Derivative = new CountingFunction<>(F2_DERIVATIVE);
        var gs = new GradientSearch(f2, f2Derivative, true, 1000);

        IVector result = gs.search(new Vector(0.1, 0.3), 1e-6);

        System.out.println(result);
        System.out.println("f2 evaluations: " + f2.getCounter());
        System.out.println("f2 gradient evaluations: " + f2Derivative.getCounter());
    }

    @Test
    void task2_Function2_NewtonRaphson() {
        var f2 = new CountingFunction<>(F2);
        var f2Derivative = new CountingFunction<>(F2_DERIVATIVE);
        var f2Hessian = new CountingFunction<>(F2_HESSIAN);
        var nrs = new NewtonRaphsonSearch(f2, f2Derivative, f2Hessian, true, 1000);

        IVector result = nrs.search(new Vector(0.1, 0.3), 1e-6);

        System.out.println(result);
        System.out.println("f evaluations: " + f2.getCounter());
        System.out.println("f gradijent evaluations: " + f2Derivative.getCounter());
        System.out.println("f hessian evaluationas: " + f2Hessian.getCounter());
    }

    @Test
    void task3_Function1() {
        var f1 = new CountingFunction<>(F1);

        var boxSearch = new BoxSearch(f1, List.of(x -> x.get(1) - x.get(0), x -> 2 - x.get(0)), x -> new Pair<>(-100.0, 100.0), 1.3);

        IVector result = boxSearch.search(new Vector(-30, 23), 1e-6);

        System.out.println(result);
        System.out.println("f evaluations: " + f1.getCounter());
    }

    @Test
    void task3_Function2() {
        var f1 = new CountingFunction<>(F2);

        var boxSearch = new BoxSearch(f1, List.of(x -> x.get(1) - x.get(0), x -> 2 - x.get(0)), x -> new Pair<>(-100.0, 100.0), 1.3);

        IVector result = boxSearch.search(new Vector(-30, 23), 1e-6);

        System.out.println(result);
        System.out.println("f evaluations: " + f1.getCounter());
    }

    @Test
    void task4_Function1() {
        var f = new CountingFunction<>(F1);
        List<Function<IVector, Double>> gi = List.of(x -> x.get(1) - x.get(0), x -> 2 - x.get(0));

        var functionSearch = new MixedFunctionSearch(f, gi, new ArrayList<>(), 1);
        var result = functionSearch.search(new Vector(-1.9, 2), 1e-6);

        System.out.println(result);
        System.out.println("f evaluations: " + f.getCounter());
    }

    @Test
    void task4_Function2() {
        var f = new CountingFunction<>(F2);
        List<Function<IVector, Double>> gi = List.of(x -> x.get(1) - x.get(0), x -> 2 - x.get(0));

        var functionSearch = new MixedFunctionSearch(f, gi, new ArrayList<>(), 1);
        var result = functionSearch.search(new Vector(0.1, 0.3), 1e-6);

        System.out.println(result);
        System.out.println("f evaluations: " + f.getCounter());
    }

    @Test
    void task5_Funckcija4_tocka55() {
        var f = new CountingFunction<>(F4);
        List<Function<IVector, Double>> gi = List.of(x -> 3 - x.get(0) - x.get(1), x -> 3 + 1.5 * x.get(0) - x.get(1), x -> x.get(1) - 1);

        var functionSearch = new MixedFunctionSearch(f, gi, new ArrayList<>(), 1);
        var result = functionSearch.search(new Vector(5, 5), 1e-6);

        System.out.println(result);
        System.out.println("f evaluations: " + f.getCounter());
    }

    @Test
    void task5_Funckcija4_tocka00() {
        var f = new CountingFunction<>(F4);
        List<Function<IVector, Double>> gi = List.of(x -> 3 - x.get(0) - x.get(1), x -> 3 + 1.5 * x.get(0) - x.get(1), x -> x.get(1) - 1);

        var functionSearch = new MixedFunctionSearch(f, gi, new ArrayList<>(), 1);
        var result = functionSearch.search(new Vector(0, 0), 1e-6);

        System.out.println(result);
        System.out.println("f evaluations: " + f.getCounter());
    }
}
