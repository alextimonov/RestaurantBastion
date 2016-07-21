package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 22.07.2016.
 */
public class ExpressionDoubleDivide implements Expression<Double, Double> {
    private Double value1;
    private Double value2;
    private Double result;

    public ExpressionDoubleDivide() {
    }

    public ExpressionDoubleDivide(Double value1, Double value2) {
        this.value1 = value1;
        this.value2 = value2;
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


    @Override
    public void calculate() {
        checkSecondArgument(value2);
        result = value1 / value2;
    }

    // checks if second argument equals to zero
    private void checkSecondArgument(double divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("Divider is equal to zero!");
        }
    }
    @Override
    public Double getResult() {
        return result;
    }

    @Override
    public void setArguments(StringExpression stringExpression) {
        this.value1 = Double.valueOf(stringExpression.getValue1());
        this.value2 = Double.valueOf(stringExpression.getValue2());
    }
}
