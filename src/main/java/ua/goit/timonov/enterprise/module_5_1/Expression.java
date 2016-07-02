package ua.goit.timonov.enterprise.module_5_1;

/**
 * Created by Alex on 02.07.2016.
 */
public interface Expression<T> {

    void evaluate();

    T getResult();
}
