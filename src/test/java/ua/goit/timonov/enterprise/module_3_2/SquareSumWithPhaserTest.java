package ua.goit.timonov.enterprise.module_3_2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing class for SquareSumWithPhaser
 */
public class SquareSumWithPhaserTest {

    SquareSum squareSum = new PhaserSquareSum();

    @Before
    public void setUp() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareSumExpectedIllegalArgumentException_1() {
        int[] values = new int[] {};
        int numberOfThreads = 5;
        squareSum.getSquareSum(values, numberOfThreads);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareSumExpectedIllegalArgumentException_2() {
        int[] values = new int[] {1, 2, 5};
        int numberOfThreads = -3;
        squareSum.getSquareSum(values, numberOfThreads);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareSumExpectedIllegalArgumentException_3()  {
        int[] values = new int[] {1, 2, 5};
        int numberOfThreads = 0;
        squareSum.getSquareSum(values, numberOfThreads);
    }

    @Test
    public void testGetSquareSumDivisionElementsOnThreads_1() {
        int[] values = new int[] {1, 2, 3, 4, 5, 6};
        int numberOfThreads = 3;
        squareSum.getSquareSum(values, numberOfThreads);
        int[] actual = squareSum.getElementsInSegments();
        int[] expected = new int[] {2, 2, 2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testGetSquareSumDivisionElementsOnThreads_2() {
        int[] values = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numberOfThreads = 3;
        squareSum.getSquareSum(values, numberOfThreads);
        int[] actual = squareSum.getElementsInSegments();
        int[] expected = new int[] {4, 3, 3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testGetSquareSumDivisionElementsOnThreads_3() {
        int[] values = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        int numberOfThreads = 4;
        squareSum.getSquareSum(values, numberOfThreads);
        int[] actual = squareSum.getElementsInSegments();
        int[] expected = new int[] {4, 4, 3, 3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testGetSquareSumNormalSimple() {
        int[] values = new int[] {1, 2, 3, 4};
        int numberOfThreads = 2;
        long actual = squareSum.getSquareSum(values, numberOfThreads);
        assertEquals(30, actual);
    }

    @Test
    public void testGetSquareSumNormal_1() {
        int[] values = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        int numberOfThreads = 4;
        long actual = squareSum.getSquareSum(values, numberOfThreads);
        assertEquals(1015, actual);
    }

    @Test
    public void testGetSquareSumNormal_2() {
        int[] values = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        int numberOfThreads = 3;
        long actual = squareSum.getSquareSum(values, numberOfThreads);
        assertEquals(1015, actual);
    }

    @Test
    public void testGetSquareSumBorder() {
        int[] values = new int[] {1, 2, 3, 4, 5, 6, 7};
        int numberOfThreads = 7;
        long actual = squareSum.getSquareSum(values, numberOfThreads);
        assertEquals(140, actual);
    }

    @Test
    public void testGetSquareSumNormalBorder_1() {
        int[] values = new int[] {1, 2, 3, 4, 5};
        int numberOfThreads = 8;
        long actual = squareSum.getSquareSum(values, numberOfThreads);
        assertEquals(55, actual);
    }

    @Test
    public void testGetSquareSumNormalBorder_2() {
        int[] values = new int[] {1, 2, 3, 4, 5};
        int numberOfThreads = 1;
        long actual = squareSum.getSquareSum(values, numberOfThreads);
        assertEquals(55, actual);
    }
}