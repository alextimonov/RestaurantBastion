package ua.goit.timonov.enterprise.module_9.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;
import ua.goit.timonov.enterprise.module_9.service.MenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controller for mapping requests to Menu pages
 */

@Controller
public class MenuWebController {

    public static final String MENUS_PAGE = "menus";
    public static final String SCHEME_PAGE = "scheme";
    public static final String MENU_PAGE = "menu";
    private MenuService menuService;

    @Autowired
    public MenuWebController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public String menus(Map<String, Object> model) {
        model.put("menus", menuService.getAllMenus());
        return MENUS_PAGE;
    }

    @RequestMapping(value = "/menu/{menuName}", method = RequestMethod.GET)
    public ModelAndView menu(@PathVariable("menuName") String menuName) {
        ModelAndView modelAndView = new ModelAndView(MENU_PAGE);
        modelAndView.addObject("menu", menuService.getMenuByName(menuName));
        return modelAndView;
    }

    @RequestMapping(value = "/scheme", method = RequestMethod.GET)
    public String invokeSchemePage(Map<String, Object> model) {
        Object file = "";
        model.put("scheme", file);
        return SCHEME_PAGE;
    }

    @RequestMapping(value = "/restMenus", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> restMenus() {
        List<Menu> menusFromDb = menuService.getAllMenus();
        // TODO search another solution
        List<Menu> restMenus = new ArrayList<>();
        for (Menu menuFromDb : menusFromDb) {
            Menu menu = new Menu();
            menu.setId(menuFromDb.getId());
            menu.setName(menuFromDb.getName());
            restMenus.add(menu);
        }
        return restMenus;
    }

    @RequestMapping(value = "/restMenus/{menuName}", method = RequestMethod.GET)
    @ResponseBody
    public Menu getMenuByName(@PathVariable String menuName) {
        try {
            int menuId = Integer.valueOf(menuName);
            Menu menu = menuService.getMenuById(menuId);
            return menu;
        }
        catch(NumberFormatException e) {
            Menu menu = menuService.getMenuByName(menuName);
            return menu;
        }
    }
}
