package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console menu with string items
 */
public class ConsoleMenu {

    private static final String MENU_PATTERN = "%s - %s";
    public static final String SUCCESS = "OPERATION SUCCEEDED!";
    public static final String ID = "id";

    /* menu name */
    private String menuName;

    /* list of menu items */
    private List<ConsoleMenuItem> items = new ArrayList<>();

    /* true if it's necessary to quit from menu */
    private boolean isExit = false;

    /**
     * Menu constructor with one obligate item
     */
    public ConsoleMenu() {
        items.add(new ConsoleMenuItem("Exit") {
            @Override
            public void run() {
                isExit = true;
            }
        });
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * runs menu, prints it, provides ability to make user's choice
     */
    public void run() {
        while (!isExit) {
            printMenu();
            Scanner sc = new Scanner(System.in);
            try {
                String userInput = sc.nextLine();
                int choice = Integer.parseInt(userInput);
                if (choice < 1 || choice > items.size()) {
                    throw new IllegalArgumentException("Choice must be in diapason [1.." + items.size() + "]. Please, repeat!");
                }
                ConsoleMenuItem item = items.get(choice - 1);
                item.run();
            } catch (NumberFormatException e) {
                ConsolePrinter.printLine("Input format error! Please, repeat!");
            } catch (IllegalArgumentException e) {
                ConsolePrinter.printLine(e.getMessage());
            }
        }
    }

    /**
     * adds item to console menu
     * @param item      menu item
     * @return          this console menu with added item
     */
    public ConsoleMenu addItem(ConsoleMenuItem item) {
        int index = items.size() - 1;
        items.add(index, item);
        return this;
    }

    // outputs menu to console
    private void printMenu() {
        List<String> menuLines = new ArrayList<>();
        menuLines.add("\n\tMenu " + menuName + ":");
        for (int i = 0; i < items.size(); i++) {
            String itemFormatted = String.format(MENU_PATTERN, (i + 1), items.get(i).getTitle());
            menuLines.add(itemFormatted);
        }
        ConsolePrinter.printList(menuLines);
    }
}
