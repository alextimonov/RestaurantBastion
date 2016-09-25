package ua.goit.timonov.enterprise.module_6_2.dao.hibernate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.controllers.DbController;
import ua.goit.timonov.enterprise.module_6_2.dao.DishDAO;
import ua.goit.timonov.enterprise.module_6_2.dao.MenuDAO;
import ua.goit.timonov.enterprise.module_6_2.exceptions.ForbidToAddException;
import ua.goit.timonov.enterprise.module_6_2.exceptions.ForbidToDeleteException;
import ua.goit.timonov.enterprise.module_6_2.exceptions.NoItemInDbException;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 22.09.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml"})
public class HibernateMenuDaoTest {
    private DbController dbController;
    private DishDAO dishDAO;
    private MenuDAO menuDAO;

    private Dish riceSoup;
    private Dish newPlov;
    private Dish salad;
    private Dish salmon;

    ObjectsFactory factory = new ObjectsFactory();

    @Autowired(required = true)
    public void setDbController(DbController dbController) {
        this.dbController = dbController;
    }

    @Autowired
    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    @Autowired
    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    @Before
    public void setUp() throws Exception {
        dbController.dropAllTables();
        dbController.createAllTables();
        dbController.fillTableJobs();

        riceSoup = factory.makeDishRiceSoup();
        newPlov = factory.makeDishNewPlov();
        salad = factory.makeDishSalad();
        salmon = factory.makeDishSalmon();
        dishDAO.add(riceSoup);
        dishDAO.add(newPlov);
        dishDAO.add(salad);
        dishDAO.add(salmon);
    }

    @Test
    public void testGetAll() throws Exception {
        fillTableMenu();
        List<Menu> createdMenus = createMenus();
        List<Menu> gotFromDbMenus = menuDAO.getAll();
        assertEqualsMenuLists(createdMenus, gotFromDbMenus);
    }

    @Test
    public void testAdd() throws Exception {
        fillTableMenu();
        List<Menu> createdMenus = createMenus();

        Menu testMenu = makeTestMenu();
        createdMenus.add(testMenu);
        menuDAO.add(testMenu);
        List<Menu> gotFromDbMenus = menuDAO.getAll();
        assertEqualsMenuLists(createdMenus, gotFromDbMenus);
    }

    @Test
    public void testSearchByIdNormal() throws Exception {
        dbController.restoreAllData();
        Menu testMenu= makeTestMenu();
        menuDAO.add(testMenu);
        Menu foundMenu = menuDAO.search(13);
        assertEquals(testMenu, foundMenu);
    }

    @Test(expected = NoItemInDbException.class)
    public void testSearchByIdAbnormal() throws Exception {
        dbController.restoreAllData();
        Menu testMenu= makeTestMenu();
        menuDAO.add(testMenu);
        Menu foundMenu = menuDAO.search(100);
        assertEquals(testMenu, foundMenu);
    }

    @Test
    public void testSearchByNameNormal() throws Exception {
        dbController.restoreAllData();
        Menu testMenu= makeTestMenu();
        menuDAO.add(testMenu);
        Menu foundMenu = menuDAO.search(testMenu.getName());
        assertEquals(testMenu, foundMenu);
    }

    @Test(expected = NoItemInDbException.class)
    public void testSearchByNameAbnormal() throws Exception {
        fillTableMenu();
        Menu testMenu = makeTestMenu();
        menuDAO.add(testMenu);
        Menu foundMenu = menuDAO.search("wrong name");
        assertEquals(testMenu, foundMenu);
    }

    @Test
    public void testDeleteById() throws Exception {
        fillTableMenu();
        Menu testMenu = makeTestMenu();
        List<Menu> listBeforeDelete = menuDAO.getAll();
        menuDAO.add(testMenu);
        menuDAO.delete(testMenu.getId());
        List<Menu> listAfterDelete = menuDAO.getAll();
        assertEqualsMenuLists(listBeforeDelete, listAfterDelete);
    }

    @Test(expected = NoItemInDbException.class)
    public void testDeleteByIdAbnormal() throws Exception {
        fillTableMenu();
        Menu testMenu = makeTestMenu();
        List<Menu> listBeforeDelete = menuDAO.getAll();
        menuDAO.add(testMenu);
        menuDAO.delete(100);
        List<Menu> listAfterDelete = menuDAO.getAll();
        assertEqualsMenuLists(listBeforeDelete, listAfterDelete);
    }

    @Test
    public void testDeleteByNameNormal() throws Exception {
        fillTableMenu();
        Menu testMenu = makeTestMenu();
        List<Menu> listBeforeDelete = menuDAO.getAll();
        menuDAO.add(testMenu);
        menuDAO.delete(testMenu.getName());
        List<Menu> listAfterDelete = menuDAO.getAll();
        assertEqualsMenuLists(listBeforeDelete, listAfterDelete);
    }

    @Test(expected = NoItemInDbException.class)
    public void testDeleteByNameAbnormal() throws Exception {
        fillTableMenu();
        Menu testMenu = makeTestMenu();
        List<Menu> listBeforeDelete = menuDAO.getAll();
        menuDAO.add(testMenu);
        menuDAO.delete("wrong name");
        List<Menu> listAfterDelete = menuDAO.getAll();
        assertEqualsMenuLists(listBeforeDelete, listAfterDelete);
    }

    @Test
    public void testAddDishNormal() throws Exception {
        fillTableMenu();
        Menu testMenu = makeTestMenu();
        menuDAO.add(testMenu);

        menuDAO.addDish(testMenu, salad);
        Menu menuFromDb = menuDAO.search(testMenu.getName());
        assertEquals(testMenu, menuFromDb);
    }

    @Test(expected = ForbidToAddException.class)
    public void testAddDishAbnormal() throws Exception {
        fillTableMenu();
        Menu testMenu = makeTestMenu();
        menuDAO.add(testMenu);
        menuDAO.addDish(testMenu, newPlov);
    }

    @Test
    public void testDeleteDishNormal() throws Exception {
        fillTableMenu();
        Menu testMenu = makeTestMenu();
        menuDAO.add(testMenu);
        menuDAO.deleteDish(testMenu, newPlov);
        Menu menuFromDb = menuDAO.search(testMenu.getName());
        assertEquals(testMenu, menuFromDb);
    }

    @Test(expected = ForbidToDeleteException.class)
    public void testDeleteDishAbnormal() throws Exception {
        fillTableMenu();
        Menu testMenu = makeTestMenu();
        menuDAO.add(testMenu);
        menuDAO.deleteDish(testMenu, salad);
    }

    @Test
    public void testUpdate() throws Exception {
        fillTableMenu();
        Menu breakfast = menuDAO.search("Breakfast");
        breakfast.getDishes().add(newPlov);
        menuDAO.update(breakfast);
        Menu updatedMenu = menuDAO.search("Breakfast");
        assertEquals(breakfast, updatedMenu);
    }

    private Menu makeTestMenu() {
        List<Dish> dishes = new ArrayList<>();
        dishes.add(newPlov);
        dishes.add(salmon);
        Menu testMenu = new Menu("testMenu");
        testMenu.setDishes(dishes);
        return testMenu;
    }

    @Transactional
    private void fillTableMenu() throws FileNotFoundException {
        Menu breakfast = new Menu("Breakfast");
        breakfast.setDishes(Arrays.asList(salad, salmon));
        Menu lunch = new Menu("Lunch");
        lunch.setDishes(Arrays.asList(newPlov, riceSoup));
        Menu dinner = new Menu("Dinner");
        dinner.setDishes(Arrays.asList(newPlov, riceSoup, salmon));
        menuDAO.add(breakfast);
        menuDAO.add(lunch);
        menuDAO.add(dinner);
    }

    private List<Menu> createMenus() {
        Menu breakfast = new Menu("Breakfast");
        breakfast.setDishes(Arrays.asList(salad, salmon));
        Menu lunch = new Menu("Lunch");
        lunch.setDishes(Arrays.asList(newPlov, riceSoup));
        Menu dinner = new Menu("Dinner");
        dinner.setDishes(Arrays.asList(newPlov, riceSoup, salmon));
        return new ArrayList<>(Arrays.asList(breakfast, lunch, dinner));
    }

    private void assertEqualsMenuLists(List<Menu> createdMenus, List<Menu> gotFromDbMenus) {
        createdMenus.stream().filter(gotFromDbMenus::contains).forEach(gotFromDbMenus::remove);
        assertEquals(new ArrayList<Menu>(), gotFromDbMenus);
    }

}