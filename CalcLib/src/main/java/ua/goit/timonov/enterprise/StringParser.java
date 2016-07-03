package ua.goit.timonov.enterprise;

/**
 * Parser of given String to an object of StringExpression with String representations of expression's
 * arguments and operation using list of permitted operations
 */
public interface StringParser {

    /**
     * makes parsing from given String to StringExpression
     * @param inputString               given string with expression
     * @param permittedOperations       list od operations that can be executed
     * @return                          object with string representation of arguments and operation
     */
    StringExpression parse(String inputString, PermittedOperations permittedOperations);

}
