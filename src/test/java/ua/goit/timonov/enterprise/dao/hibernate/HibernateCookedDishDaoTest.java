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
import ua.goit.timonov.enterprise.dao.CookedDishDAO;
import ua.goit.timonov.enterprise.dao.DishDAO;
import ua.goit.timonov.enterprise.dao.EmployeeDAO;
import ua.goit.timonov.enterprise.dao.OrderDAO;
import ua.goit.timonov.enterprise.exceptions.ForbidToAddException;
import ua.goit.timonov.enterprise.model.CookedDish;
import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.Employee;
import ua.goit.timonov.enterprise.model.Order;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for HibernateCookedDishDao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/application-context.xml",
        "file:src/main/webapp/WEB-INF/hibernate-context.xml"})
public class HibernateCookedDishDaoTest {
    private DbController dbController;
    private OrderDAO orderDAO;
    private DishDAO dishDAO;
    private EmployeeDAO employeeDAO;
    private CookedDishDAO cookedDishDAO;

    ObjectsFactory factory = new ObjectsFactory();

    private Employee mrBlack;
    private Employee mrWhite;
    private Employee mrRed;

    private Dish riceSoup;
    private Dish salad;
    private Dish salmon;

    Date date1 = new GregorianCalendar(2015, 10, 1).getTime();
    Date date2 = new GregorianCalendar(2015, 10, 2).getTime();

    private Order orderFirst;
    private Order orderSecond;
    private Order orderThird;


    @Autowired
    public void setDbController(DbController dbController) {
        this.dbController = dbController;
    }

    @Autowired
    public void setCookedDishDAO(CookedDishDAO cookedDishDAO) {
        this.cookedDishDAO = cookedDishDAO;
    }

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Autowired
    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Before
    public void setUp() throws Exception {
        dbController.createAllTables();
        dbController.fillTableJobs();

        riceSoup = factory.makeDishRiceSoup();
        salad = factory.makeDishSalad();
        salmon = factory.makeDishSalmon();
        dishDAO.add(riceSoup);
        dishDAO.add(salad);
        dishDAO.add(salmon);

        mrBlack = factory.makeEmployeeBlack();
        mrWhite = factory.makeEmployeeWhite();
        mrRed = factory.makeEmployeeRed();
        employeeDAO.add(mrBlack);
        employeeDAO.add(mrWhite);
        employeeDAO.add(mrRed);

        orderFirst = new Order(mrRed, 1, date1, true);
        orderFirst.setDishes(Arrays.asList(salad, salmon));
        orderSecond = new Order(mrRed, 2, date2, true);
        orderSecond.setDishes(Arrays.asList(riceSoup, salad));
        orderThird = new Order(mrWhite, 3, date2, false);
        orderThird.setDishes(Arrays.asList(salmon, riceSoup));
        orderDAO.add(orderFirst);
        orderDAO.add(orderSecond);
        orderDAO.add(orderThird);
    }

    @After
    @Transactional
    public void tearDown() throws Exception {
        dbController.deleteAllData();
        dbController.dropAllTables();
    }

    private void assertEqualsCookedDishLists(List<CookedDish> created, List<CookedDish> gotFromDb) {
        created.stream().filter(gotFromDb::contains).forEach(gotFromDb::remove);
        assertEquals(new ArrayList<CookedDish>(), gotFromDb);
    }

    @Transactional
    private List<CookedDish> createCookedDishesFromClosedOrders() {
        CookedDish cookedDishSalad = new CookedDish(orderFirst, salad, mrBlack);
        CookedDish cookedDishSalmon = new CookedDish(orderFirst, salmon, mrWhite);
        CookedDish cookedDishSoup = new CookedDish(orderSecond, riceSoup, mrBlack);

        cookedDishDAO.add(cookedDishSalad);
        cookedDishDAO.add(cookedDishSalmon);
        cookedDishDAO.add(cookedDishSoup);
        return new ArrayList<>(Arrays.asList(cookedDishSalad, cookedDishSalmon, cookedDishSoup));
    }

    @Transactional
    private List<CookedDish> createCookedDishesFromAllOrders() {
        CookedDish cookedDishSalad = new CookedDish(orderFirst, salad, mrBlack);
        CookedDish cookedDishSalmon = new CookedDish(orderFirst, salmon, mrWhite);
        CookedDish cookedDishSoup = new CookedDish(orderThird, salmon, mrBlack);
        CookedDish cookedDishSalad2 = new CookedDish(orderThird, riceSoup, mrWhite);

        cookedDishDAO.add(cookedDishSalad);
        cookedDishDAO.add(cookedDishSalmon);
        cookedDishDAO.add(cookedDishSoup);
        cookedDishDAO.add(cookedDishSalad2);
        return new ArrayList<>(Arrays.asList(cookedDishSalad, cookedDishSalmon, cookedDishSoup, cookedDishSalad2));
    }

    @Test
    public void testGetAll() throws Exception {
        List<CookedDish> createdCookedDishes = createCookedDishesFromClosedOrders();
        List<CookedDish> gotFromDbCookedDishes = cookedDishDAO.getAll();
        assertEqualsCookedDishLists(createdCookedDishes, gotFromDbCookedDishes);
    }

    @Test
    public void testAddCookedDishNormal() throws Exception {
        List<CookedDish> createdCookedDishes = createCookedDishesFromClosedOrders();
        CookedDish newCookedDish = new CookedDish(orderSecond, salad, mrWhite);
        createdCookedDishes.add(newCookedDish);
        cookedDishDAO.add(newCookedDish);
        List<CookedDish> gotFromDbDishes = cookedDishDAO.getAll();
        assertEqualsCookedDishLists(createdCookedDishes, gotFromDbDishes);
    }

    @Test(expected = ForbidToAddException.class)
    public void testAddCookedDishAbnormal_1() throws Exception {
        createCookedDishesFromAllOrders();
        CookedDish newCookedDish = new CookedDish(orderThird, salad, mrWhite);
        cookedDishDAO.add(newCookedDish);
    }

    @Test(expected = ForbidToAddException.class)
    public void testAddCookedDishAbnormal_2() throws Exception {
        createCookedDishesFromClosedOrders();
        CookedDish newCookedDish = new CookedDish(orderSecond, salmon, mrWhite);
        cookedDishDAO.add(newCookedDish);
    }

    @Test(expected = ForbidToAddException.class)
    public void testAddCookedDishAbnormal_3() throws Exception {
        createCookedDishesFromClosedOrders();
        CookedDish newCookedDish = new CookedDish(orderFirst, salad, mrBlack);
        cookedDishDAO.add(newCookedDish);
    }

    @Test
    public void testAddByValues() throws Exception {
        List<CookedDish> createdCookedDishes = createCookedDishesFromClosedOrders();
        CookedDish newCookedDish = new CookedDish(orderSecond, salad, mrWhite);
        createdCookedDishes.add(newCookedDish);
        cookedDishDAO.add(orderSecond.getId(), salad.getName(), mrWhite.getId());
        List<CookedDish> gotFromDbDishes = cookedDishDAO.getAll();
        assertEqualsCookedDishLists(createdCookedDishes, gotFromDbDishes);
    }

    @Test(expected = ForbidToAddException.class)
    public void testAddByValuesAbnormal() throws Exception {
        createCookedDishesFromAllOrders();
        cookedDishDAO.add(3, "salad", 2);
    }
}