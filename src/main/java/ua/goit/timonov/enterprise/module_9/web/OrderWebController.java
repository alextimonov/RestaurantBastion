package ua.goit.timonov.enterprise.module_9.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.timonov.enterprise.module_6_2.model.Order;
import ua.goit.timonov.enterprise.module_9.service.OrderService;

import java.io.IOException;
import java.util.List;

/**
 * Controller for mapping requests to Order pages
 */
@RestController
public class OrderWebController {

    private OrderService orderService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/restOrders", method = RequestMethod.GET)
    public ModelAndView restOrders() throws IOException {
        List<Order> orders = orderService.getAllOrders();
        return createModelAndView(orders);
    }

    @RequestMapping(value = "/restOrders/open", method = RequestMethod.GET)
    public ModelAndView restOpenOrders() throws IOException {
        List<Order> orders = orderService.getOpenOrders();
        return createModelAndView(orders);
    }

    @RequestMapping(value = "/restOrders/closed", method = RequestMethod.GET)
    public ModelAndView restClosedOrders() throws IOException {
        List<Order> orders = orderService.getClosedOrders();
        return createModelAndView(orders);
    }

    private ModelAndView createModelAndView(List<Order> orders) throws IOException {
        ModelAndView modelAndView = new ModelAndView("restOrders");
        modelAndView.addObject("orders", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(orders));
        return modelAndView;
    }

    @RequestMapping(value = "/restOrders/{orderId}", method = RequestMethod.GET)
    public ModelAndView getOrderById(@PathVariable Integer orderId) throws JsonProcessingException {
        String jsonOrder = getOrderFromDatabase(orderId);
        ModelAndView modelAndView = new ModelAndView("restOrders");
        modelAndView.addObject("orders", jsonOrder);
        return modelAndView;
    }

    private String getOrderFromDatabase(int orderId) {
        try {
            Order order = orderService.searhById(orderId);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order);
        }
        catch (RuntimeException | JsonProcessingException e) {
            return "{Error:There's no order with id = " + orderId + " in database!}";
        }
    }
}
