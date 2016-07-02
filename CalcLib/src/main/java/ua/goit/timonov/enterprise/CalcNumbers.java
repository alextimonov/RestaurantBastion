package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 29.06.2016.
 */
public class CalcNumbers implements Calc {
    private StringParser stringParser;
    private PermittedOperations permittedOperations = new PermittedOperations();
    private FactoryExpression factoryExpression;
    private StringExpression stringExpression;
    private Expression expression;
    private String resultString;

    public CalcNumbers() {
        permittedOperations = new PermittedOperations();
    }

    public CalcNumbers(PermittedOperations permittedOperations) {
        this.permittedOperations = permittedOperations;
    }

    public String doCalc(String inputString) {
        stringParser = new ParserStringToStringExpression();
        stringExpression = stringParser.parse(inputString, permittedOperations);
        factoryExpression = new FactoryNumberExpression();
        expression = factoryExpression.makeExpression(stringExpression);
        expression.calculate();
        resultString = expression.getResult().toString();
        return resultString;
    }
}

















