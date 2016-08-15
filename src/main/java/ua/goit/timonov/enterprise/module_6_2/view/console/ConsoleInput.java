package ua.goit.timonov.enterprise.module_6_2.view.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.timonov.enterprise.module_6_2.exceptions.UserRefuseInputException;
import ua.goit.timonov.enterprise.module_6_2.exceptions.ValueOutOfBoundsException;

import java.util.*;

/**
 * Provides input of different data types from console: String, int, float, Date
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
    public static final int FOUR_HUNDRED = 400;
    public static final String QUIT = "Q";

    public static final Logger LOGGER = LoggerFactory.getLogger(ConsoleInput.class);

    /* list of month's numbers with 31 days in that month */
    private static List<Integer> longMonths = Arrays.asList(1, 3, 5, 7, 8, 10, 12);

    /* list of month's numbers with 30 days in that month */
    private static List<Integer> shortMonths = Arrays.asList(4, 6, 9, 11);

    /**
     * inputs String from console
     * @return      inputted string
     */
    public static String inputString() {
        return inputSomeValue(DataType.STRING);
    }

    /**
     * inputs float value from console
     * @return      inputted float value
     */
    public static float inputFloat() {
        String stringWithFloat = inputSomeValue(DataType.FLOAT);
        return Float.valueOf(stringWithFloat);
    }

    /**
     * inputs int value from console
     * @return          inputted int value
     */
    public static int inputInteger() {
        String stringWithInt = inputSomeValue(DataType.INTEGER);
        return Integer.valueOf(stringWithInt);
    }

    /**
     * inputs int value from console
     * @param bounds    integer interval (low and high bound)
     * @return          inputted int value
     */
    public static int inputIntegerWithBounds(int... bounds) {
        String stringWithInt = inputSomeValue(DataType.INTEGER_WITH_DIAPASON, bounds);
        return Integer.valueOf(stringWithInt);
    }

    private static String inputSomeValue(DataType dataType, int... bounds) {
        while(true) {
            Scanner sc = new Scanner(System.in);
            String inputString = sc.next();
            checkIfUserRefusesInput(inputString);
            try {
                tryToMakeParse(inputString, dataType, bounds);
                return inputString;
            }
            catch (ValueOutOfBoundsException e) {
                LOGGER.info(e.getMessage());
            }
            catch (NumberFormatException e) {
                LOGGER.info("Wrong input! Please, input proper value or input \"Q\" or \"q\" to quit");
            }
        }
    }

    private static void checkIfUserRefusesInput(String inputString) {
        if (inputString.toUpperCase().equals(QUIT)) {
            throw new UserRefuseInputException("User refused to input value");
        }
    }

    private static void tryToMakeParse(String inputString, DataType dataType, int... bounds) {
        if (dataType == DataType.INTEGER) {
            Integer.valueOf(inputString);
        }
        if (dataType == DataType.INTEGER_WITH_DIAPASON) {
            int value = Integer.valueOf(inputString);
            if (value < bounds[0] || value > bounds[1]) {
                throw new ValueOutOfBoundsException("Value should be in diapason [" + bounds[0] + ".." + bounds[1] + "]");
            }
        }
        if (dataType == DataType.FLOAT) {
            Float.valueOf(inputString);
        }
    }

    /**
     * inputs Date from console with taking to consideration number of days in different months and years
     * @return      inputted Date value
     */
    public static Date inputDate() {
        LOGGER.info("Input year: ");
        Calendar calendar = GregorianCalendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int year = inputIntegerWithBounds(MIN_YEAR_VALUE, currentYear);

        LOGGER.info("Input month: ");
        int month = inputIntegerWithBounds(MIN_MONTH_VALUE, MAX_MONTH_VALUE);

        LOGGER.info("Input day in month: ");
        int maxDayValue = findNDays(month, year);
        int day = inputIntegerWithBounds(MIN_DAY_VALUE, maxDayValue);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(year, month - 1, day);
        return gregorianCalendar.getTime();
    }

    // returns number of days in month due to year
    private static int findNDays(int month, int year) {
        if (longMonths.contains(month))
            return DAYS_IN_LONG_MONTH;
        else
            if (shortMonths.contains(month))
                return DAYS_IN_SHORT_MONTH;
            else
                return findNDaysInFebruary(year);
    }

    // returns number of days in February by year
    private static int findNDaysInFebruary(int year) {
        if (year % FOUR == 0)
            if (year % HUNDRED == 0)
                if (year % FOUR_HUNDRED == 0)
                    return DAYS_IN_LONG_FEBRUARY;
                else
                    return DAYS_IN_SHORT_FEBRUARY;
            else
                return DAYS_IN_LONG_FEBRUARY;
        else
            return DAYS_IN_SHORT_FEBRUARY;
    }
}
