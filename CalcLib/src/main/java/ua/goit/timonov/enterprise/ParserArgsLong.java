package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class ParserArgsLong implements ParserArgs<Long> {
    private Long value1;
    private Long value2;

    @Override
    public Expression<Long> getExpression(StringExpression stringExpression) {
        value1 = Long.valueOf(stringExpression.getValue1());
        value2 = Long.valueOf(stringExpression.getValue2());
        return new Expression(value1, value2);
    }

}
