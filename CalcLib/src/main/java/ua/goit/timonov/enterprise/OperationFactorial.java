package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 04.07.2016.
 */
public class OperationFactorial implements Operation<Integer, Long> {
    @Override
    public Long execute(Integer... values) {
        checkArgument(values[0]);
        if (values[0] == 0)
            return 0L;
        else {
            Long result = 1L;
            for (int i = 1; i <= values[0]; i++) {
                result *= i;
            }
            return result;
        }
    }

    private void checkArgument(int value) {
        if (value < 0 || value > 20) {
            throw new IllegalArgumentException("Can not calculate factorial for negative numbers or numbers bigger than 20.");
        }
    }
}
