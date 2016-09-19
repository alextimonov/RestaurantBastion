package ua.goit.timonov.enterprise.module_9.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_9.service.DishService;

import java.util.Map;

/**
 * Controller for mapping requests to Dish pages
 */

@Controller
public class DishWebController {

    public static final String DISHES_PAGE = "dishes";
    public static final String DISH_PAGE = "dish";
    public static final String NOT_FOUND_PAGE = "itemNotFound";
    public static final String FIND_BY_NAME_PAGE = "findDishByName";
    private DishService dishService;

    @Autowired
    public DishWebController(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    public String getDishes(Map<String, Object> model) {
        model.put("dishes", dishService.getAllDishes());
        return DISHES_PAGE;
    }

    @RequestMapping(value = "/dish/{dishName}", method = RequestMethod.GET)
    public ModelAndView getDish(@PathVariable("dishName") String dishName) {
        ModelAndView modelAndView = new ModelAndView(DISH_PAGE);
        Dish dish = dishService.getDishByName(dishName);
        modelAndView.addObject("dish", dish);
        modelAndView.addObject("ingredients", dishService.getIngredientsByDish(dish));
        return modelAndView;
    }

    @RequestMapping(value = "/findDishByName", method = RequestMethod.GET)
    public String findDish() {
        return FIND_BY_NAME_PAGE;
    }

    @RequestMapping(value = "/searchDish", method = RequestMethod.GET)
    public String searchDishParam(@RequestParam("dishName") String dishName, Map<String, Object> model) {
        try {
            Dish dish = dishService.searchDishByName(dishName);
            model.put("dish", dish);
            model.put("ingredients", dishService.getIngredientsByDish(dish));
            return DISH_PAGE;
        }
        catch (RuntimeException e) {
            model.put("itemName", dishName);
            return NOT_FOUND_PAGE;
        }
    }
}
