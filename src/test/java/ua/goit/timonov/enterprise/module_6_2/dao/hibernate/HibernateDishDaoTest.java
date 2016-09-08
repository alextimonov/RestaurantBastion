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
import ua.goit.timonov.enterprise.module_6_2.model.Dish;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing class for HDishDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml"})
public class HibernateDishDaoTest {
    private DbController dbController;
    private DishDAO dishDAO;

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
        dishDAO.add(makeDishSalmon());
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
    public void testAddNormal() throws Exception {
        List<Dish> createdDishes = fillTableDish();

        Dish salmon = makeDishSalmon();
        createdDishes.add(salmon);
        dishDAO.add(salmon);
        List<Dish> gotFromDbDishes = dishDAO.getAll();
        assertEqualsDishLists(createdDishes, gotFromDbDishes);

        Dish friedEggs = makeDishFriedEggs();
        createdDishes.add(friedEggs);
        dishDAO.add(friedEggs);
        gotFromDbDishes = dishDAO.getAll();
        assertEqualsDishLists(createdDishes, gotFromDbDishes);
    }

    @Test
    @Transactional
    public void testAddAbnormal() throws Exception {
        List<Dish> createdDishes = fillTableDish();

        Dish salmon = makeDishSalmon();
        createdDishes.add(salmon);
        dishDAO.add(salmon);
        List<Dish> gotFromDbDishs = dishDAO.getAll();
        assertEqualsDishLists(createdDishes, gotFromDbDishs);

        Dish friedEggs = makeDishFriedEggs();
        createdDishes.add(salmon);
        dishDAO.add(friedEggs);
        gotFromDbDishs = dishDAO.getAll();
        assertNotEqualsDishLists(createdDishes, gotFromDbDishs);
    }

    @Test
    @Transactional
    public void testSearchByIdNormal() throws Exception {
        dbController.restoreAllData();
        Dish salad = makeDishSalad();
        dishDAO.add(salad);

        Dish foundDish = dishDAO.search(9);
        assertEquals(salad, foundDish);

        Dish soup = makeDishSoup();
        dishDAO.add(soup);
        foundDish = dishDAO.search(10);
        assertEquals(soup, foundDish);
    }

    @Test(expected = NoResultException.class)
    @Transactional
    public void testSearchByIdAbnormal() throws Exception {
        dbController.restoreAllData();
        dishDAO.search(12);
    }

    @Test
    @Transactional
    public void testSearchByNameNormal() throws Exception {
        dbController.restoreAllData();
        Dish salad = makeDishSalad();
        dishDAO.add(salad);

        Dish foundDish = dishDAO.search("salad");
        assertEquals(salad, foundDish);

        Dish soup = makeDishSoup();
        dishDAO.add(soup);
        foundDish = dishDAO.search("rice soup");
        assertEquals(soup, foundDish);
    }

    @Test
    @Transactional
    public void testDeleteById() throws Exception {
        dbController.restoreAllData();

        List<Dish> listBeforeAdd = dishDAO.getAll();
        Dish friedEggs = makeDishFriedEggs();
        dishDAO.add(friedEggs);
        dishDAO.delete(32);
        List<Dish> listAfterDelete = dishDAO.getAll();

        assertEqualsDishLists(listBeforeAdd, listAfterDelete);

        listBeforeAdd = dishDAO.getAll();
        Dish salmon = makeDishSalmon();
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
        Dish salad = makeDishSalad();
        dishDAO.add(salad);
        dishDAO.delete("salad");
        List<Dish> listAfterDelete = dishDAO.getAll();

        assertEqualsDishLists(listBeforeAdd, listAfterDelete);

        listBeforeAdd = dishDAO.getAll();
        Dish soup = makeDishSoup();
        dishDAO.add(soup);
        dishDAO.delete("rice soup");
        listAfterDelete = dishDAO.getAll();
        assertEqualsDishLists(listBeforeAdd, listAfterDelete);
    }

    private Dish makeDishSalad() {
        Dish salad = new Dish();
        salad
                .append("salad", "light salad with delicious vegetables")
                .append(250)
                .append(45.0F);
        return salad;
    }

    private Dish makeDishSoup() {
        Dish soup = new Dish();
        soup
                .append("rice soup", "created by standard recipe")
                .append(300)
                .append(50.0F);
        return soup;
    }

    private Dish makeDishPlov() {
        Dish plov = new Dish();
        plov
                .append("plov", "rice with meat")
                .append(350)
                .append(60.0F);
        return plov;
    }

    private Dish makeDishSalmon() {
        Dish salmon = new Dish();
        salmon
                .append("salmon", "fried salmon")
                .append(300)
                .append(80.0F);
        return salmon;
    }

    private Dish makeDishFriedEggs() {
        Dish friedEggs = new Dish();
        friedEggs
                .append("fried eggs", "fried eggs with bacon")
                .append(175)
                .append(40.0F);
        return friedEggs;
    }

    @Transactional
    private List<Dish> fillTableDish() {
        Dish salad = makeDishSalad();
        Dish soup = makeDishSoup();
        Dish plov= makeDishPlov();

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