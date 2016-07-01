package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 01.07.2016.
 */
public interface Operation<A, R> {

    R execute(A... values);
}
