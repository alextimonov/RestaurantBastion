package ua.goit.timonov.enterprise;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Alex on 01.07.2016.
 */
public class PermittedOperationsTest {
    public static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String SQUARE_ROOT = "sqrt(";
    private static final String LN = "ln(";
    private static final String FACTORIAL = "!";

    PermittedOperations permittedOperations = new PermittedOperations();

    @Test
    public void testGetSetOperations() throws Exception {
        Set<String> expected = new HashSet<>();
        expected.add(PLUS);
        expected.add(MINUS);
        expected.add(SQUARE_ROOT);
        expected.add(LN);
        expected.add(FACTORIAL);
        Set<String> actual = permittedOperations.getSetOperations();
        assertEquals(expected, actual);
    }
}