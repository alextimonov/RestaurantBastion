package ua.goit.timonov.enterprise.module_2;

/**
 * Validator for Numbers
 */
public class NumberValidator implements Validator<Number> {
    /**
     * validates given number value
     * @param result        received result
     * @return              true if result is valid
     */
    @Override
    public boolean isValid(Number result) {
        if (result.doubleValue() >= 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
