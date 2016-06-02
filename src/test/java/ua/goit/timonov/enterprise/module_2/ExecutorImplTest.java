package ua.goit.timonov.enterprise.module_2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 30.05.2016.
 */
public class ExecutorImplTest {

    @Test
    public void testAddTask() throws Exception {

    }

    @Test
    public void testAddTask1() throws Exception {

    }

    @Test
    public void testExecuteWithLong() throws Exception {
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
    public void testExecuteLong() throws Exception {
        List<Task<Long>> longTasks = new ArrayList<>();
        longTasks.add(new LongTask(5L));
        longTasks.add(new LongTask(12L));
        longTasks.add(new LongTask(-35L));
        longTasks.add(new LongTask(123L));

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
    public void testGetValidResults() {

    }

    @Test
    public void testGetInvalidResults() {

    }
}