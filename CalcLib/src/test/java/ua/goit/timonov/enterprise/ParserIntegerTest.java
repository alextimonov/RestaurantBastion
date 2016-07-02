package ua.goit.timonov.enterprise;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 29.06.2016.
 */
public class ParserIntegerTest {
    ExpressionFactory expressionFactory = new FactoryIntegerExpression();

    @Test
    public void testParseNormal_1() throws Exception {
//        StringExpression stringExpression = new StringExpression("+", "32", "12");
//        Expression actual = expressionFactory.getExpression(stringExpression);
//        Expression expected = new Expression(32, 12);
//        assertEquals(actual.getValue1(), expected.getValue1());
//        assertEquals(actual.getValue2(), expected.getValue2());
    }

    @Test
    public void testParseNormal_2() throws Exception {
//        StringExpression stringExpression = new StringExpression("-", "1234", "567");
//        Expression actual = expressionFactory.getExpression(stringExpression);
//        Expression expected = new Expression(1234, 567);
//        assertEquals(actual.getValue1(), expected.getValue1());
//        assertEquals(actual.getValue2(), expected.getValue2());
    }


    /*@Test
    public void testParseBorder_1() throws Exception {
        String input = "-4";
        OldExpression actual = expressionFactory.parse(input);
        OldExpression expected = new OldExpression("-", null, 4);
        assertEquals(actual.getOperator(), expected.getOperator());
        assertEquals(actual.getValue1(), expected.getValue1());
        assertEquals(actual.getValue2(), expected.getValue2());
    }*/

}