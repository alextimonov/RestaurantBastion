package ua.goit.timonov.enterprise;

/**
 * number calculator to evaluate given math expression
 */
public class CalcNumbers implements Calc {
    private StringParser stringParser;
    private PermittedOperations permittedOperations = new PermittedOperations();
    private FactoryExpression factoryExpression = new FactoryNumberExpression();
    private StringExpression stringExpression;
    private Expression expression;
    private String resultString;

    public CalcNumbers() {
    }

    public CalcNumbers(PermittedOperations permittedOperations, FactoryExpression factoryExpression) {
        this.permittedOperations = permittedOperations;
        this.factoryExpression = factoryExpression;
    }

    /**
     * @param inputString               given String with math expression, String must be in format
     *                                  "operand1_operator_operand2" without spaces or underscore, e.g.
     *                                  "12+5", "3.25-0.8" etc. Supported operation for base library CalcLib are
     *                                  "+" and "-", supported formats of arguments: Integer, Long, Float and Double.
     *                                  Also unary operations for evaluating natural logarithm LN(x), square root SQRT(x),
     *                                  factorial x! were added recently
     * @return                          String with result for calculated expression
     * @throws RuntimeException         if given String does not contain appropriate arguments
     */
    public String doCalc(String inputString) {
        stringParser = new ParserStringToStringExpression();
        stringExpression = stringParser.parse(inputString, permittedOperations);
        expression = factoryExpression.makeExpression(stringExpression);
        expression.calculate();
        resultString = expression.getResult().toString();
        return resultString;
    }
}

















