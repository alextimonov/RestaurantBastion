package ua.goit.timonov.enterprise;

/**
 * Math operation to multiply two Long values
 */

public class OperationLongMultiply implements Operation<Long, Long> {

    /**
     * multiplies two Long numbers
     * @param values        given Long numbers
     * @return              result of multiplication two numbers
     */
    @Override
    public Long execute(Long... values) {
        return values[0] * values[1];
    }

}
