package ua.goit.timonov.enterprise.module_9.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;
import ua.goit.timonov.enterprise.module_9.service.DishService;
import ua.goit.timonov.enterprise.module_9.service.MenuService;

import java.util.Map;

/**
 * Created by Alex on 14.09.2016.
 */
@Controller
public class MenuServiceController {

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

    @RequestMapping(value = "/service/menus", method = RequestMethod.GET)
    public String serviceMenus(Map<String, Object> model) {
        model.put("menus", menuService.getAllMenus());
        return "service/menus";
    }

    @RequestMapping(value = "/menu/add", method = RequestMethod.GET)
    public String getMenuToAdd(Map<String, Object> model) {
        model.put("menuAttribute", new Menu());
        return "menu/add";
    }

    @RequestMapping(value = "/menu/add", method = RequestMethod.POST)
    public String addDish(Map<String, Object> model, @ModelAttribute("menuAttribute") Menu menu) {
        menuService.add(menu);
        model.put("menus", menuService.getAllMenus());
        return "service/menus";
    }

    @RequestMapping(value = "/menu/deleteById", method = RequestMethod.GET)
    public String deleteDishById(Map<String, Object> model, @RequestParam(value="id", required=true) Integer menuId) {
        menuService.delete(menuId);
        model.put("menus", menuService.getAllMenus());
        return "service/menus";
    }

    @RequestMapping(value = "/menu/deleteByName", method = RequestMethod.GET)
    public String deleteDishByName(Map<String, Object> model, @RequestParam(value="name", required=true) String menuName) {
        menuService.delete(menuName);
        model.put("menus", menuService.getAllMenus());
        return "service/menus";
    }

    @RequestMapping(value = "/menu/editById", method = RequestMethod.GET)
    public String editDishById(Map<String, Object> model, @RequestParam(value="id", required=true) Integer id,
                               @ModelAttribute("dishAttribute") Dish dish) {
        Menu menu = menuService.searchMenuById(id);
        model.put("menuExisting", menu);
        return "menu/edit";
    }

    @RequestMapping(value = "/menu/editByName", method = RequestMethod.GET)
    public String editDishByName(Map<String, Object> model, @RequestParam(value="name", required=true) String name,
                                 @ModelAttribute("dishAttribute") Dish dish) {
        Menu menu = menuService.searchMenuByName(name);
        model.put("menuExisting", menu);
        return "menu/edit";
    }

    @RequestMapping(value = "/menu/edit", method = RequestMethod.POST)
    public String saveEditDish(Map<String, Object> model, @ModelAttribute("menuAttribute") Menu menu,
                               @RequestParam(value="id", required=true) Integer id) {
        menuService.update(menu);
        model.put("menus", menuService.getAllMenus());
        return "service/menus";
    }

    @RequestMapping(value = "/menu/addDish", method = RequestMethod.POST)
    public String addDishToMenu(Map<String, Object> model, @ModelAttribute("menuAttribute") Menu menu,
                                @ModelAttribute("dishAttribute") Dish dish, @RequestParam(value="id", required=true) Integer id) {
        Dish dishFound = dishService.searchDishById(id);
        menuService.addDish(menu, dishFound);
//        menu = menuService.searchMenuById(menu.getId());
        model.put("menuExisting", menu);
        return "menu/edit";
    }

    @RequestMapping(value = "/menu/addDishByName", method = RequestMethod.POST)
    public String addDishToMenuByName(Map<String, Object> model, @ModelAttribute("menuAttribute") Menu menu,
                                @RequestParam(value="name", required=true) String name) {
        Dish dish = dishService.searchDishByName(name);
        menuService.addDish(menu, dish);
//        menu = menuService.searchMenuById(menu.getId());
        model.put("menuExisting", menu);
        return "menu/edit";
    }

}
