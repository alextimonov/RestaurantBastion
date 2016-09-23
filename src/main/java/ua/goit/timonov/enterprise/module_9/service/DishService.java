package ua.goit.timonov.enterprise.module_9.service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.DishDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;

import java.util.List;

/**
 * Service for DishDAO
 */
public class DishService {

    private DishDAO dishDAO;

    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    public DishDAO getDishDAO() {
        return dishDAO;
    }

    @Transactional
    public List<Dish> getAllDishes() {
        return dishDAO.getAll();
    }

    @Transactional
    public Dish getDishByName(String dishName) {
        return dishDAO.search(dishName);
    }

    @Transactional
    public List<Ingredient> getIngredientsByDish(Dish dish) {
        return dishDAO.defineDishIngredients(dish);
    }

    @Transactional
    public Dish searchDishByName(String dishName) {
        return dishDAO.search(dishName);
    }
}
