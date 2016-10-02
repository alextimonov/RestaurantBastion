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
                                hasProperty("name", is("salad")),
                                hasProperty("description", is("light salad with delicious vegetables")),
                                hasProperty("weight", is(250)),
                                hasProperty("cost", is(45.0F)))
                        )
                ))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty("name", is("rice soup")),
                                hasProperty("description", is("created by standard recipe")),
                                hasProperty("weight", is(300)),
                                hasProperty("cost", is(50.0F)))
                        )
                ))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty("name", is("new plov")),
                                hasProperty("description", is("rice with meat")),
                                hasProperty("weight", is(350)),
                                hasProperty("cost", is(60.0F)))
                        )
                ));
        verify(dishService, times(1)).getAllDishes();
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
                .andExpect(model().attribute(DISH_ATTRIBUTE, hasProperty("id", is(0))))
                .andExpect(model().attribute(DISH_ATTRIBUTE, hasProperty("name", nullValue())))
                .andExpect(model().attribute(DISH_ATTRIBUTE, hasProperty("description", nullValue())))
                .andExpect(model().attribute(DISH_ATTRIBUTE, hasProperty("weight", is(0))))
                .andExpect(model().attribute(DISH_ATTRIBUTE, hasProperty("cost", is(0F))));
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
        when(dishService.searchDishByName("salad")).thenReturn(salad);
        mockMvc.perform(post(MAPPED_PATH + "/add")
                .param("id", "2")
                .param("name", "salad")
                .param("description", "light salad with delicious vegetables")
                .param("weight", "250")
                .param("cost", "45.0F")
                .sessionAttr(DISH_VALIDATE, dishValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DISHES))
                .andExpect(model().attribute(DISHES, hasSize(2)))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty("name", is("rice soup")),
                                hasProperty("description", is("created by standard recipe")),
                                hasProperty("weight", is(300)),
                                hasProperty("cost", is(50.0F)))
                        )
                ))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty("name", is("salad")),
                                hasProperty("description", is("light salad with delicious vegetables")),
                                hasProperty("weight", is(250)),
                                hasProperty("cost", is(45.0F)))
                        )
                ));
        verify(dishService).add(salad);
        verify(dishService).searchDishByName("salad");
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
                .param("id", "2")
                .requestAttr(DISH_TO_DELETE, salad)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DELETE))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty("id", is(2))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty("name", is("salad"))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty("description", is("light salad with delicious vegetables"))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty("weight", is(250))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty("cost", is(45.0F))));
        verify(dishService).searchDishById(2);
        verifyNoMoreInteractions(dishService);
    }

    @Test
    public void testAskForDeleteDishByName() throws Exception {
        Dish soup = objectsFactory.makeDishRiceSoup();
        soup.setId(1);
        Dish salad = objectsFactory.makeDishSalad();
        salad.setId(2);
        when(dishService.searchDishByName("salad")).thenReturn(salad);
        mockMvc.perform(get(MAPPED_PATH + "/deleteByName")
                .param("dishName", "salad")
                .requestAttr(DISH_TO_DELETE, salad)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DELETE))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty("id", is(2))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty("name", is("salad"))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty("description", is("light salad with delicious vegetables"))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty("weight", is(250))))
                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty("cost", is(45.0F))));
        verify(dishService).searchDishByName("salad");
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
                .param("id", "2")
                .requestAttr(DISHES, expectedDishes)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DISHES))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty("name", is("rice soup")),
                                hasProperty("description", is("created by standard recipe")),
                                hasProperty("weight", is(300)),
                                hasProperty("cost", is(50.0F)))
                        )
                ))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty("name", is("salad")),
                                hasProperty("description", is("light salad with delicious vegetables")),
                                hasProperty("weight", is(250)),
                                hasProperty("cost", is(45.0F)))
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
                .param("id", "1")
                .param("name", "salad")
                .param("description", "light salad with delicious vegetables")
                .param("weight", "250")
                .param("cost", "45.0F")
                .requestAttr(DISH_EXISTING, salad)
                .requestAttr(DISH_VALIDATE, dishValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_EDIT))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty("id", is(1))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty("name", is("salad"))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty("description", is("light salad with delicious vegetables"))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty("weight", is(250))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty("cost", is(45.0F))));
        verify(dishService).searchDishById(1);
        verify(dishService).getIngredientsInDish(salad);
        verifyNoMoreInteractions(dishService);
    }

//    @Test
//    public void testEditDish() throws Exception {
//
//    }

    @Test
    public void testEditDishByName() throws Exception {
        DishValidate dishValidate = new DishValidate();
        Dish salad = objectsFactory.makeDishSalad();
        salad.setId(1);
        when(dishService.searchDishByName("salad")).thenReturn(salad);
        mockMvc.perform(get(MAPPED_PATH + "/editByName")
                .param("dishName", "salad")
                .param("id", "1")
                .param("name", "salad")
                .param("description", "light salad with delicious vegetables")
                .param("weight", "250")
                .param("cost", "45.0F")
                .requestAttr(DISH_EXISTING, salad)
                .requestAttr(DISH_VALIDATE, dishValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_EDIT))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty("id", is(1))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty("name", is("salad"))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty("description", is("light salad with delicious vegetables"))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty("weight", is(250))))
                .andExpect(model().attribute(DISH_EXISTING, hasProperty("cost", is(45.0F))));
        verify(dishService).searchDishByName("salad");
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
        when(dishService.searchDishByName("salad")).thenReturn(salad);
        mockMvc.perform(post(MAPPED_PATH + "/edit")
                .param("dishId", "1")
                .param("id", "1")
                .param("name", "salad")
                .param("description", "light salad with delicious vegetables")
                .param("weight", "250")
                .param("cost", "45.0F")
                .sessionAttr(DISH_EXISTING, salad)
                .sessionAttr(DISH_VALIDATE, dishValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DISHES))
                .andExpect(model().attribute(DISHES, hasItem(
                        allOf(
                                hasProperty("name", is("salad")),
                                hasProperty("description", is("light salad with delicious vegetables")),
                                hasProperty("weight", is(250)),
                                hasProperty("cost", is(45.0F)))
                        )
                ));
        verify(dishService).searchDishByName("salad");
        verify(dishService).update(salad);
        verify(dishService).getAllDishes();
        verifyNoMoreInteractions(dishService);
    }

//    @Test
//    public void testGetAllDishes1() throws Exception {
//
//    }
//
//    @Test
//    public void testAddItemToDish() throws Exception {
//
//    }
//
//    @Test
//    public void testEditItemInDish() throws Exception {
//
//    }
//
//    @Test
//    public void testSaveEditItemInDish() throws Exception {
//
//    }
//
//    @Test
//    public void testAskForDeleteItemInDish() throws Exception {
//
//    }
//
//    @Test
//    public void testDeleteItemFromDish() throws Exception {
//
//    }
}