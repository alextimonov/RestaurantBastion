package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.DishDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;

import java.util.List;

/**
 * Controller for DishDAO
 */
public class DishController {

    private DishDAO dishDAO;

    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    /**
     * finds list of all dishes in DB
     * @return          list of dishes
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> getAll() {
        return dishDAO.getAll();
    }

    /**
     * adds new dish to DB
     * @param dish      given dish
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Dish dish) {
        dishDAO.add(dish);
    }

    /**
     * searches dish in DB by its ID
     * @param id        dish's ID to find
     * @return          found dish
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Dish search(int id) {
        return dishDAO.search(id);
    }

    /**
     * searches dish in DB by name
     * @param name           name of dish to find
     * @return name          found dish
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Dish search(String name) {
        return dishDAO.search(name);
    }

    /**
     * deletes dish from DB by its ID
     * @param id            dish's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int id) {
        dishDAO.delete(id);
    }

    /**
     * deletes dish from DB by its name
     * @param name           name of dish to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String name) {
        dishDAO.delete(name);
    }
}
