package ua.goit.timonov.enterprise.module_6_2.model.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;
import ua.goit.timonov.enterprise.module_6_2.model.StorageDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 01.08.2016.
 */
public class JdbcStorageDAO implements StorageDAO {

    private JdbcTemplate template;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void add(Ingredient ingredient) {
        String sql = "INSERT INTO ingredient VALUES ((SELECT max(id) FROM ingredient) + 1, ?, ?)";
        template.update(sql, ingredient.getName(), ingredient.getAmount());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(String ingredientName) {
        String sql = "DELETE FROM ingredient WHERE name = ?";
        template.update(sql, ingredientName);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void changeAmount(String ingredientName, int difference) {
        String sql = "UPDATE ingredient SET amount = (SELECT amount FROM ingredient WHERE name = ?) + ? WHERE name = ?";
        template.update(sql, ingredientName, difference, ingredientName);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Ingredient find(String ingredientName) {
        String sql = "SELECT * FROM ingredient WHERE ingredient.name = ?";
        Map<String, Object> map = template.queryForMap(sql, ingredientName);
        Ingredient ingredient = getIngredientFromMap(map);
        return ingredient;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private Ingredient getIngredientFromMap(Map<String, Object> map) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId((Integer) map.get("id"));
        ingredient.setName((String) map.get("name"));
        ingredient.setAmount((Integer) map.get("amount"));
        return ingredient;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Ingredient> getAll() {
        List<Ingredient> result = new ArrayList<>();
        String sql = "SELECT * FROM ingredient";
        List<Map<String, Object>> mapList = template.queryForList(sql);
        for (Map<String, Object> row : mapList) {
            Ingredient ingredient = getIngredientFromMap(row);
            result.add(ingredient);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Ingredient> getTerminatingIngredients(int limit) {
        List<Ingredient> result = new ArrayList<>();
        String sql = "SELECT * FROM ingredient WHERE amount < ?";
        List<Map<String, Object>> mapList = template.queryForList(sql, limit);
        for (Map<String, Object> row : mapList) {
            Ingredient ingredient = getIngredientFromMap(row);
            result.add(ingredient);
        }
        return result;
    }
}
