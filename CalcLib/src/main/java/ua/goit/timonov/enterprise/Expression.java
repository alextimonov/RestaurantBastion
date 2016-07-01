package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 01.07.2016.
 */
public class Expression<A, R> {
    private A value1;
    private A value2;
    private Operation<A, R> operation;
    private R result;

    public Expression() {
    }

    public Expression(A value1, A value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public Expression(A value1, A value2, Operation<A, R> operation) {
        this.value1 = value1;
        this.value2 = value2;
        this.operation = operation;
    }

    public A getValue1() {
        return value1;
    }

    public A getValue2() {
        return value2;
    }

    public Operation<A, R> getOperation() {
        return operation;
    }

    public R getResult() {
        return result;
    }

    public void setOperation(Operation<A, R> operation) {
        this.operation = operation;
    }

    public void setValue1(A value1) {
        this.value1 = value1;
    }

    public void setValue2(A value2) {
        this.value2 = value2;
    }

    public void setResult(R result) {
        this.result = result;
    }
}
