package ua.goit.timonov.enterprise.module_2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alex on 30.05.2016.
 */
public class ExecutorImplTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAddTask() throws Exception {

    }

    @Test
    public void testAddTask1() throws Exception {

    }

    @Test
    public void testExecute() throws Exception {
        List<Task<Integer>> intTasks = new ArrayList<>();
        intTasks.add(new LongTask());

        Executor<Number> numberExecutor = new ExecutorImpl<>();

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
    public void testGetValidResults() throws Exception {

    }

    @Test
    public void testGetInvalidResults() throws Exception {

    }
}