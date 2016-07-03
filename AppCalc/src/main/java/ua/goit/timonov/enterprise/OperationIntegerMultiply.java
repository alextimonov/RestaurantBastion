package ua.goit.timonov.enterprise;

/**
 * Math operation to multiply two Integer values
 */
public class OperationIntegerMultiply implements Operation<Integer, Integer> {

    /**
     * multiplies two Integer numbers
     * @param values        given Integer numbers
     * @return              result of multiplication two numbers
     */
    @Override
    public Integer execute(Integer... values) {
        return values[0] * values[1];
    }

}
