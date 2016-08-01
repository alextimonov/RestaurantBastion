package ua.goit.timonov.enterprise.module_6_2.view.console;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import static ua.goit.timonov.enterprise.module_6_2.view.console.PrintToConsole.printLine;

/**
 * Created by Alex on 31.07.2016.
 */
public class Input {

    public static String inputString(Scanner sc) {
        return sc.nextLine();
    }

    public static int inputInteger(Scanner sc) {
        return sc.nextInt();
    }

    public static float inputFloat(Scanner sc) {
        return sc.nextFloat();
    }

    public static Date inputDate(Scanner sc) {
        printLine("Input year: ");
        int year = Input.inputInteger(sc);
        printLine("Input month: ");
        int month = Input.inputInteger(sc) - 1;
        printLine("Input day in month: ");
        int day = Input.inputInteger(sc);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(year, month, day);
        return gregorianCalendar.getTime();
    }
}
