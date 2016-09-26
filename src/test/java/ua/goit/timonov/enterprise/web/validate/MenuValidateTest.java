package ua.goit.timonov.enterprise.web.validate;

import org.junit.Before;
import org.junit.Test;
import ua.goit.timonov.enterprise.model.Menu;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Alex on 24.09.2016.
 */
public class MenuValidateTest {
    public static final String SPACE = " ";
    private MenuValidate menuValidate;
    private Menu menu;

    @Before
    public void setUp() throws Exception {
        menuValidate = new MenuValidate();
        menu = new Menu("Winter");
    }

    @Test
    public void testValidItem() throws Exception {
        assertEquals(true, menuValidate.isValid(menu));
    }

    @Test
    public void testNotValidByName() throws Exception {
        menu.setName(SPACE);
        assertEquals(false, menuValidate.isValid(menu));
    }

}