package ua.goit.timonov.enterprise;

import java.util.Locale;

/**
 * Created by Alex on 30.06.2016.
 */
public class ExpressionFactoryLong implements ExpressionFactory<Long, Long> {
    private Long value1;
    private Long value2;
    Operation<Long, Long> operation;

    @Override
    public Expression<Long, Long> getExpression(StringExpression stringExpression) {
        value1 = Long.valueOf(stringExpression.getValue1());
        value2 = Long.valueOf(stringExpression.getValue2());
        if (stringExpression.getOperator().equals(PLUS)) {
            operation = new OperationLongPlus();
        }
        if (stringExpression.getOperator().equals(MINUS)) {
            operation = new OperationLongMinus();
        }
        return new Expression(value1, value2, operation);
    }
}
