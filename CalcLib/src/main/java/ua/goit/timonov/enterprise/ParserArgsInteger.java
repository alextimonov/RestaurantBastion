package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 29.06.2016.
 */
public class ParserArgsInteger implements ParserArgs<Integer> {
    private Integer value1;
    private Integer value2;

    @Override
    public Expression<Integer> getExpression(StringExpression stringExpression) {
        value1 = Integer.valueOf(stringExpression.getValue1());
        value2 = Integer.valueOf(stringExpression.getValue2());
        return new Expression<Integer>(value1, value2);
    }

}
