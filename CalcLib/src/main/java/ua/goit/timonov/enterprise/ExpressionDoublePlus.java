package ua.goit.timonov.enterprise;

/**
 * Represents expressionType with Double arguments and result
 */
public class ExpressionDoublePlus implements Expression<Double, Double> {
    private Double value1;
    private Double value2;
    private Double result;

    public ExpressionDoublePlus() {
    }

    public Double getValue1() {
        return value1;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    public Double getValue2() {
        return value2;
    }

    public void setValue2(Double value2) {
        this.value2 = value2;
    }

    /**
     * returns result of calculated Double expressionType
     * @return      result of expressionType
     */
    @Override
    public Double getResult() {
        return result;
    }

    @Override
    public void setArguments(StringExpression stringExpression) {
        this.value1 = Double.valueOf(stringExpression.getValue1());
        this.value2 = Double.valueOf(stringExpression.getValue2());
    }

    @Override
    public void calculate() {
        result = value1 + value2;
    }
}
