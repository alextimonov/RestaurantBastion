package ua.goit.timonov.enterprise.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.goit.timonov.enterprise.service.EmployeeService;

import java.util.Map;

/**
 * Spring MVC controller for mapping common pages for Employee pages
 */
@Controller
public class EmployeeWebController {

    public static final String EMPLOYEES = "employees";
    public static final String WAITERS = "waiters";
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String getEmployees(Map<String, Object> model) {
        model.put(EMPLOYEES, employeeService.getAllEmployees());
        return EMPLOYEES;
    }

    @RequestMapping(value = "/waiters", method = RequestMethod.GET)
    public String getWaiters(Map<String, Object> model) {
        model.put(WAITERS, employeeService.getWaiters());
        return WAITERS;
    }
}
