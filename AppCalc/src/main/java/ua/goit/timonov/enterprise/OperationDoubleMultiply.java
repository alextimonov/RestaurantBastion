package ua.goit.timonov.enterprise;

/**
 * Math operation to multiply two Double values
 */
public class OperationDoubleMultiply implements Operation<Double, Double> {

    /**
     * multiplies two Double numbers
     * @param values        given Double numbers
     * @return              result of multiplication two numbers
     */
    @Override
    public Double execute(Double... values) {
        return values[0] * values[1];
    }

}
