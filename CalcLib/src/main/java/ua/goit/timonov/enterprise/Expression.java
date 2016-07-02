package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 01.07.2016.
 */
public interface Expression<T> {

    void calculate();

    T getResult();
}
