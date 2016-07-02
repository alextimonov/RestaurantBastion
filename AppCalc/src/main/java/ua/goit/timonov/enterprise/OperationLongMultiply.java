package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 02.07.2016.
 */
public class OperationLongMultiply implements Operation<Long, Long> {

    @Override
    public Long execute(Long... values) {
        return values[0] * values[1];
    }

}
