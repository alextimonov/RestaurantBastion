package ua.goit.timonov.enterprise.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.goit.timonov.enterprise.controllers.DbController;

import java.util.Date;
import java.util.Map;

/**
 * Main Spring MVC controller for mapping pages main, service, scheme & contacts
 */
@Controller
public class MainController {
    public static final String PATH_SERVICE = "service/service";
    public static final String PATH_MAIN = "main";
    public static final String PATH_SCHEME = "scheme";
    public static final String PATH_CONTACTS = "contacts";

    private DbController dbController;

    public void setDbController(DbController dbController) {
        this.dbController = dbController;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getMainPage() {
//        dbController.restoreDatabase();
        return PATH_MAIN;
    }

    @RequestMapping(value = "/service/service", method = RequestMethod.GET)
    public String service(Map<String, Object> model) {
        model.put("currentTime", new Date().toString());
        return PATH_SERVICE;
    }

    @RequestMapping(value = "/scheme", method = RequestMethod.GET)
    public String invokeSchemePage(Map<String, Object> model) {
        Object file = "";
        model.put("scheme", file);
        return PATH_SCHEME;
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String getPageContacts() {
        return PATH_CONTACTS;
    }
}
