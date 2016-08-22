package ua.goit.timonov.enterprise.module_6_2.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;
import ua.goit.timonov.enterprise.module_6_2.dao.MenuDAO;

import java.util.*;

/**
 * JDBC implementation of MenuDAO
 */
public class JdbcMenuDAO implements MenuDAO {

    private JdbcTemplate template;
    private JdbcDishDAO jdbcDishDAO;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    public void setJdbcDishDAO(JdbcDishDAO jdbcDishDAO) {
        this.jdbcDishDAO = jdbcDishDAO;
    }

    /**
     * finds list of all menus in DB
     * @return          list of menus
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Menu> getAll() {
        String sql = "SELECT * FROM Menu";
        List<Map<String, Object>> mapList = template.queryForList(sql);

        List<Menu> result = new ArrayList<>();
        for (Map<String, Object> row : mapList) {
            Menu menu = new Menu();
            menu.setId((Integer) row.get("id"));
            menu.setName((String) row.get("name"));
            List<Dish> dishes = findDishesByMenuId(menu.getId());
            menu.setDishes(dishes);
            result.add(menu);
        }
        return result;
    }

    /**
     * adds new menu to DB
     * @param menu      given menus
     */
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void add(Menu menu) {
        String sql = "INSERT INTO Menu VALUES ((SELECT max(Menu.id) FROM Menu) + 1, ?)";
        template.update(sql, menu.getName());
    }

    /**
     * searches menu in DB by its ID
     * @param id        menu's ID to find
     * @return          found menu
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Menu search(int id) {
        String sql = "SELECT * FROM Menu WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, id);
        Menu menu = new Menu();
        menu.setId((Integer) map.get("id"));
        menu.setName((String) map.get("name"));
        List<Dish> dishes = findDishesByMenuId(menu.getId());
        menu.setDishes(dishes);
        return menu;
    }

    /**
     * searches menu in DB by name
     * @param name       name of menu to find
     * @return           found menu
     * throws            EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Menu search(String name) {
        String sql = "SELECT * FROM Menu WHERE name = ?";
        Map<String, Object> map = template.queryForMap(sql, name);
        Menu menu = new Menu();
        menu.setId((Integer) map.get("id"));
        menu.setName((String) map.get("name"));
        List<Dish> dishes = findDishesByMenuId(menu.getId());
        menu.setDishes(dishes);
        return menu;
    }

    /**
     * deletes menu from DB by its ID
     * @param id            menu's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void delete(int id) {
        search(id);
        String sql = "DELETE FROM Menu WHERE id = ?";
        template.update(sql, id);
    }

    /**
     * deletes menu from DB by its name
     * @param name           name of menu to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void delete(String name) {
        search(name);
        String sql = "DELETE FROM Menu WHERE name = ?";
        template.update(sql, name);
    }

    /**
     * adds dish to menu
     * @param menu          dish menu
     * @param dish          dish to be added
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addDish(Menu menu, Dish dish) {
        String sql = "INSERT INTO Dish_to_menu VALUES ((SELECT Menu.id FROM Menu WHERE id = ?), (SELECT Dish.id FROM Dish WHERE id = ?))";
        template.update(sql, menu.getId(), dish.getId());
    }

    // returns list of dishes in menu by menu's ID
    @Transactional(propagation = Propagation.MANDATORY)
    private List<Dish> findDishesByMenuId(int menuId) {
        String sql = "SELECT Dish.id, Dish.name, Dish.description, Dish.cost, Dish.weight\n" +
                "FROM (Dish_to_menu INNER JOIN Dish ON Dish_to_menu.dish_id = Dish.id)\n" +
                "WHERE Dish_to_menu.menu_id = ?";
        List<Map<String, Object>> mapList = template.queryForList(sql, menuId);

        List<Dish> result = new ArrayList<>();
        for (Map<String, Object> row : mapList) {
            Dish dish = jdbcDishDAO.getDishFromMap(row);
            result.add(dish);
        }
        return result;
    }

    /**
     * deletes dish from menu
     * @param menu          dish menu
     * @param dish          dish to be deleted
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    public void deleteDish(Menu menu, Dish dish) {
        String sql = "DELETE FROM Dish_to_menu VALUES WHERE menu_id = ? AND dish_id = ?";
        template.update(sql, menu.getId(), dish.getId());
    }
}