package ua.goit.timonov.enterprise.module_6_2.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.CookedDishDAO;
import ua.goit.timonov.enterprise.module_6_2.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 01.08.2016.
 */
public class JdbcCookedDishDAO implements CookedDishDAO {

    private JdbcTemplate template;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<CookedDish> getAll() {
        String sql = "SELECT * FROM cooked_dish";
        List<Map<String, Object>> mapList = template.queryForList(sql);

        List<CookedDish> result = new ArrayList<>();
        for (Map<String, Object> row : mapList) {
            CookedDish cookedDish = getCookedDishFromMap(row);
            result.add(cookedDish);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void add(int orderId, String dishName, int cookId) {
        if (orderIsClosedByOrderId(orderId)) {
            String sql = "INSERT INTO cooked_dish VALUES ((SELECT max(cooked_dish.id) FROM cooked_dish) + 1, " +
                    "(SELECT id FROM dish_to_ordering WHERE order_id = ? AND dish_id = " +
                    "(SELECT id FROM dish WHERE name = ?)), ?)";
            template.update(sql, orderId, dishName, cookId);
        }
        else {
            throw new IllegalArgumentException("Order is not closed.");
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private boolean orderIsClosedByOrderId(int orderedDishId) {
        String sql = "SELECT closed FROM ordering WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, orderedDishId);
        return (boolean) map.get("closed");
    }

    private CookedDish getCookedDishFromMap(Map<String, Object> map) {
        CookedDish cookedDish = new CookedDish();
        cookedDish.setId((Integer) map.get("id"));
        cookedDish.setOrderedDishId((Integer) map.get("ordered_dish_id"));
        int cookId = (Integer) map.get("cook_id");
        cookedDish.setCook(defineCookById(cookId));
        defineOrderAndDish(cookedDish);
        return cookedDish;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private void defineOrderAndDish(CookedDish cookedDish) {
        int orderedDishId = cookedDish.getOrderedDishId();
        String sql = "SELECT order_id, dish_id FROM dish_to_ordering WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, orderedDishId);
        int orderId = (Integer) map.get("order_id");
        int dishId = (Integer) map.get("dish_id");
        cookedDish.setOrder(defineOrderById(orderId));
        cookedDish.setDish(defineDishById(dishId));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private Dish defineDishById(int dishId) {
        String sql = "SELECT * FROM dish WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, dishId);
        Dish dish = new Dish();
        dish.setId((Integer) map.get("id"));
        dish.setName((String) map.get("name"));
        dish.setDescription((String) map.get("description"));
        dish.setCost((Float) map.get("cost"));
        dish.setWeight((Integer) map.get("weight"));
        return dish;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private Order defineOrderById(int orderId) {
        String sql = "SELECT * FROM ordering WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, orderId);
        Order order = new Order();
        order.setId((Integer) map.get("id"));
        order.setWaiterId((Integer) map.get("employee_id"));
        order.setTableNumber((Integer) map.get("table_number"));
        order.setDate((Date) map.get("date"));
        order.setClosed((Boolean) map.get("closed"));
        return order;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private Employee defineCookById(int cookId) {
        String sql = "SELECT employee.id, employee.surname, employee.name, JOBS.position, employee.birthday, employee.salary " +
                "FROM EMPLOYEE INNER JOIN JOBS ON EMPLOYEE.position_id = JOBS.id WHERE employee.id = ?";
        Map<String, Object> map = template.queryForMap(sql, cookId);

        Employee cook = new Employee();
        cook.setId(cookId);
        cook.setSurname((String) map.get("surname"));
        cook.setName((String) map.get("name"));
        cook.setPosition((String) map.get("position"));
        cook.setBirthday((Date) map.get("birthday"));
        cook.setSalary((Float) map.get("salary"));
        return cook;
    }
}