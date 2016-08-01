package ua.goit.timonov.enterprise.module_6_2.model;

import java.util.List;

/**
 * Created by Alex on 31.07.2016.
 */
public interface MenuDAO {

    void add(Menu menu);

    void delete(Menu menu);

    Menu find(String nameToSearch);

    List<Menu> getAll();

    void addDish(String menuName, Dish dish);

    void addDish(String menuName, String dishName);

    void deleteDish(String menuName, String dishName);
}
