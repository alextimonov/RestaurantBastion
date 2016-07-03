package ua.goit.timonov.enterprise;

/**
 * Math operation to subtract two Integer values
 */
public class OperationIntegerMinus implements Operation<Integer, Integer> {

    /**
     * subtracts second Integer number from first
     * @param values        given Integer numbers
     * @return              result of subtracting of two numbers
     */
    @Override
    public Integer execute(Integer... values) {
        return values[0] - values[1];
    }
}
