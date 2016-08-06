package ua.goit.timonov.enterprise.module_6_2.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.jdbc.JdbcDishDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;
import ua.goit.timonov.enterprise.module_6_2.dao.MenuDAO;

import java.util.*;

/**
 * Created by Alex on 31.07.2016.
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

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void add(Menu menu) {
        String sql = "INSERT INTO menu VALUES ((SELECT max(menu.id) FROM menu) + 1, ?)";
        template.update(sql, menu.getName());
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void delete(int id) {
        search(id);
        String sql = "DELETE FROM menu WHERE id = ?";
        template.update(sql, id);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void delete(String name) {
        search(name);
        String sql = "DELETE FROM menu WHERE name = ?";
        template.update(sql, name);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Menu search(String nameToSearch) {
        String sql = "SELECT * FROM menu WHERE name = ?";
        Map<String, Object> map = template.queryForMap(sql, nameToSearch);

        Menu menu = new Menu();
        menu.setId((Integer) map.get("id"));
        menu.setName((String) map.get("name"));
        List<Dish> dishes = findDishesByMenuId(menu.getId());
        menu.setDishes(dishes);
        return menu;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Menu search(int id) {
        String sql = "SELECT * FROM menu WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, id);

        Menu menu = new Menu();
        menu.setId((Integer) map.get("id"));
        menu.setName((String) map.get("name"));
        List<Dish> dishes = findDishesByMenuId(menu.getId());
        menu.setDishes(dishes);
        return menu;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Menu> getAll() {
        String sql = "SELECT * FROM menu";
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

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addDish(Menu menu, Dish dish) {
        String sql = "INSERT INTO dish_to_menu VALUES ((SELECT max(dish_to_menu.id) FROM dish_to_menu) + 1, \n" +
                "(SELECT menu.id FROM MENU WHERE id = ?), (SELECT dish.id FROM DISH WHERE id = ?))";
        template.update(sql, menu.getId(), dish.getId());
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private List<Dish> findDishesByMenuId(int menuId) {
        String sql = "SELECT DISH.id, DISH.name, DISH.description, DISH.cost, DISH.weight\n" +
                "FROM (DISH_TO_MENU INNER JOIN DISH ON DISH_TO_MENU.dish_id = DISH.id)\n" +
                "WHERE DISH_TO_MENU.menu_id = ?";
        List<Map<String, Object>> mapList = template.queryForList(sql, menuId);

        List<Dish> result = new ArrayList<>();
        for (Map<String, Object> row : mapList) {
            Dish dish = jdbcDishDAO.getDishFromMap(row);
            result.add(dish);
        }
        return result;
    }

    @Override
    public void deleteDish(Menu menu, Dish dish) {
        String sql = "DELETE FROM dish_to_menu VALUES WHERE menu_id = ? AND dish_id = ?";
        template.update(sql, menu.getId(), dish.getId());
    }
}