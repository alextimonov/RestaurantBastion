package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 01.07.2016.
 */
public class OperationIntMinus implements Operation<Integer, Integer> {

    @Override
    public Integer execute(Integer... values) {
        return values[0] - values[1];
    }
}
