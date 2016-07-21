package ua.goit.timonov.enterprise;

/**
 * Represents expression with Double arguments and result
 */
public class ExpressionDouble implements Expression<Double> {
    private Double value1;
    private Double value2;
    private Operation<Double, Double> operation;
    private Double result;

    public ExpressionDouble() {
    }

    public ExpressionDouble(Double value1, Double value2, Operation<Double, Double> operation) {
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

    /**
     * returns result of calculated Double expression
     * @return      result of expression
     */
    @Override
    public Double getResult() {
        return result;
    }

    @Override
    public void setArguments(StringExpression stringExpression) {
        this.value1 = Double.valueOf(stringExpression.getValue1());
        this.value2 = Double.valueOf(stringExpression.getValue2());
        this.operation = new OperationDoublePlus();
    }

    /**
     * calculates an expression
     */
    @Override
    public void calculate() {
        result = operation.execute(value1, value2);
    }
}
