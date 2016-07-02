package ua.goit.timonov.enterprise;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 29.06.2016.
 */
public class FactoryIntegerExpressionTest {
    FactoryExpression factoryExpression = new FactoryIntegerExpression();

    @Test
    public void testParseNormal_1() throws Exception {
        StringExpression stringExpression = new StringExpression("+", "32", "12");
        ExpressionIntegerPlusMinus actual = (ExpressionIntegerPlusMinus) factoryExpression.makeExpression(stringExpression);
        ExpressionIntegerPlusMinus expected = new ExpressionIntegerPlusMinus(32, 12, new OperationIntegerPlus());
        assertEquals(expected.getValue1(), actual.getValue1());
        assertEquals(expected.getValue2(), actual.getValue2());
    }

    @Test
    public void testParseNormal_2() throws Exception {
        StringExpression stringExpression = new StringExpression("-", "1234", "567");
        ExpressionIntegerPlusMinus actual = (ExpressionIntegerPlusMinus) factoryExpression.makeExpression(stringExpression);
        ExpressionIntegerPlusMinus expected = new ExpressionIntegerPlusMinus(1234, 567, new OperationIntegerMinus());
        assertEquals(actual.getValue1(), expected.getValue1());
        assertEquals(actual.getValue2(), expected.getValue2());
    }

}