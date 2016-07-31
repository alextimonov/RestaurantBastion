package ua.goit.timonov.enterprise.module_6_2.view.console;

import ua.goit.timonov.enterprise.module_6_2.model.Employee;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import static ua.goit.timonov.enterprise.module_6_2.view.console.PrintToConsole.printLine;

/**
 * Created by Alex on 31.07.2016.
 */
public class EmployeesIO {

    public static void outputList(List<Employee> staff) {
        printLine("Current list of employees: ");
        for (Employee employee : staff) {
            printLine(employee.toString());
        }
    }

    public static void output(String explain, Employee employee) {
        printLine(explain);
        printLine(employee.toString());
    }

    public static Employee inputEmployee() {
        Employee employee = new Employee();
        Scanner sc = new Scanner(System.in);
        printLine("Please, input new employee's data: ");

        printLine("Input employee's surname: ");
        employee.setSurname(Input.inputString(sc));

        printLine("Input employee's name: ");
        employee.setName(Input.inputString(sc));

        printLine("Input employee's birthday: ");
        Date date = inputDate(sc);
        employee.setBirthday(date);

        printLine("Input employee's position: ");
        sc = new Scanner(System.in);
        employee.setPosition(Input.inputString(sc));

        printLine("Input employee's salary: ");
        employee.setSalary(Input.inputFloat(sc));

        return employee;
    }

    private static Date inputDate(Scanner sc) {
        printLine("Input birthday's year: ");
        int year = Input.inputInteger(sc);
        printLine("Input birthday's month: ");
        int month = Input.inputInteger(sc) - 1;
        printLine("Input birthday's day in month: ");
        int day = Input.inputInteger(sc);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(year, month, day);
        return gregorianCalendar.getTime();
    }

    public static String inputString(String fieldName) {
        Scanner sc = new Scanner(System.in);
        printLine("Please input employee's " + fieldName + " that you want to find: ");
        return Input.inputString(sc);
    }
}
