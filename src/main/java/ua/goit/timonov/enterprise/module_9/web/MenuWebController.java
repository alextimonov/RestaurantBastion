package ua.goit.timonov.enterprise.module_9.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;
import ua.goit.timonov.enterprise.module_9.service.MenuService;
import ua.goit.timonov.enterprise.module_9.view.JsonMenuViews;

import java.io.IOException;
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
    private ObjectMapper objectMapper = new ObjectMapper();

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
    public ModelAndView restMenus() throws IOException {
        List<Menu> menus = menuService.getAllMenus();
        ModelAndView modelAndView = new ModelAndView("restMenus");
        String jsonMenus = objectMapper.writerWithView(JsonMenuViews.OnlyNames.class).writeValueAsString(menus);
        Object json = objectMapper.readValue(jsonMenus, Object.class);
        modelAndView.addObject("menus", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
        return modelAndView;
    }

    @RequestMapping(value = "/restMenus/{menuName}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView restMenu(@PathVariable String menuName) throws IOException {
        ModelAndView modelAndView = new ModelAndView("restMenus");
        String jsonMenu;
        try {
            int menuId = Integer.valueOf(menuName);
            jsonMenu = getMenuFromDatabase(menuId);
        }
        catch(NumberFormatException e) {
            jsonMenu = getMenuFromDatabase(menuName);
        }
        Object json = objectMapper.readValue(jsonMenu, Object.class);
        modelAndView.addObject("menus", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
        return modelAndView;
    }

    private String getMenuFromDatabase(int menuId) {
        try {
            Menu menu = menuService.searchMenuById(menuId);
            return new ObjectMapper().writerWithView(JsonMenuViews.NamesWithDishes.class).writeValueAsString(menu);
        }
        catch (RuntimeException | JsonProcessingException e) {
            return "{\"Error\":\"There's no menu with id = " + menuId + " in database!\"}";
        }
    }

    private String getMenuFromDatabase(String menuName) {
        try {
            Menu menu = menuService.getMenuByName(menuName);
            return new ObjectMapper().writerWithView(JsonMenuViews.NamesWithDishes.class).writeValueAsString(menu);
        }
        catch (RuntimeException | JsonProcessingException e) {
            return "{\"Error\":\"There's no menu with id = " + menuName + " in database!\"}";
        }
    }
}
