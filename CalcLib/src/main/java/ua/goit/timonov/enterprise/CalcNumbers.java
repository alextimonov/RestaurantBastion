package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 29.06.2016.
 */
public class CalcNumbers implements Calc {
    private StringParser stringParser;
    private PermittedOperations permittedOperations = new PermittedOperations();
    private ExpressionFactory expressionFactory;
    private StringExpression stringExpression;

    private Compute compute;

    private Expression expression;
    private String resultString;

    public CalcNumbers() {
        permittedOperations = new PermittedOperations();
    }

    public CalcNumbers(PermittedOperations permittedOperations) {
        this.permittedOperations = permittedOperations;
    }

    public String doCalc(String inputString) {
        stringParser = new StringParserToStringExpression();
        expressionFactory = new ExpressionFactoryNumber();
        stringExpression = stringParser.parse(inputString, permittedOperations);
        expression = expressionFactory.getExpression(stringExpression);
        compute = new ComputeTwoArgs();
        compute.calculate(expression);
        resultString = expression.getResult().toString();
        return resultString;
    }
}

















