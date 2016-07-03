package ua.goit.timonov.enterprise;

/**
 * Represents expression with Float arguments and result
 */
public class ExpressionFloat implements Expression<Float> {
    private Float value1;
    private Float value2;
    private Operation<Float, Float> operation;
    private Float result;

    public ExpressionFloat(Float value1, Float value2, Operation<Float, Float> operation) {
        this.value1 = value1;
        this.value2 = value2;
        this.operation = operation;
    }

    public Float getValue1() {
        return value1;
    }

    public void setValue1(Float value1) {
        this.value1 = value1;
    }

    public Float getValue2() {
        return value2;
    }

    public void setValue2(Float value2) {
        this.value2 = value2;
    }

    /**
     * returns result of calculated Float expression
     * @return      result of expression
     */
    @Override
    public Float getResult() {
        return result;
    }

    /**
     * calculates an expression
     */
    @Override
    public void calculate() {
        result = operation.execute(value1, value2);
    }
}
