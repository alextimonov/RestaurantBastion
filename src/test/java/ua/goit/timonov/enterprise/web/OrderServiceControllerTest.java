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
import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.Employee;
import ua.goit.timonov.enterprise.model.Order;
import ua.goit.timonov.enterprise.service.EmployeeService;
import ua.goit.timonov.enterprise.service.OrderService;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ua.goit.timonov.enterprise.web.OrderServiceController.ITEMS;
import static ua.goit.timonov.enterprise.web.OrderServiceController.PATH_DISHES;
import static ua.goit.timonov.enterprise.web.OrderServiceController.PATH_ORDERS;

/**
 * Testing class for OrderServiceController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/web-context.xml"})
@WebAppConfiguration
public class OrderServiceControllerTest {
    public static final String MAPPED_PATH = "/service/order";
    public static final String ID = "id";
    public static final String WAITER = "waiter";
    public static final String TABLE_NUMBER = "tableNumber";
    public static final String CLOSED = "closed";
    public static final String DATE = "date";
    public static final String ORDER = "order";
    public static final String DISHES = "dishes";

    @Autowired
    WebApplicationContext wac;

    @InjectMocks
    private OrderServiceController controller;

    @Mock
    private OrderService orderService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    View mockView;

    MockMvc mockMvc;
    ObjectsFactory objectsFactory;

    private Employee mrBlack;
    private Employee mrWhite;
    private Employee mrRed;

    private Date date1;
    private Date date2;

    private Order orderFirst;
    private Order orderSecond;
    private Order orderThird;
    private Order orderFourth;

    @Before
    public void setUp() throws Exception {
        controller = new OrderServiceController();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
        objectsFactory = new ObjectsFactory();

        mrBlack = objectsFactory.makeEmployeeBlack();
        mrWhite = objectsFactory.makeEmployeeWhite();
        mrRed = objectsFactory.makeEmployeeRed();

        Dish soup = objectsFactory.makeDishRiceSoup();
        Dish plov = objectsFactory.makeDishNewPlov();
        Dish salad = objectsFactory.makeDishSalad();
        Dish salmon = objectsFactory.makeDishSalmon();

        date1 = new GregorianCalendar(2016, 9, 1).getTime();
        date2 = new GregorianCalendar(2016, 9, 2).getTime();

        orderFirst = new Order(mrBlack, 1, date1, true);
        orderSecond = new Order(mrWhite, 2, date1, false);
        orderThird = new Order(mrRed, 1, date2, true);
        orderFourth = new Order(mrBlack, 3, date2, false);
        orderFirst.setId(1);
        orderSecond.setId(2);
        orderThird.setId(3);
        orderFourth.setId(4);
    }

    private List<Order> createAllOrders() {
        List<Order> createdOrders = new ArrayList<>();
        createdOrders.add(orderFirst);
        createdOrders.add(orderSecond);
        createdOrders.add(orderThird);
        createdOrders.add(orderFourth);
        return createdOrders;
    }

    @Test
    public void testGetAllOrders() throws Exception {
        List<Order> expectedOrders = createAllOrders();
        when(orderService.getAllOrders()).thenReturn(expectedOrders);
        mockMvc.perform(get(MAPPED_PATH + "/orders"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(ITEMS, hasSize(4)))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(ID, is(1)),
                                hasProperty(WAITER, is(mrBlack)),
                                hasProperty(TABLE_NUMBER, is(1)),
                                hasProperty(DATE, is(date1)),
                                hasProperty(CLOSED, is(true))
                        )
                )))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(ID, is(2)),
                                hasProperty(WAITER, is(mrWhite)),
                                hasProperty(TABLE_NUMBER, is(2)),
                                hasProperty(DATE, is(date1)),
                                hasProperty(CLOSED, is(false))
                        )
                )))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(ID, is(3)),
                                hasProperty(WAITER, is(mrRed)),
                                hasProperty(TABLE_NUMBER, is(1)),
                                hasProperty(DATE, is(date2)),
                                hasProperty(CLOSED, is(true))
                        )
                )))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(ID, is(4)),
                                hasProperty(WAITER, is(mrBlack)),
                                hasProperty(TABLE_NUMBER, is(3)),
                                hasProperty(DATE, is(date2)),
                                hasProperty(CLOSED, is(false))
                        )
                )));
        verify(orderService).getAllOrders();
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void testGetOrderDishes() throws Exception {
        Order order = createAllOrders().get(0);
        when(orderService.getOrder(1)).thenReturn(order);
        mockMvc.perform(get(MAPPED_PATH + "/dishes")
                .param("orderId", "1")
                .param(ID, "1")
                .param(WAITER, "mrBlack")
                .param(TABLE_NUMBER, "1")
                .param(DATE, "date1")
                .param(CLOSED, "true")
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DISHES))
                .andExpect(model().attribute(ORDER, hasProperty(ID, is(1))))
                .andExpect(model().attribute(ORDER, hasProperty(WAITER, is(mrBlack))))
                .andExpect(model().attribute(ORDER, hasProperty(TABLE_NUMBER, is(1))))
                .andExpect(model().attribute(ORDER, hasProperty(DATE, is(date1))))
                .andExpect(model().attribute(ORDER, hasProperty(CLOSED, is(true))))
        ;
        verify(orderService).getOrder(1);
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void testFilterByDate() throws Exception {
        List<Order> expectedOrders = Arrays.asList(orderFirst, orderSecond);
        Date date = new GregorianCalendar(2016, 8, 1, 0, 0, 0).getTime();
        when(orderService.filterByDate(date)).thenReturn(expectedOrders);
        mockMvc.perform(get(MAPPED_PATH + "/filterByDate")
                .param("date", "2016-09-01T00:00:00.000Z")
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_ORDERS))
                .andExpect(model().attribute(ITEMS, hasSize(2)))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(ID, is(1)),
                                hasProperty(WAITER, is(mrBlack)),
                                hasProperty(TABLE_NUMBER, is(1)),
                                hasProperty(DATE, is(date1)),
                                hasProperty(CLOSED, is(true))
                        )
                )))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(ID, is(2)),
                                hasProperty(WAITER, is(mrWhite)),
                                hasProperty(TABLE_NUMBER, is(2)),
                                hasProperty(DATE, is(date1)),
                                hasProperty(CLOSED, is(false))
                        )
                )));
        verify(orderService).filterByDate(date);
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void testFilterByName() throws Exception {
        List<Order> expectedOrders = Arrays.asList(orderFirst, orderFourth);
        when(employeeService.getEmployeeByName(mrBlack.getName())).thenReturn(mrBlack);
        when(orderService.filterByWaiter(mrBlack)).thenReturn(expectedOrders);
        mockMvc.perform(get(MAPPED_PATH + "/filterByWaiter")
                .param("waiterName", "Steven")
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_ORDERS))
                .andExpect(model().attribute(ITEMS, hasSize(2)))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(ID, is(1)),
                                hasProperty(WAITER, is(mrBlack)),
                                hasProperty(TABLE_NUMBER, is(1)),
                                hasProperty(DATE, is(date1)),
                                hasProperty(CLOSED, is(true))
                        )
                )))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(ID, is(4)),
                                hasProperty(WAITER, is(mrBlack)),
                                hasProperty(TABLE_NUMBER, is(3)),
                                hasProperty(DATE, is(date2)),
                                hasProperty(CLOSED, is(false))
                        )
                )));
        verify(orderService).filterByWaiter(mrBlack);
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void testFilterByTableNumber() throws Exception {
        List<Order> expectedOrders = Arrays.asList(orderFirst, orderThird);
        when(orderService.filterByTableNumber(1)).thenReturn(expectedOrders);
        mockMvc.perform(get(MAPPED_PATH + "/filterByTableNumber")
                .param("tableNumber", "1")
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_ORDERS))
                .andExpect(model().attribute(ITEMS, hasSize(2)))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(ID, is(1)),
                                hasProperty(WAITER, is(mrBlack)),
                                hasProperty(TABLE_NUMBER, is(1)),
                                hasProperty(DATE, is(date1)),
                                hasProperty(CLOSED, is(true))
                        )
                )))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(ID, is(3)),
                                hasProperty(WAITER, is(mrRed)),
                                hasProperty(TABLE_NUMBER, is(1)),
                                hasProperty(DATE, is(date2)),
                                hasProperty(CLOSED, is(true))
                        )
                )));
        verify(orderService).filterByTableNumber(1);
        verifyNoMoreInteractions(orderService);
    }
}