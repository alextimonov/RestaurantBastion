package ua.goit.timonov.enterprise;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 29.06.2016.
 */
public class ParserToStringTest {
    StringParser stringParser = new StringParserToStringExpression();
    PermittedOperations permittedOperations = new PermittedOperations();

    @Test
    public void testParseNormal_1() throws Exception {
        String input = "32+12";
        StringExpression actual = stringParser.parse(input, permittedOperations);
        StringExpression expected = new StringExpression("+", "32", "12");
        assertEquals(actual.getOperator(), expected.getOperator());
        assertEquals(actual.getValue1(), expected.getValue1());
        assertEquals(actual.getValue2(), expected.getValue2());
    }

    @Test
    public void testParseNormal_2() throws Exception {
        String input = "1234-567";
        StringExpression actual = stringParser.parse(input, permittedOperations);
        StringExpression expected = new StringExpression("-", "1234", "567");
        assertEquals(actual.getOperator(), expected.getOperator());
        assertEquals(actual.getValue1(), expected.getValue1());
        assertEquals(actual.getValue2(), expected.getValue2());
    }

    @Test
    public void testParseNormal_3() throws Exception {
        String input = "2+1";
        StringExpression actual = stringParser.parse(input, permittedOperations);
        StringExpression expected = new StringExpression("+", "2", "1");
        assertEquals(actual.getOperator(), expected.getOperator());
        assertEquals(actual.getValue1(), expected.getValue1());
        assertEquals(actual.getValue2(), expected.getValue2());
    }

    @Test
    public void testParseBorder_1() throws Exception {
        String input = "-4";
        StringExpression actual = stringParser.parse(input, permittedOperations);
        StringExpression expected = new StringExpression("-", "", "4");
        assertEquals(actual.getOperator(), expected.getOperator());
        assertEquals(actual.getValue1(), expected.getValue1());
        assertEquals(actual.getValue2(), expected.getValue2());
    }

    @Test
    public void testParseBorder_2() throws Exception {
        String input = "+5";
        StringExpression actual = stringParser.parse(input, permittedOperations);
        StringExpression expected = new StringExpression("+", "", "5");
        assertEquals(actual.getOperator(), expected.getOperator());
        assertEquals(actual.getValue1(), expected.getValue1());
        assertEquals(actual.getValue2(), expected.getValue2());
    }
}