package ua.goit.timonov.enterprise.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.timonov.enterprise.service.MenuService;

import java.util.Map;

/**
 * Controller for mapping requests to Menu pages
 */

@Controller
public class MenuWebController {

    public static final String MENUS = "menus";
    public static final String MENU = "menu";
    public static final String PATH_MENUS = "/menus";
    public static final String MENU_NAME = "menuName";
    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = PATH_MENUS, method = RequestMethod.GET)
    public String getAllMenus(Map<String, Object> model) {
        model.put(MENUS, menuService.getAllMenus());
        return MENUS;
    }

    @RequestMapping(value = "/menu/{menuName}", method = RequestMethod.GET)
    public ModelAndView getMenu(@PathVariable(MENU_NAME) String menuName) {
        ModelAndView modelAndView = new ModelAndView(MENU);
        modelAndView.addObject(MENU, menuService.getMenuByName(menuName));
        return modelAndView;
    }
}
