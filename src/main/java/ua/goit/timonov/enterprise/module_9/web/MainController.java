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

    public static final String INDEX_PAGE = "index";
    public static final String MAIN_PAGE = "main";
    public static final String CONTACTS_PAGE = "contacts";

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        model.put("currentTime", new Date().toString());
        return INDEX_PAGE;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getMainPage() {
        return MAIN_PAGE;
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String getPageContacts() {
        return CONTACTS_PAGE;
    }

}
