package ua.goit.timonov.enterprise;

/**
 * Represents expressionType with Integer arguments and result oriented to add and subtract operations
 */
public class ExpressionIntegerPlus implements Expression<Integer, Integer> {
    private Integer value1;
    private Integer value2;
    private Integer result;

    public ExpressionIntegerPlus() {
    }

    public ExpressionIntegerPlus(Integer value1, Integer value2) {
        this.value1 = value1;
        this.value2 = value2;
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

    /**
     * returns result of calculated Integer expressionType
     * @return      result of expressionType
     */
    @Override
    public Integer getResult() {
        return result;
    }

    @Override
    public void setArguments(StringExpression stringExpression) {
        this.value1 = Integer.valueOf(stringExpression.getValue1());
        this.value2 = Integer.valueOf(stringExpression.getValue2());
    }

    /**
     * calculates an expression
     */
    @Override
    public void calculate() {
        result = value1 + value2;
    }

}
