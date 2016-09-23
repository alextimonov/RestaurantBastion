package ua.goit.timonov.enterprise.module_9.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_9.service.DishService;

import java.util.Map;

/**
 * Created by Alex on 14.09.2016.
 */
@Controller
public class DishServiceController {

    private DishService dishService;

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(value = "/service/dishes", method = RequestMethod.GET)
    public String getAllDishes(Map<String, Object> model) {
        model.put("dishes", dishService.getAllDishes());
        return "service/dishes";
    }

    @RequestMapping(value = "/dish/add", method = RequestMethod.GET)
    public String getDishToAdd(Map<String, Object> model) {
        model.put("dishAttribute", new Dish());
        return "dish/add";
    }

    @RequestMapping(value = "/dish/add", method = RequestMethod.POST)
    public String addDish(Map<String, Object> model, @ModelAttribute("dishAttribute") Dish dish) {
        dishService.add(dish);
        model.put("dishes", dishService.getAllDishes());
        return "service/dishes";
    }

    @RequestMapping(value = "/dish/deleteById", method = RequestMethod.GET)
    public String deleteDishById(Map<String, Object> model, @RequestParam(value="id", required=true) Integer dishId) {
        dishService.delete(dishId);
        model.put("dishes", dishService.getAllDishes());
        return "service/dishes";
    }

    @RequestMapping(value = "/dish/deleteByName", method = RequestMethod.GET)
    public String deleteDishByName(Map<String, Object> model, @RequestParam(value="name", required=true) String dishName) {
        dishService.delete(dishName);
        model.put("dishes", dishService.getAllDishes());
        return "service/dishes";
    }

    @RequestMapping(value = "/dish/editById", method = RequestMethod.GET)
    public String editDishById(Map<String, Object> model, @RequestParam(value="id", required=true) Integer id) {
        Dish dish = dishService.searchDishById(id);
        model.put("dishExisting", dish);
        return "dish/edit";
    }

    @RequestMapping(value = "/dish/editByName", method = RequestMethod.GET)
    public String editDishByName(Map<String, Object> model, @RequestParam(value="name", required=true) String name) {
        Dish dish = dishService.searchDishByName(name);
        model.put("dishExisting", dish);
        return "dish/edit";
    }

    @RequestMapping(value = "/dish/edit", method = RequestMethod.POST)
    public String saveEditDish(Map<String, Object> model, @ModelAttribute("dishAttribute") Dish dish,
                               @RequestParam(value="id", required=true) Integer id) {
        dishService.update(dish);
        model.put("dishes", dishService.getAllDishes());
        return "service/dishes";
    }

}
