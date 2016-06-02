package ua.goit.timonov.enterprise.module_2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing class for NumberExecutor
 */
public class NumberExecutorTest {

    @Test
    public void testAddTaskNormal() {
        NumberExecutor executor = new NumberExecutor();
        List<TaskWithValidator> schedule = executor.getTaskSchedule();
        List<Task> testTaskList = new ArrayList<>();

        Task task = new IntSquareTask(10);
        executor.addTask(task);
        testTaskList.add(task);

        task = new IntSquareTask(-20);
        executor.addTask(task);
        testTaskList.add(task);

        int i = 0;
        for (TaskWithValidator taskWithValidator : schedule) {
            Task actual = taskWithValidator.getTask();
            Task expected = testTaskList.get(i++);
            assertEquals(actual, expected);
        }
    }

    @Test (expected = ExecuteWasInvokedException.class)
    public void testAddTaskCatchException() {
        NumberExecutor executor = new NumberExecutor();
        executor.execute();
        executor.addTask(new IntSquareTask(10));
    }

    @Test
    public void testExecuteLong() throws Exception {
        List<Task<Long>> longTasks = new ArrayList<>();
        longTasks.add(new LongSquareTask(5L));
        longTasks.add(new LongSquareTask(12L));
        longTasks.add(new LongSquareTask(-10L));

        Executor<Number> numberExecutor = new NumberExecutor();

        for (Task<Long> longTask : longTasks) {
            numberExecutor.addTask(longTask);
        }
        numberExecutor.addTask(new LongSquareTask(7L), new NumberValidator());

        numberExecutor.execute();

        List<Long> testValidResults = Arrays.asList(25L, 144L, 100L, 49L);
        int index = 0;
        for (Number number : numberExecutor.getValidResults()) {
            Number expected = testValidResults.get(index++);
            Number actual = number;
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testExecuteLongAndInvalidResults() throws Exception {
        List<Task<Long>> longTasks = new ArrayList<>();
        longTasks.add(new LongSquareTask(5L));
        longTasks.add(new LongSquareTask(12L));
        longTasks.add(new LongSquareTask(-10L));
        longTasks.add(new LongSquareTask(Long.MAX_VALUE - 1));
        longTasks.add(new LongSquareTask(Long.MIN_VALUE + 1));

        Executor<Number> numberExecutor = new NumberExecutor();

        for (Task<Long> longTask : longTasks) {
            numberExecutor.addTask(longTask);
        }
        numberExecutor.addTask(new LongSquareTask(7L), new NumberValidator());

        numberExecutor.execute();

        List<Long> testValidResults = Arrays.asList(25L, 144L, 100L, 49L);
        int index = 0;
        for (Number number : numberExecutor.getValidResults()) {
            Number expected = testValidResults.get(index++);
            Number actual = number;
            assertEquals(expected, actual);
        }

        List<Long> testInvalidResults = Arrays.asList(Long.MAX_VALUE, Long.MAX_VALUE);
        index = 0;
        for (Number number : numberExecutor.getValidResults()) {
            Number expected = testValidResults.get(index++);
            Number actual = number;
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testExecuteInt() throws Exception {
        List<Task<Integer>> intTasks = new ArrayList<>();
        intTasks.add(new IntSquareTask(5));
        intTasks.add(new IntSquareTask(12));
        intTasks.add(new IntSquareTask(-10));

        Executor<Number> numberExecutor = new NumberExecutor();

        for (Task<Integer> intTask : intTasks) {
            numberExecutor.addTask(intTask);
        }
        numberExecutor.addTask(new IntSquareTask(7), new NumberValidator());

        numberExecutor.execute();

        List<Integer> testValidResults = Arrays.asList(25, 144, 100, 49);
        int index = 0;
        for (Number number : numberExecutor.getValidResults()) {
            Number expected = testValidResults.get(index++);
            Number actual = number;
            assertEquals(expected, actual);
        }

        List<Integer> testInvalidResults = Arrays.asList(Integer.MAX_VALUE, Integer.MAX_VALUE);
        index = 0;
        for (Number number : numberExecutor.getValidResults()) {
            Number expected = testValidResults.get(index++);
            Number actual = number;
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testExecuteIntAndInvalidResults() throws Exception {
        Executor<Number> numberExecutor = makeIntTestSchedule();
        numberExecutor.execute();

        List<Integer> testValidResults = Arrays.asList(25, 144, 100, 49);
        int index = 0;
        for (Number number : numberExecutor.getValidResults()) {
            Number expected = testValidResults.get(index++);
            Number actual = number;
            assertEquals(expected, actual);
        }

        List<Integer> testInvalidResults = Arrays.asList(Integer.MAX_VALUE, Integer.MAX_VALUE);
        index = 0;
        for (Number number : numberExecutor.getValidResults()) {
            Number expected = testValidResults.get(index++);
            Number actual = number;
            assertEquals(expected, actual);
        }
    }

    private Executor<Number> makeIntTestSchedule() {
        List<Task<Integer>> intTasks = new ArrayList<>();
        intTasks.add(new IntSquareTask(5));
        intTasks.add(new IntSquareTask(12));
        intTasks.add(new IntSquareTask(-10));
        intTasks.add(new IntSquareTask(Integer.MAX_VALUE - 1));
        intTasks.add(new IntSquareTask(Integer.MIN_VALUE + 1));

        Executor<Number> numberExecutor = new NumberExecutor();
        for (Task<Integer> intTask : intTasks) {
            numberExecutor.addTask(intTask);
        }
        numberExecutor.addTask(new IntSquareTask(7), new NumberValidator());
        return numberExecutor;
    }

    @Test
    public void testGetValidResults() {
        Executor<Number> numberExecutor = makeIntTestSchedule();
        numberExecutor.execute();
        List<Integer> testValidResults = Arrays.asList(25, 144, 100, 49);
        int index = 0;
        for (Number number : numberExecutor.getValidResults()) {
            Number expected = testValidResults.get(index++);
            Number actual = number;
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testGetInvalidResults() {
        Executor<Number> numberExecutor = makeIntTestSchedule();
        numberExecutor.execute();
        List<Integer> testInValidResults = Arrays.asList(Integer.MAX_VALUE, Integer.MAX_VALUE);
        int index = 0;
        for (Number number : numberExecutor.getInvalidResults()) {
            Number expected = testInValidResults.get(index++);
            Number actual = number;
            assertEquals(expected, actual);
        }
    }
}