package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;
import ua.goit.timonov.enterprise.module_6_2.dao.MenuDAO;

import java.util.List;

/**
 * Created by Alex on 31.07.2016.
 */
public class MenuController {

    private MenuDAO menuDAO;

    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Menu> getAll() {
        return menuDAO.getAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Menu menu) {
        menuDAO.add(menu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int id) {
        menuDAO.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String name) {
        menuDAO.delete(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Menu search(String nameToSearch) {
        return menuDAO.search(nameToSearch);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Menu search(int id) {
        return menuDAO.search(id);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void addDish(String menuName, Dish dish) {
        menuDAO.addDish(menuName, dish);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addDish(String menuName, String dishName) {
        menuDAO.addDish(menuName, dishName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteDish(String menuName, String dishName) {
        menuDAO.deleteDish(menuName, dishName);
    }
}