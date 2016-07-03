package ua.goit.timonov.enterprise;

/**
 * factory to create Expression with arguments in appropriate format from given StringExpression with arguments
 * presented with String
 */
public interface FactoryExpression<T> {
    String PLUS = "+";
    String MINUS = "-";

    /**
     * converts given StringExpression to Expression format arguments in appropriate format
     * @param stringExpression      given expression with arguments in String
     * @return                      Expression with arguments in appropriate format
     */
    Expression<T> makeExpression(StringExpression stringExpression);

}
