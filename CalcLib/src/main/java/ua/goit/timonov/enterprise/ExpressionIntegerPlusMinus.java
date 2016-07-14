package ua.goit.timonov.enterprise;

/**
 * Represents expression with Integer arguments and result oriented to add and subtract operations
 */
public class ExpressionIntegerPlusMinus implements Expression<Integer> {
    private Integer value1;
    private Integer value2;
    private Operation<Integer, Integer> operation;
    private Integer result;

    public ExpressionIntegerPlusMinus() {
    }

    public ExpressionIntegerPlusMinus(Integer value1, Integer value2, Operation<Integer, Integer> operation) {
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

    public void setOperation(Operation<Integer, Integer> operation) {
        this.operation = operation;
    }

    /**
     * returns result of calculated Integer expression
     * @return      result of expression
     */
    @Override
    public Integer getResult() {
        return result;
    }

//    @Override
//    public void makeExpression(Integer value1, Integer value2, Operation<Integer, Integer> operation) {
//        this.value1 = value1;
//        this.value2 = value2;
//        this.operation = operation;
//    }

    /**
     * calculates an expression
     */
    @Override
    public void calculate() {
        result = operation.execute(value1, value2);
    }
}
