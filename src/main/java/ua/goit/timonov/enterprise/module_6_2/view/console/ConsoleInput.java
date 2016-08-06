package ua.goit.timonov.enterprise.module_6_2.view.console;

import java.util.*;

import static ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter.printLine;

/**
 * Created by Alex on 31.07.2016.
 */
public class ConsoleInput {

    public static final int MIN_YEAR_VALUE = 1900;
    public static final int MIN_MONTH_VALUE = 1;
    public static final int MAX_MONTH_VALUE = 12;
    public static final int MIN_DAY_VALUE = 1;
    public static final int DAYS_IN_LONG_MONTH = 31;
    public static final int DAYS_IN_SHORT_MONTH = 30;
    public static final int DAYS_IN_LONG_FEBRUARY = 29;
    public static final int DAYS_IN_SHORT_FEBRUARY = 28;
    public static final int FOUR = 4;
    public static final int HUNDRED = 100;
    public static final int FOUR_NUNDRED = 400;


    private static List<Integer> longMonths = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
    private static List<Integer> shortMonths = Arrays.asList(4, 6, 9, 11);

    public static String inputString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static int inputInteger() {
        while(true) {
            try {
                Scanner sc = new Scanner(System.in);
                return sc.nextInt();
            }
            catch (Exception e) {
                printLine("Wrong input! Please, input an integer value.");
            }
        }
    }

    public static int inputInteger(int lowBound, int highBound) {
        while(true) {
            try {
                Scanner sc = new Scanner(System.in);
                int value = sc.nextInt();
                if (value < lowBound || value > highBound) {
                    throw new IllegalArgumentException("Value should be in diapason [" + lowBound + ".." + highBound + "]");
                }
                return value;
            }
            catch (IllegalArgumentException e) {
                printLine(e.getMessage());
            }
            catch (Exception e) {
                printLine("Wrong input! Please, input an integer value.");
            }
        }
    }

    public static float inputFloat() {
        while(true) {
            try {
                Scanner sc = new Scanner(System.in);
                return sc.nextFloat();
            }
            catch (Exception e) {
                printLine("Wrong input! Please, input a float value.");
            }
        }
    }

    public static Date inputDate() {
        printLine("Input year: ");
        Calendar calendar = GregorianCalendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int year = inputInteger(MIN_YEAR_VALUE, currentYear);

        printLine("Input month: ");
        int month = inputInteger(MIN_MONTH_VALUE, MAX_MONTH_VALUE);

        printLine("Input day in month: ");
        int maxDayValue = findNDays(month, year);
        int day = inputInteger(MIN_DAY_VALUE, maxDayValue);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(year, month - 1, day);
        return gregorianCalendar.getTime();
    }

    private static int findNDays(int month, int year) {
        if (longMonths.contains(month))
            return DAYS_IN_LONG_MONTH;
        else
            if (shortMonths.contains(month))
                return DAYS_IN_SHORT_MONTH;
            else
                return findNDaysInFebruary(year);
    }

    private static int findNDaysInFebruary(int year) {
        if (year % FOUR == 0)
            if (year % HUNDRED == 0)
                if (year % FOUR_NUNDRED == 0)
                    return DAYS_IN_LONG_FEBRUARY;
                else
                    return DAYS_IN_SHORT_FEBRUARY;
            else
                return DAYS_IN_LONG_FEBRUARY;
        else
            return DAYS_IN_SHORT_FEBRUARY;
    }
}
