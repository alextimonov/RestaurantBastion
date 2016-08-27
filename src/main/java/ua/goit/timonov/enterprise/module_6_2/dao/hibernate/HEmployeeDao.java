package ua.goit.timonov.enterprise.module_6_2.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.EmployeeDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;

import java.util.List;

/**
 * Hibernate implementation of EmployeeDao
 */
public class HEmployeeDao implements EmployeeDAO {

    private SessionFactory sessionFactory;
    private HDaoCriteriaQueries<Employee> hDaoCriteriaQueries = new HDaoCriteriaQueries();

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void sethDaoCriteriaQueries(HDaoCriteriaQueries<Employee> hDaoCriteriaQueries) {
        this.hDaoCriteriaQueries = hDaoCriteriaQueries;
    }

    /**
     * finds list of all employees in DB
     * @return          list of employees
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Employee> getAll() {
        return hDaoCriteriaQueries.getAllEntityItems(sessionFactory, Employee.class);
    }

    /**
     * adds new employee to DB
     * @param employee      given employee
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void add(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    /**
     * searches employee in DB by its ID
     * @param id        employee's ID to find
     * @return          found employee
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Employee search(int id) {
        return hDaoCriteriaQueries.searchItemById(sessionFactory, Employee.class, id);
    }

    /**
     * searches employee in DB by its full name (surname & name)
     * @param surname        surname of employee to find
     * @param name           name of employee to find
     * @return name          found employee
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Employee search(String surname, String name) {
        return hDaoCriteriaQueries.searchItemByName(sessionFactory, Employee.class, name, surname);
    }

    /**
     * deletes employee from DB by its ID
     * @param id            employee's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(int id) {
        Employee employee = search(id);
        sessionFactory.getCurrentSession().remove(employee);
    }

    /**
     * deletes employee from DB by its full name (surname & name)
     * @param surname        surname of employee to delete
     * @param name           name of employee to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(String surname, String name) {
        Employee employee = search(surname, name);
        sessionFactory.getCurrentSession().remove(employee);
    }
}
