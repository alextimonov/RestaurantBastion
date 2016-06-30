package ua.goit.timonov.enterprise;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 29.06.2016.
 */
public class PermittedOperations {
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    private Map<String, Operation> operations = new HashMap<>();

    public Set<String> getKeySet() {
        return operations.keySet();
    }

    public PermittedOperations() {
        operations.put(PLUS, new OperationAddCast());
        operations.put(MINUS, new OperationSubtractCast());
    }

    public Operation getOperation(String notation) {
        Operation operation = operations.get(notation);
        if (operation == null) {
            throw new RuntimeException("It is not permitted operation!");
        }
        return operation;
    }

    public void putOperation(String notation, Operation operation) {
        operations.put(notation, operation);
    }

}
