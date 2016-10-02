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
import ua.goit.timonov.enterprise.model.Ingredient;
import ua.goit.timonov.enterprise.service.StorageService;
import ua.goit.timonov.enterprise.web.validate.IngredientValidate;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ua.goit.timonov.enterprise.web.StorageServiceController.*;

/**
 * Testing class for StorageServiceController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/web-context.xml"})
@WebAppConfiguration
public class StorageServiceControllerTest {
    public static final String MAPPED_PATH = "/service/storage";
    public static final String SALT = "salt";
    public static final String SUGAR = "sugar";
    public static final String WATER = "water";
    public static final String NAME = "name";
    public static final String AMOUNT = "amount";
    public static final String ID = "id";
    @Autowired
    WebApplicationContext wac;

    @InjectMocks
    private StorageServiceController controller;

    @Mock
    private StorageService storageService;

    @Mock
    View mockView;

    MockMvc mockMvc;
    ObjectsFactory objectsFactory;
    private Ingredient salt;
    private Ingredient sugar;
    private Ingredient water;

    @Before
    public void setUp() throws Exception {
        controller = new StorageServiceController();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
        objectsFactory = new ObjectsFactory();
        salt = new Ingredient(SALT, 5000);
        sugar = new Ingredient(SUGAR, 10000);
        water = new Ingredient(WATER, 20000);
    }

    @Test
    public void testGetAllIngredients() throws Exception {
        List<Ingredient> expectedItems = Arrays.asList(salt, sugar, water);
        when(storageService.getAllIngredients()).thenReturn(expectedItems);
        mockMvc.perform(get(MAPPED_PATH + "/ingredients"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(ITEMS, hasSize(3)))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                            hasProperty(NAME, is(SALT)),
                            hasProperty(AMOUNT, is(5000))
                        )
                )))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(NAME, is(SUGAR)),
                                hasProperty(AMOUNT, is(10000))
                        )
                )))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(NAME, is(WATER)),
                                hasProperty(AMOUNT, is(20000))
                        )
                )));
        verify(storageService).getAllIngredients();
        verifyNoMoreInteractions(storageService);
    }

    @Test
    public void testGetIngredientToAdd() throws Exception {
        mockMvc.perform(get(MAPPED_PATH + "/add"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(ITEM_VALIDATE, new IngredientValidate()))
                .andExpect(model().attribute(ONE_ITEM, new Ingredient()))
                .andExpect(view().name(PATH_ADD));
    }

    @Test
    public void testAddIngredientIsNotValid() throws Exception {
        IngredientValidate itemValidate = new IngredientValidate();
        Ingredient item = new Ingredient();
        mockMvc.perform(post(MAPPED_PATH + "/add")
                .sessionAttr(ONE_ITEM, item)
                .sessionAttr(ITEM_VALIDATE, itemValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_ADD))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(ID, is(0))))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(NAME, nullValue())))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(AMOUNT, is(0))));
        verifyZeroInteractions(storageService);
    }

    @Test
    public void testAddIngredientIsValid() throws Exception {
        IngredientValidate itemValidate = new IngredientValidate();
        salt.setId(1);
        sugar.setId(2);
        when(storageService.getAllIngredients()).thenReturn(Arrays.asList(salt, sugar));
        when(storageService.searchByName(SUGAR)).thenReturn(sugar);
        mockMvc.perform(post(MAPPED_PATH + "/add")
                .param(ID, "2")
                .param(NAME, SUGAR)
                .param(AMOUNT, "10000")
                .sessionAttr(ITEM_VALIDATE, itemValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_INGREDIENTS))
                .andExpect(model().attribute(ITEMS, hasSize(2)))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(NAME, is(SALT)),
                                hasProperty(AMOUNT, is(5000))
                        )
                )))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(NAME, is(SUGAR)),
                                hasProperty(AMOUNT, is(10000))
                        )
                )));
        verify(storageService).add(sugar);
        verify(storageService).searchByName(SUGAR);
        verify(storageService).getAllIngredients();
        verifyNoMoreInteractions(storageService);
    }

    @Test
    public void testAskForDeleteIngredientById() throws Exception {
        salt.setId(1);
        sugar.setId(2);
        when(storageService.searchById(2)).thenReturn(sugar);
        mockMvc.perform(get(MAPPED_PATH + "/delete")
                .param(ID, "2")
                .requestAttr(ONE_ITEM, sugar)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DELETE))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(ID, is(2))))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(NAME, is(SUGAR))))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(AMOUNT, is(10000))));
        verify(storageService).searchById(2);
        verifyNoMoreInteractions(storageService);
    }

    @Test
    public void testAskForDeleteIngredientByName() throws Exception {
        salt.setId(1);
        sugar.setId(2);
        when(storageService.searchByName(SUGAR)).thenReturn(sugar);
        mockMvc.perform(get(MAPPED_PATH + "/deleteByName")
                .param(NAME, SUGAR)
                .requestAttr(ONE_ITEM, sugar)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_DELETE))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(ID, is(2))))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(NAME, is(SUGAR))))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(AMOUNT, is(10000))));
        verify(storageService).searchByName(SUGAR);
        verifyNoMoreInteractions(storageService);
    }

    @Test
    public void testDeleteIngredientById() throws Exception {
        salt.setId(1);
        sugar.setId(2);
        water.setId(3);
        List<Ingredient> expectedItems = Arrays.asList(salt, water);
        when(storageService.getAllIngredients()).thenReturn(expectedItems);
        mockMvc.perform(post(MAPPED_PATH + "/deleteConfirmed")
                .param(ID, "2")
                .requestAttr(ITEMS, expectedItems)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_INGREDIENTS))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(NAME, is(SALT)),
                                hasProperty(AMOUNT, is(5000))
                        )
                )))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(NAME, is(WATER)),
                                hasProperty(AMOUNT, is(20000))
                        )
                )));
        verify(storageService).delete(2);
        verify(storageService).getAllIngredients();
        verifyNoMoreInteractions(storageService);
    }

    @Test
    public void testEditIngredientById() throws Exception {
        IngredientValidate itemValidate = new IngredientValidate();
        salt.setId(1);

        when(storageService.searchById(1)).thenReturn(salt);
        mockMvc.perform(get(MAPPED_PATH + "/edit")
                .param("itemId", "1")
                .param(ID, "1")
                .param(NAME, SALT)
                .param(AMOUNT, "5000")
                .requestAttr(ONE_ITEM, salt)
                .requestAttr(ITEM_VALIDATE, itemValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_EDIT))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(ID, is(1))))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(NAME, is(SALT))))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(AMOUNT, is(5000))));
        verify(storageService).searchById(1);
        verifyNoMoreInteractions(storageService);
    }

    @Test
    public void testEditDishByName() throws Exception {
        IngredientValidate itemValidate = new IngredientValidate();
        salt.setId(1);

        when(storageService.searchByName(SALT)).thenReturn(salt);
        mockMvc.perform(get(MAPPED_PATH + "/editByName")
                .param("itemName", SALT)
                .param(ID, "1")
                .param(NAME, SALT)
                .param(AMOUNT, "5000")
                .requestAttr(ONE_ITEM, salt)
                .requestAttr(ITEM_VALIDATE, itemValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_EDIT))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(ID, is(1))))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(NAME, is(SALT))))
                .andExpect(model().attribute(ONE_ITEM, hasProperty(AMOUNT, is(5000))));
        verify(storageService).searchByName(SALT);
        verifyNoMoreInteractions(storageService);
    }

    @Test
    public void testSaveEditIngredient() throws Exception {
        IngredientValidate itemValidate = new IngredientValidate();
        salt.setId(1);
        sugar.setId(2);

        when(storageService.getAllIngredients()).thenReturn(Arrays.asList(salt, sugar));
        when(storageService.searchByName(SUGAR)).thenReturn(sugar);
        mockMvc.perform(post(MAPPED_PATH + "/edit")
                .param("itemId", "2")
                .param(ID, "2")
                .param(NAME, SUGAR)
                .param(AMOUNT, "10000")
                .sessionAttr(ONE_ITEM, sugar)
                .sessionAttr(ITEM_VALIDATE, itemValidate)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_INGREDIENTS))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(NAME, is(SUGAR)),
                                hasProperty(AMOUNT, is(10000))
                        )
                )));
        verify(storageService).searchByName(SUGAR);
        verify(storageService).update(sugar);
        verify(storageService).getAllIngredients();
        verifyNoMoreInteractions(storageService);
    }

    @Test
    public void testFilterByName() throws Exception {
        salt.setId(1);
        sugar.setId(2);
        water.setId(3);
        List<Ingredient> expectedItems = Arrays.asList(salt, sugar);
        when(storageService.filterWithStartChars("s")).thenReturn(expectedItems);
        mockMvc.perform(get(MAPPED_PATH + "/filter")
                .param("name", "s")
        )
                .andExpect(status().isOk())
                .andExpect(view().name(PATH_INGREDIENTS))
                .andExpect(model().attribute(ITEMS, hasSize(2)))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(NAME, is(SALT)),
                                hasProperty(AMOUNT, is(5000))
                        )
                )))
                .andExpect(model().attribute(ITEMS, hasItem(
                        allOf(
                                hasProperty(NAME, is(SUGAR)),
                                hasProperty(AMOUNT, is(10000))
                        )
                )));
        verify(storageService).filterWithStartChars("s");
        verifyNoMoreInteractions(storageService);
    }
}