package ua.goit.timonov.enterprise;

/**
 * Expression multiply operation for arguments with Integer format
 */

public class ExpressionIntegerMultiply implements Expression<Integer> {
    private Integer value1;
    private Integer value2;
    private Operation<Integer, Integer> operation;
    private Integer result;

    public ExpressionIntegerMultiply() {
    }

    public ExpressionIntegerMultiply(Integer value1, Integer value2, Operation<Integer, Integer> operation) {
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

    public Integer getResult() {
        return result;
    }

    @Override
    public void setArguments(StringExpression stringExpression) {
        //TODO
    }

    @Override
    public void calculate() {
        result = operation.execute(value1, value2);
    }
}
