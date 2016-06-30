package ua.goit.timonov.enterprise;

import java.util.Scanner;

/**
 * Created by Alex on 29.06.2016.
 */
public class CalcNumbers implements Calc {
    private String inputString;
    private ParserToStringExpression stringParser = new ParserToStringExpression();
    private PermittedOperations permittedOperations = new PermittedOperations();
    private ParserArgs parserArgs = new ParserArgsNumber();
    private ParserOperation parserOperation = new ParserPermittedOperation();

    private Operation operation;

    private Expression expression;
    private String resultString;

    public CalcNumbers() {
    }

    public CalcNumbers(String inputString) {
        this.inputString = inputString;
    }

    public void setParserArgs(ParserArgs parserArgs) {
        this.parserArgs = parserArgs;
    }

    public String doCalc(String inputString) {
        StringExpression stringExpression = stringParser.parse(inputString, permittedOperations);
        expression = parserArgs.getExpression(stringExpression);
        operation = parserOperation.getOperation(stringExpression, permittedOperations);
        operation.evaluate(expression);
        resultString = expression.getResult().toString();
        return resultString;
    }

    void inputStringFromConsol() {
        System.out.println("Input string: ");
        Scanner scan = new Scanner(System.in);
        inputString = scan.next();
        scan.close();
    }
}
