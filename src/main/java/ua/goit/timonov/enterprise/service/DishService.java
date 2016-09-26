package ua.goit.timonov.enterprise.service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.dao.DishDAO;
import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.Ingredient;
import ua.goit.timonov.enterprise.model.IngredientsInDish;

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


    @Transactional
    public void add(Dish dish) {
        dishDAO.add(dish);
    }

    @Transactional
    public void delete(int dishId) {
        dishDAO.delete(dishId);
    }

    @Transactional
    public void delete(String dishName) {
        dishDAO.delete(dishName);
    }

    @Transactional
    public Dish searchDishById(Integer dishId) {
        return dishDAO.search(dishId);
    }

    @Transactional
    public void update(Dish dish) {
        dishDAO.update(dish);
    }

    @Transactional
    public List<IngredientsInDish> getIngredientsInDish(Dish dish) {
        return dishDAO.getIngredientsInDish(dish);
    }

    @Transactional
    public void addItemToDish(IngredientsInDish item) {
        dishDAO.addItemToDish(item);
    }

    @Transactional
    public IngredientsInDish searchItemInDish(Integer id) {
        return dishDAO.searchItemInDish(id);
    }

    @Transactional
    public void updateItemInDish(IngredientsInDish item) {
        dishDAO.updateItemInDish(item);
    }

    @Transactional
    public void deleteItemFromDish(Dish dish, IngredientsInDish item) {
        dishDAO.deleteItemFromDish(dish, item);
    }
}
