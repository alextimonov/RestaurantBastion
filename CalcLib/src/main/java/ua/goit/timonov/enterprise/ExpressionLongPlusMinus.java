package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 02.07.2016.
 */
public class ExpressionLongPlusMinus implements Expression<Long> {
    private Long value1;
    private Long value2;
    private Operation<Long, Long> operation;
    private Long result;

    public ExpressionLongPlusMinus() {
    }

    public ExpressionLongPlusMinus(Long value1, Long value2, Operation<Long, Long> operation) {
        this.value1 = value1;
        this.value2 = value2;
        this.operation = operation;
    }

    public Long getValue1() {
        return value1;
    }

    public void setValue1(Long value1) {
        this.value1 = value1;
    }

    public Long getValue2() {
        return value2;
    }

    public void setValue2(Long value2) {
        this.value2 = value2;
    }

    @Override
    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    @Override
    public void calculate() {
        result = operation.execute(value1, value2);
    }
}
