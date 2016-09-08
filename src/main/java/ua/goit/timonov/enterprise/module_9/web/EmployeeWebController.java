package ua.goit.timonov.enterprise.module_9.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_9.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
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
    public String getEmployees(Map<String, Object> model) {
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

    @RequestMapping(value = "/restEmployees", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> restEmployees() {
        List<Employee> employeesFromDB = employeeService.getAllEmployees();
        // TODO search another solution
        List<Employee> resultEmployees = new ArrayList<>();
        for (Employee employeeFromDB : employeesFromDB) {
            Employee employee = getEmployeeWithoutRecursion(employeeFromDB);
            resultEmployees.add(employee);
        }
        return resultEmployees;
    }

    @RequestMapping(value = "/restEmployees/{employeeName}", method = RequestMethod.GET)
    @ResponseBody
    public Employee getEmployeeByName(@PathVariable String employeeName) {
        try {
            int menuId = Integer.valueOf(employeeName);
            Employee employeeFromDB = employeeService.searchById(menuId);
            // TODO search another solution
            return getEmployeeWithoutRecursion(employeeFromDB);
        }
        catch(NumberFormatException e) {
            return searchEmployeeByNameOrSurname(employeeName);
        }
    }

    private Employee searchEmployeeByNameOrSurname(String employeeName) {
        try {
            Employee employeeFromDB = employeeService.searchByName(employeeName);
            return getEmployeeWithoutRecursion(employeeFromDB);
        }
        catch (IndexOutOfBoundsException e) {
            Employee employeeFromDB = employeeService.searchByName("", employeeName);
            return getEmployeeWithoutRecursion(employeeFromDB);
        }
    }

    private Employee getEmployeeWithoutRecursion(Employee employeeFromDB) {
        Employee employee = new Employee();
        employee.setId(employeeFromDB.getId());
        employee.setName(employeeFromDB.getName());
        employee.setSurname(employeeFromDB.getSurname());
        employee.setSalary(employeeFromDB.getSalary());
        employee.setJob(employeeFromDB.getJob());
        employee.setBirthday(employeeFromDB.getBirthday());
        return employee;
    }

    @RequestMapping(value = "/restEmployees/{employeeName}/{employeeSurname}", method = RequestMethod.GET)
    @ResponseBody
    public Employee getEmployeeByFullName(@PathVariable String employeeName, @PathVariable String employeeSurname) {
            Employee employeeFromDB = employeeService.searchByName(employeeName, employeeSurname);
            return getEmployeeWithoutRecursion(employeeFromDB);
    }
}
