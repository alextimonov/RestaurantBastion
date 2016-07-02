package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 01.07.2016.
 */
public class OperationDoubleDivide implements Operation<Double, Double> {

    @Override
    public Double execute(Double... values) {
        checkSecondArgument(values[1]);
        return values[0] / values[1];
    }

    private void checkSecondArgument(double divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("Divider is equal to zero!");
        }
    }
}
