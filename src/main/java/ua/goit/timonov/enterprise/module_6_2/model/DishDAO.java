package ua.goit.timonov.enterprise.module_6_2.model;

import java.util.List;

/**
 * Created by Alex on 31.07.2016.
 */
public interface DishDAO {

    void add(Dish dish);

    void delete(Dish dish);

    Dish find(String nameToFind);

    List<Dish> getAll();
}
