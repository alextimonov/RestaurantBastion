package ua.goit.timonov.enterprise;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Alex on 29.06.2016.
 */
public class ParserToStringExpression {
    public static final char DIGIT_0 = '0';
    public static final char DIGIT_9 = '9';
    public static final char CHAR_DOT = '.';
    public static final char CHAR_EXPONENT_UPPER = 'E';
    public static final char CHAR_EXPONENT_LOWER = 'e';
    public static final char CHAR_DOUBLE_UPPER = 'D';
    public static final char CHAR_DOUBLE_LOWER = 'd';
    public static final char CHAR_FLOAT_UPPER = 'F';
    public static final char CHAR_FLOAT_LOWER = 'f';
    public static final char OPENING_BRACE = '(';
    public static final char CLOSING_BRACE = ')';

    private StringExpression expression = new StringExpression();
    private char[] charSequence;

    public StringExpression parse(String inputString, PermittedOperations permittedOperations) {

        charSequence = inputString.toCharArray();
        int positionOperator = findOperatorPosition(inputString, permittedOperations);
        readFirstArgument(positionOperator);
        int startPositionSecondValue = readOperator(positionOperator);
        readSecondArgument(startPositionSecondValue);
        return expression;
    }

    private int findOperatorPosition(String inputString, PermittedOperations permittedOperations) {
        Set<String> setOperators = permittedOperations.getKeySet();
        Iterator<String> iterator = setOperators.iterator();
        int foundPosition = -1;
        while (iterator.hasNext() && foundPosition < 0) {
            String operator = iterator.next();
            foundPosition = inputString.indexOf(operator);
        }

        /*int foundPosition = 0;
        while (foundPosition < charSequence.length && isPartOfNumber(charSequence[foundPosition])) {
            foundPosition++;
        }*/
        return foundPosition;
    }

    private void readFirstArgument(int length) {
        if (length > 0) {
            String subString = String.copyValueOf(charSequence, 0, length);
            expression.setValue1(subString);
        }
        else
            expression.setValue1("");
    }

    private int readOperator(int positionOperator) {
        String operator = String.valueOf(charSequence[positionOperator++]);
        expression.setOperator(operator);
        return positionOperator;
    }

    private void readSecondArgument(int startPositionSecondValue) {
        int lengthOfSecondValue = charSequence.length - startPositionSecondValue;
//        if (inputSequence[inputSequence.length - 1] == CLOSING_BRACE)
//            lengthOfSecondValue--;
        String subString = String.copyValueOf(charSequence, startPositionSecondValue, lengthOfSecondValue);
        expression.setValue2(subString);
    }

    /*private boolean isPartOfNumber(char symbol) {
        return isaDigit(symbol) || isFloatMarker(symbol) || isDoubleMarker(symbol);
    }

    private boolean isaDigit(char symbol) {
        return symbol >= DIGIT_0 && symbol <= DIGIT_9;
    }

    private boolean isFloatMarker(char symbol) {
        return symbol == CHAR_FLOAT_LOWER || symbol == CHAR_EXPONENT_UPPER;
    }

    private boolean isDoubleMarker(char symbol) {
        return symbol == CHAR_DOT || symbol == CHAR_DOUBLE_LOWER || symbol == CHAR_DOUBLE_UPPER ||
                symbol == CHAR_EXPONENT_LOWER || symbol == CHAR_EXPONENT_UPPER;
    }*/
}
