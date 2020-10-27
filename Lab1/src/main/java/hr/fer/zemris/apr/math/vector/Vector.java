package hr.fer.zemris.apr.math.vector;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public class Vector extends AbstractVector {

    private double[] elements;
    private boolean readOnly;

    private Vector(int dimension) {
        this.elements = new double[dimension];
        this.readOnly = false;
    }

    public Vector(double... elements) {
        this.elements = elements;
        readOnly = false;
    }

    public Vector(double[] elements, boolean readOnly, boolean referenceArray) {
        this.elements = referenceArray ? elements : Arrays.copyOf(elements, elements.length);
        this.readOnly = readOnly;
    }

    public static Vector unit(int dimension, double value) {
        var elements = new double[dimension];
        var vector = new Vector(elements, false, true);
        for (int i = 0; i < dimension; i++) {
            vector.set(i, value);
        }
        return vector;
    }

    public static Vector unit(int dimension) {
        return Vector.unit(dimension, 1.0);
    }

    public static Vector e(int dimension, int atIndex) {
        return Vector.e(dimension, atIndex, 1);
    }

    public static Vector e(int dimension, int atIndex, double value) {
        var elements = new double[dimension];
        var vector = new Vector(elements, false, true);
        vector.set(atIndex, value);
        return vector;
    }

    public static Vector parseSimple(String line) {
        double[] elements = Arrays.stream(line.trim().split("\\s+")).mapToDouble(Double::parseDouble).toArray();
        return new Vector(elements, false, true);
    }

    @Override
    public double get(int orderOfComponent) {
        return elements[orderOfComponent];
    }

    @Override
    public IVector set(int orderOfComponent, double value) {
        if (readOnly)
            throw new IllegalStateException();

        elements[orderOfComponent] = value;
        return this;
    }

    @Override
    public int getDimension() {
        return elements.length;
    }

    @Override
    public IVector copy() {
        return copyPart(elements.length);
    }

    @Override
    public IVector copyPart(int numberOfElements) {
        double[] elements = new double[numberOfElements];
        System.arraycopy(this.elements, 0, elements, 0, numberOfElements < getDimension() ? numberOfElements : getDimension());
        return new Vector(Arrays.copyOf(elements, numberOfElements), false, true);
    }

    @Override
    public IVector newInstance(int dimension) {
        if (dimension < 0)
            throw new IllegalArgumentException();
        return new Vector(dimension);
    }

    public String toString(int precision) {
        String numberFormat = "%." + precision + "f";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Arrays.stream(this.elements).mapToObj(e -> String.format(numberFormat, e)).collect(joining(",", "[", "]")));
        return String.format("Vector{elements=%s, readOnly=%s}", stringBuilder.toString(), readOnly);
    }

    @Override
    public String toString() {
        return toString(3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IVector)) return false;
        IVector vector = (IVector) o;

        return equals(vector, 4);
    }

    public boolean equals(IVector matrix, int precision) {
        for (int i = 0; i < getDimension(); i++) {
            if (Math.abs(get(i) - matrix.get(i)) > Math.pow(10, -1 * precision))
                return false;

        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }
}
