package ua.goit.timonov.enterprise;

/**
 * Expression for divide operation for arguments with Long format
 */

public class ExpressionLongDivide implements Expression<Long, Double> {
    private Long value1;
    private Long value2;
    private Double result;

    public ExpressionLongDivide() {
    }

    public ExpressionLongDivide(Long value1, Long value2) {
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


    @Override
    public void calculate() {
        checkSecondArgument(value2);
        double arg2 = (double) value2;
        result = value1 / arg2;
    }

    // checks if second argument equals to zero
    private void checkSecondArgument(long divider) {
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
        this.value1 = Long.valueOf(stringExpression.getValue1());
        this.value2 = Long.valueOf(stringExpression.getValue2());
    }
}
