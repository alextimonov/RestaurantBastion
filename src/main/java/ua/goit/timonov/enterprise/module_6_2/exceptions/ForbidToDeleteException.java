package ua.goit.timonov.enterprise.module_6_2.exceptions;

/**
 * Created by Alex on 19.09.2016.
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
