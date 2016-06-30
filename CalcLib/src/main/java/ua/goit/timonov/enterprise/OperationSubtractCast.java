package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class OperationSubtractCast<T> implements Operation<T> {

    @Override
    public void evaluate(Expression<T> expression) {
        T value1 = expression.getValue1();
        T value2 = expression.getValue2();
        if (value1 instanceof Integer) {
            Integer result = (Integer) value1 - (Integer) value2;
            expression.setResult((T) result);
        }
        if (value1 instanceof Long) {
            Long result = (Long) value1 - (Long) value2;
            expression.setResult((T) result);
        }
        if (value1 instanceof Float) {
            Float result = (Float) value1 - (Float) value2;
            expression.setResult((T) result);
        }
        if (value1 instanceof Double) {
            Double result = (Double) value1 - (Double) value2;
            expression.setResult((T) result);
        }
    }
}
