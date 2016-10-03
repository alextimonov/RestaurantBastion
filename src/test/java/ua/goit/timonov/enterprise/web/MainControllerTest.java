package ua.goit.timonov.enterprise.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ua.goit.timonov.enterprise.web.MainController.*;

/**
 * Testing class for MainController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/web-context.xml"})
@WebAppConfiguration
public class MainControllerTest {

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    public void testGetMainPage() throws Exception {
        mockMvc.perform(get(SLASH_MAIN))
                .andExpect(status().isOk())
                .andExpect(view().name(MAIN));
    }

    @Test
    public void testService() throws Exception {
        mockMvc.perform(get(SLASH_SERVICE))
                .andExpect(status().isOk())
                .andExpect(view().name(SERVICE))
                .andExpect(model().attribute(CURRENT_TIME, is(new Date().toString())));
    }

    @Test
    public void testInvokeSchemePage() throws Exception {
        mockMvc.perform(get(SLASH_SCHEME))
                .andExpect(status().isOk())
                .andExpect(view().name(SCHEME));
    }

    @Test
    public void testGetPageContacts() throws Exception {
        mockMvc.perform(get(SLASH_CONTACTS))
                .andExpect(status().isOk())
                .andExpect(view().name(CONTACTS));
    }
}