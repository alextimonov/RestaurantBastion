package ua.goit.timonov.enterprise.module_2;

import java.util.Random;

/**
 * Task with long integer values, multiplication by multiplier
 */
public class LongTask implements Task<Long> {
    public static final int MULTIPLIER = 2;

    /* value that will be executed */
    private Long value;

    /* result of multiplication */
    private Long result;

    /* Generator of random numbers */
    private Random rand = new Random();

    public LongTask() {
        value = rand.nextLong();
    }

    public LongTask(Long value) {
        this.value = value;
    }

    /**
     * executes the task of multiplication
     */
    @Override
    public void execute() {
        checkLongArgument();
        result = value * MULTIPLIER;

    }

    // checks if multiplication is can be made without type overflow
    private void checkLongArgument() {
        if (value > Long.MAX_VALUE / MULTIPLIER) {
            result = Long.MAX_VALUE;
            throw new TaskOverflowDataTypeException("Long type will be overflow through Long.MAX_VALUE");
        }
        if (value < Long.MIN_VALUE / MULTIPLIER) {
            result = Long.MIN_VALUE;
            throw new TaskOverflowDataTypeException("Long type will be overflow through Long.MIN_VALUE");
        }
    }

    /**
     * returns result of multiplication
     * @return      result value
     */
    @Override
    public Long getResult() {
        return result;
    }
}
