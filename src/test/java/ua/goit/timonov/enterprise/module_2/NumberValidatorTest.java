package ua.goit.timonov.enterprise.module_2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing class for NumberValidator
 */
public class NumberValidatorTest {
    NumberValidator validator = new NumberValidator();

    @Test
    public void testIsValidNormal_1() {
        boolean actual = validator.isValid(100);
        assertEquals(true, actual);
    }

    @Test
    public void testIsValidNormal_2() {
        boolean actual = validator.isValid(-12.34);
        assertEquals(false, actual);
    }

    @Test
    public void testIsValidNormal_3() {
        boolean actual = validator.isValid(.3e-5);
        assertEquals(true, actual);
    }

    @Test
    public void testIsValidNormal_4() {
        boolean actual = validator.isValid(Long.MAX_VALUE);
        assertEquals(true, actual);
    }

    @Test
    public void testIsValidNormal_5() {
        boolean actual = validator.isValid(0L);
        assertEquals(true, actual);
    }

    @Test
    public void testIsValidNormal_6() {
        boolean actual = validator.isValid(-10);
        assertEquals(false, actual);
    }
}