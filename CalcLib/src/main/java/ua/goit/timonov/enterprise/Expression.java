package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 21.07.2016.
 */
public interface Expression<Argument, Result> {
    String PLUS = "+";
    String MINUS = "-";

    /**
     * calculates an expressionType
     */
    void calculate();

    /**
     * returns result of calculated expressionType
     * @return      result of expressionType
     */
    Result getResult();

    /**
     * @param stringExpression
     * sets arguments
     */
    void setArguments(StringExpression stringExpression);

}
