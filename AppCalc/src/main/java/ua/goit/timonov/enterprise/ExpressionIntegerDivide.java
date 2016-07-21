package ua.goit.timonov.enterprise;

/**
 * Expression for divide operation for arguments with Integer format
 */
public class ExpressionIntegerDivide implements Expression<Double> {
    private Integer value1;
    private Integer value2;
    private Operation<Integer, Double> operation;
    private Double result;

    public ExpressionIntegerDivide(Integer value1, Integer value2, Operation<Integer, Double> operation) {
        this.value1 = value1;
        this.value2 = value2;
        this.operation = operation;
    }

    public Integer getValue1() {
        return value1;
    }

    public void setValue1(Integer value1) {
        this.value1 = value1;
    }

    public Integer getValue2() {
        return value2;
    }

    public void setValue2(Integer value2) {
        this.value2 = value2;
    }


    @Override
    public void calculate() {
        result = operation.execute(value1, value2);
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
