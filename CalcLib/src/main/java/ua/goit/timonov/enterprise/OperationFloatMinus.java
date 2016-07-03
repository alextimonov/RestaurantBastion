package ua.goit.timonov.enterprise;

/**
 * Math operation to subtract two Float values
 */
public class OperationFloatMinus implements Operation<Float, Float> {

    /**
     * subtracts second Float number from first
     * @param values        given Float numbers
     * @return              result of subtracting of two numbers
     */
    @Override
    public Float execute(Float... values) {
        return values[0] - values[1];
    }
}
