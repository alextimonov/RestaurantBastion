package ua.goit.timonov.enterprise;

/**
 * Expression for divide operation for arguments with Integer format
 */
public class ExpressionIntegerDivide implements Expression<Integer, Double> {
    private Integer value1;
    private Integer value2;
    private Double result;

    public ExpressionIntegerDivide() {
    }

    public ExpressionIntegerDivide(Integer value1, Integer value2) {
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


    @Override
    public void calculate() {
        checkSecondArgument(value2);
        double arg2 = (double) value2;
        result = value1 / arg2;
    }

    // checks if second argument equals to zero
    private void checkSecondArgument(int divider) {
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
        this.value1 = Integer.valueOf(stringExpression.getValue1());
        this.value2 = Integer.valueOf(stringExpression.getValue2());
    }
}
