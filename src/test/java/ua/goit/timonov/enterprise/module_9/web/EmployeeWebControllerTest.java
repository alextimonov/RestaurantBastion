package ua.goit.timonov.enterprise.module_9.web;

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

/**
 * Created by Alex on 08.09.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:hibernate-context.xml",
        "/../webapp/WEB-INF/web-context.xml"})
@WebAppConfiguration
public class EmployeeWebControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private EmployeeWebController employeeWebController;

    @Autowired
    public void setEmployeeWebController(EmployeeWebController employeeWebController) {
        this.employeeWebController = employeeWebController;
    }

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();

    }

    @Test
    public void testGetEmployeeWithName() throws Exception {

//        RequestBuilder requestBuilder = new MockHttpServletRequestBuilder(, "/employee/{employeeName}");

        employeeWebController.getEmployeeByName("/employee/{employeeName}");
//        ResultMatcher   = new ResultMatcher() {
//            @Override
//            public void match(MvcResult mvcResult) throws Exception {
//
//            }
//        };
//        mockMvc.perform(get("/restEmployees"))
//                .andExpect()
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$"), hasSize(2))
//                .andExpect(jsonPath("$[0].id"), is("la-la-la"));

    }

    @Test
    public void testEmployees1() throws Exception {

    }
}