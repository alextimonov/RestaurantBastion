package ua.goit.timonov.enterprise.module_6_2.dao;

import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;

import java.util.List;

/**
 * Created by Alex on 31.07.2016.
 */
public interface MenuDAO {

    List<Menu> getAll();

    void add(Menu menu);

    Menu search(String nameToSearch);

    Menu search(int id);

    void delete(String name);

    void delete(int id);

    void addDish(String menuName, Dish dish);

    void addDish(String menuName, String dishName);

    void deleteDish(String menuName, String dishName);
}
