package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 01.07.2016.
 */
public class OperationLongDivide implements Operation<Long, Double> {

    @Override
    public Double execute(Long... values) {
        checkSecondArgument(values[1]);
        double arg2 = (double) values[1];
        return values[0] / arg2;
    }

    private void checkSecondArgument(long divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("Divider is equal to zero!");
        }
    }
}
