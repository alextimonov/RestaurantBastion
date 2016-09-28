package ua.goit.timonov.enterprise.web.validate;

import org.junit.Before;
import org.junit.Test;
import ua.goit.timonov.enterprise.model.Dish;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 24.09.2016.
 */
public class DishValidateTest {
    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";
    private DishValidate dishValidate;
    private Dish dish;

    @Before
    public void setUp() throws Exception {
        dishValidate = new DishValidate();
        dish = new Dish();
        dish.append("pizza", "italian dish");
        dish.setWeight(150);
        dish.setCost(45.5F);
    }

    @Test
    public void testValidDish() throws Exception {
        assertEquals(true, dishValidate.isValid(dish));
    }

    @Test
    public void testNotValidByName() throws Exception {
        dish.setName(SPACE);
        assertEquals(false, dishValidate.isValid(dish));
    }

    @Test
    public void testNotValidByDescription() throws Exception {
        dish.setDescription(EMPTY_STRING);
        assertEquals(false, dishValidate.isValid(dish));
    }

    @Test
    public void testNotValidByWeight() throws Exception {
        dish.setWeight(0);
        assertEquals(false, dishValidate.isValid(dish));
    }

    @Test
    public void testNotValidByCost() throws Exception {
        dish.setCost(-1);
        assertEquals(false, dishValidate.isValid(dish));
    }

}