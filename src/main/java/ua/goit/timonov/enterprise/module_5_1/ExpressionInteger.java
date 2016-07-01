package ua.goit.timonov.enterprise.module_5_1;

/**
 * Created by Alex on 01.07.2016.
 */
public class ExpressionInteger implements ExpressIface {
    private Integer x1;
    private Integer x2;
    private Operation<Integer> operation;
    private Integer result;

    public ExpressionInteger() {
    }

    public ExpressionInteger(Integer x1, Integer x2, Operation<Integer> operation) {
        this.x1 = x1;
        this.x2 = x2;
        this.operation = operation;
    }

    @Override
    public void evaluate() {
        result = x1 + x2;
    }

    public Integer getX1() {
        return x1;
    }

    public void setX1(Integer x1) {
        this.x1 = x1;
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        this.x2 = x2;
    }

    public Operation<Integer> getOperation() {
        return operation;
    }

    public void setOperation(Operation<Integer> operation) {
        this.operation = operation;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
