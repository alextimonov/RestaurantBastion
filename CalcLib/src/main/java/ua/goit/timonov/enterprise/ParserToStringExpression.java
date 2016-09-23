package ua.goit.timonov.enterprise;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Parser of given String to an object of StringExpression with String representations of expressionType's
 * arguments and operation using list of permitted operations
 */
public class ParserToStringExpression implements StringParser {
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String SQUARE_ROOT = "sqrt(";
    public static final String LN = "ln(";
    public static final String FACTORIAL = "!";

    public static final char CLOSING_BRACE = ')';
    public static final String NO_VALUE = "NoValue";


    private StringExpression expression = new StringExpression();
    private char[] charSequence;
    private String foundOperator;
    protected Set<String> permittedOperations = new HashSet<>();

    public ParserToStringExpression() {
        permittedOperations.add(PLUS);
        permittedOperations.add(MINUS);
        permittedOperations.add(SQUARE_ROOT);
        permittedOperations.add(LN);
        permittedOperations.add(FACTORIAL);
    }

    /**
     * makes parsing from given String to StringExpression
     * @param inputString               given string with expressionType
     * @return                          object with string representation of arguments and operation
     */
    @Override
    public StringExpression parse(String inputString) {

        charSequence = inputString.toCharArray();
        int positionOperator = findOperatorPosition(inputString);
        readFirstArgument(positionOperator);
        if (positionOperator < 0) {
            positionOperator = 0;
        }
        int startPositionSecondValue = readOperator(positionOperator);
        readSecondArgument(startPositionSecondValue);
        return expression;
    }

    // finds position operator in the string with math expressionType
    private int findOperatorPosition(String inputString) {
        Iterator<String> iterator = permittedOperations.iterator();
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

    // reads first argument from the string with math expressionType
    private void readFirstArgument(int length) {
        if (length > 0) {
            String subString = String.copyValueOf(charSequence, 0, length);
            expression.setValue1(subString);
        }
        else
            expression.setValue1(NO_VALUE);
    }

    // reads operator from the string with math expressionType
    private int readOperator(int positionOperator) {
        expression.setOperator(foundOperator);
        positionOperator += foundOperator.length();
        return positionOperator;
    }

    // reads second argument from the string with math expressionType
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
