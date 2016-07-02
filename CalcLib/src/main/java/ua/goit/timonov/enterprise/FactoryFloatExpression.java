package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class FactoryFloatExpression implements ExpressionFactory<Float> {
    private Float value1;
    private Float value2;
    Operation<Float, Float> operation;

    public Expression<Float> makeExpression(StringExpression stringExpression) {
        value1 = Float.valueOf(stringExpression.getValue1());
        value2 = Float.valueOf(stringExpression.getValue2());
        String operator = stringExpression.getOperator();
        if (operator.equals(PLUS)) {
            operation = new OperationFloatPlus();
        }
        if (operator.equals(MINUS)) {
            operation = new OperationFloatMinus();
        }
        return new ExpressionFloatPlusMinus(value1, value2, operation);
    }
}
