package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;
import hr.fer.zemris.apr.optimisations.domain.Pair;
import hr.fer.zemris.apr.optimisations.function.AprFunction;
import hr.fer.zemris.apr.optimisations.search.CoordinateSearch;
import hr.fer.zemris.apr.optimisations.search.HookeJeevesSearch;
import hr.fer.zemris.apr.optimisations.search.SearchAlgorithm;
import hr.fer.zemris.apr.optimisations.search.SimplexSearch;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static hr.fer.zemris.apr.optimisations.function.Functions.*;

public class Demo {

    private static final double E = 1e-6;

    public static void main(String[] args) {
        Demo demo = new Demo();

//        demo.task1();
//        demo.task2();
//        demo.task2_1(); //did this to have the same starting point, more relevant
        demo.task3();
//        demo.task4();
//        demo.task5();
    }

    public void task1() {
//        Function<IVector, Double> f3 = F3_SUPPLIER.apply(3);
        Function<IVector, Double> f3 = (x) -> Math.pow(x.get(0) - 3, 2);
        List<List<Integer>> fCalls = List.of(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        for (int i = 1; i < 1000; i++) {
            IVector x0 = Vector.unit(1, i);

            var coordinateSearchAprFunc = new AprFunction(f3);
            var hookeJeevesSearchAprFunc = new AprFunction(f3);
            var simplexSearchAprFunc = new AprFunction(f3);

            SearchAlgorithm coordinateSearch = new CoordinateSearch(coordinateSearchAprFunc);
            SearchAlgorithm hookeJeevesSearch = new HookeJeevesSearch(hookeJeevesSearchAprFunc);
            SearchAlgorithm simplexSearch = new SimplexSearch(simplexSearchAprFunc);

            var coordinateSearchResult = coordinateSearch.search(x0.copy(), E);
            var hookeJeevesSearchResult = hookeJeevesSearch.search(x0.copy(), E);
            var simplexSearchResult = simplexSearch.search(x0.copy(), E);

            fCalls.get(0).add(coordinateSearchAprFunc.getCounter());
            fCalls.get(1).add(hookeJeevesSearchAprFunc.getCounter());
            fCalls.get(2).add(simplexSearchAprFunc.getCounter());

            System.out.println("start point i=" + i);
            System.out.println(coordinateSearchResult + " counter:" + coordinateSearchAprFunc.getCounter());
            System.out.println(hookeJeevesSearchResult + " counter:" + hookeJeevesSearchAprFunc.getCounter());
            System.out.println(simplexSearchResult + " counter:" + simplexSearchAprFunc.getCounter());
            System.out.println();
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).title("Task 1").xAxisTitle("h").yAxisTitle("evaluation count").build();
        chart.addSeries("coordinateSearchResult", fCalls.get(0));
        chart.addSeries("hookeJeevesSearchResult", fCalls.get(1));
        chart.addSeries("simplexSearchResult", fCalls.get(2));
        new SwingWrapper(chart).displayChart();
    }

    public void task2() {
        var fxPairs = List.of(
                new Pair<>(F1, Vector.parseSimple("-1.9 2")),
                new Pair<>(F2, Vector.parseSimple("0.1 3")),
                new Pair<>(F3_SUPPLIER.apply(8), Vector.parseSimple("1 2 3 4 5 6 7 8")),
                new Pair<>(F4, Vector.parseSimple("5.1 1.1")));

        for (int i = 0; i < fxPairs.size(); i++) {
            var function = fxPairs.get(i).getFirst();
            IVector x0 = fxPairs.get(i).getSecond();

            var coordinateSearchAprFunc = new AprFunction(function);
            var hookeJeevesSearchAprFunc = new AprFunction(function);
            var simplexSearchAprFunc = new AprFunction(function);

            SearchAlgorithm coordinateSearch = new CoordinateSearch(coordinateSearchAprFunc);
            SearchAlgorithm hookeJeevesSearch = new HookeJeevesSearch(hookeJeevesSearchAprFunc);
            SearchAlgorithm simplexSearch = new SimplexSearch(simplexSearchAprFunc);

            var coordinateSearchResult = coordinateSearch.search(x0, E);
            var hookeJeevesSearchResult = hookeJeevesSearch.search(x0, E);
            var simplexSearchResult = simplexSearch.search(x0, E);

            System.out.println("function " + (i + 1));
            System.out.println("coordinateSearchResult  counter:" + coordinateSearchAprFunc.getCounter() + " min:" + coordinateSearchResult);
            System.out.println("hookeJeevesSearchResult counter:" + hookeJeevesSearchAprFunc.getCounter() + " min:" + hookeJeevesSearchResult);
            System.out.println("simplexSearchResult     counter:" + simplexSearchAprFunc.getCounter() + " min:" + simplexSearchResult);
            System.out.println();
        }
    }

    public void task2_1() {
        int dimension = 10;
        var functions = List.of(F1, F2, F3_SUPPLIER.apply(dimension), F4);
        List<List<Integer>> fCalls = List.of(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        for (int i = 0; i < functions.size(); i++) {
            var function = functions.get(i);
            IVector x0 = Vector.unit(dimension, 50);

            var coordinateSearchAprFunc = new AprFunction(function);
            var hookeJeevesSearchAprFunc = new AprFunction(function);
            var simplexSearchAprFunc = new AprFunction(function);

            SearchAlgorithm coordinateSearch = new CoordinateSearch(coordinateSearchAprFunc);
            SearchAlgorithm hookeJeevesSearch = new HookeJeevesSearch(hookeJeevesSearchAprFunc);
            SearchAlgorithm simplexSearch = new SimplexSearch(simplexSearchAprFunc);

            var coordinateSearchResult = coordinateSearch.search(x0, E);
            var hookeJeevesSearchResult = hookeJeevesSearch.search(x0, E);
            var simplexSearchResult = simplexSearch.search(x0, E);

            System.out.println("function " + (i + 1));
            System.out.println("coordinateSearchResult  counter:" + coordinateSearchAprFunc.getCounter() + " min:" + coordinateSearchResult);
            System.out.println("hookeJeevesSearchResult counter:" + hookeJeevesSearchAprFunc.getCounter() + " min:" + hookeJeevesSearchResult);
            System.out.println("simplexSearchResult     counter:" + simplexSearchAprFunc.getCounter() + " min:" + simplexSearchResult);
            System.out.println();
        }
    }

    public void task3() {
        IVector x0 = Vector.unit(2, 5);

        var hookeJeevesSearchAprFunc = new AprFunction(F4);
        var simplexSearchAprFunc = new AprFunction(F4);

        SearchAlgorithm hookeJeevesSearch = new HookeJeevesSearch(hookeJeevesSearchAprFunc);
        SearchAlgorithm simplexSearch = new SimplexSearch(simplexSearchAprFunc);

        var hookeJeevesSearchResult = hookeJeevesSearch.search(x0.copy(), E);
        var simplexSearchResult = simplexSearch.search(x0.copy(), E);

        System.out.println(hookeJeevesSearchResult + " counter:" + hookeJeevesSearchAprFunc.getCounter());
        System.out.println(simplexSearchResult + " counter:" + simplexSearchAprFunc.getCounter());
        System.out.println();
    }

    public void task4() {
        int upperRange = 50;
        List<IVector> x0List = List.of(Vector.parseSimple("0.5 0.5"), Vector.parseSimple("20 20"));
        List<List<Integer>> fCalls = new ArrayList<>();
        for (int i = 0; i < x0List.size(); i++) {
            fCalls.add(new ArrayList<>());
            for (int j = 1; j < upperRange; j++) {
                var simplexSearchAprFunc = new AprFunction(F1);
                SearchAlgorithm simplexSearch = new SimplexSearch(simplexSearchAprFunc, 1, 0.5, 2, 0.5, j);
                var result = simplexSearch.search(x0List.get(i), E);
                System.out.println("i: " + i + " minimum:" + result);
                fCalls.get(i).add(simplexSearchAprFunc.getCounter());
            }
        }

        for (int i = 0; i < x0List.size(); i++) {
            System.out.println("xo:" + x0List.get(i));
            for (int j = 0; j < upperRange - 1; j++) {
                System.out.println("i:" + j + "count:" + fCalls.get(i).get(j));
            }
        }

        List<Integer> xData = new ArrayList<>();
        for (int i = 1; i < upperRange; i++) xData.add(i);

        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Task 4").xAxisTitle("h").yAxisTitle("evaluation count").build();

        chart.addSeries("X0=(0.50,0.50)", fCalls.get(0));
        chart.addSeries("X0=(20.0,20.0)", fCalls.get(1));
        // Show it
        new SwingWrapper(chart).displayChart();
    }

    public void task5() {
        int iterationCount = 1000000;
        int success = 0;
        var f6 = F6_SUPPLIER.apply(2);
        for (int i = 0; i < iterationCount; i++) {
            var aprFunction = new AprFunction(f6);
            var alg = new SimplexSearch(aprFunction);
            double x = Math.random() * 10 - 5;
            double y = Math.random() * 10 - 5;
            var result = alg.search(new Vector(x, y), E);
            if (result.norm() < 0.001) {
                success++;
                System.out.println(result);
            }
        }
        System.out.println(success);
        System.out.println(success / (double) iterationCount);
    }
}
