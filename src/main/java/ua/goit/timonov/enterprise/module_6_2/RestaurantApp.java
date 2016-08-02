package ua.goit.timonov.enterprise.module_6_2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.goit.timonov.enterprise.module_6_2.controllers.*;
import ua.goit.timonov.enterprise.module_6_2.model.*;
import ua.goit.timonov.enterprise.module_6_2.view.console.*;

import java.util.List;

/**
 * Created by Alex on 30.07.2016.
 */
public class RestaurantApp {

    private EmployeeController employeeController;
    private DishController dishController;
    private MenuController menuController;
    private OrderController orderController;
    private CookedDishController cookedDishController;
    private StorageController storageController;

//    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantApp.class);

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void setCookedDishController(CookedDishController cookedDishController) {
        this.cookedDishController = cookedDishController;
    }

    public void setStorageController(StorageController storageController) {
        this.storageController = storageController;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        RestaurantApp restaurantApp = context.getBean(RestaurantApp.class);
        restaurantApp.start();
    }

    private void start() {

        // EMPLOYEES

        // get all list
        List<Employee> staff = employeeController.getAll();
        EmployeesIO.outputList(staff);

        // add new employee
        Employee newEmployee = EmployeesIO.inputEmployee();
        EmployeesIO.output("Employee to add:", newEmployee);
        employeeController.add(newEmployee);

        staff = employeeController.getAll();
        EmployeesIO.outputList(staff);

        // find employee
        String nameToSearch = EmployeesIO.inputString("surname");
        String nameToFind = EmployeesIO.inputString("name");
        Employee foundEmployee = employeeController.find(nameToSearch, nameToFind);
        EmployeesIO.output("Found employee: ", foundEmployee);

        // delete chosen employee
        employeeController.delete(foundEmployee);
        EmployeesIO.output("Deleted employee: ", foundEmployee);

        staff = employeeController.getAll();
        EmployeesIO.outputList(staff);

        // DISHES
        // get all list
        List<Dish> dishes = dishController.getAll();
        DishIO.outputList(dishes);

        // add new dish
        Dish newDish = DishIO.inputDish();
        DishIO.output("Dish to add: ", newDish);
        dishController.add(newDish);

        dishes = dishController.getAll();
        DishIO.outputList(dishes);

        // find dish
        nameToSearch = DishIO.inputString("name");
        Dish foundDish = dishController.find(nameToSearch);
        DishIO.output("Found dish: ", foundDish);

        // delete chosen dish
        dishController.delete(foundDish);
        DishIO.output("Deleted dish: ", foundDish);

        dishes = dishController.getAll();
        DishIO.outputList(dishes);


        // MENUS
        // get all list

        List<Menu> menus = menuController.getAll();
        MenuIO.outputList(menus);

        // add new menu
        Menu newMenu = MenuIO.inputMenu();
        MenuIO.output("Menu to add: ", newMenu);
        menuController.add(newMenu);

        menus = menuController.getAll();
        MenuIO.outputList(menus);

        // find menu
        nameToSearch = MenuIO.inputString("name");
        Menu foundMenu = menuController.find(nameToSearch);
        MenuIO.output("Found menu: ", foundMenu);

        // delete chosen menu
        menuController.delete(foundMenu);
        MenuIO.output("Deleted menu: ", foundMenu);

        menus = menuController.getAll();
        MenuIO.outputList(menus);

        // add new dish to menu

        String menuName = MenuIO.inputString("name");
        newDish = DishIO.inputDish();
        dishController.add(newDish);
        newDish = dishController.find(newDish.getName());
        menuController.addDish(menuName, newDish);

        // add existing dish to menu
        menuName = MenuIO.inputString("name");
        String dishName =  DishIO.inputString("name");
        menuController.addDish(menuName, dishName);

        // delete dish from menu
        menuName = MenuIO.inputString("name");
        dishName =  DishIO.inputString("name");
        menuController.deleteDish(menuName, dishName);

        menus = menuController.getAll();
        MenuIO.outputList(menus);

        // ORDERS

        // get open orders
        List<Order> openOrders = orderController.getOpenOrders();
        OrderIO.outputList("open", openOrders);

        // get closed orders
        List<Order> closedOrders = orderController.getClosedOrders();
        OrderIO.outputList("closed", closedOrders);

        // add new OPEN order
        Order newOrder = OrderIO.inputOrder();
        OrderIO.output("Order to add: ", newOrder);
        orderController.add(newOrder);

        openOrders = orderController.getOpenOrders();
        OrderIO.outputList("open", openOrders);

        // delete chosen OPEN order
        int orderId = OrderIO.inputOrderId();
        orderController.delete(orderId);

        openOrders = orderController.getOpenOrders();
        OrderIO.outputList("open", openOrders);

        // set order to closed
        orderId = OrderIO.inputOrderId();
        orderController.setClosed(orderId);

        // add dish to open order
//        int orderId, String dishName

        orderId = OrderIO.inputOrderId();
        dishName = DishIO.inputString("dish name to add");
        orderController.addDish(orderId, dishName);

        openOrders = orderController.getOpenOrders();
        OrderIO.outputList("open", openOrders);

        // delete dish from open order

        orderId = OrderIO.inputOrderId();
        dishName = DishIO.inputString("dish name to delete");
        orderController.deleteDish(orderId, dishName);

        openOrders = orderController.getOpenOrders();
        OrderIO.outputList("open", openOrders);


        // COOKED DISHES

        // get all list
        List<CookedDish> cookedDishes = cookedDishController.getAll();
        CookedDishIO.outputList(cookedDishes);

        // add by orderedDishId, cookId

        int orderedDishId = CookedDishIO.inputId("cooked dish's");
        int cookId = CookedDishIO.inputId("cook's");
        cookedDishController.add(orderedDishId, cookId);

        // add by orderId, dishName, cookId

        orderId = CookedDishIO.inputId("order's");
        dishName = CookedDishIO.inputString("dish name");
        cookId = CookedDishIO.inputId("cook's");
        cookedDishController.add(orderId, dishName, cookId);

        cookedDishes = cookedDishController.getAll();
        CookedDishIO.outputList(cookedDishes);

        // STORAGE

        // get all ingredients
        List<Ingredient> ingredients = storageController.getAll();
        StorageIO.outputList(ingredients);

        // add new ingredient
        Ingredient newIngredient = StorageIO.inputIngredient();
        StorageIO.output("Ingredient to add: ", newIngredient);
        storageController.add(newIngredient);

        ingredients = storageController.getAll();
        StorageIO.outputList(ingredients);

        // find ingredient by name
        nameToSearch = StorageIO.inputString("name");
        Ingredient foundIngredient = storageController.find(nameToSearch);
        StorageIO.output("Found ingredient: ", foundIngredient);

        // delete ingredient by name
        String nameToDelete = StorageIO.inputString("name");
        storageController.delete(nameToDelete);

        ingredients = storageController.getAll();
        StorageIO.outputList(ingredients);

        // change amount

        String nameToChangeAmount = StorageIO.inputString("name");
        int difference = StorageIO.inputInteger("difference");
        storageController.changeAmount(nameToChangeAmount, difference);

        //  get terminating ingredients
        int limit = StorageIO.inputInteger("limit");
        ingredients = storageController.getTerminatingIngredients(limit);
        StorageIO.outputList(ingredients);
    }
}

