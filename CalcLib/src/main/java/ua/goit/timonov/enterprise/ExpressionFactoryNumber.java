package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class ExpressionFactoryNumber implements ExpressionFactory {

    @Override
    public Expression getExpression(StringExpression stringExpression) {
        Expression expression;
        try {
            expression = new ExpressionFactoryInteger().getExpression(stringExpression);
        }
        catch (NumberFormatException eInteger)  {
            try {
                expression = new ExpressionFactoryLong().getExpression(stringExpression);
            }
            catch (NumberFormatException eLong) {
                try {
                    expression = new ExpressionFactoryFloat().getExpression(stringExpression);
                }
                catch (NumberFormatException eFloat) {
                    try {
                        expression = new ExpressionFactoryDouble().getExpression(stringExpression);
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
