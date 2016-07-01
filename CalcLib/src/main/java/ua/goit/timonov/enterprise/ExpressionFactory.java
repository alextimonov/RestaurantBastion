package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 29.06.2016.
 */
public interface ExpressionFactory<A, R> {
    public static final String PLUS = "+";
    public static final String MINUS = "-";

    Expression<A, R> getExpression(StringExpression stringExpression);

}
