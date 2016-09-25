package ua.goit.timonov.enterprise.module_9.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.goit.timonov.enterprise.module_9.service.EmployeeService;

import java.util.Map;

/**
 * Controller for mapping requests to Dish pages
 */
@Controller
public class EmployeeWebController {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String employees(Map<String, Object> model) {
        model.put("employees", employeeService.getAllEmployees());
        return "employees";
    }

    @RequestMapping(value = "/waiters", method = RequestMethod.GET)
    public String waiters(Map<String, Object> model) {
        model.put("waiters", employeeService.getWaiters());
        return "waiters";
    }
}
