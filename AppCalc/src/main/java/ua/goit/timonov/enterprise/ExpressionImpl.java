package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 13.07.2016.
 */
public class ExpressionImpl<A, R> implements Expression<R> {

    private A value1;
    private A value2;
    private Operation<A, R> operation;
    private R result;

    @Override
    public void calculate() {
        System.out.println("ExpressionImpl's calculate");
        result = operation.execute(value1, value2);
    }

    @Override
    public R getResult() {
        return result;
    }
}
