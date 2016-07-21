package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 04.07.2016.
 */
public class ExpressionFactorial implements Expression<Long> {
    private Integer value;
    private Operation<Integer, Long> operation;
    private Long result;

    public ExpressionFactorial(Integer value, Operation<Integer, Long> operation) {
        this.value = value;
        this.operation = operation;
    }

    @Override
    public void calculate() {
        result = operation.execute(value);
    }

    @Override
    public Long getResult() {
        return result;
    }

    @Override
    public void setArguments(StringExpression stringExpression) {
        //TODO
    }
}
