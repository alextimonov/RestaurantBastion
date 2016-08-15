package ua.goit.timonov.enterprise.module_6_2.view.menus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.timonov.enterprise.module_6_2.exceptions.UserRefuseInputException;
import ua.goit.timonov.enterprise.module_6_2.model.DbItem;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

/**
 * Provides handling for database component with two base methods:
 * - get from DB list of items
 * - add new item
 */
public abstract class DbItemHandlerWithBaseMethods {

    public static final String SUCCESS = "OPERATION SUCCEEDED!";
    public static final String ID = "id";
    public static final String PLURAL_END = "s";

    public static Logger LOGGER = LoggerFactory.getLogger(DbItemHandlerWithBaseMethods.class);

    // DB item's name
    protected String dbItemName;

    // console menu for DB component's handling
    protected ConsoleMenu consoleMenu;

    public ConsoleMenu getConsoleMenu() {
        return consoleMenu;
    }


    /**
     * Constructor with given DB component's name, builds console menu for this component
     * @param dbItemName        DB component's name
     */
    public DbItemHandlerWithBaseMethods(String dbItemName) {
        this.dbItemName = dbItemName;
        String pluralName = dbItemName + PLURAL_END;
        consoleMenu = new ConsoleMenu(pluralName);

        String nameMenuItem = "Get all " + pluralName;
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> getAllDbItems()));

        nameMenuItem = "Add new " + dbItemName;
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> addDbItem()));
    }

    // DB Method #1
    /**
     * gets from DB list of all items and outputs this list or message about error
     */
    public void getAllDbItems() {
        try {
            List<DbItem> itemList = getAllItems();
            outputItemList(itemList);
        } catch (RuntimeException e) {
            LOGGER.error("UNSUCCESSFUL! There's no " + dbItemName + "s in the base!");
        }
    }

    /**
     * gets from DB list of all items for this component
     * @return      list of database items
     */
    protected abstract List<DbItem> getAllItems();

    /**
     * outputs list of database items
     * @param itemList      list of database items
     */
    protected abstract void outputItemList(List<DbItem> itemList);

    // DB Method #2
    /**
     * adds new item to this DB component: provides input data of new item, adds it to DB component
     * or outputs message about error
     */
    public void addDbItem() {
        DbItem newItem = new DbItem();
        try {
            newItem = inputItem();
            ConsoleIO.outputItem(dbItemName + " to add: ", newItem.toString());
            addItem(newItem);
            LOGGER.info(SUCCESS);
        } catch (UserRefuseInputException e) {
            LOGGER.info(e.getMessage());
        } catch (RuntimeException e) {
            ConsoleIO.outputItem(e.getMessage(), newItem.getName());
        }
    }

    /**
     * provides input DB item's data
     * @return      database item
     */
    protected abstract DbItem inputItem();

    /**
     * adds new item to this DB component
     * @param newItem       database i
     */
    protected abstract void addItem(DbItem newItem);
}
