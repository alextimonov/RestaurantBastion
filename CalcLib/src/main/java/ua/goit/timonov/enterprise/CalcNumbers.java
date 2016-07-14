package ua.goit.timonov.enterprise;

/**
 * number calculator to evaluate given math expression
 */
public class CalcNumbers implements Calc {
    private StringParser stringParser = new ParserStringToStringExpression();
    private PermittedOperations permittedOperations = new PermittedOperations();
    private FactoryExpression factoryExpression = new FactoryNumberExpression();
    private StringExpression stringExpression;
    private Expression expression;
    private Expression expressionToCalc;
    private String resultString;

    public CalcNumbers() {
    }

    public CalcNumbers(PermittedOperations permittedOperations, FactoryExpression factoryExpression) {
        this.permittedOperations = permittedOperations;
        this.factoryExpression = factoryExpression;
    }

    public CalcNumbers(PermittedOperations permittedOperations, FactoryExpression factoryExpression, StringParser stringParser) {
        this(permittedOperations, factoryExpression);
        this.stringParser = stringParser;
    }

    public CalcNumbers(PermittedOperations permittedOperations, FactoryExpression factoryExpression, StringParser stringParser, Expression expression) {
        this(permittedOperations, factoryExpression, stringParser);
        this.expression = expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }


    public void setExpressionToCalc(Expression expressionToCalc) {
        this.expressionToCalc = expressionToCalc;
    }

    public void setStringParser(ParserStringToStringExpression stringParser) {
        this.stringParser = stringParser;
    }

    public void setPermittedOperations(PermittedOperations permittedOperations) {
        this.permittedOperations = permittedOperations;
    }

    public void setFactoryExpression(FactoryExpression factoryExpression) {
        this.factoryExpression = factoryExpression;
    }



    /**
     * @param inputString given String with math expression, String must be in format
     *                    "operand1_operator_operand2" without spaces or underscore, e.g.
     *                    "12+5", "3.25-0.8" etc. Supported operation for base library CalcLib are
     *                    "+" and "-", supported formats of arguments: Integer, Long, Float and Double.
     *                    Also unary operations for evaluating natural logarithm LN(x), square root SQRT(x),
     *                    factorial x! were added recently
     * @return String with result for calculated expression
     * @throws RuntimeException if given String does not contain appropriate arguments
     */
    public String doCalc(String inputString) {
        stringExpression = stringParser.parse(inputString, permittedOperations);
//        expression.calculate();
//        System.out.println(expression.getResult().toString());
        expressionToCalc = factoryExpression.makeExpression(stringExpression);
        expressionToCalc.calculate();
        resultString = expressionToCalc.getResult().toString();
        return resultString;
    }


}

















