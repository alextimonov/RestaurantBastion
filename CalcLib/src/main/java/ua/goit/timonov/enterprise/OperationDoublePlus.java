package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 01.07.2016.
 */
public class OperationDoublePlus implements Operation<Double, Double> {

    @Override
    public Double execute(Double... values) {
        return values[0] + values[1];
    }
}
