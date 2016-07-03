package ua.goit.timonov.enterprise;

/**
 * Math operation to add two Integer values
 */
public class OperationIntegerPlus implements Operation<Integer, Integer> {

    /**
     * adds two Integer numbers
     * @param values        given Integer numbers
     * @return              result of adding two numbers
     */

    @Override
    public Integer execute(Integer... values) {
        return values[0] + values[1];
    }

}
