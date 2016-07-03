package ua.goit.timonov.enterprise;

/**
 * Math operation to divide two Double values
 */
public class OperationDoubleDivide implements Operation<Double, Double> {

    /**
     * divides two Double numbers
     * @param values        given Double numbers
     * @return              result of dividing two numbers
     */
    @Override
    public Double execute(Double... values) {
        checkSecondArgument(values[1]);
        return values[0] / values[1];
    }

    // checks if second argument equals to zero
    private void checkSecondArgument(double divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("Divider is equal to zero!");
        }
    }
}
