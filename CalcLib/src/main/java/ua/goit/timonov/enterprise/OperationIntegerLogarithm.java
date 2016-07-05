package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 04.07.2016.
 */
public class OperationIntegerLogarithm implements Operation<Integer, Double> {

    @Override
    public Double execute(Integer... values) {
        checkArgument(values[0]);
        return Math.log(values[0]);
    }

    private void checkArgument(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Can not calculate logarithm for negative numbers.");
        }
    }
}
