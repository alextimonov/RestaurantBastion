package ua.goit.timonov.enterprise.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.timonov.enterprise.exceptions.NoItemInDbException;
import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.IngredientsInDish;
import ua.goit.timonov.enterprise.service.DishService;

import java.util.List;
import java.util.Map;

/**
 * Spring MVC controller for mapping common pages for Dish pages
 */
@Controller
public class DishWebController {

    public static final String DISHES = "dishes";
    public static final String DISH = "dish";
    public static final String DISH_NAME = "dishName";
    private DishService dishService;

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    public String getDishes(Map<String, Object> model) {
        model.put(DISHES, dishService.getAllDishes());
        return DISHES;
    }

    @RequestMapping(value = "/dish/{dishName}", method = RequestMethod.GET)
    public ModelAndView getDish(@PathVariable(DISH_NAME) String dishName) {
        ModelAndView modelAndView = new ModelAndView(DISH);
        Dish dish = dishService.searchDishByName(dishName);
        modelAndView.addObject(DISH, dish);
        List<IngredientsInDish> items = dishService.getIngredientsInDish(dish);
        modelAndView.addObject("itemsInDish", items);
        return modelAndView;
    }

    @RequestMapping(value = "/searchDish", method = RequestMethod.GET)
    public String searchDishParam(Map<String, Object> model, @RequestParam(DISH_NAME) String dishName) {
        try {
            Dish dish = dishService.searchDishByName(dishName);
            model.put(DISH, dish);
            model.put("itemsInDish", dishService.getIngredientsInDish(dish));
            return DISH;
        }
        catch (NoItemInDbException e) {
            model.put(DISH_NAME, dishName);
            model.put("errorMessage", e.getMessage());
            return "dishNotFound";
        }
    }
}
