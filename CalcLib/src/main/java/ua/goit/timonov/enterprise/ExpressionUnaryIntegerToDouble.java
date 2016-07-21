package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 04.07.2016.
 */
public class ExpressionUnaryIntegerToDouble implements Expression<Double> {
    private Integer value;
    private Operation<Integer, Double> operation;
    private Double result;

    public ExpressionUnaryIntegerToDouble(Integer value, Operation<Integer, Double> operation) {
        this.value = value;
        this.operation = operation;
    }

    @Override
    public void calculate() {
        result = operation.execute(value);
    }

    @Override
    public Double getResult() {
        return result;
    }

    @Override
    public void setArguments(StringExpression stringExpression) {
        //TODO
    }
}
