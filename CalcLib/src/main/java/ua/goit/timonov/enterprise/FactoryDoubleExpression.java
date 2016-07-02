package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class FactoryDoubleExpression implements FactoryExpression<Double> {
    private Double value1;
    private Double value2;
    Operation<Double, Double> operation;

    public Expression<Double> makeExpression(StringExpression stringExpression) {
        value1 = Double.valueOf(stringExpression.getValue1());
        value2 = Double.valueOf(stringExpression.getValue2());
        String operator = stringExpression.getOperator();
        if (operator.equals(PLUS)) {
            operation = new OperationDoublePlus();
        }
        if (operator.equals(MINUS)) {
            operation = new OperationDoubleMinus();
        }
        return new ExpressionDoublePlusMinus(value1, value2, operation);
    }
}
