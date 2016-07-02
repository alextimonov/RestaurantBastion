package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 02.07.2016.
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

    public Integer getResult() {
        return result;
    }

    @Override
    public void calculate() {
        result = operation.execute(value1, value2);
    }
}
