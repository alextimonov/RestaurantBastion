package ua.goit.timonov.enterprise.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for mapping security pages
 */
@Controller
public class SigninController {
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signin() {
        return "security/signin";
    }

    @RequestMapping(value = "/signin-failure", method = RequestMethod.GET)
    public String signinFailure() {
        return "security/signin_failure";
    }

}
