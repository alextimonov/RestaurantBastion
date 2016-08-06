package ua.goit.timonov.enterprise.module_6_2.dao;

import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;

import java.util.List;

/**
 * DAO interface for Storage
 */
public interface StorageDAO {

    /**
     * finds list of all ingredients in DB
     * @return              list of ingredients
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    List<Ingredient> getAll();

    /**
     * adds new ingredient to DB
     * @param ingredient      given ingredient
     */
    void add(Ingredient ingredient);

    /**
     * searches ingredient in DB by its ID
     * @param id        ingredient's ID to find
     * @return          found ingredient
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    Ingredient search(int id);

    /**
     * searches ingredient in DB by name
     * @param name       name of ingredient to find
     * @return           found ingredient
     * throws            EmptyResultDataAccessException, DataAccessException
     */
    Ingredient search(String name);

    /**
     * deletes ingredient from DB by its ID
     * @param id            ingredient's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    void delete(int id);

    /**
     * deletes ingredient from DB by its name
     * @param name           name of ingredient to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    void delete(String name);

    /**
     * changes amount of ingredient
     * @param name        ingredient's name
     * @param difference            difference to be added to current amount
     * throws                       EmptyResultDataAccessException, DataAccessException                              
     */
    void changeAmount(String name, int difference);

    /**
     * finds list of terminating ingredients with amount less than given limit
     * @param limit                 limit to chose an ingredient
     * @return                      list of ingredient
     */
    List<Ingredient> getTerminatingIngredients(int limit);
}