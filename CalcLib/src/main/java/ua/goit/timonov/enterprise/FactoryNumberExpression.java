package ua.goit.timonov.enterprise;

/**
 * factory to create Expression with number arguments in appropriate format from given StringExpression with arguments
 * presented with String. Supported formats are Integer, Long, Float, Double. Supported operations are addition ("+")
 * and subtracting ("-")
 */
public class FactoryNumberExpression implements FactoryExpression {

    protected String operator;
    private Expression expression;

    /**
     * converts given StringExpression to Expression format arguments in appropriate format
     * @param stringExpression      given expression with arguments in String
     * @return                      Expression with arguments in appropriate format
     * @throws IllegalArgumentException if given arguments are not numbers
     */
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

    // returns expression of ExpressionIntegerPlusMinus for adding or subtracting Integer numbers
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

    // returns expression of ExpressionLongPlusMinus for adding or subtracting Long numbers
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

    // returns expression of ExpressionFloat for adding or subtracting Float numbers
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

    // returns expression of ExpressionDouble for adding or subtracting Double numbers
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
