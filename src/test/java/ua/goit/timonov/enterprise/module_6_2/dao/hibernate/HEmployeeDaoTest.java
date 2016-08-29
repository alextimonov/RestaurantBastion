package ua.goit.timonov.enterprise.module_6_2.dao.hibernate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.controllers.DbController;
import ua.goit.timonov.enterprise.module_6_2.dao.EmployeeDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.model.Job;
import ua.goit.timonov.enterprise.module_6_2.model.Position;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testing class for HEmployeeDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml"})
public class HEmployeeDaoTest {
    private DbController dbController;
    private EmployeeDAO employeeDAO;

    @Autowired(required = true)
    public void setDbController(DbController dbController) {
        this.dbController = dbController;
    }

    @Autowired(required = true)
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Before
    public void setUp() throws Exception {
        dbController.dropAllTables();
        dbController.createAllTables();
        dbController.fillTableJobs();
    }

    @Test
    public void testGetAllNormal() throws Exception {
        List<Employee> createdEmployees = fillTableEmployee();
        List<Employee> gotFromDbEmployees = employeeDAO.getAll();
        assertEqualsEmployeeLists(createdEmployees, gotFromDbEmployees);
    }

    @Test
    public void testGetAllAbnormal_1() throws Exception {
        List<Employee> createdEmployees = fillTableEmployee();
        employeeDAO.add(makeEmployeeWhite());
        List<Employee> gotFromDbEmployees = employeeDAO.getAll();
        assertNotEqualsEmployeeLists(createdEmployees, gotFromDbEmployees);
    }

    @Test
    public void testGetAllAbnormal_2() throws Exception {
        List<Employee> createdEmployees = new ArrayList<>();
        List<Employee> gotFromDbEmployees = employeeDAO.getAll();
        assertEqualsEmployeeLists(createdEmployees, gotFromDbEmployees);
    }

    @Test
    public void testAddNormal() throws Exception {
        List<Employee> createdEmployees = fillTableEmployee();

        Employee mrWhite = makeEmployeeWhite();
        createdEmployees.add(mrWhite);
        employeeDAO.add(mrWhite);
        List<Employee> gotFromDbEmployees = employeeDAO.getAll();
        assertEqualsEmployeeLists(createdEmployees, gotFromDbEmployees);

        Employee mrBlack = makeEmployeeBlack();
        createdEmployees.add(mrBlack);
        employeeDAO.add(mrBlack);
        gotFromDbEmployees = employeeDAO.getAll();
        assertEqualsEmployeeLists(createdEmployees, gotFromDbEmployees);
    }

    @Test
    public void testAddAbnormal() throws Exception {
        List<Employee> createdEmployees = fillTableEmployee();

        Employee mrWhite = makeEmployeeWhite();
        createdEmployees.add(mrWhite);
        employeeDAO.add(mrWhite);
        List<Employee> gotFromDbEmployees = employeeDAO.getAll();
        assertEqualsEmployeeLists(createdEmployees, gotFromDbEmployees);

        Employee mrBlack = makeEmployeeBlack();
        createdEmployees.add(mrBlack);
        employeeDAO.add(mrWhite);
        gotFromDbEmployees = employeeDAO.getAll();
        assertNotEqualsEmployeeLists(createdEmployees, gotFromDbEmployees);
    }

    @Test
    public void testSearchByIdNormal() throws Exception {
        dbController.restoreAllData();
        Employee mrBlack = makeEmployeeBlack();
        employeeDAO.add(mrBlack);

        Employee foundEmployee = employeeDAO.search(10);
        assertEquals(mrBlack, foundEmployee);

        Employee mrWhite = makeEmployeeWhite();
        employeeDAO.add(mrWhite);
        foundEmployee = employeeDAO.search(11);
        assertEquals(mrWhite, foundEmployee);

        Employee mrRed = makeEmployeeRed();
        employeeDAO.add(mrRed);
        foundEmployee = employeeDAO.search(12);
        assertEquals(mrRed, foundEmployee);
    }

    @Test(expected = NoResultException.class)
    public void testSearchByIdAbnormal() throws Exception {
        dbController.restoreAllData();
        employeeDAO.search(10);
    }

    @Test
    public void testSearchByNameNormal() throws Exception {
        dbController.restoreAllData();

        Employee mrBlack = makeEmployeeBlack();
        employeeDAO.add(mrBlack);

        Employee foundEmployee = employeeDAO.search("Steven", "Black");
        assertEquals(mrBlack, foundEmployee);

        Employee mrWhite = makeEmployeeWhite();
        employeeDAO.add(mrWhite);
        foundEmployee = employeeDAO.search("John", "White");
        assertEquals(mrWhite, foundEmployee);
    }

    @Test
    public void testDeleteById() throws Exception {
        dbController.restoreAllData();

        List<Employee> listBeforeAdd = employeeDAO.getAll();
        Employee mrBlack = makeEmployeeBlack();
        employeeDAO.add(mrBlack);
        employeeDAO.delete(42);
        List<Employee> listAfterDelete = employeeDAO.getAll();

        assertEqualsEmployeeLists(listBeforeAdd, listAfterDelete);

        listBeforeAdd = employeeDAO.getAll();
        Employee mrWhite = makeEmployeeWhite();
        employeeDAO.add(mrWhite );
        employeeDAO.delete(43);
        listAfterDelete = employeeDAO.getAll();
        assertEqualsEmployeeLists(listBeforeAdd, listAfterDelete);
    }

    @Test
    public void testDeleteByName() throws Exception {
        dbController.restoreAllData();

        List<Employee> listBeforeAdd = employeeDAO.getAll();
        Employee mrBlack = makeEmployeeBlack();
        employeeDAO.add(mrBlack);
        employeeDAO.delete("Steven", "Black");
        List<Employee> listAfterDelete = employeeDAO.getAll();

        assertEqualsEmployeeLists(listBeforeAdd, listAfterDelete);

        listBeforeAdd = employeeDAO.getAll();
        Employee mrWhite = makeEmployeeWhite();
        employeeDAO.add(mrWhite );
        employeeDAO.delete("John", "White");
        listAfterDelete = employeeDAO.getAll();
        assertEqualsEmployeeLists(listBeforeAdd, listAfterDelete);
    }

    private Employee makeEmployeeWhite() {
        Employee mrWhite = new Employee();
        mrWhite
                .append("John", "White")
                .append(new GregorianCalendar(1990, 4, 30).getTime())
                .append(new Job(Position.MANAGER))
                .append(75000F);
        return mrWhite;
    }

    private Employee makeEmployeeBlack() {
        Employee mrBlack = new Employee();
        mrBlack
                .append("Steven", "Black")
                .append(new GregorianCalendar(1998, 5, 20).getTime())
                .append(new Job(Position.WAITER))
                .append(35000F);
        return mrBlack;
    }

    private Employee makeEmployeeRed() {
        Employee mrRed = new Employee();
        mrRed
                .append("Peter", "Red")
                .append(new GregorianCalendar(1999, 7, 12).getTime())
                .append(new Job(Position.COOK))
                .append(50000F);
        return mrRed;
    }

    @Transactional
    private List<Employee> fillTableEmployee() {
        Employee mrBlack = makeEmployeeBlack();
        Employee mrWhite = makeEmployeeWhite();
        Employee mrRed = makeEmployeeRed();

        employeeDAO.add(mrBlack);
        employeeDAO.add(mrRed);
        employeeDAO.add(mrWhite);
        employeeDAO.add(mrBlack);
        employeeDAO.add(mrRed);

        List<Employee> createdStaff = new ArrayList<>();
        createdStaff.add(mrBlack);
        createdStaff.add(mrRed);
        createdStaff.add(mrWhite);
        createdStaff.add(mrBlack);
        createdStaff.add(mrRed);
        return createdStaff;
    }

    private void assertNotEqualsEmployeeLists(List<Employee> createdEmployees, List<Employee> gotFromDbEmployees) {
        createdEmployees.stream().filter(gotFromDbEmployees::contains).forEach(gotFromDbEmployees::remove);
        assertNotEquals(new ArrayList<Employee>(), gotFromDbEmployees);
    }

    private void assertEqualsEmployeeLists(List<Employee> createdEmployees, List<Employee> gotFromDbEmployees) {
        createdEmployees.stream().filter(gotFromDbEmployees::contains).forEach(gotFromDbEmployees::remove);
        assertEquals(new ArrayList<Employee>(), gotFromDbEmployees);
    }
}