package ua.goit.timonov.enterprise.exceptions;

/**
 * Exception that can be thrown if it's forbidden to add item to database
 */
public class ForbidToAddException extends RuntimeException {

    public ForbidToAddException(String message) {
        super(message);
    }
}
