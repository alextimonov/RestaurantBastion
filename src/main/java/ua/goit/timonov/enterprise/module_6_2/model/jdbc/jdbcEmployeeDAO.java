package ua.goit.timonov.enterprise.module_6_2.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.model.EmployeeDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 31.07.2016.
 */
public class JdbcEmployeeDAO implements EmployeeDAO {

    private JdbcTemplate template;

//    private static Logger LOGGER = LoggerFactory.getLogger(JdbcEmployeeDAO.class);

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void add(Employee employee) {
        String sql = "INSERT INTO employee VALUES ((SELECT max(EMPLOYEE.id) FROM EMPLOYEE) + 1, ?, ?, ?, " +
                "(SELECT jobs.id FROM JOBS WHERE jobs.position = ?), ?)";
        template.update(sql,
                employee.getSurname(),
                employee.getName(),
                employee.getBirthday(),
                employee.getPosition(),
                employee.getSalary());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(Employee employee) {
        String sql = "DELETE FROM employee WHERE id = ?";
        template.update(sql, employee.getId());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Employee find(String surnameToSearch, String nameToSearch) {
        String sql = "SELECT employee.id, employee.surname, employee.name, JOBS.position, employee.birthday, employee.salary " +
                "FROM EMPLOYEE INNER JOIN JOBS ON EMPLOYEE.position_id = JOBS.id WHERE employee.surname = ? AND employee.name = ?";
        Map<String, Object> map = template.queryForMap(sql, surnameToSearch, nameToSearch);
        Employee employee = new Employee();
        employee.setId((Integer) map.get("id"));
        employee.setSurname((String) map.get("surname"));
        employee.setName((String) map.get("name"));
        employee.setPosition((String) map.get("position"));
        employee.setBirthday((Date) map.get("birthday"));
        employee.setSalary((Float) map.get("salary"));
        return employee;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();
        String sql = "SELECT EMPLOYEE.id, EMPLOYEE.surname, EMPLOYEE.name, JOBS.position, EMPLOYEE.birthday, EMPLOYEE.salary\n" +
                "FROM EMPLOYEE INNER JOIN JOBS ON EMPLOYEE.position_id = JOBS.id";
        List<Map<String, Object>> mapList = template.queryForList(sql);
        for (Map<String, Object> row : mapList) {
            Employee employee = new Employee();
            employee.setId((Integer) row.get("id"));
            employee.setSurname((String) row.get("surname"));
            employee.setName((String) row.get("name"));
            employee.setPosition((String) row.get("position"));
            employee.setBirthday((Date) row.get("birthday"));
            employee.setSalary((Float) row.get("salary"));
            result.add(employee);
        }
        return result;
    }

}
