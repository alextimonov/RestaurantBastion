package ua.goit.timonov.enterprise;

import java.util.Locale;

/**
 * Created by Alex on 30.06.2016.
 */
public class FactoryLongExpression implements ExpressionFactory<Long> {
    private Long value1;
    private Long value2;
    Operation<Long, Long> operation;

    public Expression<Long> makeExpression(StringExpression stringExpression) {
        value1 = Long.valueOf(stringExpression.getValue1());
        value2 = Long.valueOf(stringExpression.getValue2());
        String operator = stringExpression.getOperator();
        if (operator.equals(PLUS)) {
            operation = new OperationLongPlus();
        }
        if (operator.equals(MINUS)) {
            operation = new OperationLongMinus();
        }
        return new ExpressionLongPlusMinus(value1, value2, operation);
    }
}
