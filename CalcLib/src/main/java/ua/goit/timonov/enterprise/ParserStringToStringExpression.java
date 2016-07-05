package ua.goit.timonov.enterprise;

import java.util.Iterator;
import java.util.Set;

/**
 * Parser of given String to an object of StringExpression with String representations of expression's
 * arguments and operation using list of permitted operations
 */
public class ParserStringToStringExpression implements StringParser {
    public static final char CLOSING_BRACE = ')';
    public static final String NO_VALUE = "NoValue";
    private StringExpression expression = new StringExpression();
    private char[] charSequence;
    private String foundOperator;

    /**
     * makes parsing from given String to StringExpression
     * @param inputString               given string with expression
     * @param permittedOperations       list od operations that can be executed
     * @return                          object with string representation of arguments and operation
     */
    @Override
    public StringExpression parse(String inputString, PermittedOperations permittedOperations) {

        charSequence = inputString.toCharArray();
        int positionOperator = findOperatorPosition(inputString, permittedOperations);
        readFirstArgument(positionOperator);
        if (positionOperator < 0) {
            positionOperator = 0;
        }
        int startPositionSecondValue = readOperator(positionOperator);
        readSecondArgument(startPositionSecondValue);
        return expression;
    }

    // finds position operator in the string with math expression
    private int findOperatorPosition(String inputString, PermittedOperations permittedOperations) {
        Set<String> setOperators = permittedOperations.getSetOperations();
        Iterator<String> iterator = setOperators.iterator();
        int foundPosition = -1;
        while (iterator.hasNext() && foundPosition < 0) {
            String operator = iterator.next();
            foundPosition = inputString.indexOf(operator);
            if (foundPosition >= 0) {
                foundOperator = operator;
            }
        }
        if (foundPosition >= 0)
            return foundPosition;
        else throw new IllegalArgumentException("There is no supported operator in expession.");
    }

    // reads first argument from the string with math expression
    private void readFirstArgument(int length) {
        if (length > 0) {
            String subString = String.copyValueOf(charSequence, 0, length);
            expression.setValue1(subString);
        }
        else
            expression.setValue1(NO_VALUE);
    }

    // reads operator from the string with math expression
    private int readOperator(int positionOperator) {
        expression.setOperator(foundOperator);
        positionOperator += foundOperator.length();
        return positionOperator;
    }

    // reads second argument from the string with math expression
    private void readSecondArgument(int startPositionSecondValue) {
        int lengthOfSecondValue = charSequence.length - startPositionSecondValue;
        if (charSequence[charSequence.length - 1] == CLOSING_BRACE)
            lengthOfSecondValue--;
        String subString = String.copyValueOf(charSequence, startPositionSecondValue, lengthOfSecondValue);
        if (lengthOfSecondValue > 0)
            expression.setValue2(subString);
        else
            expression.setValue2(NO_VALUE);
    }
}
