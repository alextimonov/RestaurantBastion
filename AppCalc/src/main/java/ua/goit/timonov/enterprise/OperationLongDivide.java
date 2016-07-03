package ua.goit.timonov.enterprise;

/**
 * Math operation to divide two Long values
 */
public class OperationLongDivide implements Operation<Long, Double> {

    /**
     * divides two Long numbers
     * @param values        given Long numbers
     * @return              result of dividing two numbers
     */
    @Override
    public Double execute(Long... values) {
        checkSecondArgument(values[1]);
        double arg2 = (double) values[1];
        return values[0] / arg2;
    }

    // checks if second argument equals to zero
    private void checkSecondArgument(long divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("Divider is equal to zero!");
        }
    }
}
