package ua.goit.timonov.enterprise.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.goit.timonov.enterprise.exceptions.NoItemInDbException;
import ua.goit.timonov.enterprise.model.Employee;
import ua.goit.timonov.enterprise.service.EmployeeService;
import ua.goit.timonov.enterprise.web.validate.EmployeeValidate;

import java.util.Map;

/**
 * Spring MVC controller for mapping service pages for employees
 */
@ControllerAdvice
@Controller
@RequestMapping("/service/employee")
@SessionAttributes("employeeView")
public class EmployeeServiceController {

    public static final String PATH_EMPLOYEES = "service/employee/employees";
    public static final String PATH_ADD = "service/employee/add";
    public static final String PATH_EDIT = "service/employee/edit";
    public static final String PATH_ERROR = "service/errorMessage";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String EMPLOYEES = "employees";
    public static final String EMPLOYEE_VALIDATE = "employeeValidate";
    public static final String EMPLOYEE_VIEW = "employeeView";
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String serviceEmployees(Map<String, Object> model) {
        model.put(EMPLOYEES, employeeService.getAllEmployees());
        return PATH_EMPLOYEES;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getEmployeeToAdd(Map<String, Object> model) {
        model.put(EMPLOYEE_VALIDATE, new EmployeeValidate());
        model.put(EMPLOYEE_VIEW, new EmployeeView());
        return PATH_ADD;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addEmployee(Map<String, Object> model, @ModelAttribute("employeeView") EmployeeView employeeView,
                              @ModelAttribute("employeeValidate") EmployeeValidate employeeValidate) {
        if (employeeValidate.isValid(employeeView)) {
            Employee employee = employeeView.getEmployeeFromView();
            employeeService.add(employee);
            model.put("employees", employeeService.getAllEmployees());
            return PATH_EMPLOYEES;
        }
        else {
            model.put("employeeValidate", employeeValidate);
            model.put("employeeView", employeeView);
            return PATH_ADD;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editEmployeeById(Map<String, Object> model, @RequestParam(value="id", required=true) Integer id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            model.put("employeeView", new EmployeeView(employee));
            model.put("employeeValidate", new EmployeeValidate());
            return PATH_EDIT;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/editByName", method = RequestMethod.GET)
    public String editEmployeeByName(Map<String, Object> model, @RequestParam(value="name", required=true) String name,
                                     @RequestParam(value="surname", required=true) String surname) {
        try {
            Employee employee = employeeService.getEmployeeByName(name, surname);
            model.put("employeeView", new EmployeeView(employee));
            model.put("employeeValidate", new EmployeeValidate());
            return PATH_EDIT;
        }
        catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditDish(Map<String, Object> model, @RequestParam(value="id", required=true) Integer id,
                               @ModelAttribute("employeeView") EmployeeView employeeView,
                               @ModelAttribute("employeeValidate") EmployeeValidate employeeValidate) {
        if (employeeValidate.isValid(employeeView)) {
            Employee employee = employeeView.getEmployeeFromView();
            employee.setId(id);
            employeeService.update(employee);
            model.put("employees", employeeService.getAllEmployees());
            return PATH_EMPLOYEES;
        }
        else {
            model.put("employeeView", employeeView);
            model.put("employeeValidate", employeeValidate);
            return PATH_EDIT;
        }
    }
}