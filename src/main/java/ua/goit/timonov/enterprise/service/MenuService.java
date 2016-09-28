package ua.goit.timonov.enterprise.service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.dao.MenuDAO;
import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.Menu;

import java.util.List;

/**
 * Service for MenuDAO
 */
public class MenuService {

    private MenuDAO menuDAO;

    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    public MenuDAO getMenuDAO() {
        return menuDAO;
    }

    @Transactional
    public List<Menu> getAllMenus() {
        return menuDAO.getAll();
    }

    @Transactional
    public Menu getMenuByName(String menuName) {
        return menuDAO.search(menuName);
    }

    @Transactional
    public void add(Menu menu) {
        menuDAO.add(menu);
    }

    @Transactional
    public void delete(Integer id) {
        menuDAO.delete(id);
    }

    @Transactional
    public void delete(String name) {
        menuDAO.delete(name);
    }

    @Transactional
    public Menu searchMenuById(Integer id) {
        return menuDAO.search(id);
    }

    @Transactional
    public Menu searchMenuByName(String name) {
        return menuDAO.search(name);
    }

    @Transactional
    public void update(Menu menu) {
        menuDAO.update(menu);
    }

    @Transactional
    public void addDish(Menu menu, Dish dish) {
        menuDAO.addDish(menu, dish);
    }

    @Transactional
    public void deleteDish(Menu menu, Dish dish) {
        menuDAO.deleteDish(menu, dish);
    }
}
