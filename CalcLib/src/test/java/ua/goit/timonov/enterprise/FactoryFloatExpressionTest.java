package ua.goit.timonov.enterprise;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 02.07.2016.
 */
public class FactoryFloatExpressionTest {
    FactoryExpression factoryExpression = new FactoryNumberExpression();

    @Test
    public void testParseNormal_1() throws Exception {
        StringExpression stringExpression = new StringExpression("+", "1.75", "3.75");
        ExpressionFloatPlusMinus actual = (ExpressionFloatPlusMinus) factoryExpression.makeExpression(stringExpression);
        ExpressionFloatPlusMinus expected = new ExpressionFloatPlusMinus(1.75f, 3.75F, new OperationFloatPlus());
        assertEquals(expected.getValue1(), actual.getValue1());
        assertEquals(expected.getValue2(), actual.getValue2());
    }

    @Test
    public void testParseNormal_2() throws Exception {
        StringExpression stringExpression = new StringExpression("-", "5e-3", "0.002");
        ExpressionFloatPlusMinus actual = (ExpressionFloatPlusMinus) factoryExpression.makeExpression(stringExpression);
        ExpressionFloatPlusMinus expected = new ExpressionFloatPlusMinus(0.005F, 0.002f, new OperationFloatMinus());
        assertEquals(expected.getValue1(), actual.getValue1());
        assertEquals(expected.getValue2(), actual.getValue2());
    }
}
