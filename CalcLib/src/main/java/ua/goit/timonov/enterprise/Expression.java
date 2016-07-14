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

//    void makeExpression(A value1, A value2, Operation<A, T> operation);
}
