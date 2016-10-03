package ua.goit.timonov.enterprise.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;
import ua.goit.timonov.enterprise.dao.hibernate.ObjectsFactory;
import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.Menu;
import ua.goit.timonov.enterprise.service.MenuService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ua.goit.timonov.enterprise.web.MenuWebController.MENUS;
import static ua.goit.timonov.enterprise.web.MenuWebController.PATH_MENUS;
import static ua.goit.timonov.enterprise.web.MenuWebController.MENU;
import static ua.goit.timonov.enterprise.web.MenuWebController.MENU_NAME;

/**
 * Testing class for MenuWebController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/web-context.xml"})
@WebAppConfiguration
public class MenuWebControllerTest {

    public static final String BREAKFAST = "Breakfast";
    public static final String LUNCH = "lunch";
    public static final String DINNER = "Dinner";
    public static final String NAME = "name";
    public static final String ID = "id";

    @Autowired
    WebApplicationContext wac;

    @InjectMocks
    private MenuWebController controller;

    @Mock
    private MenuService menuService;

    @Mock
    View mockView;

    MockMvc mockMvc;
    ObjectsFactory objectsFactory;
    private Menu breakfast;
    private Menu lunch;
    private Menu dinner;

    private Dish newPlov;
    private Dish salad;
    private Dish salmon;
    private Dish riceSoup;

    @Before
    public void setUp() throws Exception {
        controller = new MenuWebController();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
        objectsFactory = new ObjectsFactory();
        breakfast = new Menu(BREAKFAST);
        lunch = new Menu(LUNCH);
        dinner = new Menu(DINNER);

        riceSoup = objectsFactory.makeDishRiceSoup();
        newPlov = objectsFactory.makeDishNewPlov();
        salad = objectsFactory.makeDishSalad();
        salmon = objectsFactory.makeDishSalmon();
    }

    private List<Menu> createMenus() {
        breakfast.setDishes(Arrays.asList(salad, salmon));
        dinner.setDishes(Arrays.asList(newPlov, riceSoup, salmon));
        lunch.setDishes(Arrays.asList(newPlov, riceSoup));
        return new ArrayList<>(Arrays.asList(breakfast, lunch, dinner));
    }

    @Test
    public void testGetAllMenus() throws Exception {
        List<Menu> expectedMenus = createMenus();
        when(menuService.getAllMenus()).thenReturn(expectedMenus);
        mockMvc.perform(get(PATH_MENUS))
                .andExpect(status().isOk())
                .andExpect(model().attribute(MENUS, hasSize(3)))
                .andExpect(model().attribute(MENUS, hasItem(
                        hasProperty(NAME, is(BREAKFAST))
                )))
                .andExpect(model().attribute(MENUS, hasItem(
                        hasProperty(NAME, is(LUNCH))
                )))
                .andExpect(model().attribute(MENUS, hasItem(
                        hasProperty(NAME, is(DINNER))
                )));
        verify(menuService, times(1)).getAllMenus();
        verifyNoMoreInteractions(menuService);
    }

    @Test
    public void testGetMenu() throws Exception {
        breakfast.setId(1);
        lunch.setId(2);
        when(menuService.getMenuByName(LUNCH)).thenReturn(lunch);
        mockMvc.perform(get("/menu/lunch")
                .param(MENU_NAME, LUNCH)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(MENU))
                .andExpect(model().attribute(MENU, hasProperty(NAME, is(LUNCH))))
                .andExpect(model().attribute(MENU, hasProperty(ID, is(2))))
        ;
        verify(menuService).getMenuByName(LUNCH);
        verifyNoMoreInteractions(menuService);
    }
}