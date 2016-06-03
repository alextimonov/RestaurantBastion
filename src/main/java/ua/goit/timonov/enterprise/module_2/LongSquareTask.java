package ua.goit.timonov.enterprise.module_2;

import java.util.Random;

/**
 * Task with long integer values, multiplication by multiplier
 */
public class LongSquareTask implements Task<Long> {
    public static final int POWER = 2;

    /* value that will be executed */
    private Long value;

    /* result of multiplication */
    private Long result;

    public LongSquareTask() {
        Random rand = new Random();
        value = rand.nextLong();
    }

    public LongSquareTask(Long value) {
        this.value = value;
    }

    /**
     * executes the task of multiplication
     */
    @Override
    public void execute() {
        checkLongArgument();
        result = 1L;
        for (int i = 0; i < POWER; i++) {
            result *= value;
        }
    }

    // checks if multiplication is can be made without type overflow
    private void checkLongArgument() {
        if (Math.abs(value) > Math.sqrt(Long.MAX_VALUE)) {
            result = Long.MAX_VALUE;
            throw new TaskOverflowDataTypeException("Long type will be overflow through Long.MAX_VALUE");
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
