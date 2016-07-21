package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 04.07.2016.
 */
public class ExpressionFactorial implements Expression<Integer, Long> {
    private Integer value;
    private Long result;

    public ExpressionFactorial() {
    }

    public ExpressionFactorial(Integer value) {
        this.value = value;
    }

    @Override
    public void calculate() {
        checkArgument(value);
        if (value == 0)
            result = 0L;
        else {
            result = 1L;
            for (int i = 1; i <= value; i++) {
                result *= i;
            }
        }
    }

    private void checkArgument(int value) {
        if (value < 0 || value > 30) {
            throw new IllegalArgumentException("Can not calculate factorial for negative numbers or numbers bigger than 20.");
        }
    }

    @Override
    public Long getResult() {
        return result;
    }

    @Override
    public void setArguments(StringExpression stringExpression) {
        this.value = Integer.valueOf(stringExpression.getValue1());
    }
}
