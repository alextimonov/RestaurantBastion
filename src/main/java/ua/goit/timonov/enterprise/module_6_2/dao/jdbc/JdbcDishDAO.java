package ua.goit.timonov.enterprise.module_6_2.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.DishDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JDBC implementation of DishDAO
 */
public class JdbcDishDAO implements DishDAO {

    private JdbcTemplate template;
    private JdbcStorageDAO jdbcStorageDAO;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    public void setJdbcStorageDAO(JdbcStorageDAO jdbcStorageDAO) {
        this.jdbcStorageDAO = jdbcStorageDAO;
    }

    /**
     * adds new dish to DB
     * @param dish      given dish
     */
    @Override
    @Transactional
    public void add(Dish dish) {
        String sql = "INSERT INTO Dish (name, description, cost, weight) VALUES (?, ?, ?, ?)";
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
    @Transactional
    public void delete(int id) {
        search(id);
        String sql = "DELETE FROM Dish WHERE id = ?";
        template.update(sql, id);
    }

    /**
     * deletes dish from DB by its name
     * @param name           name of dish to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public void delete(String name) {
        search(name);
        String sql = "DELETE FROM Dish WHERE name = ?";
        template.update(sql, name);
    }

    @Override
    public List<Ingredient> defineDishIngredients(Dish dish) {
        String sql = "SELECT Ingredient.* \n" +
                "FROM (Ingredient INNER JOIN Ingredient_to_dish ON Ingredient_to_dish.ingredient_id = Ingredient.id) \n" +
                "WHERE Ingredient_to_dish.dish_id = ?";
        List<Map<String, Object>> mapList = template.queryForList(sql, dish.getId());
        return mapList.stream()
                .map(row -> jdbcStorageDAO.getIngredientFromMap(row))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Dish dish) {
        String sql = "UPDATE Dish SET name = ?, description = ?, cost = ?, weight = ? WHERE id = ?";
        template.update(sql, dish.getName(), dish.getDescription(), dish.getCost(), dish.getWeight(), dish.getId());
    }

    /**
     * searches dish in DB by name
     * @param name           name of dish to find
     * @return name          found dish
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public Dish search(String name) {
        String sql = "SELECT * FROM Dish WHERE name = ?";
        Map<String, Object> map = template.queryForMap(sql, name);
        Dish dish = getDishFromMap(map);
        return dish;
    }

    /**
     * searches dish in DB by its ID
     * @param id        dish's ID to find
     * @return          found dish
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public Dish search(int id) {
        String sql = "SELECT * FROM Dish WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, id);
        return getDishFromMap(map);
    }

    @Override
    @Transactional
    public List<Dish> getAll() {
        List<Dish> result = new ArrayList<>();
        String sql = "SELECT * FROM Dish";
        List<Map<String, Object>> mapList = template.queryForList(sql);
        for (Map<String, Object> row : mapList) {
            Dish dish = getDishFromMap(row);
            result.add(dish);
        }
        return mapList.stream()
                .map(row -> getDishFromMap(row))
                .collect(Collectors.toList());
    }

    @Transactional
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
