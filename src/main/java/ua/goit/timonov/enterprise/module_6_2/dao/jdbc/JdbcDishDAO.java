package ua.goit.timonov.enterprise.module_6_2.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.dao.DishDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JDBC implementation of DishDAO
 */
public class JdbcDishDAO implements DishDAO {

    private JdbcTemplate template;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    /**
     * adds new dish to DB
     * @param dish      given dish
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void add(Dish dish) {
        String sql = "INSERT INTO dish VALUES ((SELECT max(dish.id) FROM dish) + 1, ?, ?, ?, ?)";
        template.update(sql,
                dish.getName(),
                dish.getDescription(),
                dish.getCost(),
                dish.getWeight());
    }

    /**
     * deletes dish from DB by its ID
     * @param id            dish's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(int id) {
        search(id);
        String sql = "DELETE FROM dish WHERE id = ?";
        template.update(sql, id);
    }

    /**
     * deletes dish from DB by its name
     * @param name           name of dish to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(String name) {
        search(name);
        String sql = "DELETE FROM dish WHERE name = ?";
        template.update(sql, name);
    }

    /**
     * searches dish in DB by name
     * @param name           name of dish to find
     * @return name          found dish
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Dish search(String name) {
        String sql = "SELECT * FROM dish WHERE name = ?";
        Map<String, Object> map = template.queryForMap(sql, name);
        return getDishFromMap(map);
    }

    /**
     * searches dish in DB by its ID
     * @param id        dish's ID to find
     * @return          found dish
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Dish search(int id) {
        String sql = "SELECT * FROM dish WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, id);
        return getDishFromMap(map);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Dish> getAll() {
        List<Dish> result = new ArrayList<>();
        String sql = "SELECT * FROM dish";
        List<Map<String, Object>> mapList = template.queryForList(sql);
        for (Map<String, Object> row : mapList) {
            Dish dish = getDishFromMap(row);
            result.add(dish);
        }
        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    Dish getDishFromMap(Map<String, Object> map) {
        Dish dish = new Dish();
        dish.setId((Integer) map.get("id"));
        dish.setName((String) map.get("name"));
        dish.setDescription((String) map.get("description"));
        dish.setCost((Float) map.get("cost"));
        dish.setWeight((Integer) map.get("weight"));
        return dish;
    }
}
