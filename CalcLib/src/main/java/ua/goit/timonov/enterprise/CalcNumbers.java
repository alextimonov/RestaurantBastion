package ua.goit.timonov.enterprise;

/**
 * number calculator to evaluate given math expressionType
 */
public class CalcNumbers implements Calc {
    private StringParser stringParser = new ParserToStringExpression();
    private ExpressionTypeIdentifier expressionTypeIdentifier = new ExpressionTypeIdentifier();
    private ExpressionProvider expressionProvider = new ExpressionProvider();
    private StringExpression stringExpression;
    private Expression expression;

    public CalcNumbers() {
    }

    public CalcNumbers(ExpressionTypeIdentifier expressionTypeIdentifier) {
        this.expressionTypeIdentifier = expressionTypeIdentifier;
    }

    public CalcNumbers(ExpressionTypeIdentifier expressionTypeIdentifier, StringParser stringParser) {
        this(expressionTypeIdentifier);
        this.stringParser = stringParser;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void setExpressionProvider(ExpressionProvider expressionProvider) {
        this.expressionProvider = expressionProvider;
    }

    /**
     * @param inputString given String with math expressionType, String must be in format
     *                    "operand1_operator_operand2" without spaces or underscore, e.g.
     *                    "12+5", "3.25-0.8" etc. Supported operation for base library CalcLib are
     *                    "+" and "-", supported formats of arguments: Integer, Long, Float and Double.
     *                    Also unary operations for evaluating natural logarithm LN(x), square root SQRT(x),
     *                    factorial x! were added recently
     * @return String with result for calculated expressionType
     * @throws RuntimeException if given String does not contain appropriate arguments
     */
    public String doCalc(String inputString) {
        stringExpression = stringParser.parse(inputString);
        String expressionType = expressionTypeIdentifier.identify(stringExpression);
        expression = expressionProvider.getExpression(expressionType);
        expression.setArguments(stringExpression);
        expression.calculate();
        return expression.getResult().toString();
    }


}

















