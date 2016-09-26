package ua.goit.timonov.enterprise.web.validate;

import org.junit.Before;
import org.junit.Test;
import ua.goit.timonov.enterprise.web.EmployeeView;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 24.09.2016.
 */
public class EmployeeValidateTest {
    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";
    public static final String MANAGER = "manager";
    private EmployeeView employeeView;
    private EmployeeValidate employeeValidate;

    @Before
    public void setUp() throws Exception {
        employeeValidate = new EmployeeValidate();
        employeeView = new EmployeeView();
        employeeView.setName("Joe");
        employeeView.setSurname("Black");
        employeeView.setSalary(25000);
        Date birthday = new GregorianCalendar(1990, 1, 25).getTime();
        employeeView.setBirthday(birthday);
        employeeView.setPosition(MANAGER);
    }

    @Test
    public void testValidEmployee() throws Exception {
        assertEquals(true, employeeValidate.isValid(employeeView));
    }

    @Test
    public void testNotValidByName() throws Exception {
        employeeView.setName(SPACE);
        assertEquals(false, employeeValidate.isValid(employeeView));
    }

    @Test
    public void testNotValidBySurname() throws Exception {
        employeeView.setSurname(EMPTY_STRING);
        assertEquals(false, employeeValidate.isValid(employeeView));
    }

    @Test
    public void testNotValidBySaalary() throws Exception {
        employeeView.setSalary(0);
        assertEquals(false, employeeValidate.isValid(employeeView));
    }

    @Test
    public void testNotValidByBirthday() throws Exception {
        employeeView.setBirthday(null);
        assertEquals(false, employeeValidate.isValid(employeeView));
    }

    @Test
    public void testNotValidByPosition() throws Exception {
        employeeView.setPosition("someone else");
        assertEquals(false, employeeValidate.isValid(employeeView));
    }
}