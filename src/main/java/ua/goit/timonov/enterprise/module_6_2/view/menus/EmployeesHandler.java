package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.EmployeeController;
import ua.goit.timonov.enterprise.module_6_2.model.DbItem;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
public class EmployeesHandler extends DbItemHandler {

    public static final String EMPLOYEE = "Employee";

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
    protected List<DbItem> getAllItems() {
        List<Employee> employees = employeeController.getAll();
        List<DbItem> items = employees.stream().collect(Collectors.toList());
        return items;
    }

    @Override
    protected void outputItemList(List<DbItem> itemList) {
        List<Employee> employees = new ArrayList<>();
        for (DbItem dbItem : itemList) {
            employees.add((Employee) dbItem);
        }
        ConsoleIO.outputEmployees(employees);
    }

    @Override
    protected DbItem inputItem() {
        return ConsoleIO.inputEmployee();
    }

    @Override
    protected void addItem(DbItem newItem) {
        employeeController.add((Employee) newItem);
    }

    @Override
    protected DbItem searchItem(int id) {
        return employeeController.search(id);
    }

    @Override
    protected DbItem searchItem(String... name) {
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

