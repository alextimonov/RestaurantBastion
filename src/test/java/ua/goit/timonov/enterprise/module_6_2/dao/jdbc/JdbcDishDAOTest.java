package ua.goit.timonov.enterprise.module_6_2.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.controllers.DbController;
import ua.goit.timonov.enterprise.module_6_2.dao.DishDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing class for JdbcDishDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class JdbcDishDAOTest {

    private DbController dbController;
    private DishDAO dishDAO;

    @Autowired(required = true)
    public void setDbController(DbController dbController) {
        this.dbController = dbController;
    }

    @Before
    public void setUp() throws Exception {
        dbController.deleteAllData();
        dbController.fillTableJobs();
        dishDAO = dbController.getDishDAO();
    }

    @Test
    public void testGetAllNormal() throws Exception {
        List<Dish> createdDishes = fillTableDish();
        List<Dish> gotFromDbDishes = dishDAO.getAll();
        assertEqualsDishLists(createdDishes, gotFromDbDishes);
    }

    @Test
    public void testGetAllAbnormal_1() throws Exception {
        List<Dish> createdDishes = fillTableDish();
        dishDAO.add(makeDishSalmon());
        List<Dish> gotFromDbDishes = dishDAO.getAll();
        assertNotEqualsDishLists(createdDishes, gotFromDbDishes);
    }

    @Test
    public void testGetAllAbnormal_2() throws Exception {
        List<Dish> createdDishes = new ArrayList<>();
        List<Dish> gotFromDbDishs = dishDAO.getAll();
        assertEquals(createdDishes, gotFromDbDishs);
    }

    @Test
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
    public void testSearchByIdNormal() throws Exception {
        restoreAllData();
        Dish salad = makeDishSalad();
        dishDAO.add(salad);

        Dish foundDish = dishDAO.search(9);
        assertEquals(salad, foundDish);

        Dish soup = makeDishSoup();
        dishDAO.add(soup);
        foundDish = dishDAO.search(10);
        assertEquals(soup, foundDish);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testSearchByIdAbnormal() throws Exception {
        restoreAllData();
        dishDAO.search(12);
    }

    @Test
    public void testSearchByNameNormal() throws Exception {
        restoreAllData();
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
    public void testDeleteById() throws Exception {
        restoreAllData();

        List<Dish> listBeforeAdd = dishDAO.getAll();
        Dish soup = makeDishSoup();
        dishDAO.add(soup);
        dishDAO.delete(9);
        List<Dish> listAfterDelete = dishDAO.getAll();

        assertEqualsDishLists(listBeforeAdd, listAfterDelete);

        listBeforeAdd = dishDAO.getAll();
        Dish salmon = makeDishSalmon();
        dishDAO.add(salmon );
        dishDAO.delete(10);
        listAfterDelete = dishDAO.getAll();
        assertEqualsDishLists(listBeforeAdd, listAfterDelete);
    }

    @Test
    public void testDeleteByName() throws Exception {
        restoreAllData();

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
                .append(45.0F)
                .append(250);
        return salad;
    }

    private Dish makeDishSoup() {
        Dish soup = new Dish();
        soup
                .append("rice soup", "created by standard recipe")
                .append(50.0F)
                .append(300);
        return soup;
    }

    private Dish makeDishPlov() {
        Dish plov = new Dish();
        plov
                .append("plov", "rice with meat")
                .append(60.0F)
                .append(350);
        return plov;
    }

    private Dish makeDishSalmon() {
        Dish salmon = new Dish();
        salmon
                .append("salmon", "fried salmon")
                .append(80.0F)
                .append(275);
        return salmon;
    }

    private Dish makeDishFriedEggs() {
        Dish friedEggs = new Dish();
        friedEggs
                .append("fried eggs", "fried eggs with bacon")
                .append(40.0F)
                .append(175);
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

    @Transactional
    private void restoreAllData() throws FileNotFoundException {
        dbController.dropAllTables();
        dbController.createAllTables();
        dbController.fillTableJobs();
        dbController.restoreAllData();
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