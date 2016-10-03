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
import ua.goit.timonov.enterprise.service.DishService;
import ua.goit.timonov.enterprise.web.validate.DishValidate;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ua.goit.timonov.enterprise.web.DishServiceController.*;

/**
 * Testing class for DishServiceController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/web-context.xml"})
@WebAppConfiguration
public class DishServiceControllerTest {
    public static final String MAPPED_PATH = "/service/dish";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String WEIGHT = "weight";
    public static final String COST = "cost";
    public static final String SALAD = "salad";
    public static final String RICE_SOUP = "rice soup";
    public static final String ID = "id";
    @Autowired
    WebApplicationContext wac;

    @InjectMocks
    private DishServiceController controller;

    @Mock
    private DishService dishService;

    @Mock
    View mockView;

    MockMvc mockMvc;
    ObjectsFactory objectsFactory;

    @Before
    public void setUp() throws Exception {
        controller = new DishServiceController();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
        objectsFactory = new ObjectsFactory();
    }

    @Test
    public void testGetAllDishes() throws Exception {
        Dish salad = objectsFactory.makeDishSalad();
        Dish soup = objectsFactory.makeDishRiceSoup();
        Dish plov = objectsFactory.makeDishNewPlov();
        List<Dish> expectedDishes = Arrays.asList(salad, soup, plov);
        when(dishService.getAllDishes()).thenReturn(expectedDishes);
        mockMvc.perform(get(MAPPED_PATH + "/dishes"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(DISHES, hasSize(3)))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty(NAME, is(SALAD)),
                                hasProperty(DESCRIPTION, is("light salad with delicious vegetables")),
                                hasProperty(WEIGHT, is(250)),
                                hasProperty(COST, is(45.0F)))
                        )
                ))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty(NAME, is(RICE_SOUP)),
                                hasProperty(DESCRIPTION, is("created by standard recipe")),
                                hasProperty(WEIGHT, is(300)),
                                hasProperty(COST, is(50.0F)))
                        )
                ))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty(NAME, is("new plov")),
                                hasProperty(DESCRIPTION, is("rice with meat")),
                                hasProperty(WEIGHT, is(350)),
                                hasProperty(COST, is(60.0F)))
                        )
                ));
        verify(dishService).getAllDishes();
        verifyNoMoreInteractions(dishService);
    }

    @Test
    public void testGetDishToAdd() throws Exception {
        mockMvc.perform(get(MAPPED_PATH + "/add"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(DISH_VALIDATE, new DishValidate()))
                .andExpect(model().attribute(DISH_ATTRIBUTE, new Dish()))
                .andExpect(view().name(PATH_ADD));
    }

    @Test
    public void testAddDishIsNotValid() throws Exception {
        DishValidate dishValidate = new DishValidate();
        Dish dish = new Dish();
        mockMvc.perform(post(MAPPED_PATH + "/add")
                .sessionAttr(DISH_ATTRIBUTE, dish)
                .sessionAttr(DISH_VALIDATE, dishValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_ADD))
                .andExpect(model().attribute(DISH_ATTRIBUTE, hasProperty(ID, is(0))))
                .andExpect(model().attribute(DISH_ATTRIBUTE, hasProperty(NAME, nullValue())))
                .andExpect(model().attribute(DISH_ATTRIBUTE, hasProperty(DESCRIPTION, nullValue())))
                .andExpect(model().attribute(DISH_ATTRIBUTE, hasProperty(WEIGHT, is(0))))
                .andExpect(model().attribute(DISH_ATTRIBUTE, hasProperty(COST, is(0F))));
        verifyZeroInteractions(dishService);
    }

    @Test
    public void testAddDish() throws Exception {
        DishValidate dishValidate = new DishValidate();
        Dish soup = objectsFactory.makeDishRiceSoup();
        soup.setId(1);
        Dish salad = objectsFactory.makeDishSalad();
        salad.setId(2);
        when(dishService.getAllDishes()).thenReturn(Arrays.asList(soup, salad));
        when(dishService.searchDishByName(SALAD)).thenReturn(salad);
        mockMvc.perform(post(MAPPED_PATH + "/add")
                .param(ID, "2")
                .param(NAME, SALAD)
                .param(DESCRIPTION, "light salad with delicious vegetables")
                .param(WEIGHT, "250")
                .param(COST, "45.0F")
                .sessionAttr(DISH_VALIDATE, dishValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DISHES))
                .andExpect(model().attribute(DISHES, hasSize(2)))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty(NAME, is(RICE_SOUP)),
                                hasProperty(DESCRIPTION, is("created by standard recipe")),
                                hasProperty(WEIGHT, is(300)),
                                hasProperty(COST, is(50.0F)))
                        )
                ))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty(NAME, is(SALAD)),
                                hasProperty(DESCRIPTION, is("light salad with delicious vegetables")),
                                hasProperty(WEIGHT, is(250)),
                                hasProperty(COST, is(45.0F)))
                        )
                ));
        verify(dishService).add(salad);
        verify(dishService).searchDishByName(SALAD);
        verify(dishService).getAllDishes();
        verifyNoMoreInteractions(dishService);
    }

    @Test
    public void testAskForDeleteDishById() throws Exception {
        Dish soup = objectsFactory.makeDishRiceSoup();
        soup.setId(1);
        Dish salad = objectsFactory.makeDishSalad();
        salad.setId(2);
        when(dishService.searchDishById(2)).thenReturn(salad);
        mockMvc.perform(get(MAPPED_PATH + "/delete")
                .param(ID, "2")
                .requestAttr(DISH_TO_DELETE, salad)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DELETE))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(ID, is(2))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(NAME, is(SALAD))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(DESCRIPTION, is("light salad with delicious vegetables"))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(WEIGHT, is(250))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(COST, is(45.0F))));
        verify(dishService).searchDishById(2);
        verifyNoMoreInteractions(dishService);
    }

    @Test
    public void testAskForDeleteDishByName() throws Exception {
        Dish soup = objectsFactory.makeDishRiceSoup();
        soup.setId(1);
        Dish salad = objectsFactory.makeDishSalad();
        salad.setId(2);
        when(dishService.searchDishByName(SALAD)).thenReturn(salad);
        mockMvc.perform(get(MAPPED_PATH + "/deleteByName")
                .param(NAME, SALAD)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DELETE))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(ID, is(2))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(NAME, is(SALAD))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(DESCRIPTION, is("light salad with delicious vegetables"))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(WEIGHT, is(250))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(COST, is(45.0F))));
        verify(dishService).searchDishByName(SALAD);
        verifyNoMoreInteractions(dishService);
    }

    @Test
    public void testDeleteDish() throws Exception {
        Dish soup = objectsFactory.makeDishRiceSoup();
        soup.setId(1);
        Dish plov = objectsFactory.makeDishNewPlov();
        plov.setId(2);
        Dish salad = objectsFactory.makeDishSalad();
        salad.setId(3);
        List<Dish> expectedDishes = Arrays.asList(soup, salad);
        when(dishService.getAllDishes()).thenReturn(expectedDishes);
        mockMvc.perform(post(MAPPED_PATH + "/deleteConfirmed")
                .param(ID, "2")
                .requestAttr(DISHES, expectedDishes)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DISHES))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty(NAME, is(RICE_SOUP)),
                                hasProperty(DESCRIPTION, is("created by standard recipe")),
                                hasProperty(WEIGHT, is(300)),
                                hasProperty(COST, is(50.0F)))
                        )
                ))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty(NAME, is(SALAD)),
                                hasProperty(DESCRIPTION, is("light salad with delicious vegetables")),
                                hasProperty(WEIGHT, is(250)),
                                hasProperty(COST, is(45.0F)))
                        )
                ));
        verify(dishService).delete(2);
        verify(dishService).getAllDishes();
        verifyNoMoreInteractions(dishService);
    }

    @Test
    public void testEditDishById() throws Exception {
        DishValidate dishValidate = new DishValidate();
        Dish salad = objectsFactory.makeDishSalad();
        salad.setId(1);
        when(dishService.searchDishById(1)).thenReturn(salad);
        mockMvc.perform(get(MAPPED_PATH + "/edit")
                .param("dishId", "1")
                .param(ID, "1")
                .param(NAME, SALAD)
                .param(DESCRIPTION, "light salad with delicious vegetables")
                .param(WEIGHT, "250")
                .param(COST, "45.0F")
                .requestAttr(DISH_EXISTING, salad)
                .requestAttr(DISH_VALIDATE, dishValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_EDIT))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty(ID, is(1))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty(NAME, is(SALAD))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty(DESCRIPTION, is("light salad with delicious vegetables"))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty(WEIGHT, is(250))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty(COST, is(45.0F))));
        verify(dishService).searchDishById(1);
        verify(dishService).getIngredientsInDish(salad);
        verifyNoMoreInteractions(dishService);
    }

    @Test
    public void testEditDishByName() throws Exception {
        DishValidate dishValidate = new DishValidate();
        Dish salad = objectsFactory.makeDishSalad();
        salad.setId(1);
        when(dishService.searchDishByName(SALAD)).thenReturn(salad);
        mockMvc.perform(get(MAPPED_PATH + "/editByName")
                .param("dishName", SALAD)
                .param(ID, "1")
                .param(NAME, SALAD)
                .param(DESCRIPTION, "light salad with delicious vegetables")
                .param(WEIGHT, "250")
                .param(COST, "45.0F")
                .requestAttr(DISH_EXISTING, salad)
                .requestAttr(DISH_VALIDATE, dishValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_EDIT))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty(ID, is(1))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty(NAME, is(SALAD))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty(DESCRIPTION, is("light salad with delicious vegetables"))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty(WEIGHT, is(250))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty(COST, is(45.0F))));
        verify(dishService).searchDishByName(SALAD);
        verify(dishService).getIngredientsInDish(salad);
        verifyNoMoreInteractions(dishService);
    }

    @Test
    public void testSaveEditDish() throws Exception {
        Dish salad = objectsFactory.makeDishSalad();
        Dish soup = objectsFactory.makeDishRiceSoup();
        salad.setId(1);
        DishValidate dishValidate = new DishValidate();
        when(dishService.getAllDishes()).thenReturn(Arrays.asList(soup, salad));
        when(dishService.searchDishByName(SALAD)).thenReturn(salad);
        mockMvc.perform(post(MAPPED_PATH + "/edit")
                .param("dishId", "1")
                .param(ID, "1")
                .param(NAME, SALAD)
                .param(DESCRIPTION, "light salad with delicious vegetables")
                .param(WEIGHT, "250")
                .param(COST, "45.0F")
                .sessionAttr(DISH_EXISTING, salad)
                .sessionAttr(DISH_VALIDATE, dishValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DISHES))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty(NAME, is(SALAD)),
                                hasProperty(DESCRIPTION, is("light salad with delicious vegetables")),
                                hasProperty(WEIGHT, is(250)),
                                hasProperty(COST, is(45.0F)))
                        )
                ));
        verify(dishService).searchDishByName(SALAD);
        verify(dishService).update(salad);
        verify(dishService).getAllDishes();
        verifyNoMoreInteractions(dishService);
    }
}