package ua.goit.timonov.enterprise;

/**
 * Math operation to add two Double values
 */
public class OperationDoublePlus implements Operation<Double, Double> {

    /**
     * adds two Double numbers
     * @param values        given Double numbers
     * @return              result of adding two numbers
     */
    @Override
    public Double execute(Double... values) {
        return values[0] + values[1];
    }
}
