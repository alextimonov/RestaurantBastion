package ua.goit.timonov.enterprise;

/**
 * factory to create Expression with number arguments in appropriate format from given StringExpression with arguments
 * presented with String. Supported formats are Integer, Long, Float, Double. Supported operations are addition ("+"),
 * subtracting ("-") for inherited class FactoryNumberExpression and division("/"), multiplication ("*") in this class
 */
public class FactoryNumberExpressionForApp extends FactoryNumberExpression {
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";

    // returns expression of ExpressionIntegerPlusMinus for adding or subtracting,
    // ExpressionIntegerMultiply for multiplication or ExpressionIntegerDivide for division Integer numbers
    @Override
    protected Expression makeIntegerExpression(Integer value1, Integer value2) {
        Expression integerExpression = super.makeIntegerExpression(value1, value2);
        if (operator.equals(MULTIPLY)) {
            integerExpression = new ExpressionIntegerMultiply (value1, value2, new OperationIntegerMultiply());
        }
        if (operator.equals(DIVIDE)) {
            integerExpression = new ExpressionIntegerDivide(value1, value2, new OperationIntegerDivide());
        }
        return integerExpression;
    }

    // returns expression of ExpressionLongPlusMinus for adding or subtracting,
    // ExpressionLongMultiply for multiplication or ExpressionLongDivide for division Long numbers
    @Override
    protected Expression makeLongExpression(Long value1, Long value2) {
        Expression longExpression = super.makeLongExpression(value1, value2);
        if (operator.equals(MULTIPLY)) {
            longExpression = new ExpressionLongMultiply (value1, value2, new OperationLongMultiply());
        }
        if (operator.equals(DIVIDE)) {
            longExpression = new ExpressionLongDivide(value1, value2, new OperationLongDivide());
        }
        return longExpression;
    }

    // returns expression of ExpressionFloat for adding, subtracting, multiplication or division
    // Float numbers
    protected Expression makeFloatExpression(Float value1, Float value2) {
        Expression floatExpression = super.makeFloatExpression(value1, value2);
        if (operator.equals(MULTIPLY)) {
            floatExpression = new ExpressionFloat(value1, value2, new OperationFloatMultiply());
        }
        if (operator.equals(DIVIDE)) {
            floatExpression = new ExpressionFloat(value1, value2, new OperationFloatDivide());
        }
        return floatExpression;
    }

    // returns expression of ExpressionDouble for adding, subtracting, multiplication or division
    // Double numbers
    protected Expression makeDoubleExpression(Double value1, Double value2) {
        Expression doubleExpression = super.makeDoubleExpression(value1, value2);
        if (operator.equals(MULTIPLY)) {
            doubleExpression = new ExpressionDouble(value1, value2, new OperationDoubleMultiply());
        }
        if (operator.equals(DIVIDE)) {
            doubleExpression = new ExpressionDouble(value1, value2, new OperationDoubleDivide());
        }
        return doubleExpression;
    }
}
