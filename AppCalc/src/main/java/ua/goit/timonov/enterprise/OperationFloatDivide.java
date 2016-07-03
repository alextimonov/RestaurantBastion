package ua.goit.timonov.enterprise;

/**
 * Math operation to divide two Float values
 */
public class OperationFloatDivide implements Operation<Float, Float> {

    /**
     * divides two Float numbers
     * @param values        given Float numbers
     * @return              result of dividing two numbers
     */
    @Override
    public Float execute(Float... values) {
        checkSecondArgument(values[1]);
        return values[0] / values[1];
    }

    // checks if second argument equals to zero
    private void checkSecondArgument(float divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("Divider is equal to zero!");
        }
    }
}
