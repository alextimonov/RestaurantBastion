package ua.goit.timonov.enterprise.module_2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing class for IntSquareTask
 */
public class IntTaskTest {

    @Test
    public void testExecuteNormal_1() {
        IntSquareTask task = new IntSquareTask(10);
        task.execute();
        int actual = task.getResult();
        assertEquals(100, actual);
    }

    @Test
    public void testExecuteNormal_2() {
        IntSquareTask task = new IntSquareTask(0);
        task.execute();
        int actual = task.getResult();
        assertEquals(0, actual);
    }

    @Test
    public void testExecuteNormal_3() {
        IntSquareTask task = new IntSquareTask(-20);
        task.execute();
        int actual = task.getResult();
        assertEquals(400, actual);
    }

    @Test (expected = TaskOverflowDataTypeException.class)
    public void testExecuteCatchForException_1() {
        IntSquareTask task = new IntSquareTask(Integer.MAX_VALUE - 1);
        task.execute();
    }

    @Test (expected = TaskOverflowDataTypeException.class)
    public void testExecuteCatchForException_2() {
        IntSquareTask task = new IntSquareTask(Integer.MIN_VALUE + 1);
        task.execute();
    }

    @Test
    public void testGetResultNormal_1() {
        IntSquareTask task = new IntSquareTask(100);
        task.execute();
        int actual = task.getResult();
        assertEquals(10_000, actual);
    }

    @Test
    public void testGetResultNormal_2() {
        IntSquareTask task = new IntSquareTask(0);
        task.execute();
        int actual = task.getResult();
        assertEquals(0, actual);
    }

    @Test
    public void testGetResultNormal_3() {
        IntSquareTask task = new IntSquareTask(-20);
        task.execute();
        int actual = task.getResult();
        assertEquals(400, actual);
    }
}