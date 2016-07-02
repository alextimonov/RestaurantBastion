package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 29.06.2016.
 */
public class FactoryIntegerExpression implements ExpressionFactory<Integer> {
    private Integer value1;
    private Integer value2;
    Operation<Integer, Integer> operation;

    public Expression<Integer> makeExpression(StringExpression stringExpression) {
        value1 = Integer.valueOf(stringExpression.getValue1());
        value2 = Integer.valueOf(stringExpression.getValue2());
        String operator = stringExpression.getOperator();
        if (operator.equals(PLUS)) {
            operation = new OperationIntegerPlus();
        }
        if (operator.equals(MINUS)) {
            operation = new OperationIntegerMinus();
        }
        return new ExpressionIntegerPlusMinus(value1, value2, operation);
    }
}
