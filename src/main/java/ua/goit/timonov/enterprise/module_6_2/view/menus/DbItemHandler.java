package ua.goit.timonov.enterprise.module_6_2.view.menus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.timonov.enterprise.module_6_2.exceptions.UserRefuseInputException;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

/**
 * Provides handling for database component with two base methods:
 * - get from DB list of items in parent class DbItemHandlerWithBaseMethods
 * - add new item from in parent DbItemHandlerWithBaseMethods
 * - search item by its ID
 * - search item by its name
 * - delete item from DB by ID
 * - search item from DB by name
 */
public abstract class DbItemHandler<T> extends DbItemHandlerWithBaseMethods<T> {

    public static final String SUCCESS = "OPERATION SUCCEEDED!";
    public static final String NO_SUCCESS = "UNSUCCESSFUL! There's no item with such ";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String COLON = ": ";

    public static Logger LOGGER = LoggerFactory.getLogger(DbItemHandler.class);

    /**
     * Constructor with given DB component's name, builds console menu for this component
     * @param dbItemName        DB component's name
     */
    public DbItemHandler(String dbItemName) {
        super(dbItemName);

        String nameMenuItem = "Search " + dbItemName + " by id";
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> searchDbItemById()));

        nameMenuItem = "Search " + dbItemName + " by name";
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> searchDbItemByName()));

        nameMenuItem = "Delete " + dbItemName + " by id";
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> deleteDbItemById()));

        nameMenuItem = "Delete " + dbItemName + " by name";
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> deleteDbItemByName()));
    }

    // DB Method #3
    /**
     * searches item by ID and outputs found item or message about error
     */
    public void searchDbItemById() {
        int id = 0;
        try {
            id = ConsoleIO.inputInteger(dbItemName, ID);
            T foundItem = searchItem(id);
            ConsoleIO.outputItem(SUCCESS + " Found "+ dbItemName + COLON, foundItem.toString());
        }
        catch (UserRefuseInputException e) {
            LOGGER.info(e.getMessage());
        }
        catch (RuntimeException e) {
            ConsoleIO.outputItem(NO_SUCCESS + ID, String.valueOf(id));
        }
    }

    /**
     * searches item by its ID
     * @param id        item's to search
     * @return          found DB item
     */
    protected abstract T searchItem(int id);

    // DB Method #4
    /**
     * searches item by name and outputs found item or message about error
     */
    public void searchDbItemByName() {
        String nameToSearch = "";
        try {
            nameToSearch = ConsoleIO.inputString(dbItemName, NAME);
            T foundItem = searchItem(nameToSearch);
            ConsoleIO.outputItem(SUCCESS + " Found "+ dbItemName + COLON, foundItem.toString());
        }
        catch (UserRefuseInputException e) {
            LOGGER.info(e.getMessage());
        }
        catch (RuntimeException e) {
            ConsoleIO.outputItem(NO_SUCCESS + NAME, nameToSearch);
        }
    }

    /**
     * searches item by name or a few names
     * @param name      item's name (or a few names) to search
     * @return          found DB item
     */
    protected abstract T searchItem(String... name);

    // DB Method #5
    /**
     * deletes item by ID and outputs message about success or error
     */
    public void deleteDbItemById() {
        int id = 0;
        try {
            id = ConsoleIO.inputInteger(dbItemName, ID);
            deleteItem(id);
            LOGGER.info(SUCCESS);
        }
        catch (UserRefuseInputException e) {
            LOGGER.info(e.getMessage());
        }
        catch (RuntimeException e) {
            ConsoleIO.outputItem(NO_SUCCESS + ID, String.valueOf(id));
        }
    }

    /**
     * deletes item by ID
     * @param id        item's ID to delete
     */
    protected abstract void deleteItem(int id);

    // Method #6
    /**
     * deletes item by name and outputs message about success or error
     */
    public void deleteDbItemByName() {
        String nameToDelete = "";
        try {
            nameToDelete = ConsoleIO.inputString(dbItemName, NAME);
            deleteItem(nameToDelete);
            LOGGER.info(SUCCESS);
        }
        catch (UserRefuseInputException e) {
            LOGGER.info(e.getMessage());
        }
        catch (RuntimeException e) {
            ConsoleIO.outputItem(NO_SUCCESS + NAME, nameToDelete);
        }
    }

    /**
     * deletes item by name (or few names)
     * @param name      one or a few item's names to delete
     */
    protected abstract void deleteItem(String... name);

}
