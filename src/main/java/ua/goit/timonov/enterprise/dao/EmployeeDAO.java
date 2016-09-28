package ua.goit.timonov.enterprise.dao;

import ua.goit.timonov.enterprise.model.Employee;
import ua.goit.timonov.enterprise.model.Waiter;

import java.util.List;

/**
 * DAO interface for Employee
 */
public interface EmployeeDAO {

    /**
     * finds list of all employees in DB
     * @return              list of employees
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    List<Employee> getAll();

    /**
     * adds new employee to DB
     * @param employee      given employee
     */
    void add(Employee employee);

    /**
     * searches employee in DB by its ID
     * @param id        employee's ID to find
     * @return          found employee
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    Employee search(int id);

    /**
     * searches employee in DB by its full name (surname & name)
     * @param name           name, surname of employee to find
     * @return name          found employee
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    Employee search(String... name);

    /**
     * deletes employee from DB by its ID
     * @param id            employee's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    void delete(int id);

    /**
     * deletes employee from DB by its full name (surname & name)
     * @param name           name of employee to delete
     * @param surname        surname of employee to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    void delete(String name, String surname);

    /**
     * returns list of waiters
     * @return      list of waiters
     */
    List<Waiter> getWaiters();

    /**
     * updates employee's data in DB
     * @param employee      given employee with data
     */
    void update(Employee employee);
}