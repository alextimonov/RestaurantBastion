package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;
import ua.goit.timonov.enterprise.module_6_2.dao.MenuDAO;

import java.util.List;

/**
 * Controller for EmployeeDAO
 */
public class MenuController {

    private MenuDAO menuDAO;

    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    /**
     * finds list of all menus in DB
     * @return          list of menus
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Menu> getAll() {
        return menuDAO.getAll();
    }

    /**
     * adds new menu to DB
     * @param menu      given menu
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Menu menu) {
        menuDAO.add(menu);
    }

    /**
     * searches menu in DB by its ID
     * @param id        menu's ID to find
     * @return          found menu
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Menu search(int id) {
        return menuDAO.search(id);
    }

    /**
     * searches menu in DB by name
     * @param name       name of menu to find
     * @return           found menu
     * throws            EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Menu search(String name) {
        return menuDAO.search(name);
    }

    /**
     * deletes menu from DB by its ID
     * @param id            menu's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int id) {
        menuDAO.delete(id);
    }

    /**
     * deletes menu from DB by its name
     * @param name           name of menu to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String name) {
        menuDAO.delete(name);
    }

    /**
     * adds dish to menu
     * @param menu          dish menu
     * @param dish          dish to be added
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addDish(Menu menu, Dish dish) {
        menuDAO.addDish(menu, dish);
    }

    /**
     * deletes dish from menu
     * @param menu          dish menu
     * @param dish          dish to be deleted
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteDish(Menu menu, Dish dish) {
        menuDAO.deleteDish(menu, dish);
    }
}