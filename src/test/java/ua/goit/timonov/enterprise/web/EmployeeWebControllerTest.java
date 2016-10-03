package ua.goit.timonov.enterprise.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;
import ua.goit.timonov.enterprise.dao.hibernate.ObjectsFactory;
import ua.goit.timonov.enterprise.model.Employee;
import ua.goit.timonov.enterprise.model.Job;
import ua.goit.timonov.enterprise.model.Position;
import ua.goit.timonov.enterprise.model.Waiter;
import ua.goit.timonov.enterprise.service.EmployeeService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.goit.timonov.enterprise.web.EmployeeServiceController.EMPLOYEES;
import static ua.goit.timonov.enterprise.web.EmployeeWebController.WAITERS;

/**
 * Testing class for EmployeeWebController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/web-context.xml"})
@WebAppConfiguration
public class EmployeeWebControllerTest {
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String SALARY = "salary";
    public static final String JOB = "job";
    public static final String ID = "id";

    @Autowired
    WebApplicationContext wac;

    @InjectMocks
    private EmployeeWebController controller;

    @Mock
    private EmployeeService employeeService;

    @Mock
    View mockView;

    MockMvc mockMvc;

    ObjectsFactory objectsFactory;

    @Before
    public void setUp() throws Exception {
        controller = new EmployeeWebController();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
        objectsFactory = new ObjectsFactory();
    }

    @Test
    public void testGetEmployees() throws Exception {
        Employee mrBlack = objectsFactory.makeEmployeeBlack();
        Employee mrWhite = objectsFactory.makeEmployeeWhite();
        Employee mrRed = objectsFactory.makeEmployeeRed();
        List<Employee> expectedEmployees = Arrays.asList(mrBlack, mrWhite, mrRed);
        when(employeeService.getAllEmployees()).thenReturn(expectedEmployees);
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(EMPLOYEES, hasSize(3)))
                .andExpect(model().attribute(EMPLOYEES, hasItem(
                        allOf(
                                hasProperty(NAME, is("Steven")),
                                hasProperty(SURNAME, is("Black")),
                                hasProperty(SALARY, is(35000F)),
                                hasProperty(JOB, is(new Job(Position.WAITER)))
                        )
                )))
                .andExpect(model().attribute(EMPLOYEES, hasItem(
                        allOf(
                                hasProperty(NAME, is("John")),
                                hasProperty(SURNAME, is("White")),
                                hasProperty(SALARY, is(75000F)),
                                hasProperty(JOB, is(new Job(Position.MANAGER)))
                        )
                )))
                .andExpect(model().attribute(EMPLOYEES, hasItem(
                        allOf(
                                hasProperty(NAME, is("Peter")),
                                hasProperty(SURNAME, is("Red")),
                                hasProperty(SALARY, is(50000F)),
                                hasProperty(JOB, is(new Job(Position.COOK)))
                        )
                )));
        verify(employeeService).getAllEmployees();
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testGetWaiters() throws Exception {
        Waiter mrBlack = objectsFactory.makeWaiterBlack();
        Waiter mrGreen = objectsFactory.makeWaiterGreen();

        List<Waiter> expectedWaiters = Arrays.asList(mrBlack, mrGreen);
        when(employeeService.getWaiters()).thenReturn(expectedWaiters);
        mockMvc.perform(get("/waiters"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(WAITERS, hasSize(2)))
                .andExpect(model().attribute(WAITERS, hasItem(
                        allOf(
                                hasProperty(NAME, is("Steven")),
                                hasProperty(SURNAME, is("Black")),
                                hasProperty(SALARY, is(35000F)),
                                hasProperty(JOB, is(new Job(Position.WAITER)))
                        )
                )))
                .andExpect(model().attribute(WAITERS, hasItem(
                        allOf(
                                hasProperty(NAME, is("Tom")),
                                hasProperty(SURNAME, is("Green")),
                                hasProperty(SALARY, is(37000F)),
                                hasProperty(JOB, is(new Job(Position.WAITER)))
                        )
                )));
        verify(employeeService).getWaiters();
        verifyNoMoreInteractions(employeeService);
    }
}