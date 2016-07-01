package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class ExpressionFactoryDouble implements ExpressionFactory<Double, Double> {
    private Double value1;
    private Double value2;
    Operation<Double, Double> operation;

    @Override
    public Expression<Double, Double> getExpression(StringExpression stringExpression) {
        value1 = Double.valueOf(stringExpression.getValue1());
        value2 = Double.valueOf(stringExpression.getValue2());
        if (stringExpression.getOperator().equals(PLUS)) {
            operation = new OperationDoublePlus();
        }
        if (stringExpression.getOperator().equals(MINUS)) {
            operation = new OperationDoubleMinus();
        }
        return new Expression(value1, value2, operation);
    }
}
