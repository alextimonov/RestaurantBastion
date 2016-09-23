package ua.goit.timonov.enterprise.module_2;

/**
 * Indicates that a type overflow will take place during execution with received value
 */
public class TaskOverflowDataTypeException extends IllegalArgumentException {

    public TaskOverflowDataTypeException(String message) {
        super(message);
    }
}
