package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.math.vector.Vector;

import java.util.function.Function;

public class UniModalInterval {

    public static Pair<IVector> of(Function<IVector, Double> function, double initialStep, IVector startValue, int index) {
        int n = startValue.getDimension();
        IVector left = startValue.nSub(Vector.e(n, index, initialStep));
        IVector right = startValue.nAdd(Vector.e(n, index, initialStep));
        IVector m = startValue;
        double fl, fm, fr;
        long step = 1;

        fm = function.apply(startValue);
        fl = function.apply(left);
        fr = function.apply(right);

        if (fm < fr && fm < fl) {
            return new Pair<>(left, right);
        }
        if (fm > fr) {
            do {
                left = m;
                m = right;
                fm = fr;
                right = startValue.nAdd(Vector.e(n, index, initialStep * (step *= 2)));
                fr = function.apply(right);
            } while (fm > fr);
        } else {
            do {
                right = m;
                m = left;
                fm = fl;
                left = startValue.nSub(Vector.e(n, index, initialStep * (step *= 2)));
                fl = function.apply(left);
            } while (fm > fl);
        }
        return new Pair<>(left, right);
    }
}
