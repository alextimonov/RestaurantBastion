package ua.goit.timonov.enterprise.module_6_2.exceptions;

/**
 * Exception that could be thrown if inputted value is out of certain value bounds
 */
public class ValueOutOfBoundsException extends RuntimeException {

    public ValueOutOfBoundsException(String message) {
        super(message);
    }
}
