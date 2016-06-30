package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class ParserArgsDouble implements ParserArgs<Double> {
    private Double value1;
    private Double value2;

    @Override
    public Expression<Double> getExpression(StringExpression stringExpression) {
        value1 = Double.valueOf(stringExpression.getValue1());
        value2 = Double.valueOf(stringExpression.getValue2());
        return new Expression(value1, value2);
    }
}
