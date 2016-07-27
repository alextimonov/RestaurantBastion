package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 22.07.2016.
 */
public class ParserToStringExpressionForApp extends ParserToStringExpression {

    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";


    public ParserToStringExpressionForApp() {
        super();
        permittedOperations.add(MULTIPLY);
        permittedOperations.add(DIVIDE);
    }
}
