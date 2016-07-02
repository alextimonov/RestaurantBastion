package ua.goit.timonov.enterprise;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Alex on 29.06.2016.
 */
public class PermittedOperations {
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    private Map<Operation, String> operations = new HashMap<>();

    public Set<String> getSetOperations() {
        return operations.values().stream().collect(Collectors.toSet());

        /*Set<String> setOperators = new HashSet<>();
        for (String stringNotation : operations.values()) {
            setOperators.add(stringNotation);
        }
        return setOperators;*/
    }

    public PermittedOperations() {
        operations.put(new OperationIntegerPlus(), PLUS);
        operations.put(new OperationIntegerMinus(), MINUS);
        operations.put(new OperationDoublePlus(), PLUS);
        operations.put(new OperationDoubleMinus(), MINUS);
    }

/*    public OldOperation getOperation(String notation) {
        OldOperation oldOperation = operations.get(notation);
        if (oldOperation == null) {
            throw new RuntimeException("It is not permitted oldOperation!");
        }
        return oldOperation;
    }*/

    public void putOperation(Operation operation, String notation) {
        operations.put(operation, notation);
    }

}
