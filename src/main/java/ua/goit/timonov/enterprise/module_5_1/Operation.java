package ua.goit.timonov.enterprise.module_5_1;

/**
 * Created by Alex on 01.07.2016.
 */
public interface Operation<A, R> {
    R calc(A x1, A x2);
}
