package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;
import ua.goit.timonov.enterprise.module_6_2.dao.StorageDAO;
import ua.goit.timonov.enterprise.module_6_2.dao.jdbc.JdbcStorageDAO;

import java.util.List;

/**
 * Controller for StorageDAO
 */
public class StorageController {

    private StorageDAO storageDAO;

    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    /**
     * finds list of all ingredients in DB
     * @return              list of ingredients
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public List<Ingredient> getAll() {
        return storageDAO.getAll();
    }

    /**
     * adds new ingredient to DB
     * @param ingredient      given ingredient
     */
    @Transactional
    public void add(Ingredient ingredient) {
        storageDAO.add(ingredient);
    }

    /**
     * searches ingredient in DB by its ID
     * @param id        ingredient's ID to find
     * @return          found ingredient
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public Ingredient search(Integer id) {
        return storageDAO.search(id);
    }

    /**
     * searches ingredient in DB by name
     * @param name       name of ingredient to find
     * @return           found ingredient
     * throws            EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public Ingredient search(String name) {
        return storageDAO.search(name);
    }

    /**
     * deletes ingredient from DB by its ID
     * @param id            ingredient's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public void delete(Integer id) {
        storageDAO.delete(id);
    }

    /**
     * deletes ingredient from DB by its name
     * @param name           name of ingredient to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public void delete(String name) {
        storageDAO.delete(name);
    }

    /**
     * changes amount of ingredient
     * @param ingredient            ingredient which amount to be changed
     * @param difference            difference to be added to current amount
     * throws                       EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public void changeAmount(Ingredient ingredient, int difference) {
        storageDAO.changeAmount(ingredient, difference);
    }

    /**
     * finds list of terminating ingredients with amount less than given limit
     * @param limit                 limit to chose an ingredient
     * @return                      list of ingredient
     */
    @Transactional
    public List<Ingredient> getTerminatingIngredients(int limit) {
        return storageDAO.getTerminatingIngredients(limit);
    }
}
