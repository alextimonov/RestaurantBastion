package ua.goit.timonov.enterprise.module_9.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Map;

/**
 * Main Spring MVC controller for mapping pages main, index, contacts
 */

@Controller
public class MainController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        model.put("currentTime", new Date().toString());
        return "index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getMainPage() {
        return "main";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String getPageContacts() {
        return "contacts";
    }

}
