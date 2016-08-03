package ua.goit.timonov.enterprise.module_6_2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.goit.timonov.enterprise.module_6_2.view.menus.MainMenu;

/**
 * Created by Alex on 30.07.2016.
 */
public class RestaurantApp {

    private MainMenu mainMenu;

//    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantApp.class);

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        RestaurantApp restaurantApp = context.getBean(RestaurantApp.class);
        restaurantApp.start();
    }

    private void start() {
        mainMenu.run();
    }
}


