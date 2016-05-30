package ua.goit.timonov.enterprise.module_2;

/**
 * Created by Alex on 30.05.2016.
 */
public class NumberValidator implements Validator<Number> {
    @Override
    public boolean isValid(Number result) {
        if (result instanceof Number)
            return true;
        return false;
    }
}
