package ua.goit.timonov.enterprise;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for CalcNumbers
 */
public class CalcNumbersTest {
    CalcNumbers calculator = new CalcNumbers();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testDoCalcIntegerNormal_1() throws Exception {
        String input = "3+2";
        String actual = calculator.doCalc(input);
        String expected= "5";
        assertEquals(actual, expected);
    }

    @Test
    public void testDoCalcIntegerNormal_2() throws Exception {
        String input = "67+33";
        String actual = calculator.doCalc(input);
        String expected= "100";
        assertEquals(actual, expected);
    }

    @Test
    public void testDoCalcIntegerNormal_3() throws Exception {
        String input = "33-67";
        String actual = calculator.doCalc(input);
        String expected= "-34";
        assertEquals(actual, expected);
    }

    @Test
    public void testDoCalcIntegerNormal_4() throws Exception {
        String input = "5-2";
        String actual = calculator.doCalc(input);
        String expected= "3";
        assertEquals(actual, expected);
    }

    @Test
    public void testDoCalcIntegerNormal_5() throws Exception {
        String input = "35-35";
        String actual = calculator.doCalc(input);
        String expected= "0";
        assertEquals(actual, expected);
    }

    @Test
    public void testDoCalcLongNormal_1() throws Exception {
        String input = "2000000000000000+3000000000000000";
        String actual = calculator.doCalc(input);
        String expected= "5000000000000000";
        assertEquals(actual, expected);
    }

    @Test
    public void testDoCalcLongNormal_2() throws Exception {
        String input = "2150000000000+7";
        String actual = calculator.doCalc(input);
        String expected= "2150000000007";
        assertEquals(actual, expected);
    }

    @Test
    public void testDoCalcDoubleNormal_1() throws Exception {
        String input = "2.5+3.25";
        String actual = calculator.doCalc(input);
        String expected= "5.75";
        assertEquals(actual, expected);
    }

    @Test
    public void testDoCalcDoubleNormal_2() throws Exception {
        String input = "6+7.8";
        String actual = calculator.doCalc(input);
        String expected= "13.8";
        assertEquals(actual, expected);
    }

    @Test
    public void testDoCalcDoubleNormal_3() throws Exception {
        String input = "0.001+2e-3";
        String actual = calculator.doCalc(input);
        String expected= "0.003";
        assertEquals(actual, expected);
    }

    @Test
    public void testDoCalcDoubleNormal_4() throws Exception {
        String input = "2e-3+0.01";
        String actual = calculator.doCalc(input);
        String expected= "0.012";
        assertEquals(actual, expected);
    }

    @Test
    public void testDoCalcDoubleNormal_5() throws Exception {
        String input = "2e-3+3e-4";
        String actual = calculator.doCalc(input);
        String expected= "0.0023";
        assertEquals(actual, expected);
    }

    @Ignore
    @Test
    public void testDoCalcDoubleNormal_6() throws Exception {
        String input = "2e-3-3e-4";
        String actual = calculator.doCalc(input);
        String expected= "0.0017";
        assertEquals(actual, expected);
    }

}