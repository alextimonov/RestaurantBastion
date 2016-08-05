package ua.goit.timonov.enterprise.module_6_2.dao;

import ua.goit.timonov.enterprise.module_6_2.model.Dish;

import java.util.List;

/**
 * Created by Alex on 31.07.2016.
 */
public interface DishDAO {

    void add(Dish dish);

    void delete(String name);

    void delete(int id);

    Dish search(String nameToFind);

    Dish search(int id);

    List<Dish> getAll();
}
