package ua.goit.timonov.enterprise.module_6_2.view.menus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.timonov.enterprise.module_6_2.controllers.StorageController;
import ua.goit.timonov.enterprise.module_6_2.exceptions.UserRefuseInputException;
import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

/**
 * Handler for tasks with DB Restaurant's component Ingredient
 * with implementation of methods from DbItemHandler:
 * - get from DB list of all ingredients
 * - add new ingredient to DB
 * - search ingredient by ID
 * - search ingredient by name
 * - delete ingredient from DB by ID
 * - search ingredient from DB by name
 * and additional tasks:
 * - change ingredient's amount in the storage
 * - get terminating ingredients in the storage
 */
public class StorageHandler extends DbItemHandler<Ingredient> {

    public static final String INGREDIENT = "Ingredient";
    public static final String AMOUNT = "amount";
    public static final String LIMIT = "limit";
    public static final String DIFF = "difference";

    public static Logger LOGGER = LoggerFactory.getLogger(StorageHandler.class);

    private StorageController storageController;

    public void setStorageController(StorageController storageController) {
        this.storageController = storageController;
    }

    /**
     * Uses inherited constructor with setting component's name
     * and adds to handler additional methods
     */
    public StorageHandler() {
        super(INGREDIENT);

        String nameMenuItem = "Change ingredient's amount";
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> changeAmount()));

        nameMenuItem = "Get terminating ingredients";
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> searchTerminating()));
    }

    // changes amount of ingredient
    // provides user input of ingredient's name and difference, outputs message about success or error
    private void changeAmount() {
        int diff = 0;
        try {
            String nameToChangeAmount = ConsoleIO.inputString(INGREDIENT, NAME);
            diff = ConsoleIO.inputInteger(INGREDIENT, DIFF);
            storageController.changeAmount(nameToChangeAmount, diff);
            Ingredient ingredient = storageController.search(nameToChangeAmount);
            ConsoleIO.outputItem(SUCCESS + AMOUNT, ingredient.toString());
        }
        catch (UserRefuseInputException e) {
            LOGGER.info(e.getMessage());
        }
        catch (RuntimeException e) {
            ConsoleIO.outputItem(NO_SUCCESS + AMOUNT, String.valueOf(diff));
        }
    }

    // searches ingredients with terminating amount, less than inputted limit
    // provides user input of limit, outputs message about success or error
    private void searchTerminating() {
        try {
            int limit = ConsoleIO.inputInteger(INGREDIENT, LIMIT);
            List<Ingredient> ingredients = storageController.getTerminatingIngredients(limit);
            ConsoleIO.outputIngredients(ingredients);
        }
        catch (UserRefuseInputException e) {
            LOGGER.info(e.getMessage());
        }
        catch (RuntimeException e) {
            LOGGER.info("UNSUCCESSFUL! There's no terminating ingredients in the base!");
        }
    }

    // implementation of inherited methods from DbItemHandler
    @Override
    protected List<Ingredient> getAllItems() {
        return storageController.getAll();
    }

    @Override
    protected void outputItemList(List<Ingredient> ingredients) {
        ConsoleIO.outputIngredients(ingredients);
    }

    @Override
    protected String getName(Ingredient ingredient) {
        return ingredient.getName();
    }

    @Override
    protected Ingredient inputItem() {
        return ConsoleIO.inputIngredient();
    }

    @Override
    protected void addItem(Ingredient ingredient) {
        storageController.add(ingredient);
    }

    @Override
    protected Ingredient searchItem(int id) {
        return storageController.search(id);
    }

    @Override
    protected Ingredient searchItem(String... name) {
        return storageController.search(name[0]);
    }

    @Override
    protected void deleteItem(int id) {
        storageController.delete(id);
    }

    @Override
    protected void deleteItem(String... name) {
        storageController.delete(name[0]);
    }
}
