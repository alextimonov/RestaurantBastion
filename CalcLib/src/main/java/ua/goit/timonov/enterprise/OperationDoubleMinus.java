package ua.goit.timonov.enterprise;

/**
 * Math operation to subtract two Double values
 */
public class OperationDoubleMinus implements Operation<Double, Double> {

    /**
     * subtracts second Double number from first
     * @param values        given Double numbers
     * @return              result of subtracting of two numbers
     */
    @Override
    public Double execute(Double... values) {
        return values[0] - values[1];
    }
}
