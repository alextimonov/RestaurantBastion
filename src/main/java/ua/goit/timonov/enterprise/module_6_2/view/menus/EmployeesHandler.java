package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.EmployeeController;
import ua.goit.timonov.enterprise.module_6_2.exceptions.UserRefuseInputException;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

/**
 * Handler for tasks with DB Restaurant's component Employee
 * with implementation of methods from DbItemHandler:
 * - get from DB list of all employees
 * - add new employee to DB
 * - search employee by ID
 * - search employee by full name (surname & name)
 * - delete employee from DB by ID
 * - search employee from DB by full name (surname & name)
 */
public class EmployeesHandler extends DbItemHandler<Employee> {

    public static final String EMPLOYEE = "Employee";
    public static final String SURNAME = "surname";

    /**
     * Uses inherited constructor with setting component's name
     */
    public EmployeesHandler() {
        super(EMPLOYEE);
    }

    private EmployeeController employeeController;

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    // implementation of inherited methods from DbItemHandler
    @Override
    protected List<Employee> getAllItems() {
        return employeeController.getAll();
    }

    @Override
    protected void outputItemList(List<Employee> employees) {
        ConsoleIO.outputEmployees(employees);
    }

    @Override
    protected String getName(Employee employee) {
        return employee.getName();
    }

    @Override
    protected Employee inputItem() {
        return ConsoleIO.inputEmployee();
    }

    @Override
    protected void addItem(Employee employee) {
        employeeController.add(employee);
    }

    @Override
    protected Employee searchItem(int id) {
        return employeeController.search(id);
    }

    public void searchDbItemByName() {
        try {
            String name = ConsoleIO.inputString(dbItemName, NAME);
            String surname = ConsoleIO.inputString(dbItemName, SURNAME);
            Employee foundItem = searchItem(name, surname);
            ConsoleIO.outputItem(SUCCESS + " Found "+ dbItemName + COLON, foundItem.toString());
        }
        catch (UserRefuseInputException e) {
            LOGGER.info(e.getMessage());
        }
        catch (RuntimeException e) {
            ConsoleIO.outputItem(NO_SUCCESS, NAME + " " + SURNAME);
        }
    }

    @Override
    protected Employee searchItem(String... name) {
        return employeeController.search(name[0], name[1]);
    }

    @Override
    protected void deleteItem(int id) {
        employeeController.delete(id);
    }

    @Override
    protected void deleteItem(String... name) {
        employeeController.delete(name[0], name[1]);
    }
}

