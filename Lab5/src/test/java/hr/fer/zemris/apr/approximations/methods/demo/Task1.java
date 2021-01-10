package hr.fer.zemris.apr.approximations.methods.demo;

import hr.fer.zemris.apr.approximations.ApproxHistoryRecord;
import hr.fer.zemris.apr.approximations.Approximation;
import hr.fer.zemris.apr.approximations.methods.*;
import hr.fer.zemris.apr.math.matrix.Matrix;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.List;

public class Task1 {

    public static final int WIDTH = 1920 / 2;
    public static final int HEIGHT = 1080 / 2;
    private static final int x0_1 = 1;
    private static final int x0_2 = 1;
    private static double[] realValue1;
    private static double[] realValue2;

    public static void main(String[] args) {
        List<ApproxHistoryRecord> eulerHistory = aprox(new EulerMethod());
        List<ApproxHistoryRecord> inverseEulerHistory = aprox(new InverseEulerMethod());
        List<ApproxHistoryRecord> peceHistory = aprox(new PeceMethod(new EulerMethod(), new TrapezeMethod(), 1));
        List<ApproxHistoryRecord> pece2History = aprox(new PeceMethod(new EulerMethod(), new InverseEulerMethod(), 2));
        List<ApproxHistoryRecord> rungeKuttaMehodHistory = aprox(new RungeKuttaMethod());
        List<ApproxHistoryRecord> trapezeHistory = aprox(new TrapezeMethod());

        realValue1 = new double[eulerHistory.size()];
        realValue2 = new double[eulerHistory.size()];

        for (int i = 0; i < eulerHistory.size(); i++) {
            var t = eulerHistory.get(i).getT();
            realValue1[i] = x0_1 * Math.cos(t) + x0_2 * Math.sin(t);
            realValue2[i] = x0_2 * Math.cos(t) - x0_1 * Math.sin(t);
        }

        XYChart chart = new XYChartBuilder().width(WIDTH).height(HEIGHT).title("Shift").xAxisTitle("t*0.01").yAxisTitle("Pomak").build();
        chart.addSeries("eulerShift", transform(eulerHistory, 0));
        chart.addSeries("inverseEulerShift", transform(inverseEulerHistory, 0));
        chart.addSeries("pece", transform(peceHistory, 0));
        chart.addSeries("pece2", transform(pece2History, 0));
        chart.addSeries("rungeKuttaShift", transform(rungeKuttaMehodHistory, 0));
        chart.addSeries("trapezeShift", transform(trapezeHistory, 0));
        new SwingWrapper(chart).displayChart();

        XYChart chart2 = new XYChartBuilder().width(WIDTH).height(HEIGHT).title("Speed").xAxisTitle("t*0.01").yAxisTitle("Brzina").build();
        chart2.addSeries("eulerSpeed", transform(eulerHistory, 1));
        chart2.addSeries("inverseEulerSpeed", transform(inverseEulerHistory, 1));
        chart2.addSeries("peceSpeed", transform(peceHistory, 1));
        chart2.addSeries("pece2Speed", transform(pece2History, 1));
        chart2.addSeries("rungeKuttaSpeed", transform(rungeKuttaMehodHistory, 1));
        chart2.addSeries("trapezeSpeed", transform(trapezeHistory, 1));
        new SwingWrapper(chart2).displayChart();

        XYChart chart3 = new XYChartBuilder().width(WIDTH).height(HEIGHT).title("Shift error").xAxisTitle("t*0.01").yAxisTitle("Cumulative error").build();
        chart3.addSeries("eulerShiftError", calculateError(eulerHistory, 0));
        chart3.addSeries("inverseEulerShiftError", calculateError(inverseEulerHistory, 0));
        chart3.addSeries("peceShiftError", calculateError(peceHistory, 0));
        chart3.addSeries("pece2ShiftError", calculateError(pece2History, 0));
        chart3.addSeries("rungeKuttaShiftError", calculateError(rungeKuttaMehodHistory, 0));
        chart3.addSeries("trapezeShiftError", calculateError(trapezeHistory, 0));
        new SwingWrapper(chart3).displayChart();

        XYChart chart4 = new XYChartBuilder().width(WIDTH).height(HEIGHT).title("Speed Error").xAxisTitle("t*0.01").yAxisTitle("Cumulative error").build();
        chart4.addSeries("eulerSpeedError", calculateError(eulerHistory, 1));
        chart4.addSeries("inverseEulerSpeedError", calculateError(inverseEulerHistory, 1));
        chart4.addSeries("peceShiftError", calculateError(peceHistory, 1));
        chart4.addSeries("pece2ShiftError", calculateError(pece2History, 1));
        chart4.addSeries("rungeKuttaSpeedError", calculateError(rungeKuttaMehodHistory, 1));
        chart4.addSeries("trapezeSpeedError", calculateError(trapezeHistory, 1));
        new SwingWrapper(chart4).displayChart();

        System.out.println("eulerSpeedError " + calculateError(eulerHistory, 0)[rungeKuttaMehodHistory.size() - 1]);
        System.out.println("eulerSpeedError " + calculateError(eulerHistory, 1)[rungeKuttaMehodHistory.size() - 1]);
        System.out.println("inverseEulerSpeedError " + calculateError(inverseEulerHistory, 0)[rungeKuttaMehodHistory.size() - 1]);
        System.out.println("inverseEulerSpeedError " + calculateError(inverseEulerHistory, 1)[rungeKuttaMehodHistory.size() - 1]);
        System.out.println("peceShiftError " + calculateError(peceHistory, 0)[rungeKuttaMehodHistory.size() - 1]);
        System.out.println("peceShiftError " + calculateError(peceHistory, 1)[rungeKuttaMehodHistory.size() - 1]);
        System.out.println("pece2ShiftError " + calculateError(pece2History, 0)[rungeKuttaMehodHistory.size() - 1]);
        System.out.println("pece2ShiftError " + calculateError(pece2History, 1)[rungeKuttaMehodHistory.size() - 1]);
        System.out.println("rungeKuttaSpeedError " + calculateError(rungeKuttaMehodHistory, 0)[rungeKuttaMehodHistory.size() - 1]);
        System.out.println("rungeKuttaSpeedError " + calculateError(rungeKuttaMehodHistory, 1)[rungeKuttaMehodHistory.size() - 1]);
        System.out.println("trapezeSpeedError " + calculateError(trapezeHistory, 0)[rungeKuttaMehodHistory.size() - 1]);
        System.out.println("trapezeSpeedError " + calculateError(trapezeHistory, 1)[rungeKuttaMehodHistory.size() - 1]);
    }

    private static double[] calculateError(List<ApproxHistoryRecord> eulerHistory, int i) {
        var real = i == 0 ? realValue1 : realValue2;
        var result = new double[eulerHistory.size()];
        double totalErr = 0;
        for (int j = 0; j < eulerHistory.size(); j++) {
            totalErr += Math.abs(real[j] - eulerHistory.get(j).getState().get(i, 0));
            result[j] = totalErr;
        }
        return result;
    }

    private static double[] transform(List<ApproxHistoryRecord> eulerHistory, int i) {
        var result = new double[eulerHistory.size()];
        for (int j = 0; j < eulerHistory.size(); j++) {
            result[j] = eulerHistory.get(j).getState().get(i, 0);
        }
        return result;
    }

    private static List<ApproxHistoryRecord> aprox(Approximation method) {
        var a = Matrix.parseString("0 1 | -1 0");
        var x = Matrix.parseString(x0_1 + " | " + x0_2);

        var b = Matrix.parseString("0 0 | 0 0 ");
        var r = Matrix.parseString("0 | 0");

        method.approximate(x, a, b, r, 10, 0.01, false);
        return method.getHistory();
    }
}
