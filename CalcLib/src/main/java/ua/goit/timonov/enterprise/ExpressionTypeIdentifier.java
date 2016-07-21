package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 21.07.2016.
 */
public class ExpressionTypeIdentifier {

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String SQUARE_ROOT = "sqrt(";
    private static final String LN = "ln(";
    private static final String FACTORIAL = "!";

    protected String operator;
    protected String expressionType;

    /**
     * converts given StringExpression to Expression format arguments in appropriate format
     * @param stringExpression      given expressionType with arguments in String
     * @return                      Expression with arguments in appropriate format
     * @throws IllegalArgumentException if given arguments are not numbers
     */
    public String identify(StringExpression stringExpression) {
        operator = stringExpression.getOperator();
        switch (operator) {
            case SQUARE_ROOT:
            case LN: {
                makeExpressionWithOnlySecondArg(stringExpression);
            }
            break;
            case FACTORIAL: {
                makeExpressionWithOnlyFirstArg(stringExpression);
            }
            break;
            default: {
                makeExpressionWithTwoArgs(stringExpression);
            }
        }
        return expressionType;
    }

    private void makeExpressionWithOnlyFirstArg(StringExpression stringExpression) {
        try {
            Integer.valueOf(stringExpression.getValue1());
            expressionType = "Factorial";
        } catch (NumberFormatException eInteger) {
            throw new IllegalArgumentException("Factorial's argument must be an integer number!");
        }
    }

    private void makeExpressionWithOnlySecondArg(StringExpression stringExpression) {
        try {
            Double.valueOf(stringExpression.getValue2());
            if (operator.equals(SQUARE_ROOT))
                expressionType = "SquareRoot";
            else
                expressionType = "Logarithm";
        } catch (NumberFormatException eDouble) {
            throw new IllegalArgumentException("Arguments are not numbers!");
        }
    }

    private void makeExpressionWithTwoArgs(StringExpression stringExpression) {
        try {
            Integer.valueOf(stringExpression.getValue1());
            Integer.valueOf(stringExpression.getValue2());
            expressionType = makeIntegerExpression();
        } catch (NumberFormatException eInteger) {
            try {
                Long.valueOf(stringExpression.getValue1());
                Long.valueOf(stringExpression.getValue2());
                expressionType = makeLongExpression();
            } catch (NumberFormatException eLong) {
                try {
                    Float.valueOf(stringExpression.getValue1());
                    Float.valueOf(stringExpression.getValue2());
                    expressionType = makeFloatExpression();
                } catch (NumberFormatException eFloat) {
                    try {
                        Double.valueOf(stringExpression.getValue1());
                        Double.valueOf(stringExpression.getValue2());
                        expressionType = makeDoubleExpression();
                    } catch (NumberFormatException eDouble) {
                        throw new IllegalArgumentException("Arguments are not numbers!");
                    }
                }
            }
        }
    }

    // returns expressionType of ExpressionIntegerPlusMinus for adding or subtracting Integer numbers
    protected String makeIntegerExpression() {
        String expressionType = null;
        if (operator.equals(PLUS)) {
            expressionType = "IntegerPlus";
        }
        if (operator.equals(MINUS)) {
            expressionType = "IntegerMinus";
        }
        return expressionType;
    }

    // returns expressionType of ExpressionLongPlusMinus for adding or subtracting Long numbers
    protected String makeLongExpression() {
        String expressionType = null;
        if (operator.equals(PLUS)) {
            expressionType = "LongPlus";
        }
        if (operator.equals(MINUS)) {
            expressionType = "LongMinus";
        }
        return expressionType;
    }

    // returns expressionType of ExpressionFloat for adding or subtracting Float numbers
    protected String makeFloatExpression() {
        String expressionType = null;
        if (operator.equals(PLUS)) {
            expressionType = "FloatPlus";
        }
        if (operator.equals(MINUS)) {
            expressionType = "FloatMinus";
        }
        return expressionType;
    }

    // returns expressionType of ExpressionDouble for adding or subtracting Double numbers
    protected String makeDoubleExpression() {
        String expressionType = null;
        if (operator.equals(PLUS)) {
            expressionType = "DoublePlus";
        }
        if (operator.equals(MINUS)) {
            expressionType = "DoubleMinus";
        }
        return expressionType;
    }
}
