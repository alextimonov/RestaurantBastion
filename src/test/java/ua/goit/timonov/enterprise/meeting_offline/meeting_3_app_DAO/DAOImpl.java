package ua.goit.timonov.enterprise.meeting_offline.meeting_3_app_DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 21.06.2016.
 */
public class DAOImpl implements DAO {

    static {
//        DriverManager. ...
//        Class.forName(""); ...
    }

    @Override
    public void createEmployee(String name) {

        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO employee(name) values(?)");
            ps.setString(1, name);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployee(long id) {

        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee WHERE id = 1");
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            Employee employee = null;
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                long id2 = resultSet.getLong("id");
            }
            ps.execute();
            return employee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee WHERE name = ");
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            Employee employee = null;
            List<Employee> resultList = new ArrayList<>();
            while (resultSet.next()) {
                employee = new Employee();
                name = resultSet.getString("name");
                resultList.add(employee);
                long id2 = resultSet.getLong("id");
            }
            ps.execute();
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEmployee(long id, Employee employee) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE employee SET name = ? WHERE id = ?");
            ps.setString(1, employee.getName());
            ps.setLong(2, employee.getId());

            ResultSet resultSet = ps.executeQuery();
            List<Employee> resultList = new ArrayList<>();
            while (resultSet.next()) {
                employee = new Employee();
                String name = resultSet.getString("name");
                resultList.add(employee);
                long id2 = resultSet.getLong("id");
            }
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEmployee(long id) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE * FROM employee WHERE name = ");
            ps.setString(1, "");
            ResultSet resultSet = ps.executeQuery();
            Employee employee = null;
            List<Employee> resultList = new ArrayList<>();
            while (resultSet.next()) {
                employee = new Employee();
                String name = resultSet.getString("name");
                resultList.add(employee);
                long id2 = resultSet.getLong("id");
            }
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readMetaData() {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee");
            ResultSet resultSet = ps.executeQuery();
            ResultSetMetaData metaData = ps.getMetaData();
            int n = metaData.getColumnCount();
            for (int i = 1; i <= n; i++) {
                // class name of column in DB
                System.out.println(metaData.getColumnClassName(i));
                // column name in DB
                System.out.println(metaData.getColumnName(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void bulkAction() {
        // ACID - атомарность - единство операций, либо все, либо ни одной
        // консинстентность
        // изоляция

        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO employee ...");
            ps.execute();
            ps = connection.prepareStatement("SELECT * FROM employee");
            ps.execute();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        String url = "jdbc:postgresql://localhost/test";
        try {
            return DriverManager.getConnection("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
