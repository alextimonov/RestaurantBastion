package ua.goit.timonov.enterprise.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.goit.timonov.enterprise.exceptions.NoItemInDbException;
import ua.goit.timonov.enterprise.model.Ingredient;
import ua.goit.timonov.enterprise.service.StorageService;
import ua.goit.timonov.enterprise.web.validate.IngredientValidate;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Map;

/**
 * Spring MVC controller for mapping ingredient's service pages
 */

@Controller
@RequestMapping("/service/storage")
public class StorageServiceController {

    public static final String PATH_INGREDIENTS = "service/storage/ingredients";
    public static final String PATH_ADD = "service/storage/add";
    public static final String ITEMS = "ingredients";
    public static final String ONE_ITEM = "ingredient";
    public static final String PATH_EDIT = "service/storage/edit";
    public static final String PATH_DELETE = "service/storage/delete";
    public static final String PATH_ERROR = "service/errorMessage";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String ITEM_VALIDATE = "itemValidate";

    private StorageService storageService;

    @Autowired
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    public String getAllIngredients(Map<String, Object> model) {
        model.put(ITEMS, storageService.getAllIngredients());
        return PATH_INGREDIENTS;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getIngredientToAdd(Map<String, Object> model) {
        model.put(ONE_ITEM, new Ingredient());
        model.put(ITEM_VALIDATE, new IngredientValidate());
        return PATH_ADD;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addIngredient(Map<String, Object> model, @ModelAttribute(ONE_ITEM) Ingredient item,
                                @ModelAttribute("itemValidate") IngredientValidate itemValidate) {
        if (itemValidate.isValid(item)) {
            if (checkForItemWithSameName(model, item))
                return PATH_ERROR;
            storageService.add(item);
            model.put(ITEMS, storageService.getAllIngredients());
            return PATH_INGREDIENTS;
        }
        else {
            model.put(ITEM_VALIDATE, itemValidate);
            model.put(ONE_ITEM, item);
            return PATH_ADD;
        }
    }

    private boolean checkForItemWithSameName(Map<String, Object> model, Ingredient newItem) {
        try {
            Ingredient foundItem = storageService.searchByName(newItem.getName());
            if (newItemHasDifferentId(newItem, foundItem)) {
                model.put(ERROR_MESSAGE, "There is ingredient with name \"" + newItem.getName() + "\" already in database");
                return true;
            }
            else
                return false;
        }
        catch (NoItemInDbException e) {
            return false;
        }
    }

    private boolean newItemHasDifferentId(Ingredient newItem, Ingredient foundItem) {
        return newItem.getId() != foundItem.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String askForDeleteIngredientById(Map<String, Object> model, @RequestParam(value="id", required=true) Integer id) {
        try {
            Ingredient item = storageService.searchById(id);
            model.put(ONE_ITEM, item);
            return PATH_DELETE;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/deleteByName", method = RequestMethod.GET)
    public String askForDeleteIngredientByName(Map<String, Object> model, @RequestParam(value="name", required=true) String name) {
        try {
            Ingredient item = storageService.searchByName(name);
            model.put(ONE_ITEM, item);
            return PATH_DELETE;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/deleteConfirmed", method = RequestMethod.POST)
    public String deleteIngredientById(Map<String, Object> model, @RequestParam(value="id", required=true) Integer itemId) {
        try {
            storageService.delete(itemId);
            model.put(ITEMS, storageService.getAllIngredients());
            return PATH_INGREDIENTS;
        }
        catch (PersistenceException e) {
            model.put(ERROR_MESSAGE, "Ingredient with id=" + itemId + " can not be deleted due to using in another tables");
            model.put("additionalMessage", e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editIngredientById(Map<String, Object> model, @RequestParam(value="id", required=true) Integer id) {
        try {
            Ingredient item = storageService.searchById(id);
            model.put(ONE_ITEM, item);
            model.put(ITEM_VALIDATE, new IngredientValidate());
            return PATH_EDIT;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/editByName", method = RequestMethod.GET)
    public String editDishByName(Map<String, Object> model, @RequestParam(value="name", required=true) String itemName) {
        try {
            Ingredient item = storageService.searchByName(itemName);
            model.put(ONE_ITEM, item);
            model.put(ITEM_VALIDATE, new IngredientValidate());
            return PATH_EDIT;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditIngredient(Map<String, Object> model, @RequestParam(value="id", required=true) Integer itemId,
                                     @ModelAttribute(ONE_ITEM) Ingredient item,
                                     @ModelAttribute(ITEM_VALIDATE) IngredientValidate itemValidate) {
        item.setId(itemId);
        if (itemValidate.isValid(item)) {
            if (checkForItemWithSameName(model, item))
                return PATH_ERROR;
            storageService.update(item);
            model.put(ITEMS, storageService.getAllIngredients());
            return PATH_INGREDIENTS;
        }
        else {
            model.put(ONE_ITEM, item);
            model.put(ITEM_VALIDATE, itemValidate);
            return PATH_EDIT;
        }
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String filterByName(Map<String, Object> model, @RequestParam(value="name", required=true) String startChars) {
        List<Ingredient> items = storageService.filterWithStartChars(startChars);
        model.put(ITEMS, items);
        return PATH_INGREDIENTS;
    }
}
