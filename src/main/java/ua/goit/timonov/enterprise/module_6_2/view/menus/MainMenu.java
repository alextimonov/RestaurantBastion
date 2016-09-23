package ua.goit.timonov.enterprise.module_6_2.view.menus;

/**
 * Main console menu
 */
public class MainMenu extends ConsoleMenu {

    /* console menus for different groups of tasks */
    private EmployeesHandler employeesHandler;
    private DishesHandler dishesHandler;
    private MenusHandler menusHandler;
    private OrdersHandler ordersHandler;
    private StorageHandler storageHandler;
    private CookedDishHandler cookedDishHandler;

    public void setEmployeesHandler(EmployeesHandler employeesHandler) {
        this.employeesHandler = employeesHandler;
    }

    public void setDishesHandler(DishesHandler dishesHandler) {
        this.dishesHandler = dishesHandler;
    }

    public void setMenusHandler(MenusHandler menusHandler) {
        this.menusHandler = menusHandler;
    }

    public void setOrdersHandler(OrdersHandler ordersHandler) {
        this.ordersHandler = ordersHandler;
    }

    public void setStorageHandler(StorageHandler storageHandler) {
        this.storageHandler = storageHandler;
    }

    public void setCookedDishHandler(CookedDishHandler cookedDishHandler) {
        this.cookedDishHandler = cookedDishHandler;
    }

    /**
     * configures main menu's items
     */
    public MainMenu() {
        addItem(new ConsoleMenuItem("Employees", () -> runConsoleMenu(employeesHandler)));
        addItem(new ConsoleMenuItem("Dishes", () -> runConsoleMenu(dishesHandler)));
        addItem(new ConsoleMenuItem("Menus", () -> runConsoleMenu(menusHandler)));
        addItem(new ConsoleMenuItem("Orders", () -> runConsoleMenu(ordersHandler)));
        addItem(new ConsoleMenuItem("Cooked Dishes", () -> runConsoleMenu(cookedDishHandler)));
        addItem(new ConsoleMenuItem("Storage", () -> runConsoleMenu(storageHandler)));
    }

    private void runConsoleMenu(DbItemHandlerWithBaseMethods handler) {
        ConsoleMenu consoleMenu = handler.getConsoleMenu();
        consoleMenu.run();
    }
}
