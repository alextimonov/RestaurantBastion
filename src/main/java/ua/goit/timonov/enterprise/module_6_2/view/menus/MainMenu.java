package ua.goit.timonov.enterprise.module_6_2.view.menus;

/**
 * Created by Alex on 03.08.2016.
 */
public class MainMenu extends ConsoleMenu {

    private EmployeesConsoleMenu employeesConsoleMenu;
    private DishesConsoleMenu dishesConsoleMenu;
    private MenusConsoleMenu menusConsoleMenu;
    private OrdersConsoleMenu ordersConsoleMenu;
    private CookedDishConsoleMenu cookedDishConsoleMenu;
    private StorageConsoleMenu storageConsoleMenu;

    public void setEmployeesConsoleMenu(EmployeesConsoleMenu employeesConsoleMenu) {
        this.employeesConsoleMenu = employeesConsoleMenu;
    }

    public void setDishesConsoleMenu(DishesConsoleMenu dishesConsoleMenu) {
        this.dishesConsoleMenu = dishesConsoleMenu;
    }

    public void setMenusConsoleMenu(MenusConsoleMenu menusConsoleMenu) {
        this.menusConsoleMenu = menusConsoleMenu;
    }

    public void setOrdersConsoleMenu(OrdersConsoleMenu ordersConsoleMenu) {
        this.ordersConsoleMenu = ordersConsoleMenu;
    }

    public void setCookedDishConsoleMenu(CookedDishConsoleMenu cookedDishConsoleMenu) {
        this.cookedDishConsoleMenu = cookedDishConsoleMenu;
    }

    public void setStorageConsoleMenu(StorageConsoleMenu storageConsoleMenu) {
        this.storageConsoleMenu = storageConsoleMenu;
    }

    public MainMenu() {
        super();
        addItem(new ConsoleMenuItem("Employees") {
            @Override
            public void run() {
                employeesConsoleMenu.run();
            }
        });
        addItem(new ConsoleMenuItem("Dishes") {
            @Override
            public void run() {
                dishesConsoleMenu.run();
            }
        });

        addItem(new ConsoleMenuItem("Menus") {
            @Override
            public void run() {
                menusConsoleMenu.run();
            }
        });

        addItem(new ConsoleMenuItem("Orders") {
            @Override
            public void run() {
                ordersConsoleMenu.run();
            }
        });

        addItem(new ConsoleMenuItem("Cooked dishes") {
            @Override
            public void run() {
                cookedDishConsoleMenu.run();
            }
        });

        addItem(new ConsoleMenuItem("Storage") {
            @Override
            public void run() {
                storageConsoleMenu.run();
            }
        });


    }
}
