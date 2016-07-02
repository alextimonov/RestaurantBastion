package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 29.06.2016.
 */
public interface ExpressionFactory<T> {
    public static final String PLUS = "+";
    public static final String MINUS = "-";

    Expression<T> makeExpression(StringExpression stringExpression);

}
