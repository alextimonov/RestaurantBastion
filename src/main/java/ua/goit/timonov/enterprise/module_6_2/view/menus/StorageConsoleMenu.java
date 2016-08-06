package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.StorageController;
import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

import static ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter.printLine;

/**
 * Console menu for tasks with ingredient storage
 */
public class StorageConsoleMenu extends ConsoleMenu {

    public static final String NAME = "name";
    public static final String INGREDIENT = "ingredient";
    public static final String ID = "id";
    public static final String AMOUNT = "amount";
    public static final String LIMIT = "limit";
    public static final String DIFF = "difference";
    public static final String NO_SUCCESS = "UNSUCCESSFUL! There's no ingredient with such ";

    /* controller for tasks with ingredient storage */
    private StorageController storageController;

    public void setStorageController(StorageController storageController) {
        this.storageController = storageController;
    }

    /**
     * configures menu's items:
     * - get from DB list of all ingredients
     * - add new ingredient to DB
     * - search ingredient by ID
     * - search ingredient by name
     * - delete ingredient from DB by ID
     * - search ingredient from DB by name
     * - change ingredient's amount in the storage
     * - get terminating ingredients in the storage
     */
    public StorageConsoleMenu() {
        addItem(new ConsoleMenuItem("Get all ingredients") {
            @Override
            public void run() {
                try {
                    List<Ingredient> ingredients = storageController.getAll();
                    ConsoleIO.outputIngredients(ingredients);
                } catch (RuntimeException e) {
                    printLine("UNSUCCESSFUL! There's no ingredients in the base!");
                }
            }
        });

        addItem(new ConsoleMenuItem("Add new ingredient") {
            @Override
            public void run() {
                Ingredient ingredient = ConsoleIO.inputIngredient();
                ConsoleIO.outputItem("Ingredient to add: ", ingredient.toString());
                try {
                    storageController.add(ingredient);
                    printLine(SUCCESS);
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(e.getMessage(), ingredient.getName());
                }
            }
        });

        addItem(new ConsoleMenuItem("Search ingredient by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(INGREDIENT, ID);
                try {
                    Ingredient foundIngredient = storageController.search(id);
                    ConsoleIO.outputItem(SUCCESS + " Found ingredient: ", foundIngredient.toString());
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + ID, String.valueOf(id));
                }
            }
        });

        addItem(new ConsoleMenuItem("Search ingredient by name") {
            @Override
            public void run() {
                String nameToSearch = ConsoleIO.inputString(INGREDIENT, NAME);
                try {
                    Ingredient foundIngredient = storageController.search(nameToSearch);
                    ConsoleIO.outputItem("Found ingredient: ", foundIngredient.toString());
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + NAME, nameToSearch);
                }
            }
        });

        addItem(new ConsoleMenuItem("Delete ingredient by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(INGREDIENT, ID);
                try {
                    storageController.delete(id);
                    printLine(SUCCESS);
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + ID, String.valueOf(id));
                }
            }
        });

        addItem(new ConsoleMenuItem("Delete ingredient by name") {
            @Override
            public void run() {
                String nameToDelete = ConsoleIO.inputString(INGREDIENT, NAME);
                try {
                    storageController.delete(nameToDelete);
                    printLine(SUCCESS);
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + NAME, String.valueOf(nameToDelete));
                }
            }
        });

        addItem(new ConsoleMenuItem("Change ingredient's amount ") {
            @Override
            public void run() {
                String nameToChangeAmount = ConsoleIO.inputString(INGREDIENT, NAME);
                Integer diff = ConsoleIO.inputInteger(INGREDIENT, DIFF);
                try {
                    storageController.changeAmount(nameToChangeAmount, diff);
                    Ingredient ingredient = storageController.search(nameToChangeAmount);
                    ConsoleIO.outputItem(SUCCESS + AMOUNT, ingredient.toString());
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + AMOUNT, String.valueOf(diff));
                }
            }
        });

        addItem(new ConsoleMenuItem("Get terminating ingredients") {
            @Override
            public void run() {
                Integer limit = ConsoleIO.inputInteger(INGREDIENT, LIMIT);
                try {
                    List<Ingredient> ingredients = storageController.getTerminatingIngredients(limit);
                    ConsoleIO.outputIngredients(ingredients);
                } catch (RuntimeException e) {
                    printLine("UNSUCCESSFUL! There's no terminating ingredients in the base!");
                }
            }
        });
    }
}
