package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.math.vector.IVector;
import hr.fer.zemris.apr.optimisations.domain.Pair;

import java.util.function.Function;

public class GeneralUniModalInterval {

    public static Pair<IVector, IVector> of(Function<IVector, Double> function, IVector startValue, IVector direction) {
        IVector left = startValue.nSub(direction);
        IVector right = startValue.nAdd(direction);
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
                right = startValue.nAdd(direction.nScalarMultiply(step *= 2));
                fr = function.apply(right);
            } while (fm > fr);
        } else {
            do {
                right = m;
                m = left;
                fm = fl;
                left = startValue.nSub(direction.nScalarMultiply(step *= 2));
                fl = function.apply(left);
            } while (fm > fl);
        }
        return new Pair<>(left, right);
    }
}
