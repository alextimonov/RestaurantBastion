package ua.goit.timonov.enterprise;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 02.07.2016.
 */
public class FactoryFloatExpressionTest {

    @Test
    public void testParseNormal_1() throws Exception {
        StringExpression stringExpression = new StringExpression("+", "1.75", "3.75");
        ExpressionFloatPlus actual = new ExpressionFloatPlus();
        ExpressionFloatPlus expected = new ExpressionFloatPlus(1.75f, 3.75F);
        assertEquals(expected.getValue1(), actual.getValue1());
        assertEquals(expected.getValue2(), actual.getValue2());
    }

    @Test
    public void testParseNormal_2() throws Exception {
        StringExpression stringExpression = new StringExpression("-", "5e-3", "0.002");
        ExpressionFloatPlus actual = new ExpressionFloatPlus();
        ExpressionFloatPlus expected = new ExpressionFloatPlus(0.005F, 0.002f);
        assertEquals(expected.getValue1(), actual.getValue1());
        assertEquals(expected.getValue2(), actual.getValue2());
    }
}
