package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class ExpressionFactoryFloat implements ExpressionFactory<Float, Float> {
    private Float value1;
    private Float value2;
    Operation<Float, Float> operation;

    @Override
    public Expression<Float, Float> getExpression(StringExpression stringExpression) {
        value1 = Float.valueOf(stringExpression.getValue1());
        value2 = Float.valueOf(stringExpression.getValue2());
        if (stringExpression.getOperator().equals(PLUS)) {
            operation = new OperationFloatPlus();
        }
        if (stringExpression.getOperator().equals(MINUS)) {
            operation = new OperationFloatMinus();
        }
        return new Expression(value1, value2, operation);
    }
}
