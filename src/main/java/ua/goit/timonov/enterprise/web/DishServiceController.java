package ua.goit.timonov.enterprise.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.goit.timonov.enterprise.exceptions.NoItemInDbException;
import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.Ingredient;
import ua.goit.timonov.enterprise.model.IngredientsInDish;
import ua.goit.timonov.enterprise.service.DishService;
import ua.goit.timonov.enterprise.service.StorageService;
import ua.goit.timonov.enterprise.web.validate.DishValidate;
import ua.goit.timonov.enterprise.web.validate.ItemInDishValidate;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Map;

/**
 * Spring MVC controller for mapping service pages for dishes
 */
@Controller
@RequestMapping("/service/dish")
public class DishServiceController {

    public static final String PATH_DISHES = "service/dish/dishes";
    public static final String PATH_EDIT = "service/dish/edit";
    public static final String PATH_ADD = "service/dish/add";
    public static final String PATH_ERROR = "service/errorMessage";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String PATH_DELETE = "service/dish/delete";
    public static final String PATH_EDIT_ITEM = "service/dish/editItem";
    public static final String PATH_ADD_ITEM = "service/dish/addItem";
    public static final String PATH_DELETE_ITEM = "service/dish/deleteItem";
    private DishService dishService;
    private StorageService storageService;

    public DishServiceController(DishService dishService) {
        this.dishService = dishService;
    }

    @Autowired
    public DishServiceController(DishService dishService, StorageService storageService) {
        this.dishService = dishService;
        this.storageService = storageService;
    }

    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    public String getAllDishes(Map<String, Object> model) {
        model.put("dishes", dishService.getAllDishes());
        return PATH_DISHES;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getDishToAdd(Map<String, Object> model) {
        model.put("dishValidate", new DishValidate());
        model.put("dishAttribute", new Dish());
        return PATH_ADD;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addDish(Map<String, Object> model, @ModelAttribute("dishAttribute") Dish dish,
                          @ModelAttribute("dishValidate") DishValidate dishValidate) {
        if (dishValidate.isValid(dish)) {
            if (checkForDishWithSameName(model, dish))
                return PATH_ERROR;
            dishService.add(dish);
            model.put("dishes", dishService.getAllDishes());
            return PATH_DISHES;
        }
        else {
            model.put("dishValidate", dishValidate);
            model.put("dishAttribute", dish);
            return PATH_ADD;
        }
    }

    private boolean checkForDishWithSameName(Map<String, Object> model, Dish newDish) {
        try {
            Dish foundDish = dishService.searchDishByName(newDish.getName());
            if (newDishHasDifferentId(newDish, foundDish)) {
                model.put(ERROR_MESSAGE, "There is dish with name \"" + newDish.getName() + "\" already in database");
                return true;
            }
            else
                return false;
        }
        catch (NoItemInDbException e) {
            return false;
        }
    }

    private boolean newDishHasDifferentId(Dish newDish, Dish foundDish) {
        return newDish.getId() != foundDish.getId();
    }

    private boolean checkForItemWithSameName(Map<String, Object> model, Dish dish, Ingredient ingredient, String newName) {
        try {
            List<Ingredient> ingredients = dishService.getIngredientsByDish(dish);
            if (ingredients.contains(ingredient)) {
                model.put(ERROR_MESSAGE, "There is ingredient with name \"" + newName +
                        "\" already in dish \"" + dish.getName() + "\"!");
                return true;
            }
            else
                return false;
        }
        catch (NoItemInDbException e) {
            return false;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String askForDeleteDishById(Map<String, Object> model, @RequestParam(value="id", required=true) Integer id) {
        try {
            Dish dish = dishService.searchDishById(id);
            model.put("dishToDelete", dish);
            return PATH_DELETE;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/deleteByName", method = RequestMethod.GET)
    public String askForDeleteDishByName(Map<String, Object> model, @RequestParam(value="name", required=true) String dishName) {
        try {
            Dish dish = dishService.searchDishByName(dishName);
            model.put("dishToDelete", dish);
            return PATH_DELETE;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/deleteConfirmed", method = RequestMethod.POST)
    public String deleteDish(Map<String, Object> model, @RequestParam(value="id", required=true) Integer id) {
        try {
            dishService.delete(id);
            model.put("dishes", dishService.getAllDishes());
            return PATH_DISHES;
        }
        catch (PersistenceException e) {
            model.put(ERROR_MESSAGE, "Dish with id=" + id + " can not be deleted due to using in another tables");
            model.put("additionalMessage", e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editDishById(Map<String, Object> model, @RequestParam(value="id", required=true) Integer id) {
        try {
            Dish dish = dishService.searchDishById(id);
            List<IngredientsInDish> items = dishService.getIngredientsInDish(dish);
            model.put("dishExisting", dish);
            model.put("dishValidate", new DishValidate());
            model.put("itemsInDish", items);
            return PATH_EDIT;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/{dishId}/edit", method = RequestMethod.GET)
    public String editDish(Map<String, Object> model, @PathVariable int dishId) {
        try {
            Dish dish = dishService.searchDishById(dishId);
            List<IngredientsInDish> items = dishService.getIngredientsInDish(dish);
            model.put("dishExisting", dish);
            model.put("dishValidate", new DishValidate());
            model.put("itemsInDish", items);
            return PATH_EDIT;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/editByName", method = RequestMethod.GET)
    public String editDishByName(Map<String, Object> model, @RequestParam(value="name", required=true) String name) {
        try {
            Dish dish = dishService.searchDishByName(name);
            List<IngredientsInDish> items = dishService.getIngredientsInDish(dish);
            model.put("dishExisting", dish);
            model.put("dishValidate", new DishValidate());
            model.put("itemsInDish", items);
            return PATH_EDIT;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditDish(Map<String, Object> model,
                               @RequestParam(value="id", required=true) int id,
                               @ModelAttribute("dishExisting") Dish dish,
                               @ModelAttribute("dishValidate") DishValidate dishValidate) {
        dish.setId(id);
        if (dishValidate.isValid(dish)) {
            if (checkForDishWithSameName(model, dish))
                return PATH_ERROR;
            dishService.update(dish);
            model.put("dishes", dishService.getAllDishes());
            return PATH_DISHES;
        }
        else {
            model.put("dishExisting", dish);
            model.put("dishValidate", new DishValidate());
            model.put("itemsInDish", dishService.getIngredientsInDish(dish));
            return PATH_EDIT;
        }
    }

    @RequestMapping(value = "{dishId}/addItem", method = RequestMethod.GET)
    public String getAllDishes(Map<String, Object> model, @PathVariable("dishId") int dishId) {
        model.put("dish", dishService.searchDishById(dishId));
        model.put("item", new IngredientsInDish());
        model.put("itemValidate", new ItemInDishValidate());
        return PATH_ADD_ITEM;
    }

    @RequestMapping(value = "{dishId}/addItem", method = RequestMethod.POST)
    public String addItemToDish(Map<String, Object> model, @PathVariable("dishId") int dishId,
                                @ModelAttribute("item") IngredientsInDish item,
                                @ModelAttribute("itemValidate") ItemInDishValidate itemValidate) {
        Dish dish = dishService.searchDishById(dishId);
        if (itemValidate.isValid(item)) {
            try {
                String newName = item.getIngredient().getName();
                Ingredient ingredient = storageService.searchByName(newName);
                IngredientsInDish newItem = new IngredientsInDish(dish, ingredient, item.getIngredientWeight());
                if (checkForItemWithSameName(model, dish, ingredient, newName))
                    return PATH_ERROR;
                dishService.addItemToDish(newItem);
            } catch (NoItemInDbException e) {
                model.put(ERROR_MESSAGE, e.getMessage());
                return PATH_ERROR;
            }
            model.put("dishExisting", dish);
            model.put("dishValidate", new DishValidate());
            model.put("itemsInDish", dishService.getIngredientsInDish(dish));
            return PATH_EDIT;
        }
        else {
            model.put("dish", dish);
            model.put("item", item);
            model.put("itemValidate", itemValidate);
            return PATH_ADD_ITEM;
        }
    }

    @RequestMapping(value = "/{dishId}/editItem", method = RequestMethod.GET)
    public String editItemInDish(Map<String, Object> model, @PathVariable int dishId, @RequestParam(value="id", required=true) Integer id) {
        try {
            IngredientsInDish item = dishService.searchItemInDish(id);
            Dish dish = dishService.searchDishById(dishId);
            model.put("dish", dish);
            model.put("itemValidate", new ItemInDishValidate());
            model.put("item", item);
            return PATH_EDIT_ITEM;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/{dishId}/editItem", method = RequestMethod.POST)
    public String saveEditItemInDish(Map<String, Object> model,  @PathVariable int dishId, @RequestParam(value="id", required=true) int id,
                                     @ModelAttribute("item") IngredientsInDish item,
                                     @ModelAttribute("itemValidate") ItemInDishValidate itemValidate) {
        Dish dish = dishService.searchDishById(dishId);
        IngredientsInDish itemInDb = dishService.searchItemInDish(id);
        itemInDb.setIngredientWeight(item.getIngredientWeight());
        if (itemValidate.isValid(itemInDb)) {
            dishService.updateItemInDish(itemInDb);
            model.put("dishExisting", dish);
            model.put("dishValidate", new DishValidate());
            model.put("itemsInDish", dishService.getIngredientsInDish(dish));
            return PATH_EDIT;
        }
        else {
            model.put("dish", dish);
            model.put("itemValidate", itemValidate);
            model.put("item", item);
            return PATH_EDIT_ITEM;
        }
    }

    @RequestMapping(value = "/{dishId}/deleteItem", method = RequestMethod.GET)
    public String askForDeleteItemInDish(Map<String, Object> model, @PathVariable int dishId,
                                         @RequestParam(value="id", required=true) int id) {
        try {
            Dish dish = dishService.searchDishById(dishId);
            IngredientsInDish item = dishService.searchItemInDish(id);
            model.put("dish", dish);
            model.put("item", item);
            return PATH_DELETE_ITEM;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }
    @RequestMapping(value = "/{dishId}/deleteItemConfirmed", method = RequestMethod.POST)
    public String deleteItemFromDish(Map<String, Object> model, @PathVariable int dishId, @RequestParam(value="id", required=true) int id) {
        try {
            Dish dish = dishService.searchDishById(dishId);
            IngredientsInDish item = dishService.searchItemInDish(id);
            dishService.deleteItemFromDish(dish, item);
            model.put("dishExisting", dish);
            model.put("dishValidate", new DishValidate());
            model.put("itemsInDish", dishService.getIngredientsInDish(dish));
            return PATH_EDIT;
        }
        catch (PersistenceException e) {
            model.put(ERROR_MESSAGE, "Ingredient with id=" + id + " can not be deleted");
            model.put("additionalMessage", e.getMessage());
            return PATH_ERROR;
        }
    }

}