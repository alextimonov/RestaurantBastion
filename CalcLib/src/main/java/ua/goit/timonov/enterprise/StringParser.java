package ua.goit.timonov.enterprise;

/**
 * Parser of given String to an object of StringExpression with String representations of expressionType's
 * arguments and operation using list of permitted operations
 */
public interface StringParser {

    /**
     * makes parsing from given String to StringExpression
     * @param inputString               given string with expressionType
     * @return                          object with string representation of arguments and operation
     */
    StringExpression parse(String inputString);

}
