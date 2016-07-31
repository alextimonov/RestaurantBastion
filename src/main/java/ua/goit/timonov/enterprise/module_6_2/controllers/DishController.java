package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.DishDAO;
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
    public void delete(Dish dish) {
        dishDAO.delete(dish);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Dish find(String nameToFind) {
        return dishDAO.find(nameToFind);
    }
}
