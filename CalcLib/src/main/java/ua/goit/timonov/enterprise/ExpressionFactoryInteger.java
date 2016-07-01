package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 29.06.2016.
 */
public class ExpressionFactoryInteger implements ExpressionFactory<Integer, Integer> {
    private Integer value1;
    private Integer value2;
    Operation<Integer, Integer> operation;
    Expression<Integer, Integer> expression = new Expression<>();

    @Override
    public Expression<Integer, Integer> getExpression(StringExpression stringExpression) {
        value1 = Integer.valueOf(stringExpression.getValue1());
        value2 = Integer.valueOf(stringExpression.getValue2());
        String operator = stringExpression.getOperator();
        if (operator.equals(PLUS)) {
            operation = new OperationIntPlus();
        }
        if (operator.equals(MINUS)) {
            operation = new OperationIntMinus();
        }
        expression.setValue1(value1);
        expression.setValue2(value2);
        expression.setOperation(operation);
        return expression;
    }
}


