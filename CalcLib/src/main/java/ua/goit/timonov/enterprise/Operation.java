package ua.goit.timonov.enterprise;

/**
 * Some operation (math or other) that can be executed. Operation is typified by one type for arguments
 * and another for result
 */
public interface Operation<Argument, Result> {

    /**
     * executes an operation
     * @param values    arguments with one type
     * @return          result that can have different format from arguments
     */
    Result execute(Argument... values);
}
