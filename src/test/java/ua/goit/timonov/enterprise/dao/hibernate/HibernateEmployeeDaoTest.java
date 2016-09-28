package ua.goit.timonov.enterprise.dao.hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.controllers.DbController;
import ua.goit.timonov.enterprise.dao.EmployeeDAO;
import ua.goit.timonov.enterprise.exceptions.NoItemInDbException;
import ua.goit.timonov.enterprise.model.Employee;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testing class for HibernateEmployeeDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/application-context.xml",
        "file:src/main/webapp/WEB-INF/hibernate-context.xml"})
public class HibernateEmployeeDaoTest {
    private DbController dbController;
    private EmployeeDAO employeeDAO;
    ObjectsFactory factory = new ObjectsFactory();

    @Autowired(required = true)
    public void setDbController(DbController dbController) {
        this.dbController = dbController;
    }

    @Autowired(required = true)
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Before
    @Transactional
    public void setUp() throws Exception {
        dbController.createAllTables();
        dbController.fillTableJobs();
    }

    @After
    @Transactional
    public void tearDown() throws Exception {
        dbController.deleteAllData();
        dbController.dropAllTables();
    }

    @Test
    public void dataPopulation() throws FileNotFoundException {
        dbController.restoreAllData();
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
        employeeDAO.add(factory.makeEmployeeWhite());
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

        Employee mrWhite = factory.makeEmployeeWhite();
        createdEmployees.add(mrWhite);
        employeeDAO.add(mrWhite);
        List<Employee> gotFromDbEmployees = employeeDAO.getAll();
        assertEqualsEmployeeLists(createdEmployees, gotFromDbEmployees);

        Employee mrBlack = factory.makeEmployeeBlack();
        createdEmployees.add(mrBlack);
        employeeDAO.add(mrBlack);
        gotFromDbEmployees = employeeDAO.getAll();
        assertEqualsEmployeeLists(createdEmployees, gotFromDbEmployees);
    }

    @Test
    public void testAddAbnormal() throws Exception {
        List<Employee> createdEmployees = fillTableEmployee();

        Employee mrWhite = factory.makeEmployeeWhite();
        createdEmployees.add(mrWhite);
        employeeDAO.add(mrWhite);
        List<Employee> gotFromDbEmployees = employeeDAO.getAll();
        assertEqualsEmployeeLists(createdEmployees, gotFromDbEmployees);

        Employee mrBlack = factory.makeEmployeeBlack();
        createdEmployees.add(mrBlack);
        employeeDAO.add(mrWhite);
        gotFromDbEmployees = employeeDAO.getAll();
        assertNotEqualsEmployeeLists(createdEmployees, gotFromDbEmployees);
    }

    @Test
    public void testSearchByIdNormal() throws Exception {
        dbController.restoreAllData();
        Employee mrBlack = factory.makeEmployeeBlack();
        employeeDAO.add(mrBlack);
        int id = mrBlack.getId();
        Employee foundEmployee = employeeDAO.search(id);
        assertEquals(mrBlack, foundEmployee);

        Employee mrWhite = factory.makeEmployeeWhite();
        employeeDAO.add(mrWhite);
        id = mrWhite.getId();
        foundEmployee = employeeDAO.search(id);
        assertEquals(mrWhite, foundEmployee);

        Employee mrRed = factory.makeEmployeeRed();
        employeeDAO.add(mrRed);
        id = mrRed.getId();
        foundEmployee = employeeDAO.search(id);
        assertEquals(mrRed, foundEmployee);
    }

    @Test(expected = NoItemInDbException.class)
    public void testSearchByIdAbnormal() throws Exception {
        dbController.restoreAllData();
        employeeDAO.search(100);
    }

    @Test
    public void testSearchByNameNormal() throws Exception {
        dbController.restoreAllData();

        Employee mrBlack = factory.makeEmployeeBlack();
        employeeDAO.add(mrBlack);

        Employee foundEmployee = employeeDAO.search("Steven", "Black");
        assertEquals(mrBlack, foundEmployee);

        Employee mrWhite = factory.makeEmployeeWhite();
        employeeDAO.add(mrWhite);
        foundEmployee = employeeDAO.search("John", "White");
        assertEquals(mrWhite, foundEmployee);
    }

    @Test
    public void testDeleteById() throws Exception {
        dbController.restoreAllData();

        List<Employee> listBeforeAdd = employeeDAO.getAll();
        Employee mrBlack = factory.makeEmployeeBlack();
        employeeDAO.add(mrBlack);
        int id = mrBlack.getId();
        employeeDAO.delete(id);
        List<Employee> listAfterDelete = employeeDAO.getAll();

        assertEqualsEmployeeLists(listBeforeAdd, listAfterDelete);

        listBeforeAdd = employeeDAO.getAll();
        Employee mrWhite = factory.makeEmployeeWhite();
        employeeDAO.add(mrWhite );
        id = mrWhite.getId();
        employeeDAO.delete(id);
        listAfterDelete = employeeDAO.getAll();
        assertEqualsEmployeeLists(listBeforeAdd, listAfterDelete);
    }

    @Test
    public void testDeleteByName() throws Exception {
        dbController.restoreAllData();

        List<Employee> listBeforeAdd = employeeDAO.getAll();
        Employee mrBlack = factory.makeEmployeeBlack();
        employeeDAO.add(mrBlack);
        employeeDAO.delete("Steven", "Black");
        List<Employee> listAfterDelete = employeeDAO.getAll();

        assertEqualsEmployeeLists(listBeforeAdd, listAfterDelete);

        listBeforeAdd = employeeDAO.getAll();
        Employee mrWhite = factory.makeEmployeeWhite();
        employeeDAO.add(mrWhite );
        employeeDAO.delete("John", "White");
        listAfterDelete = employeeDAO.getAll();
        assertEqualsEmployeeLists(listBeforeAdd, listAfterDelete);
    }

    @Transactional
    private List<Employee> fillTableEmployee() {
        Employee mrBlack = factory.makeEmployeeBlack();
        Employee mrWhite = factory.makeEmployeeWhite();
        Employee mrRed = factory.makeEmployeeRed();

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