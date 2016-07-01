package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class OldOperationDivide<T> implements OldOperation<T> {
    @Override
    public void evaluate(OldExpression<T> oldExpression) {
        T value1 = oldExpression.getValue1();
        T value2 = oldExpression.getValue2();
        if (value1 instanceof Integer) {
            Integer result = (Integer) value1 / (Integer) value2;
            oldExpression.setResult((T) result);
        }
        if (value1 instanceof Long) {
            Long result = (Long) value1 / (Long) value2;
            oldExpression.setResult((T) result);
        }
        if (value1 instanceof Float) {
            Float result = (Float) value1 / (Float) value2;
            oldExpression.setResult((T) result);
        }
        if (value1 instanceof Double) {
            Double result = (Double) value1 / (Double) value2;
            oldExpression.setResult((T) result);
        }
    }
}
