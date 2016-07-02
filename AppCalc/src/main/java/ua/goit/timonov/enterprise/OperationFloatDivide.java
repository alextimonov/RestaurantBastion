package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 01.07.2016.
 */
public class OperationFloatDivide implements Operation<Float, Float> {

    @Override
    public Float execute(Float... values) {
        checkSecondArgument(values[1]);
        return values[0] / values[1];
    }

    private void checkSecondArgument(float divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("Divider is equal to zero!");
        }
    }
}
