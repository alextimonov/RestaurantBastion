package ua.goit.timonov.enterprise;

/**
 * Represents some math expression typified by result data format
 */
public interface Expression<T> {

    /**
     * calculates an expression
     */
    void calculate();

    /**
     * returns result of calculated expression
     * @return      result of expression
     */
    T getResult();
}
