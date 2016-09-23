package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 22.07.2016.
 */
public class ExpressionFloatDivide implements Expression<Float, Float> {
    private Float value1;
    private Float value2;
    private Float result;

    public ExpressionFloatDivide() {
    }

    public ExpressionFloatDivide(Float value1, Float value2) {
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


    @Override
    public void calculate() {
        checkSecondArgument(value2);
        result = value1 / value2;
    }

    // checks if second argument equals to zero
    private void checkSecondArgument(float divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("Divider is equal to zero!");
        }
    }
    @Override
    public Float getResult() {
        return result;
    }

    @Override
    public void setArguments(StringExpression stringExpression) {
        this.value1 = Float.valueOf(stringExpression.getValue1());
        this.value2 = Float.valueOf(stringExpression.getValue2());
    }
}
