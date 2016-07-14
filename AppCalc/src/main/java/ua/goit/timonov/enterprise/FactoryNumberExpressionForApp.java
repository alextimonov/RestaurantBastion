package ua.goit.timonov.enterprise;

/**
 * factory to create Expression with number arguments in appropriate format from given StringExpression with arguments
 * presented with String. Supported formats are Integer, Long, Float, Double. Supported operations are addition ("+"),
 * subtracting ("-") and unary operations of natural logarithm LN(x), square root SQRT(x), factorial x! for inherited
 * class FactoryNumberExpression and division("/"), multiplication ("*") in this class
 */
public class FactoryNumberExpressionForApp extends FactoryNumberExpression {
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";

    // returns expression of ExpressionIntegerPlusMinus for adding or subtracting,
    // ExpressionIntegerMultiply for multiplication or ExpressionIntegerDivide for division Integer numbers
    @Override
    protected Expression makeIntegerExpression(Integer value1, Integer value2) {
        expression = super.makeIntegerExpression(value1, value2);
        if (operator.equals(MULTIPLY)) {
            expression = new ExpressionIntegerMultiply (value1, value2, new OperationIntegerMultiply());
        }
        if (operator.equals(DIVIDE)) {
            expression = new ExpressionIntegerDivide(value1, value2, new OperationIntegerDivide());
        }
        return expression;
    }

    // returns expression of ExpressionLongPlusMinus for adding or subtracting,
    // ExpressionLongMultiply for multiplication or ExpressionLongDivide for division Long numbers
    @Override
    protected Expression makeLongExpression(Long value1, Long value2) {
        expression = super.makeLongExpression(value1, value2);
        if (operator.equals(MULTIPLY)) {
            expression = new ExpressionLongMultiply (value1, value2, new OperationLongMultiply());
        }
        if (operator.equals(DIVIDE)) {
            expression = new ExpressionLongDivide(value1, value2, new OperationLongDivide());
        }
        return expression;
    }

    // returns expression of ExpressionFloat for adding, subtracting, multiplication or division
    // Float numbers
    protected Expression makeFloatExpression(Float value1, Float value2) {
        expression = super.makeFloatExpression(value1, value2);
        if (operator.equals(MULTIPLY)) {
            expression = new ExpressionFloat(value1, value2, new OperationFloatMultiply());
        }
        if (operator.equals(DIVIDE)) {
            expression = new ExpressionFloat(value1, value2, new OperationFloatDivide());
        }
        return expression;
    }

    // returns expression of ExpressionDouble for adding, subtracting, multiplication or division
    // Double numbers
    protected Expression makeDoubleExpression(Double value1, Double value2) {
        expression = super.makeDoubleExpression(value1, value2);
        if (operator.equals(MULTIPLY)) {
            expression = new ExpressionDouble(value1, value2, new OperationDoubleMultiply());
        }
        if (operator.equals(DIVIDE)) {
            expression = new ExpressionDouble(value1, value2, new OperationDoubleDivide());
        }
        return expression;
    }
}
