package ua.goit.timonov.enterprise.module_9.service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.EmployeeDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.model.Waiter;

import java.util.List;

/**
 * Service for EmployeeDAO
 */
public class EmployeeService {

    private EmployeeDAO employeeDAO;

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAll();
    }

    @Transactional
    public Employee getEmployeeByName(String... name) {
        return employeeDAO.search(name);
    }

    @Transactional
    public List<Waiter> getWaiters() {
        List<Waiter> waiters = employeeDAO.getWaiters();
        return waiters;
    }

    @Transactional
    public void add(Employee employee) {
        employeeDAO.add(employee);
    }

    @Transactional
    public Employee getEmployeeById(Integer id) {
        return employeeDAO.search(id);
    }

    @Transactional
    public void update(Employee employee) {
        employeeDAO.update(employee);
    }
}
