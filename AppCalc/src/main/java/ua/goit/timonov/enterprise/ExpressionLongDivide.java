package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 02.07.2016.
 */
public class ExpressionLongDivide implements Expression<Double> {
    private Long value1;
    private Long value2;
    private Operation<Long, Double> operation;
    private Double result;

    public ExpressionLongDivide() {
    }

    public ExpressionLongDivide(Long value1, Long value2, Operation<Long, Double> operation) {
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
    public void calculate() {
        result = operation.execute(value1, value2);
    }

    @Override
    public Double getResult() {
        return result;
    }
}
