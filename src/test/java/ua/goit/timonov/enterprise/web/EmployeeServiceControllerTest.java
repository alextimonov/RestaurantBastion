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
import static ua.goit.timonov.enterprise.web.EmployeeServiceController.*;

/**
 * Testing class for EmployeeServiceController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/web-context.xml"})
@WebAppConfiguration
public class EmployeeServiceControllerTest {
    public static final String MAPPED_PATH = "/service/employee";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String SALARY = "salary";
    public static final String JOB = "job";
    public static final String ID = "id";
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
        Employee mrRed = objectsFactory.makeEmployeeRed();
        List<Employee> expectedEmployees = Arrays.asList(mrBlack, mrWhite, mrRed);
        when(employeeService.getAllEmployees()).thenReturn(expectedEmployees);
        mockMvc.perform(get(MAPPED_PATH + "/employees"))
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
    public void testGetEmployeeToAdd() throws Exception {
        mockMvc.perform(get(MAPPED_PATH + "/add"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(EMPLOYEE_VALIDATE, new EmployeeValidate()))
                .andExpect(model().attribute(EMPLOYEE_VIEW, new EmployeeView()))
                .andExpect(view().name(EmployeeServiceController.PATH_ADD));
    }

    @Test
    public void testAddEmployeeIsNotValid() throws Exception {
        EmployeeValidate employeeValidate = new EmployeeValidate();
        EmployeeView employeeView = new EmployeeView();
        mockMvc.perform(post(MAPPED_PATH + "/add")
                .sessionAttr(EMPLOYEE_VIEW, employeeView)
                .sessionAttr(EMPLOYEE_VALIDATE, employeeValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(EmployeeServiceController.PATH_ADD))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(ID, is(0))))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(NAME, nullValue())))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(SURNAME, nullValue())))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(SALARY, is(0F))))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty("position", nullValue())))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty("birthday", nullValue())));
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
                .sessionAttr(EMPLOYEE_VIEW, mrBlackView)
                .requestAttr(EMPLOYEE_VALIDATE, employeeValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_EMPLOYEES))
                .andExpect(model().attribute(EMPLOYEES, hasSize(2)))
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
                .param(ID, "1")
                .requestAttr(EMPLOYEE_VIEW, employeeView)
                .requestAttr(EMPLOYEE_VALIDATE, employeeValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_EDIT))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(ID, is(1))))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(NAME, is("Steven"))))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(SURNAME, is("Black"))))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(SALARY, is(35000F))))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(
                        "position", is(new Job(Position.WAITER).toString().toLowerCase()))))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty("birthday", is(new GregorianCalendar(1998, 5, 20).getTime()))));
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
                .param(NAME, "Steven")
                .param(SURNAME, "Black")
                .requestAttr(EMPLOYEE_VIEW, employeeView)
                .requestAttr(EMPLOYEE_VALIDATE, employeeValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_EDIT))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(ID, is(1))))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(NAME, is("Steven"))))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(SURNAME, is("Black"))))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(SALARY, is(35000F))))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty(
                        "position", is(new Job(Position.WAITER).toString().toLowerCase()))))
                .andExpect(model().attribute(EMPLOYEE_VIEW, hasProperty("birthday", is(new GregorianCalendar(1998, 5, 20).getTime()))));
        verify(employeeService).getEmployeeByName("Steven", "Black");
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testSaveEditDish() throws Exception {
        Employee mrBlack = objectsFactory.makeEmployeeBlack();
        Employee mrWhite = objectsFactory.makeEmployeeBlack();
        mrBlack.setId(1);
        EmployeeView employeeView = new EmployeeView(mrBlack);
        EmployeeValidate employeeValidate = new EmployeeValidate();
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(mrWhite, mrBlack));
        mockMvc.perform(post(MAPPED_PATH + "/edit")
                .param(ID, "1")
                .sessionAttr(EMPLOYEE_VIEW, employeeView)
                .sessionAttr(EMPLOYEE_VALIDATE, employeeValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_EMPLOYEES))
                .andExpect(model().attribute(EMPLOYEES, hasItem(
                allOf(
                        hasProperty(NAME, is("Steven")),
                        hasProperty(SURNAME, is("Black")),
                        hasProperty(SALARY, is(35000F)),
                        hasProperty(JOB, is(new Job(Position.WAITER)))
                    )
                )));
        verify(employeeService).update(mrBlack);
        verify(employeeService).getAllEmployees();
        verifyNoMoreInteractions(employeeService);
    }

//    @Test
//    public void testSetEmployeeService() throws Exception {
//
//    }
//
//    @Test
//    public void testAddEmployee() throws Exception {
//
//    }


}