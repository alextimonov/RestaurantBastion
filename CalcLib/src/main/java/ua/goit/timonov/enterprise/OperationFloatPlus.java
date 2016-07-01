package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 01.07.2016.
 */
public class OperationFloatPlus implements Operation<Float, Float> {
    @Override
    public Float execute(Float... values) {
        return values[0] + values[1];
    }
}
