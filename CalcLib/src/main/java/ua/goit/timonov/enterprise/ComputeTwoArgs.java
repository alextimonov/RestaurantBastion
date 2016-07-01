package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 01.07.2016.
 */
public class ComputeTwoArgs<A, R> implements Compute<A, R> {
    @Override
    public void calculate(Expression<A, R> expression) {
        A value1 = expression.getValue1();
        A value2 = expression.getValue2();
        Operation<A, R> operation = expression.getOperation();
        operation.execute(value1, value2);
        R result = operation.execute(value1, value2);
        expression.setResult(result);
    }
}
