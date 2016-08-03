package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.model.EmployeeDAO;

import java.util.ArrayList;
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
    public List<String> getAll() {
        List<Employee> staff = employeeDAO.getAll();
        List<String> staffLines = new ArrayList<>();
        for (Employee employee : staff) {
            staffLines.add(employee.toString());
        }
        return staffLines;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Employee employee) {
        employeeDAO.add(employee);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int id) {
        employeeDAO.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String surname, String name) {
        employeeDAO.delete(surname, name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Employee search(String surname, String name) {
        return employeeDAO.search(surname, name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Employee search(int id) {
        return employeeDAO.search(id);
    }
}
