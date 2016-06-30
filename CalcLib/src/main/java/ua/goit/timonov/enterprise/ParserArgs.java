package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 29.06.2016.
 */
public interface ParserArgs<T> {

    Expression<T> getExpression(StringExpression stringExpression);

}
