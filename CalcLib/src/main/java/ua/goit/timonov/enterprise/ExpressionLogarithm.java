package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 04.07.2016.
 */

public class ExpressionLogarithm implements Expression<Double, Double> {
    private Double value;
    private Double result;

    public ExpressionLogarithm() {
    }

    public ExpressionLogarithm(Double value) {
        this.value = value;
    }

    @Override
    public void calculate() {
        checkArgument(value);
        result = Math.log(value);
    }

    private void checkArgument(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Can not calculate square root for negative numbers.");
        }
    }

    @Override
    public Double getResult() {
        return result;
    }

    @Override
    public void setArguments(StringExpression stringExpression) {
        value = Double.valueOf(stringExpression.getValue2());
    }
}
