package ua.goit.timonov.enterprise.module_6_2.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.dao.EmployeeDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * JDBC implementation of EmployeeDAO
 */
public class JdbcEmployeeDAO implements EmployeeDAO {

    private JdbcTemplate template;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    /**
     * adds new employee to DB
     * @param employee      given employee
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void add(Employee employee) {
        if (isValidPosition(employee.getPosition())) {
            String sql = "INSERT INTO employee VALUES ((SELECT max(EMPLOYEE.id) FROM EMPLOYEE) + 1, ?, ?, ?, " +
                    "(SELECT jobs.id FROM JOBS WHERE jobs.position = ?), ?)";
            template.update(sql,
                    employee.getSurname(),
                    employee.getName(),
                    employee.getBirthday(),
                    employee.getPosition(),
                    employee.getSalary());
        }
        else
            throw new IllegalArgumentException("UNSUCCESSFUL! Unable to add employee. Such position don't exist:");
    }

    // returns true if given position exists in table JOBS
    @Transactional(propagation = Propagation.MANDATORY)
    private boolean isValidPosition(String position) {
        String sql = "SELECT * FROM jobs WHERE position = ?";
        List<Map<String, Object>> listMap= template.queryForList(sql, position);
        return !listMap.isEmpty();
    }

    /**
     * deletes employee from DB by its ID
     * @param id            employee's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(int id) {
            search(id);
            String sql = "DELETE FROM employee WHERE id = ?";
            template.update(sql, id);
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
            search(surname, name);
            String sql = "DELETE FROM employee WHERE surname = ? AND name = ?";
            template.update(sql, surname, name);
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
        String sql = "SELECT employee.id, employee.surname, employee.name, JOBS.position, employee.birthday, employee.salary " +
                "FROM EMPLOYEE INNER JOIN JOBS ON EMPLOYEE.position_id = JOBS.id WHERE employee.id = ?";
        Map<String, Object> map = template.queryForMap(sql, id);
        Employee employee = getEmployeeFromMap(map);
        return employee;
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
        String sql = "SELECT employee.id, employee.surname, employee.name, JOBS.position, employee.birthday, employee.salary " +
                "FROM EMPLOYEE INNER JOIN JOBS ON EMPLOYEE.position_id = JOBS.id WHERE employee.surname = ? AND employee.name = ?";
        Map<String, Object> map = template.queryForMap(sql, surname, name);
        Employee employee = getEmployeeFromMap(map);
        return employee;
    }

    // gets employee's data from SQL query's map
    @Transactional(propagation = Propagation.MANDATORY)
    private Employee getEmployeeFromMap(Map<String, Object> map) {
        Employee employee = new Employee();
        employee.setId((Integer) map.get("id"));
        employee.setSurname((String) map.get("surname"));
        employee.setName((String) map.get("name"));
        employee.setPosition((String) map.get("position"));
        employee.setBirthday((Date) map.get("birthday"));
        employee.setSalary((Float) map.get("salary"));
        return employee;
    }

    /**
     * finds list of all employees in DB
     * @return          list of employees
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();
        String sql = "SELECT EMPLOYEE.id, EMPLOYEE.surname, EMPLOYEE.name, JOBS.position, EMPLOYEE.birthday, EMPLOYEE.salary\n" +
                "FROM EMPLOYEE INNER JOIN JOBS ON EMPLOYEE.position_id = JOBS.id";
        List<Map<String, Object>> mapList = template.queryForList(sql);
        for (Map<String, Object> row : mapList) {
            Employee employee = getEmployeeFromMap(row);
            result.add(employee);
        }
        return result;
    }

}
