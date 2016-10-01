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
import ua.goit.timonov.enterprise.service.EmployeeService;
import ua.goit.timonov.enterprise.web.validate.EmployeeValidate;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testing class for EmployeeServiceController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/web-context.xml"})
@WebAppConfiguration
public class EmployeeServiceControllerTest {
    public static final String MAPPED_PATH = "/service/employee";
    @Autowired
    WebApplicationContext wac;

    @InjectMocks
    private EmployeeServiceController controller;

    @Mock
    private EmployeeService employeeService;

    @Mock
    View mockView;

    MockMvc mockMvc;

    ObjectsFactory objectsFactory;

    @Before
    public void setUp() throws Exception {
        controller = new EmployeeServiceController();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
        objectsFactory = new ObjectsFactory();
    }

    @Test
    public void testServiceEmployees() throws Exception {
        Employee mrBlack = objectsFactory.makeEmployeeBlack();
        Employee mrWhite = objectsFactory.makeEmployeeWhite();
        List<Employee> expectedEmployees = Arrays.asList(mrBlack, mrWhite);
        when(employeeService.getAllEmployees()).thenReturn(expectedEmployees);
        mockMvc.perform(get(MAPPED_PATH + "/employees"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEES, hasSize(2)))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEES, hasItem(
                        allOf(
                                hasProperty("name", is("Steven")),
                                hasProperty("surname", is("Black")),
                                hasProperty("salary", is(35000F)),
                                hasProperty("job", is(new Job(Position.WAITER)))
                        )
                )))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEES, hasItem(
                        allOf(
                                hasProperty("name", is("John")),
                                hasProperty("surname", is("White")),
                                hasProperty("salary", is(75000F)),
                                hasProperty("job", is(new Job(Position.MANAGER)))
                        )
                )));
        verify(employeeService, times(1)).getAllEmployees();
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testGetEmployeeToAdd() throws Exception {
        mockMvc.perform(get(MAPPED_PATH + "/add"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VALIDATE, new EmployeeValidate()))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, new EmployeeView()))
                .andExpect(view().name(EmployeeServiceController.PATH_ADD));
    }

    @Test
    public void testAddEmployeeIsNotValid() throws Exception {
        EmployeeValidate employeeValidate = new EmployeeValidate();
        EmployeeView employeeView = new EmployeeView();
        mockMvc.perform(post(MAPPED_PATH + "/add")
                .sessionAttr(EmployeeServiceController.EMPLOYEE_VIEW, employeeView)
                .sessionAttr(EmployeeServiceController.EMPLOYEE_VALIDATE, employeeValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(EmployeeServiceController.PATH_ADD))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("id", is(0))))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("name", nullValue())))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("surname", nullValue())))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("salary", is(0F))))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("position", nullValue())))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("birthday", nullValue())));
        verifyZeroInteractions(employeeService);
    }

    @Test
    public void testAddEmployeeIsValid() throws Exception {
        EmployeeValidate employeeValidate = new EmployeeValidate();
        Employee mrWhite = objectsFactory.makeEmployeeWhite();
        Employee mrBlack = objectsFactory.makeEmployeeBlack();
        EmployeeView mrBlackView = new EmployeeView(mrBlack);
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(mrWhite, mrBlack));
        mockMvc.perform(post(MAPPED_PATH + "/add")
                .sessionAttr(EmployeeServiceController.EMPLOYEE_VIEW, mrBlackView)
                .requestAttr(EmployeeServiceController.EMPLOYEE_VALIDATE, employeeValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(EmployeeServiceController.PATH_EMPLOYEES))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEES, hasSize(2)))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEES, hasItem(
                        allOf(
                                hasProperty("name", is("Steven")),
                                hasProperty("surname", is("Black")),
                                hasProperty("salary", is(35000F)),
                                hasProperty("job", is(new Job(Position.WAITER)))
                        )
                )))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEES, hasItem(
                        allOf(
                                hasProperty("name", is("John")),
                                hasProperty("surname", is("White")),
                                hasProperty("salary", is(75000F)),
                                hasProperty("job", is(new Job(Position.MANAGER)))
                        )
                )));
        verify(employeeService).add(mrBlack);
        verify(employeeService).getAllEmployees();
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testEditEmployeeById() throws Exception {
        EmployeeValidate employeeValidate = new EmployeeValidate();
        Employee mrBlack = objectsFactory.makeEmployeeBlack();
        mrBlack.setId(1);
        EmployeeView employeeView = new EmployeeView(mrBlack);
        when(employeeService.getEmployeeById(1)).thenReturn(mrBlack);
        mockMvc.perform(get(MAPPED_PATH + "/edit")
                        .param("id", "1")
                .requestAttr(EmployeeServiceController.EMPLOYEE_VIEW, employeeView)
                .requestAttr(EmployeeServiceController.EMPLOYEE_VALIDATE, employeeValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(EmployeeServiceController.PATH_EDIT))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("id", is(1))))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("name", is("Steven"))))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("surname", is("Black"))))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("salary", is(35000F))))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty(
                        "position", is(new Job(Position.WAITER).toString().toLowerCase()))))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("birthday", is(new GregorianCalendar(1998, 5, 20).getTime()))));
        verify(employeeService).getEmployeeById(1);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testEditEmployeeByName() throws Exception {
        EmployeeValidate employeeValidate = new EmployeeValidate();
        Employee mrBlack = objectsFactory.makeEmployeeBlack();
        mrBlack.setId(1);
        EmployeeView employeeView = new EmployeeView(mrBlack);
        when(employeeService.getEmployeeByName("Steven", "Black")).thenReturn(mrBlack);
        mockMvc.perform(get(MAPPED_PATH + "/editByName")
                .param("name", "Steven")
                .param("surname", "Black")
                .requestAttr(EmployeeServiceController.EMPLOYEE_VIEW, employeeView)
                .requestAttr(EmployeeServiceController.EMPLOYEE_VALIDATE, employeeValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(EmployeeServiceController.PATH_EDIT))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("id", is(1))))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("name", is("Steven"))))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("surname", is("Black"))))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("salary", is(35000F))))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty(
                        "position", is(new Job(Position.WAITER).toString().toLowerCase()))))
                .andExpect(model().attribute(EmployeeServiceController.EMPLOYEE_VIEW, hasProperty("birthday", is(new GregorianCalendar(1998, 5, 20).getTime()))));
        verify(employeeService).getEmployeeByName("Steven", "Black");
        verifyNoMoreInteractions(employeeService);
    }
}