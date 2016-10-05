package ua.goit.timonov.enterprise.exceptions;

/**
 * Exception that can be thrown if it's forbidden to delete item from database
 */
public class ForbidToDeleteException extends RuntimeException {

    private String nestedMessage;

    public String getNestedMessage() {
        return nestedMessage;
    }

    public ForbidToDeleteException(String message, String nestedMessage) {
        super(message);
        this.nestedMessage = nestedMessage;
    }
}
