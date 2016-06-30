package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public class ParserPermittedOperation<T> implements ParserOperation<T> {
    @Override
    public Operation<T> getOperation(StringExpression stringExpression, PermittedOperations permittedOperations) {
        {
            String operator = stringExpression.getOperator();
            Operation<T> operation = permittedOperations.getOperation(operator);
            return operation;
        }
    }
}
