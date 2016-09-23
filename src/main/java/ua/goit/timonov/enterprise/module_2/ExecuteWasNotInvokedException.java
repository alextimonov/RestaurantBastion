package ua.goit.timonov.enterprise.module_2;

/**
 * Indicates that method execute() hasn't been invoked
 */
public class ExecuteWasNotInvokedException extends RuntimeException {
    public ExecuteWasNotInvokedException(String message) {
        super(message);
    }
}
