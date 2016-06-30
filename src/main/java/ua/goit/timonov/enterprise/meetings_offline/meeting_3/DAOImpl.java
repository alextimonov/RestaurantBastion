package ua.goit.timonov.enterprise.meetings_offline.meeting_3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andre on 21.06.2016.
 */
public class DAOImpl implements DAO {

    static {
        try {
            //DriverManager.registerDriver("org.postgresql.Driver");
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createEmployee(String name) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO employee (id, name) values ((SELECT MAX(id)+1 FROM employee),?)");
            ps.setString(1, name);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployye(long id) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            Employee employee = null;
            while (resultSet.next()) {
                String name  = resultSet.getString("name");
                long id2 = resultSet.getLong("id");
                employee = new Employee(id2, name);
            }
            return employee;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> getEmployeeByName(String name) {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee WHERE name =?");
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            Employee employee = null;
            while (resultSet.next()) {
                employee = new Employee(resultSet.getLong("id"), resultSet.getString("name"));
                result.add(employee);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
    public void readMetadata() {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee");
            ResultSet resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int n = metaData.getColumnCount();
            for (int i = 1; i<n+1; i++) {
                System.out.print(metaData.getColumnClassName(i));
                System.out.println("====" + metaData.getColumnName(i));;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void bulkAction() {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO employee (id, name) values ((SELECT MAX(id)+1 FROM employee), 'Test')");
            ps.execute();
            ps = connection.prepareStatement("SELECT_ * FROM employee");
            ps.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void updateEmployee(long id, Employee employee) {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE employee SET name=? WHERE id=?");
            ps.setString(1, employee.getName());
            ps.setLong(2, employee.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteEmployee(long id) {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Nakladnaya","user", "111");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
