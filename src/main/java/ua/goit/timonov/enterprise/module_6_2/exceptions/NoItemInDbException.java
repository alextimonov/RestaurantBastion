package ua.goit.timonov.enterprise.module_6_2.exceptions;

/**
 * Created by Alex on 19.09.2016.
 */
public class NoItemInDbException extends RuntimeException {

    public NoItemInDbException(String message) {
        super(message);
    }
}
