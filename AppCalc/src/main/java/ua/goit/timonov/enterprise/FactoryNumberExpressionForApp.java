package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class FactoryNumberExpressionForApp extends FactoryNumberExpression {
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";

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
