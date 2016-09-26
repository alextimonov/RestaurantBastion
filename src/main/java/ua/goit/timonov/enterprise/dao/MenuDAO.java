package ua.goit.timonov.enterprise.dao;

import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.Menu;

import java.util.List;

/**
 * DAO interface for Menu (dish menu)
 */
public interface MenuDAO {

    /**
     * finds list of all menus in DB
     * @return          list of menus
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    List<Menu> getAll();

    /**
     * adds new menu to DB
     * @param menu      given menu
     */
    void add(Menu menu);

    /**
     * searches menu in DB by its ID
     * @param id        menu's ID to find
     * @return          found menu
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    Menu search(int id);

    /**
     * searches menu in DB by name
     * @param name       name of menu to find
     * @return           found menu
     * throws            EmptyResultDataAccessException, DataAccessException
     */
    Menu search(String name);

    /**
     * deletes menu from DB by its ID
     * @param id            menu's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    void delete(int id);

    /**
     * deletes menu from DB by its name
     * @param name           name of menu to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    void delete(String name);

    /**
     * adds dish to menu
     * @param menu          dish menu
     * @param dish          dish to be added
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    void addDish(Menu menu, Dish dish);

    /**
     * deletes dish from menu
     * @param menu          dish menu
     * @param dish          dish to be deleted
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    void deleteDish(Menu menu, Dish dish);

    void update(Menu menu);
}
