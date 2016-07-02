package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class FactoryNumberExpression implements FactoryExpression {

    public Expression makeExpression(StringExpression stringExpression) {
        Expression expression;
        try {
            expression = new FactoryIntegerExpression().makeExpression(stringExpression);
        }
        catch (NumberFormatException eInteger)  {
            try {
                expression = new FactoryLongExpression().makeExpression(stringExpression);
            }
            catch (NumberFormatException eLong) {
                try {
                    expression = new FactoryFloatExpression().makeExpression(stringExpression);
                }
                catch (NumberFormatException eFloat) {
                    try {
                        expression = new FactoryDoubleExpression().makeExpression(stringExpression);
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
