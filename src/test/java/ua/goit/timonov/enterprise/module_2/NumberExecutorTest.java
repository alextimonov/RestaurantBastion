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

        Task task = new IntTask(100);
        executor.addTask(task);
        testTaskList.add(task);

        task = new IntTask(-200);
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
        executor.addTask(new IntTask(100));
    }

    @Test
    public void testExecuteLong() throws Exception {
        List<Task<Long>> longTasks = new ArrayList<>();
        longTasks.add(new LongTask(5L));
        longTasks.add(new LongTask(12L));
        longTasks.add(new LongTask(-35L));

        Executor<Number> numberExecutor = new NumberExecutor();

        for (Task<Long> longTask : longTasks) {
            numberExecutor.addTask(longTask);
        }
        numberExecutor.addTask(new LongTask(10L), new NumberValidator());

        numberExecutor.execute();

        System.out.println("Valid results:");
        for (Number number : numberExecutor.getValidResults()) {
            System.out.println(number);
        }
        System.out.println("Invalid results:");
        for (Number number : numberExecutor.getInvalidResults()) {
            System.out.println(number);
        }
    }

    @Test
    public void testExecuteLongAndInvalidResults() throws Exception {
        List<Task<Long>> longTasks = new ArrayList<>();
        longTasks.add(new LongTask(5L));
        longTasks.add(new LongTask(12L));
        longTasks.add(new LongTask(-35L));
        longTasks.add(new LongTask(Long.MAX_VALUE - 1));
        longTasks.add(new LongTask(Long.MIN_VALUE + 1));

        Executor<Number> numberExecutor = new NumberExecutor();

        for (Task<Long> longTask : longTasks) {
            numberExecutor.addTask(longTask);
        }
        numberExecutor.addTask(new LongTask(10L), new NumberValidator());

        numberExecutor.execute();

        System.out.println("Valid results:");
        for (Number number : numberExecutor.getValidResults()) {
            System.out.println(number);
        }
        System.out.println("Invalid results:");
        for (Number number : numberExecutor.getInvalidResults()) {
            System.out.println(number);
        }
    }

    @Test
    public void testExecuteInt() throws Exception {
        List<Task<Integer>> intTasks = new ArrayList<>();
        intTasks.add(new IntTask(5));
        intTasks.add(new IntTask(12));
        intTasks.add(new IntTask(-35));

        Executor<Number> numberExecutor = new NumberExecutor();

        for (Task<Integer> intTask : intTasks) {
            numberExecutor.addTask(intTask);
        }
        numberExecutor.addTask(new LongTask(10L), new NumberValidator());

        numberExecutor.execute();

        System.out.println("Valid results:");
        for (Number number : numberExecutor.getValidResults()) {
            System.out.println(number);
        }
        System.out.println("Invalid results:");
        for (Number number : numberExecutor.getInvalidResults()) {
            System.out.println(number);
        }
    }

    @Test
    public void testExecuteIntAndInvalidResults() throws Exception {
        Executor<Number> numberExecutor = makeIntTestSchedule();
        numberExecutor.execute();

        System.out.println("Valid results:");
        for (Number number : numberExecutor.getValidResults()) {
            System.out.println(number);
        }
        System.out.println("Invalid results:");
        for (Number number : numberExecutor.getInvalidResults()) {
            System.out.println(number);
        }
    }

    private Executor<Number> makeIntTestSchedule() {
        List<Task<Integer>> intTasks = new ArrayList<>();
        intTasks.add(new IntTask(5));
        intTasks.add(new IntTask(12));
        intTasks.add(new IntTask(-35));
        intTasks.add(new IntTask(Integer.MAX_VALUE - 1));
        intTasks.add(new IntTask(Integer.MIN_VALUE + 1));

        Executor<Number> numberExecutor = new NumberExecutor();
        for (Task<Integer> intTask : intTasks) {
            numberExecutor.addTask(intTask);
        }
        numberExecutor.addTask(new IntTask(10), new NumberValidator());
        return numberExecutor;
    }

    @Test
    public void testGetValidResults() {
        Executor<Number> numberExecutor = makeIntTestSchedule();
        numberExecutor.execute();
        List<Integer> testValidResults = Arrays.asList(10, 24, -70, 20);
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
        List<Integer> testInValidResults = Arrays.asList(Integer.MAX_VALUE, Integer.MIN_VALUE);
        int index = 0;
        for (Number number : numberExecutor.getInvalidResults()) {
            Number expected = testInValidResults.get(index++);
            Number actual = number;
            assertEquals(expected, actual);
        }
    }
}