package ua.goit.timonov.enterprise;

/**
 * Represents expressionType with Float arguments and result
 */
public class ExpressionFloatPlus implements Expression<Float, Float> {
    private Float value1;
    private Float value2;
    private Float result;

    public ExpressionFloatPlus() {
    }

    public ExpressionFloatPlus(Float value1, Float value2) {
        this.value1 = value1;
        this.value2 = value2;
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
     * returns result of calculated Double expressionType
     * @return      result of expressionType
     */
    @Override
    public Float getResult() {
        return result;
    }

    @Override
    public void setArguments(StringExpression stringExpression) {
        this.value1 = Float.valueOf(stringExpression.getValue1());
        this.value2 = Float.valueOf(stringExpression.getValue2());
    }

    @Override
    public void calculate() {
        result = value1 + value2;
    }
}
