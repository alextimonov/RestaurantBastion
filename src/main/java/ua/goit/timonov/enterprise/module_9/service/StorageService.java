package ua.goit.timonov.enterprise.module_9.service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.StorageDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;

import java.util.List;

/**
 * Created by Alex on 17.09.2016.
 */
public class StorageService {

    private StorageDAO storageDAO;

    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    @Transactional
    public List<Ingredient> getAllIngredients() {
        return storageDAO.getAll();
    }

    @Transactional
    public void add(Ingredient ingredient) {
        storageDAO.add(ingredient);
    }

    @Transactional
    public void delete(Integer id) {
        storageDAO.delete(id);
    }

    @Transactional
    public void delete(String name) {
        storageDAO.delete(name);
    }

    @Transactional
    public Ingredient searchById(Integer id) {
        return storageDAO.search(id);
    }

    @Transactional
    public Ingredient searchByName(String name) {
        return storageDAO.search(name);
    }

    @Transactional
    public void update(Ingredient ingredient) {
        storageDAO.update(ingredient);
    }

    @Transactional
    public List<Ingredient> filterWithStartChars(String startChars) {
        return storageDAO.filterWithStartChars(startChars);
    }
}
