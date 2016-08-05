package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;
import ua.goit.timonov.enterprise.module_6_2.dao.StorageDAO;
import ua.goit.timonov.enterprise.module_6_2.dao.jdbc.JdbcStorageDAO;

import java.util.List;

/**
 * Created by Alex on 01.08.2016.
 */
public class StorageController {

    private StorageDAO storageDAO;

    public void setStorageDAO(JdbcStorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Ingredient ingredient) {
        storageDAO.add(ingredient);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        storageDAO.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String ingredientName) {
        storageDAO.delete(ingredientName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void changeAmount(String ingredientName, int difference) {
        storageDAO.changeAmount(ingredientName, difference);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Ingredient search(String ingredientName) {
        return storageDAO.search(ingredientName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Ingredient search(Integer id) {
        return storageDAO.search(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Ingredient> getAll() {
        return storageDAO.getAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Ingredient> getTerminatingIngredients(int limit) {
        return storageDAO.getTerminatingIngredients(limit);
    }
}
