package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.EmployeeController;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

/**
 * Created by Alex on 03.08.2016.
 */
public class EmployeesConsoleMenu extends ConsoleMenu {
    public static final String EMPLOYEE = "employee";
    public static final String ID = "id";
    public static final String SURNAME = "surname";
    public static final String NAME = "name";


    private EmployeeController employeeController;

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public EmployeesConsoleMenu() {
        super();

        addItem(new ConsoleMenuItem("Get all employees") {
            @Override
            public void run() {
                List<String> staff = employeeController.getAll();
                ConsoleIO.outputList(EMPLOYEE, staff);
            }
        });

        addItem(new ConsoleMenuItem("Add new employee") {
            @Override
            public void run() {
                Employee newEmployee = ConsoleIO.inputEmployee();
                ConsoleIO.outputItem("Employee to add:", newEmployee.toString());
                employeeController.add(newEmployee);
            }
        });

        addItem(new ConsoleMenuItem("Search employee by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(EMPLOYEE, ID);
                Employee foundEmployee = employeeController.search(id);
                ConsoleIO.outputItem("Found employee: ", foundEmployee.toString());
            }
        });

        addItem(new ConsoleMenuItem("Search employee by surname & name") {
            @Override
            public void run() {
                String surname = ConsoleIO.inputString(EMPLOYEE, SURNAME);
                String name = ConsoleIO.inputString(EMPLOYEE, NAME);
                Employee foundEmployee = employeeController.search(surname, name);
                ConsoleIO.outputItem("Found employee: ", foundEmployee.toString());
            }
        });

        addItem(new ConsoleMenuItem("Delete employee by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(EMPLOYEE, ID);
                employeeController.delete(id);
            }
        });

        addItem(new ConsoleMenuItem("Delete employee by surname and name") {
            @Override
            public void run() {
                String surname = ConsoleIO.inputString(EMPLOYEE, SURNAME);
                String name = ConsoleIO.inputString(EMPLOYEE, NAME);
                employeeController.delete(surname, name);
            }
        });
    }
}
