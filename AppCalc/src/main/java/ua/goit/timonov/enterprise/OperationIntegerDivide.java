package ua.goit.timonov.enterprise;

/**
 * Math operation to divide two Integer values
 */
public class OperationIntegerDivide implements Operation<Integer, Double> {

    /**
     * divides two Integer numbers
     * @param values        given Integer numbers
     * @return              result of dividing two numbers
     */
    @Override
    public Double execute(Integer... values) {
        checkSecondArgument(values[1]);
        double arg2 = (double) values[1];
        return values[0] / arg2;
    }

    // checks if second argument equals to zero
    private void checkSecondArgument(int divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("Divider is equal to zero!");
        }
    }
}
