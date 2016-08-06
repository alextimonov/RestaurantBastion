package ua.goit.timonov.enterprise.module_6_2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.goit.timonov.enterprise.module_6_2.view.menus.MainMenu;

/**
 * Restaurant application, uses Spring and JDBC to access to database
 * Main executable class
 */
public class RestaurantApp {

    // main console menu
    private MainMenu mainMenu;

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**
     * starts application
     * @param args      arguments are not expected
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        RestaurantApp restaurantApp = context.getBean(RestaurantApp.class);
        restaurantApp.start();
    }

    // executes main menu
    private void start() {
        mainMenu.run();
    }
}


