package ua.goit.timonov.enterprise.module_2;

import java.util.Random;

/**
 * Task with integer values, multiplication by multiplier
 */
public class IntTask implements Task<Integer> {
    public static final int MULTIPLIER = 2;

    /* value that will be executed */
    private Integer value;

    /* result of multiplication */
    private Integer result;

    /* Generator of random numbers */
    private Random rand = new Random();

    public IntTask() {
        value = rand.nextInt();
    }

    public IntTask(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    /**
     * executes the task
     */
    @Override
    public void execute() {
        checkIntegerArgument();
        result = value * MULTIPLIER;
    }

    // checks if multiplication can be made without type overflow
    private void checkIntegerArgument() {
        if (value > Integer.MAX_VALUE / MULTIPLIER) {
            result = Integer.MAX_VALUE;
            throw new TaskOverflowDataTypeException("Long type will be overflow through Integer.MAX_VALUE");
        }
        if (value < Integer.MIN_VALUE / MULTIPLIER) {
            result = Integer.MIN_VALUE;
            throw new TaskOverflowDataTypeException("Long type will be overflow through Integer.MIN_VALUE");
        }
    }

    /**
     * return the result of execution
     * @return      result of execution
     */
    @Override
    public Integer getResult() {
        return result;
    }
}
