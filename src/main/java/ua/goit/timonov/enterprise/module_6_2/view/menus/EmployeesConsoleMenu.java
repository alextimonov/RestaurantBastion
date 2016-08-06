package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.EmployeeController;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

import static ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter.*;

/**
 * Created by Alex on 03.08.2016.
 */
public class EmployeesConsoleMenu extends ConsoleMenu {
    public static final String EMPLOYEE = "employee";
    public static final String SURNAME = "surname";
    public static final String NAME = "name";
    public static final String NO_SUCCESS = "UNSUCCESSFUL! There's no employee with such ";
    public static final String FULL_NAME = "surname & name";


    private EmployeeController employeeController;

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public EmployeesConsoleMenu() {
        addItem(new ConsoleMenuItem("Get all employees") {
            @Override
            public void run() {
                try {
                    List<String> staff = employeeController.getAll();
                    ConsoleIO.outputItems(EMPLOYEE, staff);
                } catch (RuntimeException e) {
                    printLine("UNSUCCESSFUL! There's no employees in the base!");
                }
            }
        });

        addItem(new ConsoleMenuItem("Add new employee") {
            @Override
            public void run() {
                Employee newEmployee = ConsoleIO.inputEmployee();
                ConsoleIO.outputItem("Data for new employee:", newEmployee.toString());
                try {
                    employeeController.add(newEmployee);
                    printLine(SUCCESS);
                }
                catch (RuntimeException e) {
                    ConsoleIO.outputItem(e.getMessage(), newEmployee.getPosition());
                }
            }
        });

        addItem(new ConsoleMenuItem("Search employee by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(EMPLOYEE, ID);
                try {
                    Employee foundEmployee = employeeController.search(id);
                    ConsoleIO.outputItem(SUCCESS + " Found employee: ", foundEmployee.toString());
                }
                catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + ID, String.valueOf(id));
                }
            }
        });

        addItem(new ConsoleMenuItem("Search employee by surname & name") {
            @Override
            public void run() {
                String surname = ConsoleIO.inputString(EMPLOYEE, SURNAME);
                String name = ConsoleIO.inputString(EMPLOYEE, NAME);
                try {
                    Employee foundEmployee = employeeController.search(surname, name);
                    ConsoleIO.outputItem(SUCCESS + " Found employee: ", foundEmployee.toString());
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + FULL_NAME, surname + " " + name);
                }
            }
        });

        addItem(new ConsoleMenuItem("Delete employee by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(EMPLOYEE, ID);
                try {
                    employeeController.delete(id);
                    printLine(SUCCESS);
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + ID, String.valueOf(id));
                }
            }
        });

        addItem(new ConsoleMenuItem("Delete employee by surname and name") {
            @Override
            public void run() {
                String surname = ConsoleIO.inputString(EMPLOYEE, SURNAME);
                String name = ConsoleIO.inputString(EMPLOYEE, NAME);
                try {
                    employeeController.delete(surname, name);
                    printLine(SUCCESS);
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + FULL_NAME, surname + " " + name);
                }
            }
        });
    }
}
