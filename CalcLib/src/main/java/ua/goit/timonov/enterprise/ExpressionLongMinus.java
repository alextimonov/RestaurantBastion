package ua.goit.timonov.enterprise;

/**
 * Represents expressionType with Long arguments and result oriented to add and subtract operations
 */
public class ExpressionLongMinus implements Expression<Long, Long> {
    private Long value1;
    private Long value2;
    private Long result;

    public ExpressionLongMinus() {
    }

    public ExpressionLongMinus(Long value1, Long value2) {
        this.value1 = value1;
        this.value2 = value2;
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

    /**
     * returns result of calculated Integer expressionType
     * @return      result of expressionType
     */
    @Override
    public Long getResult() {
        return result;
    }

    @Override
    public void setArguments(StringExpression stringExpression) {
        this.value1 = Long.valueOf(stringExpression.getValue1());
        this.value2 = Long.valueOf(stringExpression.getValue2());
    }

    /**
     * calculates an expression
     */
    @Override
    public void calculate() {
        result = value1 + value2;
    }
}
