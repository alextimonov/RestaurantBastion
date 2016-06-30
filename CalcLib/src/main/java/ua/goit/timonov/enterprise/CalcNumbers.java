package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 29.06.2016.
 */
public class CalcNumbers implements Calc {
    private ParserAlgebraicToStringExpression stringParser;
    private PermittedOperations permittedOperations;
    private ParserArgs parserArgs;
    private Operation operation;
    private Expression expression;
    private String resultString;

    public CalcNumbers() {
    }

    public String doCalc(String inputString) {
        stringParser = new ParserAlgebraicToStringExpression();
        permittedOperations = new PermittedOperations();
        parserArgs = new ParserArgsNumber();

        StringExpression stringExpression = stringParser.parse(inputString, permittedOperations);
        expression = parserArgs.getExpression(stringExpression);
//        operation = parserOperation.getOperation(stringExpression, permittedOperations);
        operation = permittedOperations.getOperation(stringExpression.getOperator());
        operation.evaluate(expression);
        resultString = expression.getResult().toString();
        return resultString;
    }
}
