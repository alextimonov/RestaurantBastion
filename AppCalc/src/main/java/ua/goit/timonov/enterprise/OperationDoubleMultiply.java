package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 02.07.2016.
 */
public class OperationDoubleMultiply implements Operation<Double, Double> {

    @Override
    public Double execute(Double... values) {
        return values[0] * values[1];
    }

}
