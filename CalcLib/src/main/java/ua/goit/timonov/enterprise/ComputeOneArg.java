package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 01.07.2016.
 */
public class ComputeOneArg<A, R> implements Compute<A, R> {
    @Override
    public void calculate(Expression<A, R> expression) {
        //TODO
        A value = expression.getValue1();
        R result = (R) expression.getOperation().execute(value);
    }
}
