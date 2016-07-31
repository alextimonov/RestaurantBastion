package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.model.EmployeeDAO;

import java.util.List;

/**
 * Created by Alex on 31.07.2016.
 */
public class EmployeeController {

    private EmployeeDAO employeeDAO;

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Employee employee) {
        employeeDAO.add(employee);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Employee employee) {
        employeeDAO.delete(employee);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Employee find(String surname, String name) {
        return employeeDAO.find(surname, name);
    }
}
