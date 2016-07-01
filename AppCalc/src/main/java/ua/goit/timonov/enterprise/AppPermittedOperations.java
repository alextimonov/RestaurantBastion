package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class AppPermittedOperations {
    PermittedOperations permittedOperations;

    public PermittedOperations getPermittedOperations() {
        return permittedOperations;
    }

    public void addOperations() {
        permittedOperations = new PermittedOperations();
        permittedOperations.putOperation("*", new OldOperationMultiply());
        permittedOperations.putOperation("/", new OldOperationDivide<>());
    }

}
