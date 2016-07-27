package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 22.07.2016.
 */
public class ExpressionTypeIdentifierForApp extends ExpressionTypeIdentifier {

    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";

    // returns expressionType of ExpressionIntegerPlusMinus for adding or subtracting,
    // ExpressionIntegerMultiply for multiplication or ExpressionIntegerDivide for division Integer numbers
    @Override
    protected String makeIntegerExpression() {
        expressionType = super.makeIntegerExpression();
        if (operator.equals(MULTIPLY)) {
            expressionType = "IntegerMultiply";
        }
        if (operator.equals(DIVIDE)) {
            expressionType = "IntegerDivide";
        }
        return expressionType;
    }

    // returns expressionType of ExpressionLongPlusMinus for adding or subtracting,
    // ExpressionLongMultiply for multiplication or ExpressionLongDivide for division Long numbers
    @Override
    protected String makeLongExpression() {
        expressionType = super.makeLongExpression();
        if (operator.equals(MULTIPLY)) {
            expressionType = "LongMultiply";
        }
        if (operator.equals(DIVIDE)) {
            expressionType = "LongDivide";
        }
        return expressionType;
    }

    // returns expressionType of ExpressionFloat for adding, subtracting, multiplication or division
    // Float numbers
    @Override
    protected String makeFloatExpression() {
        expressionType = super.makeFloatExpression();
        if (operator.equals(MULTIPLY)) {
            expressionType = "FloatMultiply";
        }
        if (operator.equals(DIVIDE)) {
            expressionType = "FloatDivide";
        }
        return expressionType;
    }

    // returns expressionType of ExpressionDouble for adding, subtracting, multiplication or division
    // Double numbers
    @Override
    protected String makeDoubleExpression() {
        expressionType = super.makeDoubleExpression();
        if (operator.equals(MULTIPLY)) {
            expressionType = "DoubleMultiply";
        }
        if (operator.equals(DIVIDE)) {
            expressionType = "DoubleDivide";
        }
        return expressionType;
    }
}
