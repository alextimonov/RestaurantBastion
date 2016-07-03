package ua.goit.timonov.enterprise;

/**
 * Math operation to add two Long values
 */
public class OperationLongPlus implements Operation<Long, Long> {

    /**
     * adds two Long numbers
     * @param values        given Long numbers
     * @return              result of adding two numbers
     */

    @Override
    public Long execute(Long... values) {
        return values[0] + values[1];
    }
}
