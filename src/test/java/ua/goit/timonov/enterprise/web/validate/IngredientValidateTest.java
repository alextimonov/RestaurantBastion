package ua.goit.timonov.enterprise.web.validate;

import org.junit.Before;
import org.junit.Test;
import ua.goit.timonov.enterprise.model.Ingredient;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 24.09.2016.
 */
public class IngredientValidateTest {
    public static final String SPACE = " ";
    private IngredientValidate itemValidate;
    private Ingredient item;

    @Before
    public void setUp() throws Exception {
        itemValidate = new IngredientValidate();
        item = new Ingredient("salt", 5000);
    }

    @Test
    public void testValidItem() throws Exception {
        assertEquals(true, itemValidate.isValid(item));
    }

    @Test
    public void testNotValidByName() throws Exception {
        item.setName(SPACE);
        assertEquals(false, itemValidate.isValid(item));
    }

    @Test
    public void testNotValidByAmount() throws Exception {
        item.setAmount(0);
        assertEquals(false, itemValidate.isValid(item));
    }
}