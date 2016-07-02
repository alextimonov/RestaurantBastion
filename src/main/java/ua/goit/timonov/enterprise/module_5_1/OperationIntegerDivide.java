package ua.goit.timonov.enterprise.module_5_1;

/**
 * Created by Alex on 01.07.2016.
 */
public class OperationIntegerDivide implements Operation<Integer, Double> {
    @Override
    public Double calc(Integer x1, Integer x2) {
        double d2 = (double) x2;
        return  x1 / d2;
    }
}
