package ua.goit.timonov.enterprise.module_2;

import java.util.Random;

/**
 * Created by Alex on 30.05.2016.
 */
public class LongTask implements Task<Long> {
    private Long value;
    private Random rand = new Random();

    @Override
    public void execute() {
        value = rand.nextLong();
//        System.out.println(this + " is running!");
    }

    @Override
    public Long getResult() {
        return value;
    }
}
