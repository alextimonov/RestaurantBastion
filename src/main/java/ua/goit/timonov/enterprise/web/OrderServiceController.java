package ua.goit.timonov.enterprise.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.goit.timonov.enterprise.exceptions.NoItemInDbException;
import ua.goit.timonov.enterprise.model.Employee;
import ua.goit.timonov.enterprise.service.EmployeeService;
import ua.goit.timonov.enterprise.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Spring MVC controller for mapping order's service pages
 */
@Controller
@RequestMapping("/service/order")
public class OrderServiceController {

    public static final String ITEMS = "orders";
    public static final String PATH_ORDERS = "service/order/orders";
    public static final String PATH_DISHES = "service/order/dishesInOrder";
    public static final String PATH_ERROR = "service/errorMessage";
    public static final String ERROR_MESSAGE = "errorMessage";

    private OrderService orderService;
    private EmployeeService employeeService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getAllIngredients(Map<String, Object> model) {
        model.put(ITEMS, orderService.getAllOrders());
        return PATH_ORDERS;
    }

    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    public String getOrderDishes(Map<String, Object> model, @RequestParam(value="order", required=true) Integer id) {
        model.put("order", orderService.getOrder(id));
        return PATH_DISHES;
    }

    @RequestMapping(value = "/filterByDate", method = RequestMethod.GET)
    public String filterByDate(Map<String, Object> model, @RequestParam(value="date", required=true) Date date) {
        model.put(ITEMS, orderService.filterByDate(date));
        return PATH_ORDERS;
    }

    @RequestMapping(value = "/filterByWaiter", method = RequestMethod.GET)
    public String filterByName(Map<String, Object> model, @RequestParam(value="waiterName", required=true) String waiterName) {
        try {
            Employee waiter = employeeService.getEmployeeByName(waiterName);
            model.put(ITEMS, orderService.filterByWaiter(waiter));
            return PATH_ORDERS;
        } catch (NoItemInDbException e) {
            model.put(ERROR_MESSAGE, e.getMessage());
            return PATH_ERROR;
        }
    }

    @RequestMapping(value = "/filterByTableNumber", method = RequestMethod.GET)
    public String filterByName(Map<String, Object> model, @RequestParam(value="tableNumber", required=true) Integer tableNumber) {
        model.put(ITEMS, orderService.filterByTableNumber(tableNumber));
        return PATH_ORDERS;
    }

    @InitBinder
    public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
