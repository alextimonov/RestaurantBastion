package ua.goit.timonov.enterprise;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Alex on 29.06.2016.
 */
public class PermittedOperations {
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    protected Map<Operation, String> operations = new HashMap<>();

    public Set<String> getSetOperations() {
        return operations.values().stream().collect(Collectors.toSet());
    }

    public PermittedOperations() {
        operations.put(new OperationIntegerPlus(), PLUS);
        operations.put(new OperationIntegerMinus(), MINUS);
        operations.put(new OperationLongPlus(), PLUS);
        operations.put(new OperationLongMinus(), MINUS);
        operations.put(new OperationFloatPlus(), PLUS);
        operations.put(new OperationFloatMinus(), MINUS);
        operations.put(new OperationDoublePlus(), PLUS);
        operations.put(new OperationDoubleMinus(), MINUS);
    }

    public void addOperation(Operation operation, String notation) {
        operations.put(operation, notation);
    }

}
