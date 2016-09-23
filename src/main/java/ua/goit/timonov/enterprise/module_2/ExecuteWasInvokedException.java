package ua.goit.timonov.enterprise.module_2;

/**
 * Indicates that method execute() has been invoked
 */
public class ExecuteWasInvokedException extends RuntimeException {

    public ExecuteWasInvokedException(String message) {
        super(message);
    }
}
