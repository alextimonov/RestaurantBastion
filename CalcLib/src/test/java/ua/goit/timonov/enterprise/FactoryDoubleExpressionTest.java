package ua.goit.timonov.enterprise;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 02.07.2016.
 */
public class FactoryDoubleExpressionTest {
    ExpressionFactory expressionFactory = new FactoryDoubleExpression();

    @Test
    public void testParseNormal_1() throws Exception {
        StringExpression stringExpression = new StringExpression("+", "1.75", "3.75");
        ExpressionDoublePlusMinus actual = (ExpressionDoublePlusMinus) expressionFactory.makeExpression(stringExpression);
        ExpressionDoublePlusMinus expected = new ExpressionDoublePlusMinus(1.75, 3.75, new OperationDoublePlus());
        assertEquals(expected.getValue1(), actual.getValue1());
        assertEquals(expected.getValue2(), actual.getValue2());
    }

    @Test
    public void testParseNormal_2() throws Exception {
        StringExpression stringExpression = new StringExpression("-", "5e-3", "0.002");
        ExpressionDoublePlusMinus actual = (ExpressionDoublePlusMinus) expressionFactory.makeExpression(stringExpression);
        ExpressionDoublePlusMinus expected = new ExpressionDoublePlusMinus(0.005, 0.002, new OperationDoubleMinus());
        assertEquals(expected.getValue1(), actual.getValue1());
        assertEquals(expected.getValue2(), actual.getValue2());
    }
}
