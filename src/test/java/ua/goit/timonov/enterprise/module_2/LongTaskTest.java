package ua.goit.timonov.enterprise.module_2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing class for LongSquareTask
 */
public class LongTaskTest {

    @Test
    public void testExecuteNormal_1() {
        LongSquareTask task = new LongSquareTask(100L);
        task.execute();
        long actual = task.getResult();
        assertEquals(200, actual);
    }

    @Test
    public void testExecuteNormal_2() {
        LongSquareTask task = new LongSquareTask(0L);
        task.execute();
        long actual = task.getResult();
        assertEquals(0, actual);
    }

    @Test
    public void testExecuteNormal_3() {
        LongSquareTask task = new LongSquareTask(-200L);
        task.execute();
        long actual = task.getResult();
        assertEquals(-400, actual);
    }

    @Test (expected = TaskOverflowDataTypeException.class)
    public void testExecuteCatchForException_1() {
        LongSquareTask task = new LongSquareTask(Long.MAX_VALUE - 1);
        task.execute();
    }

    @Test (expected = TaskOverflowDataTypeException.class)
    public void testExecuteCatchForException_2() {
        LongSquareTask task = new LongSquareTask(Long.MIN_VALUE + 1);
        task.execute();
    }

    @Test
    public void testGetResultNormal_1() {
        LongSquareTask task = new LongSquareTask(100L);
        task.execute();
        long actual = task.getResult();
        assertEquals(200, actual);
    }

    @Test
    public void testGetResultNormal_2() {
        LongSquareTask task = new LongSquareTask(0L);
        task.execute();
        long actual = task.getResult();
        assertEquals(0, actual);
    }

    @Test
    public void testGetResultNormal_3() {
        LongSquareTask task = new LongSquareTask(-200L);
        task.execute();
        long actual = task.getResult();
        assertEquals(-400, actual);
    }
}