package ua.goit.timonov.enterprise;

/**
 * Represents some math expression typified by result data format
 */
public interface Expression<T> {
    String PLUS = "+";
    String MINUS = "-";

    /**
     * calculates an expression
     */
    void calculate();

    /**
     * returns result of calculated expression
     * @return      result of expression
     */
    T getResult();

    /**
     * @param stringExpression
     * sets arguments
     */
    void setArguments(StringExpression stringExpression);

}
