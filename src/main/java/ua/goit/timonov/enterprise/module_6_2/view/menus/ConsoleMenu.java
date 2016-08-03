package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alex on 03.08.2016.
 */
public class ConsoleMenu {
    private static final String MENU_PATTERN = "%s - %s";

    private String menuName;
    private List<ConsoleMenuItem> items = new ArrayList<>();
    private boolean isExit = false;

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

    public ConsoleMenu addItem(ConsoleMenuItem item) {
        int index = items.size() - 1;
        items.add(index, item);
        return this;
    }

    private void printMenu() {
        List<String> menuLines = new ArrayList<>();
        menuLines.add("\n\tMenu " + menuName + " : \n");
        for (int i = 0; i < items.size(); i++) {
            String itemFormatted = String.format(MENU_PATTERN, (i + 1), items.get(i).getTitle());
            menuLines.add(itemFormatted);
        }
        ConsolePrinter.printList(menuLines);
    }
}
