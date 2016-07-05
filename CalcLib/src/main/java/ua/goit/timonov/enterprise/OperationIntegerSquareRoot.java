package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 04.07.2016.
 */
public class OperationIntegerSquareRoot implements Operation<Integer, Double> {

    @Override
    public Double execute(Integer... values) {
        checkArgument(values[0]);
        return Math.sqrt(values[0]);
    }

    private void checkArgument(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Can not calculate square root for negative numbers.");
        }
    }
}
