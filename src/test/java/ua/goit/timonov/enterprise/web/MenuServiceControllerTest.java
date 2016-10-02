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
import ua.goit.timonov.enterprise.web.validate.MenuValidate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ua.goit.timonov.enterprise.web.MenuServiceController.*;

/**
 * Testing class for DishServiceController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/web-context.xml"})
@WebAppConfiguration
public class MenuServiceControllerTest {
    public static final String MAPPED_PATH = "/service/menu";
    public static final String BREAKFAST = "Breakfast";
    public static final String LUNCH = "Lunch";
    public static final String DINNER = "Dinner";
    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String DISHES = "dishes";
    @Autowired
    WebApplicationContext wac;

    @InjectMocks
    private MenuServiceController controller;

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
        controller = new MenuServiceController();
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
    public void testServiceMenus() throws Exception {
        List<Menu> expectedMenus = createMenus();
        when(menuService.getAllMenus()).thenReturn(expectedMenus);
        mockMvc.perform(get(MAPPED_PATH + "/menus"))
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
    public void testGetMenuToAdd() throws Exception {
        mockMvc.perform(get(MAPPED_PATH + "/add"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(MENU_VALIDATE, new MenuValidate()))
                .andExpect(model().attribute(MENU_ITEM, new Menu()))
                .andExpect(view().name(PATH_ADD));
    }

    @Test
    public void testAddMenuIsNotValid() throws Exception {
        MenuValidate menuValidate = new MenuValidate();
        Menu menu = new Menu();

        mockMvc.perform(post(MAPPED_PATH + "/add")
                .sessionAttr(MENU_ITEM, menu)
                .sessionAttr(MENU_VALIDATE, menuValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_ADD))
                .andExpect(model().attribute(MENU_ITEM, hasProperty(ID, is(0))))
                .andExpect(model().attribute(MENU_ITEM, hasProperty(NAME, nullValue())))
                .andExpect(model().attribute(MENU_ITEM, hasProperty(DISHES, nullValue())));
        verifyZeroInteractions(menuService);
    }

    @Test
    public void testAddMenuIsValid() throws Exception {
        MenuValidate menuValidate = new MenuValidate();
        Menu breakfast = new Menu(BREAKFAST);
        breakfast.setId(1);
        Menu lunch = new Menu(LUNCH);
        lunch.setId(2);
        when(menuService.getAllMenus()).thenReturn(Arrays.asList(breakfast, lunch));
        when(menuService.searchMenuByName(LUNCH)).thenReturn(lunch);
        mockMvc.perform(post(MAPPED_PATH + "/add")
                .param(ID, "2")
                .param(NAME, LUNCH)
                .param("dishes.size()", "0")
                .sessionAttr(MENU_VALIDATE, menuValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_MENUS))
                .andExpect(model().attribute(MENUS, hasSize(2)))
                .andExpect(model().attribute(MENUS, hasItem(
                        hasProperty(NAME, is(BREAKFAST))
                )))
                .andExpect(model().attribute(MENUS, hasItem(
                        hasProperty(NAME, is(LUNCH))
                )));
        verify(menuService).add(lunch);
        verify(menuService).searchMenuByName(LUNCH);
        verify(menuService).getAllMenus();
        verifyNoMoreInteractions(menuService);
    }

    @Test
    public void testAskForDeleteMenuById() throws Exception {
        breakfast.setId(1);
        lunch.setId(2);
        when(menuService.searchMenuById(2)).thenReturn(lunch);
        mockMvc.perform(get(MAPPED_PATH + "/delete")
                .param(ID, "2")
                .requestAttr(MENU_TO_DELETE, lunch)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DELETE))
                .andExpect(model().attribute(MENU_TO_DELETE, hasProperty(ID, is(2))))
                .andExpect(model().attribute(MENU_TO_DELETE, hasProperty(NAME, is(LUNCH))));
        verify(menuService).searchMenuById(2);
        verifyNoMoreInteractions(menuService);
    }

    @Test
    public void testAskForDeleteMenuByName() throws Exception {
        breakfast.setId(1);
        lunch.setId(2);
        when(menuService.searchMenuByName(LUNCH)).thenReturn(lunch);
        mockMvc.perform(get(MAPPED_PATH + "/deleteByName")
                .param(NAME, LUNCH)
                .requestAttr(MENU_TO_DELETE, lunch)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DELETE))
                .andExpect(model().attribute(MENU_TO_DELETE, hasProperty(ID, is(2))))
                .andExpect(model().attribute(MENU_TO_DELETE, hasProperty(NAME, is(LUNCH))));
        verify(menuService).searchMenuByName(LUNCH);
        verifyNoMoreInteractions(menuService);
    }

    @Test
    public void testDeleteMenu() throws Exception {
        breakfast.setId(1);
        lunch.setId(2);
        dinner.setId(3);

        List<Menu> expectedMenus = Arrays.asList(breakfast, dinner);
        when(menuService.getAllMenus()).thenReturn(expectedMenus);
        mockMvc.perform(post(MAPPED_PATH + "/deleteConfirmed")
                .param(ID, "2")
                .requestAttr(MENUS, expectedMenus)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_MENUS))
                .andExpect(model().attribute(MENUS, hasItem(
                        hasProperty(NAME, is(BREAKFAST))
                )))
                .andExpect(model().attribute(MENUS, hasItem(
                         hasProperty(NAME, is(DINNER))
                )));
        verify(menuService).delete(2);
        verify(menuService).getAllMenus();
        verifyNoMoreInteractions(menuService);
    }

    @Test
    public void testEditMenuById() throws Exception {
        MenuValidate menuValidate = new MenuValidate();
        lunch.setId(1);
        when(menuService.searchMenuById(1)).thenReturn(lunch);
        mockMvc.perform(get(MAPPED_PATH + "/edit")
                .param("menuId", "1")
                .param(ID, "1")
                .param(NAME, LUNCH)
                .param("dishes.size()", "0")
                .requestAttr(MENU_ITEM, lunch)
                .requestAttr(MENU_VALIDATE, menuValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_EDIT))
                .andExpect(model().attribute(MENU_ITEM, hasProperty(ID, is(1))))
                .andExpect(model().attribute(MENU_ITEM, hasProperty(NAME, is(LUNCH))));
        verify(menuService).searchMenuById(1);
        verifyNoMoreInteractions(menuService);
    }

    @Test
    public void testEditMenuByName() throws Exception {
        MenuValidate menuValidate = new MenuValidate();
        Menu lunch = new Menu(LUNCH);
        lunch.setId(1);
        when(menuService.searchMenuByName(LUNCH)).thenReturn(lunch);
        mockMvc.perform(get(MAPPED_PATH + "/editByName")
                .param("menuName", LUNCH)
                .param(ID, "1")
                .param(NAME, LUNCH)
                .param("dishes.size()", "0")
                .requestAttr(MENU_ITEM, lunch)
                .requestAttr(MENU_VALIDATE, menuValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_EDIT))
                .andExpect(model().attribute(MENU_ITEM, hasProperty(ID, is(1))))
                .andExpect(model().attribute(MENU_ITEM, hasProperty(NAME, is(LUNCH))));
        verify(menuService).searchMenuByName(LUNCH);
        verifyNoMoreInteractions(menuService);
    }

    @Test
    public void testSaveEditMenu() throws Exception {
        MenuValidate menuValidate = new MenuValidate();
        breakfast.setId(1);
        lunch.setId(2);
        when(menuService.getAllMenus()).thenReturn(Arrays.asList(breakfast, lunch));
        when(menuService.searchMenuByName(LUNCH)).thenReturn(lunch);
        mockMvc.perform(post(MAPPED_PATH + "/edit")
                .param("menuId", "2")
                .param(ID, "2")
                .param(NAME, LUNCH)
                .param("dishes.size()", "0")
                .sessionAttr(MENU_ITEM, lunch)
                .sessionAttr(MENU_VALIDATE, menuValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_MENUS))
                .andExpect(model().attribute(MENUS, hasItem(
                        allOf(
                                hasProperty(NAME, is(LUNCH)),
                                hasProperty(DISHES, is(new ArrayList<>())))
                        )
                ));
        verify(menuService).searchMenuByName(LUNCH);
        verify(menuService).update(lunch);
        verify(menuService).getAllMenus();
        verifyNoMoreInteractions(menuService);
    }

//    @Test
//    public void testAddDishToMenu() throws Exception {
//
//    }
//
//    @Test
//    public void testAddDishToMenuByName() throws Exception {
//
//    }
//
//    @Test
//    public void testDeleteDishFromMenu() throws Exception {
//
//    }
//
//    @Test
//    public void testDeleteDishByNameFromMenu() throws Exception {
//
//    }
}
