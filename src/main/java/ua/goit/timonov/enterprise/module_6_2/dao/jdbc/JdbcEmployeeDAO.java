package ua.goit.timonov.enterprise.module_6_2.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.EmployeeDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.model.Job;
import ua.goit.timonov.enterprise.module_6_2.model.Waiter;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Transactional
    public void add(Employee employee) {
        if (isValidPosition(employee.getJob().getPosition().toString())) {
            String sql = "INSERT INTO employee VALUES ((SELECT max(EMPLOYEE.id) FROM EMPLOYEE) + 1, ?, ?, ?, " +
                    "(SELECT jobs.id FROM JOBS WHERE jobs.position = ?), ?)";
            template.update(sql,
                    employee.getSurname(),
                    employee.getName(),
                    employee.getBirthday(),
                    employee.getJob(),
                    employee.getSalary());
        }
        else
            throw new IllegalArgumentException("UNSUCCESSFUL! Unable to add employee. Such position doesn't exist:");
    }

    // returns true if given position exists in table JOBS
    @Transactional
    private boolean isValidPosition(String position) {
        String sql = "SELECT * FROM Jobs WHERE position = ?";
        List<Map<String, Object>> listMap = template.queryForList(sql, position);
        return !listMap.isEmpty();
    }

    /**
     * deletes employee from DB by its ID
     * @param id            employee's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public void delete(int id) {
            search(id);
            String sql = "DELETE FROM Employee WHERE id = ?";
            template.update(sql, id);
    }

    /**
     * deletes employee from DB by its full name (surname & name)
     * @param name           name of employee to delete
     * @param surname        surname of employee to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public void delete(String name, String surname) {
            search(name, surname);
            String sql = "DELETE FROM Employee WHERE name = ? AND surname = ?";
            template.update(sql, name, surname);
    }

    @Override
    public List<Waiter> getWaiters() {
        String sql = "SELECT Employee.id, Employee.surname, Employee.name, Jobs.position, Employee.birthday, Employee.salary\n" +
                "FROM Employee INNER JOIN Jobs ON Employee.position_id = Jobs.id WHERE Employee.dtype = 'WAITER'";
        List<Map<String, Object>> mapList = template.queryForList(sql);
        return mapList.stream()
                .map(row -> new Waiter(getEmployeeFromMap(row)))
                .collect(Collectors.toList());
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
        String sql = "SELECT Employee.id, Employee.surname, Employee.name, Jobs.position, Employee.birthday, Employee.salary " +
                "FROM Employee INNER JOIN Jobs ON Employee.position_id = Jobs.id WHERE Employee.id = ?";
        Map<String, Object> map = template.queryForMap(sql, id);
        Employee employee = getEmployeeFromMap(map);
        return employee;
    }

    /**
     * searches employee in DB by its full name (surname & name)
     * @param name           name, surname of employee to find
     * @return name          found employee
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public Employee search(String... name) {
        if (name.length > 1)
            return search(name[0], name[1]);
        else
            return searchByFirstName(name[0]);
    }

    @Transactional
    private Employee searchByFirstName(String name) {
        String sql = "SELECT Employee.id, Employee.surname, Employee.name, Jobs.position, Employee.birthday, Employee.salary " +
                "FROM Employee INNER JOIN Jobs ON Employee.position_id = Jobs.id WHERE Employee.name = ?";
        Map<String, Object> map = template.queryForMap(sql, name);
        Employee employee = getEmployeeFromMap(map);
        return employee;
    }

    @Transactional
    public Employee search(String name, String surname) {
        String sql = "SELECT Employee.id, Employee.surname, Employee.name, Jobs.position, Employee.birthday, Employee.salary " +
                "FROM Employee INNER JOIN Jobs ON Employee.position_id = Jobs.id WHERE Employee.surname = ? AND Employee.name = ?";
        Map<String, Object> map = template.queryForMap(sql, surname, name);
        Employee employee = getEmployeeFromMap(map);
        return employee;
    }

    // gets employee's data from SQL query's map
    @Transactional
    private Employee getEmployeeFromMap(Map<String, Object> map) {
        Employee employee = new Employee();
        employee.setId((Integer) map.get("id"));
        employee.setSurname((String) map.get("surname"));
        employee.setName((String) map.get("name"));
        employee.setJob((Job) map.get("job"));
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
    @Transactional
    public List<Employee> getAll() {
        String sql = "SELECT Employee.id, Employee.surname, Employee.name, Jobs.position, Employee.birthday, Employee.salary\n" +
                "FROM Employee INNER JOIN Jobs ON Employee.position_id = Jobs.id";
        List<Map<String, Object>> mapList = template.queryForList(sql);
        return mapList.stream()
                .map(row -> getEmployeeFromMap(row))
                .collect(Collectors.toList());
    }
}
