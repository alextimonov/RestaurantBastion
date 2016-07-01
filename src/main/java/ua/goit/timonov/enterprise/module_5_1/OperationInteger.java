package ua.goit.timonov.enterprise.module_5_1;

/**
 * Created by Alex on 01.07.2016.
 */
public class OperationInteger implements Operation<Integer> {
    @Override
    public Integer calc(Integer x1, Integer x2) {
        return x1 + x2;
    }
}
