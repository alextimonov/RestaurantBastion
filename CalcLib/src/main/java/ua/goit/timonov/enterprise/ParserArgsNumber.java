package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class ParserArgsNumber implements ParserArgs {

    @Override
    public Expression getExpression(StringExpression stringExpression) {
        Expression expression;
        try {
            expression = new ParserArgsInteger().getExpression(stringExpression);
        }
        catch (NumberFormatException eInteger)  {
            try {
                expression = new ParserArgsLong().getExpression(stringExpression);
            }
            catch (NumberFormatException eLong) {
                try {
                    expression = new ParserArgsFloat().getExpression(stringExpression);
                }
                catch (NumberFormatException eFloat) {
                    try {
                        expression = new ParserArgsDouble().getExpression(stringExpression);
                    }
                    catch (NumberFormatException eDouble) {
                        throw new IllegalArgumentException("Arguments are not numbers!");
                    }
                }
            }
        }
        return expression;
    }
}
