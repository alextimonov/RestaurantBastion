package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.dao.EmployeeDAO;

import java.util.List;

/**
 * Controller for EmployeeDAO
 */
public class EmployeeController {

    private EmployeeDAO employeeDAO;

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    /**
     * finds list of all employees in DB
     * @return              list of employees
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    /**
     * adds new employee to DB
     * @param employee      given employee
     */
    @Transactional
    public void add(Employee employee) {
        employeeDAO.add(employee);
    }

    /**
     * searches employee in DB by its ID
     * @param id        employee's ID to find
     * @return          found employee
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public Employee search(int id) {
        return employeeDAO.search(id);
    }

    /**
     * searches employee in DB by its full name (surname & name)
     * @param surname        surname of employee to find
     * @param name           name of employee to find
     * @return name          found employee
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public Employee search(String surname, String name) {
        return employeeDAO.search(surname, name);
    }

    /**
     * deletes employee from DB by its ID
     * @param id            employee's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public void delete(int id) {
        employeeDAO.delete(id);
    }

    /**
     * deletes employee from DB by its full name (surname & name)
     * @param surname        surname of employee to delete
     * @param name           name of employee to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public void delete(String surname, String name) {
        employeeDAO.delete(surname, name);
    }
}
