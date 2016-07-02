package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class FactoryNumberExpression implements FactoryExpression {

    protected String operator;
    private Expression expression;

    public Expression makeExpression(StringExpression stringExpression) {
        operator = stringExpression.getOperator();
        try {
            Integer value1 = Integer.valueOf(stringExpression.getValue1());
            Integer value2 = Integer.valueOf(stringExpression.getValue2());
            expression = makeIntegerExpression(value1, value2);
        } catch (NumberFormatException eInteger) {
            try {
                Long value1 = Long.valueOf(stringExpression.getValue1());
                Long value2 = Long.valueOf(stringExpression.getValue2());
                expression = makeLongExpression(value1, value2);
            } catch (NumberFormatException eLong) {
                try {
                    Float value1 = Float.valueOf(stringExpression.getValue1());
                    Float value2 = Float.valueOf(stringExpression.getValue2());
                    expression = makeFloatExpression(value1, value2);
                } catch (NumberFormatException eFloat) {
                    try {
                        Double value1 = Double.valueOf(stringExpression.getValue1());
                        Double value2 = Double.valueOf(stringExpression.getValue2());
                        expression = makeDoubleExpression(value1, value2);
                    } catch (NumberFormatException eDouble) {
                        throw new IllegalArgumentException("Arguments are not numbers!");
                    }
                }
            }
        }
        return expression;
    }

    protected Expression makeIntegerExpression(Integer value1, Integer value2) {
        Expression integerExpression = null;
        if (operator.equals(PLUS)) {
            integerExpression = new ExpressionIntegerPlusMinus(value1, value2, new OperationIntegerPlus());
        }
        if (operator.equals(MINUS)) {
            integerExpression = new ExpressionIntegerPlusMinus(value1, value2, new OperationIntegerMinus());
        }
        return integerExpression;
    }

    protected Expression makeLongExpression(Long value1, Long value2) {
        Expression longExpression = null;
        if (operator.equals(PLUS)) {
            longExpression = new ExpressionLongPlusMinus(value1, value2, new OperationLongPlus());
        }
        if (operator.equals(MINUS)) {
            longExpression = new ExpressionLongPlusMinus(value1, value2, new OperationLongMinus());
        }
        return longExpression;
    }

    protected Expression makeFloatExpression(Float value1, Float value2) {
        Expression floatExpression = null;
        if (operator.equals(PLUS)) {
            floatExpression = new ExpressionFloat(value1, value2, new OperationFloatPlus());
        }
        if (operator.equals(MINUS)) {
            floatExpression = new ExpressionFloat(value1, value2, new OperationFloatMinus());
        }
        return floatExpression;
    }

    protected Expression makeDoubleExpression(Double value1, Double value2) {
        Expression doubleExpression = null;
        if (operator.equals(PLUS)) {
            doubleExpression = new ExpressionDouble(value1, value2, new OperationDoublePlus());
        }
        if (operator.equals(MINUS)) {
            doubleExpression = new ExpressionDouble(value1, value2, new OperationDoubleMinus());
        }
        return doubleExpression;
    }
}
