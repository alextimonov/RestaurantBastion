package ua.goit.timonov.enterprise.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.dao.EmployeeDAO;
import ua.goit.timonov.enterprise.exceptions.NoItemInDbException;
import ua.goit.timonov.enterprise.model.Employee;
import ua.goit.timonov.enterprise.model.Waiter;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Hibernate implementation of EmployeeDao
 */
public class HibernateEmployeeDao implements EmployeeDAO {

    private SessionFactory sessionFactory;
    private JpaCriteriaQueries<Employee> hDaoCriteriaQueries = new JpaCriteriaQueries();

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void sethDaoCriteriaQueries(JpaCriteriaQueries<Employee> hDaoCriteriaQueries) {
        this.hDaoCriteriaQueries = hDaoCriteriaQueries;
    }

    /**
     * finds list of all employees in DB
     * @return          list of employees
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public List<Employee> getAll() {
        return hDaoCriteriaQueries.getAllEntityItems(sessionFactory, Employee.class);
    }

    /**
     * adds new employee to DB
     * @param employee      given employee
     */
    @Override
    @Transactional
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
    @Transactional
    public Employee search(int id) {
        try {
            return hDaoCriteriaQueries.searchItemById(sessionFactory, Employee.class, id);
        }
        catch (IndexOutOfBoundsException | NoResultException e) {
            throw new NoItemInDbException("There's no employee with id=" + id + " in database!");
        }
    }

    /**
     * searches employee in DB by its full name (surname & name)
     * @param fullName           name, surname of employee to find
     * @return name          found employee
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public Employee search(String... fullName) {
        try {
            return hDaoCriteriaQueries.searchItemByName(sessionFactory, Employee.class, fullName);
        }
        catch (IndexOutOfBoundsException | NoResultException e) {
            String message = "There's no employee with name \"" + fullName[0];
            if (fullName.length > 1)
                message += " " + fullName[1] + "\" in database";
            else
                message += "\" in database";
            throw new NoItemInDbException(message);
        }
    }

    /**
     * deletes employee from DB by its ID
     * @param id            employee's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
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
    @Transactional
    public void delete(String name, String surname) {
        Employee employee = search(name, surname);
        sessionFactory.getCurrentSession().remove(employee);
    }

    /**
     * returns list of waiters
     * @return      list of waiters
     */
    @Override
    @Transactional
    public List<Waiter> getWaiters() {
        JpaCriteriaQueries<Waiter> hDaoCriteriaQueries = new JpaCriteriaQueries();
        List<Waiter> waiters = hDaoCriteriaQueries.getAllEntityItems(sessionFactory, Waiter.class);
        return waiters;
    }

    /**
     * updates employee's data in DB
     * @param employee      given employee with data
     */
    @Override
    @Transactional
    public void update(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.update("Employee", employee);
    }
}
