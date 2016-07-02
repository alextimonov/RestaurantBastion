package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 01.07.2016.
 */
public class OperationIntegerDivide implements Operation<Integer, Double> {

    @Override
    public Double execute(Integer... values) {
        checkSecondArgument(values[1]);
        double arg2 = (double) values[1];
        return values[0] / arg2;
    }

    private void checkSecondArgument(int divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("Divider is equal to zero!");
        }
    }
}
