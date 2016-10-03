package ua.goit.timonov.enterprise.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Map;

/**
 * Main Spring MVC controller for mapping pages main, service, scheme & contacts
 */
@Controller
public class MainController {
    public static final String MAIN = "main";
    public static final String SERVICE = "service/service";
    public static final String SCHEME = "scheme";
    public static final String CONTACTS = "contacts";
    public static final String SLASH_MAIN = "/main";
    public static final String SLASH_SERVICE = "/service/service";
    public static final String SLASH_SCHEME = "/scheme";
    public static final String SLASH_CONTACTS = "/contacts";
    public static final String CURRENT_TIME = "currentTime";

    @RequestMapping(value = SLASH_MAIN, method = RequestMethod.GET)
    public String getMainPage() {
        return MAIN;
    }

    @RequestMapping(value = SLASH_SERVICE, method = RequestMethod.GET)
    public String service(Map<String, Object> model) {
        model.put(CURRENT_TIME, new Date().toString());
        return SERVICE;
    }

    @RequestMapping(value = SLASH_SCHEME, method = RequestMethod.GET)
    public String invokeSchemePage(Map<String, Object> model) {
        return SCHEME;
    }

    @RequestMapping(value = SLASH_CONTACTS, method = RequestMethod.GET)
    public String getPageContacts() {
        return CONTACTS;
    }
}