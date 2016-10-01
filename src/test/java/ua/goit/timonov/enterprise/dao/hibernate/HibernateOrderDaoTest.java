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
import ua.goit.timonov.enterprise.dao.DishDAO;
import ua.goit.timonov.enterprise.dao.EmployeeDAO;
import ua.goit.timonov.enterprise.dao.OrderDAO;
import ua.goit.timonov.enterprise.exceptions.ForbidToAddException;
import ua.goit.timonov.enterprise.exceptions.ForbidToDeleteException;
import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.Employee;
import ua.goit.timonov.enterprise.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testing class for HibernateOrderDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml"})
public class HibernateOrderDaoTest {
    private DbController dbController;
    private OrderDAO orderDAO;
    private EmployeeDAO employeeDAO;
    private DishDAO dishDAO;

    private Employee mrBlack;
    private Employee mrWhite;
    private Employee mrRed;

    private Dish soup;
    private Dish plov;
    private Dish salad;
    private Dish salmon;

    ObjectsFactory factory = new ObjectsFactory();

    @Autowired(required = true)
    public void setDbController(DbController dbController) {
        this.dbController = dbController;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Autowired
    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    @Before
    public void setUp() throws Exception {
        dbController.createAllTables();
        dbController.fillTableJobs();

        mrBlack = factory.makeEmployeeBlack();
        mrWhite = factory.makeEmployeeWhite();
        mrRed = factory.makeEmployeeRed();
        employeeDAO.add(mrBlack);
        employeeDAO.add(mrWhite);
        employeeDAO.add(mrRed);

        soup = factory.makeDishRiceSoup();
        plov = factory.makeDishNewPlov();
        salad = factory.makeDishSalad();
        salmon = factory.makeDishSalmon();

        dishDAO.add(soup);
        dishDAO.add(plov);
        dishDAO.add(salad);
        dishDAO.add(salmon);
    }

    @After
    @Transactional
    public void tearDown() throws Exception {
        dbController.deleteAllData();
        dbController.dropAllTables();
    }

    @Test
    public void testGetAllOrders() throws Exception {
        fillTableOrder();
        List<Order> createdOrders = createAllOrders();
        List<Order> gotFromDbOrders = orderDAO.getAllOrders();
        assertEqualsOrderLists(createdOrders, gotFromDbOrders);
    }

    @Test
    public void testGetOpenOrders() throws Exception {
        fillTableOrder();
        List<Order> createdOrders = createOpenOrders();
        List<Order> gotFromDbOrders = orderDAO.getOpenOrders();
        assertEqualsOrderLists(createdOrders, gotFromDbOrders);
    }

    @Test
    public void testGetClosedOrders() throws Exception {
        fillTableOrder();
        List<Order> createdOrders = createClosedOrders();
        List<Order> gotFromDbOrders = orderDAO.getClosedOrders();
        assertEqualsOrderLists(createdOrders, gotFromDbOrders);
    }

    @Test
    public void testGetOrdersByTableNumber() throws Exception {
        fillTableOrder();
        List<Order> createdOrders = createClosedOrders();
        List<Order> gotFromDbOrders = orderDAO.getOrdersByTableNumber(1);
        assertEqualsOrderLists(createdOrders, gotFromDbOrders);
    }

    @Test
    public void testGetOrdersByWaiter() throws Exception {
        fillTableOrder();
        List<Order> createdOrders = createOrdersMrBlack();
        List<Order> gotFromDbOrders = orderDAO.getOrdersByWaiter(mrBlack);
        assertEqualsOrderLists(createdOrders, gotFromDbOrders);
    }

    @Test
    public void testGetOrdersByDate() throws Exception {
        fillTableOrder();
        List<Order> createdOrders = createOrdersDate1();
        Date date1 = new GregorianCalendar(2016, 9, 1).getTime();
        List<Order> gotFromDbOrders = orderDAO.getOrdersByDate(date1);
        assertEqualsOrderLists(createdOrders, gotFromDbOrders);
    }

    @Test
    public void testGetOrder() throws Exception {
        fillTableOrder();
        Order newOrder = makeNewOrder();
        orderDAO.add(newOrder);
        Order foundOrder = orderDAO.getOrder(newOrder.getId()); //54
        assertEquals(newOrder, foundOrder);
    }

    @Test
    public void testGetOrderAbnormal() throws Exception {
        fillTableOrder();
        Order order = orderDAO.getOrder(1000);
        assertEquals(null, order);
    }

    @Test
    public void testAdd() throws Exception {
        fillTableOrder();
        List<Order> createdOrders = createAllOrders();

        Order newOrder = makeNewOrder();
        createdOrders.add(newOrder);
        orderDAO.add(newOrder);

        List<Order> gotFromDbOrders = orderDAO.getAllOrders();
        assertEqualsOrderLists(createdOrders, gotFromDbOrders);
    }

    @Test
    public void testDelete() throws Exception {
        fillTableOrder();
        Order newOrder = makeNewOrder();
        List<Order> listBeforeDelete = orderDAO.getAllOrders();
        orderDAO.add(newOrder);
        orderDAO.delete(newOrder.getId()); //45
        List<Order> listAfterDelete = orderDAO.getAllOrders();
        assertEqualsOrderLists(listBeforeDelete, listAfterDelete);
    }

    @Test
    public void testAddDish() throws Exception {
        fillTableOrder();
        Order newOrder = makeNewOrder();
        orderDAO.add(newOrder);
        orderDAO.addDish(newOrder.getId(), soup); //13
        Order orderFromDb = orderDAO.getOrder(newOrder.getId()); //13
        Dish dishFromDb = orderFromDb.getDishes().get(0);
        assertEquals(soup, dishFromDb);
    }

    @Test(expected = ForbidToAddException.class)
    public void testAddDishAbnormal() throws Exception {
        fillTableOrder();
        Order newOrder = makeNewOrder();
        newOrder.setClosed(true);
        orderDAO.add(newOrder);
        orderDAO.addDish(newOrder.getId(), soup);
    }

    @Test
    public void testDeleteDish() throws Exception {
        fillTableOrder();
        Order newOrder = makeNewOrder();
        orderDAO.add(newOrder);
        int id = newOrder.getId();
        orderDAO.addDish(id, soup); //40
        orderDAO.addDish(id, salad);
        orderDAO.deleteDish(id, soup);
        Order orderFromDb = orderDAO.getOrder(id);
        Dish dishFromDb = orderFromDb.getDishes().get(0);
        assertEquals(salad, dishFromDb);
    }

    @Test(expected = ForbidToDeleteException.class)
    public void testDeleteDishAbnormal() throws Exception {
        fillTableOrder();
        Order newOrder = makeNewOrder();
        newOrder.getDishes().add(soup);
        newOrder.setClosed(true);
        orderDAO.add(newOrder);
        int id = newOrder.getId();
        orderDAO.deleteDish(id, soup); //40
    }

    @Test
    public void testSetClosed() throws Exception {
        fillTableOrder();
        Order newOrder = makeNewOrder();
        orderDAO.add(newOrder);
        newOrder.setClosed(true);
        orderDAO.setClosed(newOrder.getId()); //35
        Order setClosed = orderDAO.getOrder(newOrder.getId());
        assertEquals(newOrder, setClosed);
    }

    @Transactional
    private void fillTableOrder() {
        Date date1 = new GregorianCalendar(2016, 9, 1).getTime();
        Date date2 = new GregorianCalendar(2016, 9, 2).getTime();

        Order orderFirst = new Order(mrBlack, 1, date1, true);
        Order orderSecond = new Order(mrWhite, 2, date1, false);
        Order orderThird = new Order(mrRed, 1, date2, true);
        Order orderFourth = new Order(mrBlack, 3, date2, false);

        orderDAO.add(orderFirst);
        orderDAO.add(orderSecond);
        orderDAO.add(orderThird);
        orderDAO.add(orderFourth);
    }

    @Transactional
    private List<Order> createAllOrders() {
        Date date1 = new GregorianCalendar(2016, 9, 1).getTime();
        Date date2 = new GregorianCalendar(2016, 9, 2).getTime();

        Order orderFirst = new Order(mrBlack, 1, date1, true);
        Order orderSecond = new Order(mrWhite, 2, date1, false);
        Order orderThird = new Order(mrRed, 1, date2, true);
        Order orderFourth = new Order(mrBlack, 3, date2, false);

        List<Order> createdOrders = new ArrayList<>();
        createdOrders.add(orderFirst);
        createdOrders.add(orderSecond);
        createdOrders.add(orderThird);
        createdOrders.add(orderFourth);
        return createdOrders;
    }

    @Transactional
    private List<Order> createOpenOrders() {
        Date date1 = new GregorianCalendar(2016, 9, 1).getTime();
        Date date2 = new GregorianCalendar(2016, 9, 2).getTime();

        Order orderSecond = new Order(mrWhite, 2, date1, false);
        Order orderFourth = new Order(mrBlack, 3, date2, false);

        List<Order> createdOrders = new ArrayList<>();
        createdOrders.add(orderSecond);
        createdOrders.add(orderFourth);
        return createdOrders;
    }

    @Transactional
    private List<Order> createClosedOrders() {
        Date date1 = new GregorianCalendar(2016, 9, 1).getTime();
        Date date2 = new GregorianCalendar(2016, 9, 2).getTime();

        Order orderFirst = new Order(mrBlack, 1, date1, true);
        Order orderThird = new Order(mrRed, 1, date2, true);

        List<Order> createdOrders = new ArrayList<>();
        createdOrders.add(orderFirst);
        createdOrders.add(orderThird);
        return createdOrders;
    }

    private List<Order> createOrdersMrBlack() {
        Date date1 = new GregorianCalendar(2016, 9, 1).getTime();
        Date date2 = new GregorianCalendar(2016, 9, 2).getTime();
        Order orderFirst = new Order(mrBlack, 1, date1, true);
        Order orderFourth = new Order(mrBlack, 3, date2, false);

        List<Order> createdOrders = new ArrayList<>();
        createdOrders.add(orderFirst);
        createdOrders.add(orderFourth);
        return createdOrders;
    }

    private List<Order> createOrdersDate1() {
        Date date1 = new GregorianCalendar(2016, 9, 1).getTime();
        Order orderFirst = new Order(mrBlack, 1, date1, true);
        Order orderSecond = new Order(mrWhite, 2, date1, false);

        List<Order> createdOrders = new ArrayList<>();
        createdOrders.add(orderFirst);
        createdOrders.add(orderSecond);
        return createdOrders;
    }

    private Order makeNewOrder() {
        Date date = new GregorianCalendar(2016, 9, 3).getTime();
        return new Order(mrRed, 5, date, false);
    }

    private void assertNotEqualsOrderLists(List<Order> createdOrders, List<Order> gotFromDbOrders) {
        createdOrders.stream().filter(gotFromDbOrders::contains).forEach(gotFromDbOrders::remove);
        assertNotEquals(new ArrayList<Order>(), gotFromDbOrders);
    }

    private void assertEqualsOrderLists(List<Order> createdOrders, List<Order> gotFromDbOrders) {
        createdOrders.stream().filter(gotFromDbOrders::contains).forEach(gotFromDbOrders::remove);
        assertEquals(new ArrayList<Order>(), gotFromDbOrders);
    }


}