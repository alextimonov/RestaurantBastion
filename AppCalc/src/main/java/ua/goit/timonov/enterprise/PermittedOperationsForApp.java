package ua.goit.timonov.enterprise;

/**
 * Class with map of permitted operation to execute
 */
public class PermittedOperationsForApp extends PermittedOperations {
    public static final String DIVIDE = "/";
    public static final String MULTIPLY = "*";

    public PermittedOperationsForApp() {
        super();
        operations.put(new OperationIntegerDivide(), DIVIDE);
        operations.put(new OperationIntegerMultiply(), MULTIPLY);
        operations.put(new OperationLongDivide(), DIVIDE);
        operations.put(new OperationLongMultiply(), MULTIPLY);
        operations.put(new OperationFloatDivide(), DIVIDE);
        operations.put(new OperationFloatMultiply(), MULTIPLY);
        operations.put(new OperationDoubleDivide(), DIVIDE);
        operations.put(new OperationDoubleMultiply(), MULTIPLY);
    }
}
