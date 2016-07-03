package ua.goit.timonov.enterprise;

/**
 * Math operation to add two Float values
 */
public class OperationFloatPlus implements Operation<Float, Float> {

    /**
     * adds two Float numbers
     * @param values        given Float numbers
     * @return              result of adding two numbers
     */
    @Override
    public Float execute(Float... values) {
        return values[0] + values[1];
    }
}
