package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class ParserArgsFloat implements ParserArgs<Float> {
    private Float value1;
    private Float value2;

    @Override
    public Expression<Float> getExpression(StringExpression stringExpression) {
        value1 = Float.valueOf(stringExpression.getValue1());
        value2 = Float.valueOf(stringExpression.getValue2());
        return new Expression(value1, value2);
    }
}
