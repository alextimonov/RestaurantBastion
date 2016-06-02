package ua.goit.timonov.enterprise.module_2;

/**
 * Validator for Numbers
 */
public class NumberValidator implements Validator<Number> {
    /**
     * dummy validator, validates given number value
     * @param result        received result
     * @return              true if result is valid
     */
    @Override
    public boolean isValid(Number result) {
        if (result instanceof Number)
            return true;
        return false;
    }
}
