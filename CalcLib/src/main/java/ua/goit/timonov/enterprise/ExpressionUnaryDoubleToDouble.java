package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 04.07.2016.
 */
public class ExpressionUnaryDoubleToDouble implements Expression<Double> {
    private Double value;
    private Operation<Double, Double> operation;
    private Double result;

    public ExpressionUnaryDoubleToDouble(Double value, Operation<Double, Double> operation) {
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