package ua.goit.timonov.enterprise.module_5_1;

/**
 * Created by Alex on 01.07.2016.
 */
public class Expression<T> {
    private T x1;
    private T x2;
    private Operation<T> operation;

    public Expression() {
    }

    public Expression(T x1, T x2, Operation<T> operation) {
        this.x1 = x1;
        this.x2 = x2;
        this.operation = operation;
    }

    public T getX1() {
        return x1;
    }

    public void setX1(T x1) {
        this.x1 = x1;
    }

    public T getX2() {
        return x2;
    }

    public void setX2(T x2) {
        this.x2 = x2;
    }

    public Operation<T> getOperation() {
        return operation;
    }

    public void setOperation(Operation<T> operation) {
        this.operation = operation;
    }
}
