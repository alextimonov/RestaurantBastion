package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 01.07.2016.
 */
public class OperationIntDivide implements Operation<Integer, Double> {

    @Override
    public Double execute(Integer... values) {
        double value1 = values[0];
        double value2 = values[1];
        return value1 / value2;
    }
}

