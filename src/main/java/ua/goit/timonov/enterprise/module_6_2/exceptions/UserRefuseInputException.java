package ua.goit.timonov.enterprise.module_6_2.exceptions;

/**
 * Exception that could be thrown if user refuses to continue input data
 */
public class UserRefuseInputException extends RuntimeException {

    public UserRefuseInputException(String message) {
        super(message);
    }
}
