package ua.goit.timonov.enterprise.module_2;

/**
 * Created by Alex on 30.05.2016.
 */
public class ExecuteWasNotInvokedException extends RuntimeException {
    public ExecuteWasNotInvokedException(String message) {
        super(message);
    }
}
