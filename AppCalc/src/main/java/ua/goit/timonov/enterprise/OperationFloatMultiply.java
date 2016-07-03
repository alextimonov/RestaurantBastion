package ua.goit.timonov.enterprise;

/**
 * Math operation to multiply two Float values
 */
public class OperationFloatMultiply implements Operation<Float, Float> {

    /**
     * multiplies two Float numbers
     * @param values        given Float numbers
     * @return              result of multiplication two numbers
     */

    @Override
    public Float execute(Float... values) {
        return values[0] * values[1];
    }

}
