package ua.goit.timonov.enterprise.module_2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing class for IntTask
 */
public class IntTaskTest {

    @Test
    public void testExecuteNormal_1() {
        IntTask task = new IntTask(100);
        task.execute();
        int actual = task.getResult();
        assertEquals(200, actual);
    }

    @Test
    public void testExecuteNormal_2() {
        IntTask task = new IntTask(0);
        task.execute();
        int actual = task.getResult();
        assertEquals(0, actual);
    }

    @Test
    public void testExecuteNormal_3() {
        IntTask task = new IntTask(-200);
        task.execute();
        int actual = task.getResult();
        assertEquals(-400, actual);
    }

    @Test (expected = TaskOverflowDataTypeException.class)
    public void testExecuteCatchForException_1() {
        IntTask task = new IntTask(Integer.MAX_VALUE - 1);
        task.execute();
    }

    @Test (expected = TaskOverflowDataTypeException.class)
    public void testExecuteCatchForException_2() {
        IntTask task = new IntTask(Integer.MIN_VALUE + 1);
        task.execute();
    }

    @Test
    public void testGetResultNormal_1() {
        IntTask task = new IntTask(100);
        task.execute();
        int actual = task.getResult();
        assertEquals(200, actual);
    }

    @Test
    public void testGetResultNormal_2() {
        IntTask task = new IntTask(0);
        task.execute();
        int actual = task.getResult();
        assertEquals(0, actual);
    }

    @Test
    public void testGetResultNormal_3() {
        IntTask task = new IntTask(-200);
        task.execute();
        int actual = task.getResult();
        assertEquals(-400, actual);
    }
}