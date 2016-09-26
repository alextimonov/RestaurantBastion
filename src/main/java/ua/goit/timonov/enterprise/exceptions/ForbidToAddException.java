package ua.goit.timonov.enterprise.exceptions;

/**
 * Created by Alex on 20.09.2016.
 */
public class ForbidToAddException extends RuntimeException {

    public ForbidToAddException(String message) {
        super(message);
    }
}
