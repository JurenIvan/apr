package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.optimisations.function.DoubleFunction;

public class UniModalInterval {

    /**
     * @param function   funcika
     * @param h          -step
     * @param startValue -startpoint
     */
    public static Pair of(DoubleFunction function, double h, double startValue) {

        double left = startValue - h;
        double right = startValue + h;
        double m = startValue;
        double fl, fm, fr;
        long step = 1;

        fm = function.apply(startValue);
        fl = function.apply(left);
        fr = function.apply(right);

        if (fm < fr && fm < fl) {
            return Pair.of(left, right);
        }
        if (fm > fr) {
            do {
                left = m;
                m = right;
                fm = fr;
                right = startValue + h * (step *= 2);
                fr = function.apply(right);
            } while (fm > fr);
        } else {
            do {
                right = m;
                m = left;
                fm = fl;
                left = startValue - h * (step *= 2);
                fl = function.apply(left);
            } while (fm > fl);
        }
        return Pair.of(left, right);
    }
}
