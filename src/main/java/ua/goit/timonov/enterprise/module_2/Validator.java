package ua.goit.timonov.enterprise.module_2;

/**
 * Validator for received value
 */
public interface Validator <T> {

    /**
     * validates given value
     * @param result        received result
     * @return              true if result is valid
     */
    boolean isValid(T result);
}
