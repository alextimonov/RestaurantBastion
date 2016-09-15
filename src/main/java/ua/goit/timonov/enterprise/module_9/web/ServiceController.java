package ua.goit.timonov.enterprise.module_9.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.goit.timonov.enterprise.module_9.service.MenuService;

import java.util.Date;
import java.util.Map;

/**
 * Created by Alex on 14.09.2016.
 */
@Controller
public class ServiceController {

    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = "/service", method = RequestMethod.GET)
    public String service(Map<String, Object> model) {
        model.put("currentTime", new Date().toString());
        return "service";
    }

    @RequestMapping(value = "/service/employees", method = RequestMethod.GET)
    public String serviceEmployees(Map<String, Object> model) {
        return "service/employees";
    }

}
