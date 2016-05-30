package ua.goit.timonov.enterprise.module_2;

/**
 * Created by Alex on 30.05.2016.
 */
public interface Validator <T> {

    /**
     * validates given value
     * @param result        received result
     * @return              true if result is valid
     */
    boolean isValid(T result);
}
