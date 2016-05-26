package ua.goit.timonov.enterprise.module_1;

import java.util.List;

/**
 * Created by Alex on 26.05.2016.
 */
public class TestAdd <T> implements Test<T> {
    @Override
    public long makeTest(List<T> collection, int nElements) {
        long averageTime = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            averageTime += fixTimeToPopulate(nElements, value);
        }
        averageTime /= REPETITIONS;
        return averageTime;


        return 0;
    }

    public long findTimeOfPopulate(int nElements, T value) {
        long averageTime = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            averageTime += fixTimeToPopulate(nElements, value);
        }
        averageTime /= REPETITIONS;
        return averageTime;
    }
}
