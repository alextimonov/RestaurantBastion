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
import ua.goit.timonov.enterprise.module_6_2.exceptions.NoItemInDbException;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testing class for HDishDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml"})
public class HibernateDishDaoTest {
    private DbController dbController;
    private DishDAO dishDAO;
    private ObjectsFactory factory = new ObjectsFactory();

    @Autowired(required = true)
    public void setDbController(DbController dbController) {
        this.dbController = dbController;
    }

    @Autowired
    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    @Before
    @Transactional
    public void setUp() throws Exception {
        dbController.dropAllTables();
        dbController.createAllTables();
        dbController.fillTableJobs();
    }

    @Test
    @Transactional
    public void testGetAllNormal() throws Exception {
        List<Dish> createdDishes = fillTableDish();
        List<Dish> gotFromDbDishes = dishDAO.getAll();
        assertEqualsDishLists(createdDishes, gotFromDbDishes);
    }

    @Test
    @Transactional
    public void testGetAllAbnormal_1() throws Exception {
        List<Dish> createdDishes = fillTableDish();
        dishDAO.add(factory.makeDishSalmon());
        List<Dish> gotFromDbDishes = dishDAO.getAll();
        assertNotEqualsDishLists(createdDishes, gotFromDbDishes);
    }

    @Test
    @Transactional
    public void testGetAllAbnormal_2() throws Exception {
        List<Dish> createdDishes = new ArrayList<>();
        List<Dish> gotFromDbDishes = dishDAO.getAll();
        assertEqualsDishLists(createdDishes, gotFromDbDishes);
    }

    @Test
    @Transactional
    public void testGetAllAbNormal_3() throws Exception {
        dbController.deleteAllData();
        List<Dish> createdDishes = new ArrayList<>();
        List<Dish> gotFromDbDishes = dishDAO.getAll();
        assertEqualsDishLists(createdDishes, gotFromDbDishes);
    }

    @Test
    @Transactional
    public void testAddNormal() throws Exception {
        List<Dish> createdDishes = fillTableDish();

        Dish salmon = factory.makeDishSalmon();
        createdDishes.add(salmon);
        dishDAO.add(salmon);
        List<Dish> gotFromDbDishes = dishDAO.getAll();
        assertEqualsDishLists(createdDishes, gotFromDbDishes);

        Dish friedEggs = factory.makeDishFriedEggs();
        createdDishes.add(friedEggs);
        dishDAO.add(friedEggs);
        gotFromDbDishes = dishDAO.getAll();
        assertEqualsDishLists(createdDishes, gotFromDbDishes);
    }

    @Test
    @Transactional
    public void testAddAbnormal() throws Exception {
        List<Dish> createdDishes = fillTableDish();

        Dish salmon = factory.makeDishSalmon();
        createdDishes.add(salmon);
        dishDAO.add(salmon);
        List<Dish> gotFromDbDishs = dishDAO.getAll();
        assertEqualsDishLists(createdDishes, gotFromDbDishs);

        Dish friedEggs = factory.makeDishFriedEggs();
        createdDishes.add(salmon);
        dishDAO.add(friedEggs);
        gotFromDbDishs = dishDAO.getAll();
        assertNotEqualsDishLists(createdDishes, gotFromDbDishs);
    }

    @Test
    @Transactional
    public void testSearchByIdNormal() throws Exception {
        dbController.restoreAllData();
        Dish salad = factory.makeDishSalad();
        dishDAO.add(salad);

        Dish foundDish = dishDAO.search(9);
        assertEquals(salad, foundDish);

        Dish soup = factory.makeDishRiceSoup();
        dishDAO.add(soup);
        foundDish = dishDAO.search(10);
        assertEquals(soup, foundDish);
    }

    @Test(expected = NoItemInDbException.class)
    @Transactional
    public void testSearchByIdAbnormal() throws Exception {
        dbController.restoreAllData();
        dishDAO.search(100);
    }

    @Test
    @Transactional
    public void testSearchByNameNormal() throws Exception {
        dbController.restoreAllData();
        Dish salad = factory.makeDishSalad();
        dishDAO.add(salad);

        Dish foundDish = dishDAO.search("salad");
        assertEquals(salad, foundDish);

        Dish soup = factory.makeDishRiceSoup();
        dishDAO.add(soup);
        foundDish = dishDAO.search("rice soup");
        assertEquals(soup, foundDish);
    }

    @Test
    @Transactional
    public void testDeleteById() throws Exception {
        dbController.restoreAllData();

        List<Dish> listBeforeAdd = dishDAO.getAll();
        Dish friedEggs = factory.makeDishFriedEggs();
        dishDAO.add(friedEggs);
        dishDAO.delete(32);
        List<Dish> listAfterDelete = dishDAO.getAll();

        assertEqualsDishLists(listBeforeAdd, listAfterDelete);

        listBeforeAdd = dishDAO.getAll();
        Dish salmon = factory.makeDishSalmon();
        dishDAO.add(salmon );
        dishDAO.delete(33);
        listAfterDelete = dishDAO.getAll();
        assertEqualsDishLists(listBeforeAdd, listAfterDelete);
    }

    @Test
    @Transactional
    public void testDeleteByName() throws Exception {
        dbController.restoreAllData();

        List<Dish> listBeforeAdd = dishDAO.getAll();
        Dish salad = factory.makeDishSalad();
        dishDAO.add(salad);
        dishDAO.delete("salad");
        List<Dish> listAfterDelete = dishDAO.getAll();

        assertEqualsDishLists(listBeforeAdd, listAfterDelete);

        listBeforeAdd = dishDAO.getAll();
        Dish soup = factory.makeDishRiceSoup();
        dishDAO.add(soup);
        dishDAO.delete("rice soup");
        listAfterDelete = dishDAO.getAll();
        assertEqualsDishLists(listBeforeAdd, listAfterDelete);
    }



    @Transactional
    private List<Dish> fillTableDish() {
        Dish salad = factory.makeDishSalad();
        Dish soup = factory.makeDishRiceSoup();
        Dish plov= factory.makeDishNewPlov();

        dishDAO.add(salad);
        dishDAO.add(soup);
        dishDAO.add(plov);

        List<Dish> createdDishes = new ArrayList<>();
        createdDishes.add(salad);
        createdDishes.add(soup);
        createdDishes.add(plov);
        return createdDishes;
    }

    private void assertNotEqualsDishLists(List<Dish> createdDishes, List<Dish> gotFromDbDishes) {
        createdDishes.stream().filter(createdDish -> gotFromDbDishes.contains(createdDish)).
                forEach(gotFromDbDishes::remove);
        assertNotEquals(new ArrayList<Dish>(), gotFromDbDishes);
    }

    private void assertEqualsDishLists(List<Dish> createdDishes, List<Dish> gotFromDbDishes) {
        createdDishes.stream().filter(createdDish -> gotFromDbDishes.contains(createdDish)).
                forEach(gotFromDbDishes::remove);
        assertEquals(new ArrayList<Dish>(), gotFromDbDishes);
    }
}