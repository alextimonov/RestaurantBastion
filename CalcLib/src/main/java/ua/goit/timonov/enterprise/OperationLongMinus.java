package ua.goit.timonov.enterprise;

/**
 * Math operation to subtract two Long values
 */
public class OperationLongMinus implements Operation<Long, Long> {

    /**
     * subtracts second Long number from first
     * @param values        given Long numbers
     * @return              result of subtracting of two numbers
     */

    @Override
    public Long execute(Long... values) {
        return values[0] - values[1];
    }
}
