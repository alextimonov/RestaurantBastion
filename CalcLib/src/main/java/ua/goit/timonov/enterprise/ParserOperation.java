package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public interface ParserOperation<T> {

    Operation<T> getOperation(StringExpression stringExpression, PermittedOperations permittedOperations);
}
