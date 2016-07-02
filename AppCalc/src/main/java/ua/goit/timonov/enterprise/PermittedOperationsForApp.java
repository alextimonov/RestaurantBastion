package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class PermittedOperationsForApp extends PermittedOperations {
    public static final String DIVIDE = "/";
    public static final String MILTIPLY = "*";

    public PermittedOperationsForApp() {
        super();
        operations.put(new OperationIntegerDivide(), DIVIDE);
    }
}
