package ua.goit.timonov.enterprise.module_6_2.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.CookedDishDAO;
import ua.goit.timonov.enterprise.module_6_2.model.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JDBC implementation of CookedDishDAO
 */
public class JdbcCookedDishDAO implements CookedDishDAO {

    private JdbcTemplate template;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    /**
     * finds list of all cooked dishes in DB
     * @return              list of cooked dishes
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<CookedDish> getAll() {
        String sql = "SELECT * FROM Cooked_dish";
        List<Map<String, Object>> mapList = template.queryForList(sql);
        return mapList.stream()
                .map(row -> getCookedDishFromMap(row))
                .collect(Collectors.toList());
    }

    /**
     * adds new cooked dish to DB table
     * @param orderId       order's id, in which dish is cooked
     * @param dishName      name of dish
     * @param cookId        employee's employee that cooked the dish
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void add(int orderId, String dishName, int cookId) {
        if (orderIsClosedByOrderId(orderId)) {
            String sql = "INSERT INTO Cooked_dish VALUES ((SELECT max(Cooked_dish.id) FROM Cooked_dish) + 1, ?, ?, " +
                    "(SELECT Dish.id FROM Dish WHERE Dish.name = ?))";
            template.update(sql, orderId, dishName, cookId);
        }
        else {
            throw new IllegalArgumentException("Order is not closed.");
        }
    }

    /*
     * adds new cooked dish to DB table
     * @param cookedDish        cooked dish to add
     * throws                   EmptyResultDataAccessException, DataAccessException
     */
     @Override
     @Transactional(propagation = Propagation.MANDATORY)
     public void add(CookedDish cookedDish) {
         int orderId = cookedDish.getOrder().getId();
         int dishId = cookedDish.getDish().getId();
         int cookId = cookedDish.getCook().getId();
         if (orderIsClosedByOrderId(orderId)) {
             String sql = "INSERT INTO Cooked_dish VALUES ((SELECT max(Cooked_dish.id) FROM Cooked_dish) + 1, ?, ?, ?)";
             template.update(sql, orderId, cookId, dishId);
         }
         else {
             throw new IllegalArgumentException("Order is not closed.");
         }
     }

    // returns true if order is closed
    @Transactional(propagation = Propagation.MANDATORY)
    private boolean orderIsClosedByOrderId(int orderedId) {
        String sql = "SELECT closed FROM Orders WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, orderedId);
        return (boolean) map.get("closed");
    }

    // returns cooked dish' data from SQL query map
    private CookedDish getCookedDishFromMap(Map<String, Object> map) {
        CookedDish cookedDish = new CookedDish();
        cookedDish.setId((Integer) map.get("id"));
        int orderId = (Integer) map.get("order_id");
        int cookId = (Integer) map.get("cook_id");
        int dishId = (Integer) map.get("menu_dish_id");
        cookedDish.setCook(defineCookById(cookId));
        cookedDish.setOrder(defineOrderById(orderId));
        cookedDish.setDish(defineDishById(dishId));
        return cookedDish;
    }

    // gets dish by its ID
    @Transactional(propagation = Propagation.MANDATORY)
    private Dish defineDishById(int dishId) {
        String sql = "SELECT * FROM Dish WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, dishId);
        Dish dish = new Dish();
        dish.setId((Integer) map.get("id"));
        dish.setName((String) map.get("name"));
        dish.setDescription((String) map.get("description"));
        dish.setCost((Float) map.get("cost"));
        dish.setWeight((Integer) map.get("weight"));
        return dish;
    }

    // gets order by its ID
    @Transactional(propagation = Propagation.MANDATORY)
    private Order defineOrderById(int orderId) {
        String sql = "SELECT * FROM Orders WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, orderId);
        Order order = new Order();
        order.setId((Integer) map.get("id"));
        order.setWaiter((Employee) map.get("employee_id"));
        order.setTableNumber((Integer) map.get("table_number"));
        order.setDate((Date) map.get("date"));
        order.setClosed((Boolean) map.get("closed"));
        return order;
    }

    // gets employee (cook) by its ID
    @Transactional(propagation = Propagation.MANDATORY)
    private Employee defineCookById(int cookId) {
        String sql = "SELECT Employee.id, Employee.surname, Employee.name, Jobs.position, Employee.birthday, Employee.salary " +
                "FROM Employee INNER JOIN Jobs ON Employee.position_id = Jobs.id WHERE Employee .id = ?";
        Map<String, Object> map = template.queryForMap(sql, cookId);

        Employee cook = new Employee();
        cook.setId(cookId);
        cook.setSurname((String) map.get("surname"));
        cook.setName((String) map.get("name"));
        cook.setJob((Job) map.get("job"));
        cook.setBirthday((Date) map.get("birthday"));
        cook.setSalary((Float) map.get("salary"));
        return cook;
    }
}