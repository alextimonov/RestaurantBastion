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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.goit.timonov.enterprise.web.DishServiceController.DISHES;

/**
 * Testing class for DishWebController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/web-context.xml"})
@WebAppConfiguration
public class DishWebControllerTest {
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
    private DishWebController controller;

    @Mock
    private DishService dishService;

    @Mock
    View mockView;

    MockMvc mockMvc;
    ObjectsFactory objectsFactory;

    @Before
    public void setUp() throws Exception {
        controller = new DishWebController();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
        objectsFactory = new ObjectsFactory();
    }
    @Test
    public void testGetDishes() throws Exception {
        Dish salad = objectsFactory.makeDishSalad();
        Dish soup = objectsFactory.makeDishRiceSoup();
        Dish plov = objectsFactory.makeDishNewPlov();
        List<Dish> expectedDishes = Arrays.asList(salad, soup, plov);
        when(dishService.getAllDishes()).thenReturn(expectedDishes);
        mockMvc.perform(get("/dishes"))
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
    public void testGetDish() throws Exception {
//        Dish soup = objectsFactory.makeDishRiceSoup();
//        soup.setId(1);
//        Dish salad = objectsFactory.makeDishSalad();
//        salad.setId(2);
//        when(dishService.searchDishById(2)).thenReturn(salad);
//        mockMvc.perform(get("/dish/{dishName}")
//                .param(ID, "2")
//                .requestAttr(DISH_TO_DELETE, salad)
//        )
//                .andExpect(status().isOk())
//                .andExpect(view().name(PATH_DELETE))
//                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(ID, is(2))))
//                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(NAME, is(SALAD))))
//                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(DESCRIPTION, is("light salad with delicious vegetables"))))
//                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(WEIGHT, is(250))))
//                .andExpect(model().attribute(DISH_TO_DELETE, hasProperty(COST, is(45.0F))));
//        verify(dishService).searchDishById(2);
//        verifyNoMoreInteractions(dishService);
    }

//    @RequestMapping(value = "/dish/{dishName}", method = RequestMethod.GET)
//    public ModelAndView getDish(@PathVariable("dishName") String dishName) {
//        ModelAndView modelAndView = new ModelAndView(DISH);
//        Dish dish = dishService.getDishByName(dishName);
//        modelAndView.addObject(DISH, dish);
//        List<IngredientsInDish> items = dishService.getIngredientsInDish(dish);
//        modelAndView.addObject("itemsInDish", items);
//        return modelAndView;
//    }

    @Test
    public void testSearchDishParam() throws Exception {

    }
}