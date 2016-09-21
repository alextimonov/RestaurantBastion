package ua.goit.timonov.enterprise.module_9.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.timonov.enterprise.module_9.service.EmployeeService;

import java.util.Map;

/**
 * Controller for mapping requests to Dish pages
 */
@Controller
public class EmployeeWebController {

    public static final String EMPLOYEES_PAGE = "employees";
    public static final String WAITERS_PAGE = "waiters";
    public static final String EMPLOYEE_PAGE = "employee";
    private EmployeeService employeeService;

    @Autowired
    public EmployeeWebController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String employees(Map<String, Object> model) {
        model.put("employees", employeeService.getAllEmployees());
        return EMPLOYEES_PAGE;
    }

    @RequestMapping(value = "/employee/{employeeName}", method = RequestMethod.GET)
    public ModelAndView employees(@PathVariable("employeeName") String employeeName) {
        ModelAndView modelAndView = new ModelAndView(EMPLOYEE_PAGE);
        modelAndView.addObject("employee", employeeService.getEmployeeByName(employeeName));
        return modelAndView;
    }

    @RequestMapping(value = "/waiters", method = RequestMethod.GET)
    public String waiters(Map<String, Object> model) {
        model.put("waiters", employeeService.getWaiters());
        return WAITERS_PAGE;
    }
}
