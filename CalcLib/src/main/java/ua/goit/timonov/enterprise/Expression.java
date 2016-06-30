package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 29.06.2016.
 */
public class Expression<T>  {
    private T value1;
    private T value2;
    private T result;

    public Expression() {
    }

    public Expression(T value1, T value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getValue1() {
        return value1;
    }

    public T getValue2() {
        return value2;
    }

    public T getResult() {
        return result;
    }

    public void setValue1(T value1) {
        this.value1 = value1;
    }

    public void setValue2(T value2) {
        this.value2 = value2;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
