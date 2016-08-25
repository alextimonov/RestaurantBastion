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
import ua.goit.timonov.enterprise.module_6_2.dao.StorageDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing class for JdbcStorageDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class JdbcStorageDAOTest {

    private DbController dbController;
    private StorageDAO storageDAO;

    @Autowired(required = true)
    public void setDbController(DbController dbController) {
        this.dbController = dbController;
    }

    @Autowired
    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    @Before
    public void setUp() throws Exception {
        dbController.deleteAllData();
        dbController.fillTableJobs();
    }

    @Test
    public void testGetAllNormal() throws Exception {
        List<Ingredient> createdIngredients = fillTableIngredient();
        List<Ingredient> gotFromDbIngredients = storageDAO.getAll();
        assertEqualsIngredientLists(createdIngredients, gotFromDbIngredients);
    }

    @Test
    public void testGetAllAbnormal_1() throws Exception {
        List<Ingredient> createdIngredients = fillTableIngredient();
        Ingredient redPepper = new Ingredient("red pepper", 2000);
        storageDAO.add(redPepper);
        List<Ingredient> gotFromDbIngredients = storageDAO.getAll();
        assertNotEqualsIngredientLists(createdIngredients, gotFromDbIngredients);
    }

    @Test
    public void testGetAllAbnormal_2() throws Exception {
        List<Ingredient> createdIngredients = new ArrayList<>();
        List<Ingredient> gotFromDbIngredients = storageDAO.getAll();
        assertEqualsIngredientLists(createdIngredients, gotFromDbIngredients);
    }

    @Test
    public void testAddNormal() throws Exception {
        List<Ingredient> createdIngredients = fillTableIngredient();

        Ingredient redPepper = new Ingredient("red pepper", 3000);
        createdIngredients.add(redPepper);
        storageDAO.add(redPepper);
        List<Ingredient> gotFromDbIngredients = storageDAO.getAll();
        assertEqualsIngredientLists(createdIngredients, gotFromDbIngredients);

        Ingredient eggplant = new Ingredient("eggplant", 15000);
        createdIngredients.add(eggplant);
        storageDAO.add(eggplant);
        gotFromDbIngredients = storageDAO.getAll();
        assertEqualsIngredientLists(createdIngredients, gotFromDbIngredients);
    }

    @Test
    public void testAddAbnormal() throws Exception {
        List<Ingredient> createdIngredients = fillTableIngredient();

        Ingredient redPepper = new Ingredient("red pepper", 2000);
        createdIngredients.add(redPepper);
        storageDAO.add(redPepper);
        List<Ingredient> gotFromDbIngredients = storageDAO.getAll();
        assertEqualsIngredientLists(createdIngredients, gotFromDbIngredients);

        Ingredient eggplant = new Ingredient("eggplant", 15000);
        createdIngredients.add(redPepper);
        storageDAO.add(eggplant);
        gotFromDbIngredients = storageDAO.getAll();
        assertNotEqualsIngredientLists(createdIngredients, gotFromDbIngredients);
    }

    @Test
    public void testSearchByIdNormal() throws Exception {
        restoreAllData();
        Ingredient sugar = new Ingredient("sugar", 25000);
        storageDAO.add(sugar);

        Ingredient foundIngredient = storageDAO.search(25);
        assertEquals(sugar, foundIngredient);

        Ingredient garlic = new Ingredient("garlic", 5000);
        storageDAO.add(garlic);
        foundIngredient = storageDAO.search(26);
        assertEquals(garlic, foundIngredient);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testSearchByIdAbnormal() throws Exception {
        restoreAllData();
        storageDAO.search(30);
    }

    @Test
    public void testSearchByNameNormal() throws Exception {
        restoreAllData();
        Ingredient sugar = new Ingredient("sugar", 25000);
        storageDAO.add(sugar);

        Ingredient foundIngredient = storageDAO.search("sugar");
        assertEquals(sugar, foundIngredient);

        Ingredient garlic = new Ingredient("garlic", 5000);
        storageDAO.add(garlic);
        foundIngredient = storageDAO.search("garlic");
        assertEquals(garlic, foundIngredient);
    }

    @Test
    public void testDeleteById() throws Exception {
        restoreAllData();

        List<Ingredient> listBeforeAdd = storageDAO.getAll();
        Ingredient sugar = new Ingredient("sugar", 25000);
        storageDAO.add(sugar);
        storageDAO.delete(25);
        List<Ingredient> listAfterDelete = storageDAO.getAll();

        assertEqualsIngredientLists(listBeforeAdd, listAfterDelete);

        listBeforeAdd = storageDAO.getAll();
        Ingredient garlic = new Ingredient("garlic", 5000);
        storageDAO.add(garlic);
        storageDAO.delete(26);
        listAfterDelete = storageDAO.getAll();
        assertEqualsIngredientLists(listBeforeAdd, listAfterDelete);
    }

    @Test
    public void testDeleteByName() throws Exception {
        restoreAllData();

        List<Ingredient> listBeforeAdd = storageDAO.getAll();
        Ingredient sugar = new Ingredient("sugar", 25000);
        storageDAO.add(sugar);
        storageDAO.delete("sugar");
        List<Ingredient> listAfterDelete = storageDAO.getAll();
        assertEqualsIngredientLists(listBeforeAdd, listAfterDelete);

        listBeforeAdd = storageDAO.getAll();
        Ingredient garlic = new Ingredient("garlic", 5000);
        storageDAO.add(garlic);
        storageDAO.delete("garlic");
        listAfterDelete = storageDAO.getAll();
        assertEqualsIngredientLists(listBeforeAdd, listAfterDelete);
    }

    @Transactional
    private List<Ingredient> fillTableIngredient() {
        Ingredient sugar = new Ingredient("sugar", 25000);
        Ingredient garlic = new Ingredient("garlic", 5000);
        Ingredient blackPepper = new Ingredient("black pepper", 250);

        storageDAO.add(sugar);
        storageDAO.add(garlic);
        storageDAO.add(blackPepper);

        List<Ingredient> createdIngredients = new ArrayList<>();
        createdIngredients.add(sugar);
        createdIngredients.add(garlic);
        createdIngredients.add(blackPepper);
        return createdIngredients;
    }

    @Transactional
    private void restoreAllData() throws FileNotFoundException {
        dbController.dropAllTables();
        dbController.createAllTables();
        dbController.fillTableJobs();
        dbController.restoreAllData();
    }

    private void assertNotEqualsIngredientLists(List<Ingredient> createdIngredients, List<Ingredient> gotFromDbIngredients) {
        createdIngredients.stream().filter(createdIngredient -> gotFromDbIngredients.contains(createdIngredient)).
                forEach(gotFromDbIngredients::remove);
        assertNotEquals(new ArrayList<Ingredient>(), gotFromDbIngredients);
    }

    private void assertEqualsIngredientLists(List<Ingredient> createdIngredients, List<Ingredient> gotFromDbIngredients) {
        createdIngredients.stream().filter(createdIngredient -> gotFromDbIngredients.contains(createdIngredient)).
                forEach(gotFromDbIngredients::remove);
        assertEquals(new ArrayList<Ingredient>(), gotFromDbIngredients);
    }
}