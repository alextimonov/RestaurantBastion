package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.StorageController;
import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

/**
 * Created by Alex on 03.08.2016.
 */
public class StorageConsoleMenu extends ConsoleMenu {

    public static final String NAME = "name";
    public static final String INGREDIENT = "ingredient";
    public static final String ID = "id";
    public static final String AMOUNT = "amount";
    public static final String LIMIT = "limit";
    public static final String DIFF = "difference";
    private StorageController storageController;

    public void setStorageController(StorageController storageController) {
        this.storageController = storageController;
    }

    public StorageConsoleMenu() {
        addItem(new ConsoleMenuItem("Get all ingredients") {
            @Override
            public void run() {
                List<Ingredient> ingredients = storageController.getAll();
                ConsoleIO.outputIngredients(ingredients);
            }
        });

        addItem(new ConsoleMenuItem("Add new ingredient") {
            @Override
            public void run() {
                Ingredient ingredient = ConsoleIO.inputIngredient();
                ConsoleIO.outputItem("Ingredient to add: ", ingredient.toString());
                storageController.add(ingredient);
            }
        });

        addItem(new ConsoleMenuItem("Search ingredient by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(INGREDIENT, ID);
                Ingredient foundIngredient = storageController.search(id);
                ConsoleIO.outputItem("Found ingredient: ", foundIngredient.toString());
            }
        });
        addItem(new ConsoleMenuItem("Search ingredient by name") {
            @Override
            public void run() {
                String nameToSearch = ConsoleIO.inputString(INGREDIENT, NAME);
                Ingredient foundIngredient = storageController.search(nameToSearch);
                ConsoleIO.outputItem("Found ingredient: ", foundIngredient.toString());
            }
        });

        addItem(new ConsoleMenuItem("Delete ingredient by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(INGREDIENT, ID);
                storageController.delete(id);
            }
        });

        addItem(new ConsoleMenuItem("Delete ingredient by name") {
            @Override
            public void run() {
                String nameToDelete = ConsoleIO.inputString(INGREDIENT, NAME);
                storageController.delete(nameToDelete);
            }
        });

        addItem(new ConsoleMenuItem("Change ingredient's amount ") {
            @Override
            public void run() {
                String nameToChangeAmount = ConsoleIO.inputString(INGREDIENT, NAME);
                Integer diff = ConsoleIO.inputInteger(INGREDIENT, DIFF);
                storageController.changeAmount(nameToChangeAmount, diff);
            }
        });

        addItem(new ConsoleMenuItem("Get terminating ingredients") {
            @Override
            public void run() {
                Integer limit = ConsoleIO.inputInteger(INGREDIENT, LIMIT);
                List<Ingredient> ingredients = storageController.getTerminatingIngredients(limit);
                ConsoleIO.outputIngredients(ingredients);
            }
        });
    }
}
