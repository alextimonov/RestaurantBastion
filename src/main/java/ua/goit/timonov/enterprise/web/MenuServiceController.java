package ua.goit.timonov.enterprise.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.goit.timonov.enterprise.exceptions.ForbidToAddException;
import ua.goit.timonov.enterprise.exceptions.ForbidToDeleteException;
import ua.goit.timonov.enterprise.exceptions.NoItemInDbException;
import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.Menu;
import ua.goit.timonov.enterprise.service.DishService;
import ua.goit.timonov.enterprise.service.MenuService;
import ua.goit.timonov.enterprise.web.validate.MenuValidate;

import javax.persistence.PersistenceException;
import java.util.Map;

/**
 * Spring MVC controller for mapping menu's service pages
 */
@Controller
@RequestMapping("/service/menu")
public class MenuServiceController {

    public static final String PATH_MENUS = "service/menu/menus";
    public static final String PATH_ADD = "service/menu/add";
    public static final String PATH_EDIT = "service/menu/edit";
    public static final String PATH_DELETE = "service/menu/delete";
    public static final String PATH_ERROR = "service/errorMessage";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String MENU_ITEM = "menuAttribute";
    public static final String MENU_VALIDATE = "menuValidate";

    private MenuService menuService;
    private DishService dishService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public String serviceMenus(Map<String, Object> model) {
        model.put("menus", menuService.getAllMenus());
        return PATH_MENUS;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getMenuToAdd(Map<String, Object> model) {
        model.put(MENU_ITEM, new Menu());
        model.put(MENU_VALIDATE, new MenuValidate());
        return PATH_ADD;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addMenu(Map<String, Object> model, @ModelAttribute("menuAttribute") Menu menu,
                          @ModelAttribute("menuValidate") MenuValidate menuValidate) {
        if (menuValidate.isValid(menu)) {
            if (checkForMenuWithSameName(model, menu))
                return PATH_ERROR;
            menuService.add(menu);
            model.put("menus", menuService.getAllMenus());
            return PATH_MENUS;
        }
        else {
            model.put(MENU_ITEM, menu);
            model.put(MENU_VALIDATE, menuValidate);
            return PATH_ADD;
        }
    }

    private boolean checkForMenuWithSameName(Map<String, Object> model, Menu newMenu) {
        try {
            Menu foundMenu = menuService.searchMenuByName(newMenu.getName());
            if (newMenuHasDifferentId(newMenu, foundMenu)) {
                model.put(ERROR_MESSAGE, "There is menu with name \"" + newMenu.getName() + "\" already in database");
                return true;
            }
            else
                return false;
        }
        catch (NoItemInDbException e) {
            return false;
        }
    }

    private boolean newMenuHasDifferentId(Menu newMenu, Menu foundMenu) {
        return newMenu.getId() != foundMenu.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String askForDeleteMenuById(Map<String, Object> model, @RequestParam(value="id", required=true) int id) {
        try {
            Menu menu = menuService.searchMenuById(id);
            model.put("menuToDelete", menu);
            return PATH_DELETE;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/deleteByName", method = RequestMethod.GET)
    public String askForDeleteMenuByName(Map<String, Object> model, @RequestParam(value="name", required=true) String name) {
        try {
            Menu menu = menuService.searchMenuByName(name);
            model.put("menuToDelete", menu);
            return PATH_DELETE;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/deleteConfirmed", method = RequestMethod.POST)
    public String deleteMenu(Map<String, Object> model, @RequestParam(value="id", required=true) Integer id) {
        try {
            menuService.delete(id);
            model.put("menus", menuService.getAllMenus());
            return PATH_MENUS;
        }
        catch (PersistenceException e) {
            model.put(ERROR_MESSAGE, "Menu with id=" + id + " can not be deleted ~~due to using in another tables");
            model.put("additionalMessage", e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editMenuById(Map<String, Object> model, @RequestParam(value="id", required=true) Integer id) {
        try {
            Menu menu = menuService.searchMenuById(id);
            model.put(MENU_ITEM, menu);
            model.put(MENU_VALIDATE, new MenuValidate());
            return PATH_EDIT;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/editByName", method = RequestMethod.GET)
    public String editMenuByName(Map<String, Object> model, @RequestParam(value="name", required=true) String name) {
        try {
            Menu menu = menuService.searchMenuByName(name);
            model.put(MENU_ITEM, menu);
            model.put(MENU_VALIDATE, new MenuValidate());
            return PATH_EDIT;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditMenu(Map<String, Object> model, @RequestParam(value="id", required=true) int id,
                               @ModelAttribute("menuAttribute") Menu menu, @ModelAttribute("menuValidate") MenuValidate menuValidate) {
        menu.setId(id);
        if (menuValidate.isValid(menu)) {
            if (checkForMenuWithSameName(model, menu))
                return PATH_ERROR;
            menuService.update(menu);
            model.put("menus", menuService.getAllMenus());
            return PATH_MENUS;
        }
        else {
            model.put(MENU_ITEM, menu);
            model.put(MENU_VALIDATE, menuValidate);
            return PATH_EDIT;
        }
    }

    @RequestMapping(value = "/{menuId}/addDish", method = RequestMethod.GET)
    public String addDishToMenu(Map<String, Object> model, @PathVariable Integer menuId,
                                @RequestParam(value = "id", required = true) Integer id) {
        try {
            Menu menu = menuService.searchMenuById(menuId);
            Dish dishFound = dishService.searchDishById(id);
            menuService.addDish(menu, dishFound);
            model.put(MENU_ITEM, menu);
            return PATH_EDIT;
        }
        catch (NoItemInDbException | ForbidToAddException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/{menuId}/addDishByName", method = RequestMethod.GET)
    public String addDishToMenuByName(Map<String, Object> model, @PathVariable Integer menuId,
                                @RequestParam(value = "name", required = true) String name) {
        try {
            Menu menu = menuService.searchMenuById(menuId);
            Dish dish = dishService.searchDishByName(name);
            menuService.addDish(menu, dish);
            model.put(MENU_ITEM, menu);
            return PATH_EDIT;
        }
        catch (NoItemInDbException | ForbidToAddException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/{menuId}/deleteDish", method = RequestMethod.GET)
    public String deleteDishFromMenu(Map<String, Object> model, @PathVariable Integer menuId,
                                @RequestParam(value = "id", required = true) Integer id) {
        try {
            Menu menu = menuService.searchMenuById(menuId);
            Dish dish = dishService.searchDishById(id);
            menuService.deleteDish(menu, dish);
            model.put(MENU_ITEM, menu);
            return PATH_EDIT;
        }
        catch (NoItemInDbException | ForbidToDeleteException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/{menuId}/deleteDishByName", method = RequestMethod.GET)
    public String deleteDishByNameFromMenu(Map<String, Object> model, @PathVariable Integer menuId,
                                      @RequestParam(value = "name", required = true) String name) {
        try {
            Menu menu = menuService.searchMenuById(menuId);
            Dish dish = dishService.searchDishByName(name);
            menuService.deleteDish(menu, dish);
            model.put(MENU_ITEM, menu);
            return PATH_EDIT;
        }
        catch (NoItemInDbException | ForbidToDeleteException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }
}
