package ua.goit.timonov.enterprise;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Alex on 01.07.2016.
 */
public class ParserStringToStringExpression implements StringParser {
    private StringExpression expression = new StringExpression();
    private char[] charSequence;

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

    private int findOperatorPosition(String inputString, PermittedOperations permittedOperations) {
        Set<String> setOperators = permittedOperations.getSetOperations();
        Iterator<String> iterator = setOperators.iterator();
        int foundPosition = -1;
        while (iterator.hasNext() && foundPosition < 0) {
            String operator = iterator.next();
            foundPosition = inputString.indexOf(operator);
        }
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
}
