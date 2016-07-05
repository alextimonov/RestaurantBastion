package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 04.07.2016.
 */
public class OperationDoubleSquareRoot implements Operation<Double, Double> {

    @Override
    public Double execute(Double... values) {
        checkArgument(values[0]);
        return Math.sqrt(values[0]);
    }

    private void checkArgument(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Can not calculate square root for negative numbers.");
        }
    }
}
