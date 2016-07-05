package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 04.07.2016.
 */
public class OperationDoubleLogarithm implements Operation<Double, Double> {

    @Override
    public Double execute(Double... values) {
        checkArgument(values[0]);
        return Math.log(values[0]);
    }

    private void checkArgument(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Can not calculate logarithm for negative numbers.");
        }
    }
}