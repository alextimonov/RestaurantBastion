package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.DishDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;

import java.util.List;

/**
 * Created by Alex on 31.07.2016.
 */
public class DishController {

    private DishDAO dishDAO;

    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> getAll() {
        return dishDAO.getAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Dish dish) {
        dishDAO.add(dish);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int id) {
        dishDAO.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String name) {
        dishDAO.delete(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Dish search(String nameToFind) {
        return dishDAO.search(nameToFind);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Dish search(int id) {
        return dishDAO.search(id);
    }
}
