package ua.goit.timonov.enterprise.meeting_offline.meeting_3_app_DAO;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alex on 21.06.2016.
 */
public class DAOImplTest {

    @Test(expected = NullPointerException.class)

    public void testCreateEmployee_1() throws Exception {
        String s = null;
        s.toString();
    }

    @Test(timeout = 5000)
    public void testCreateEmployee() throws Exception {
        DAO dao = new DAOImpl();
        dao.createEmployee("John");
        List<Employee> employees = null;
        dao.getEmployee(1);
        assertNotNull(employees.get(0));
        assertEquals(employees.get(0).getName(), "John");

        while (true) {
        }
    }


    @Test
    @Ignore
    public void testGetEmployeeByName() throws Exception {
        DAO dao = new DAOImpl();
        Employee employee = dao.getEmployee(1);
        assertTrue(employee.getName().equals("John"));

    }

    @Test
    @Ignore
    public void testGetEmployee1() throws Exception {

    }

    @Test
    @Ignore
    public void testUpdateEmployee() throws Exception {

    }

    @Test
    @Ignore
    public void testDeleteEmployee() throws Exception {
        DAO dao = new DAOImpl();
        dao.createEmployee("Qwerty");
        List<Employee> employees = dao.getEmployeeByName("Qwerty");
        assertTrue(employees.size() != 0);
        dao.getEmployee(employees.get(0).getId());


        Employee employee = dao.getEmployee(1);
        assertTrue(employee.getName().equals("John"));
    }
}