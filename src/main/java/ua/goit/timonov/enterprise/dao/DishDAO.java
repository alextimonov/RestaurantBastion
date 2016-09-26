package ua.goit.timonov.enterprise.dao;

import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.Ingredient;
import ua.goit.timonov.enterprise.model.IngredientsInDish;

import java.util.List;

/**
 * DAO interface for Dish
 */
public interface DishDAO {

    /**
     * finds list of all dishes in DB
     * @return          list of dishes
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    List<Dish> getAll();

    /**
     * adds new dish to DB
     * @param dish      given dish
     */
    void add(Dish dish);

    /**
     * searches dish in DB by its ID
     * @param id        dish's ID to find
     * @return          found dish
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    Dish search(int id);

    /**
     * searches dish in DB by name
     * @param name           name of dish to find
     * @return name          found dish
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    Dish search(String name);

    /**
     * deletes dish from DB by its ID
     * @param id            dish's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    void delete(int id);

    /**
     * deletes dish from DB by its name
     * @param name           name of dish to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    void delete(String name);

    /**
     * defines ingredients of given dish
     * @param dish           given dish
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    List<Ingredient> defineDishIngredients(Dish dish);

    /**
     * updates edited dish in DB
     * @param dish             given dish
     * throws                  EmptyResultDataAccessException, DataAccessException
     */
    void update(Dish dish);

    List<IngredientsInDish> getIngredientsInDish(Dish dish);

    void addItemToDish(IngredientsInDish item);

    IngredientsInDish searchItemInDish(Integer id);

    void updateItemInDish(IngredientsInDish item);

    void deleteItemFromDish(Dish dish, IngredientsInDish item);
}
