package ua.goit.timonov.enterprise.exceptions;

/**
 * Exception that can be thrown if there is no item in database
 */
public class NoItemInDbException extends RuntimeException {

    public NoItemInDbException(String message) {
        super(message);
    }
}
