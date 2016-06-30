package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 29.06.2016.
 */
public interface ParserArgs<T> {

    public static final char DIGIT_0 = '0';
    public static final char DIGIT_9 = '9';
    public static final char DOT = '.';
    public static final char SPACE = ' ';
    public static final int RADIX = 10;

    Expression<T> getExpression(StringExpression stringExpression);

}
