package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 02.07.2016.
 */
public class ExpressionDoublePlusMinus implements Expression {
    private Double value1;
    private Double value2;
    private Operation<Double, Double> operation;
    private Double result;

    public ExpressionDoublePlusMinus() {
    }

    public ExpressionDoublePlusMinus(Double value1, Double value2, Operation<Double, Double> operation) {
        this.value1 = value1;
        this.value2 = value2;
        this.operation = operation;
    }

    public Double getValue1() {
        return value1;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    public Double getValue2() {
        return value2;
    }

    public void setValue2(Double value2) {
        this.value2 = value2;
    }

    @Override
    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    @Override
    public void calculate() {
        result = operation.execute(value1, value2);
    }
}
