package ua.goit.timonov.enterprise.module_6_2.view.console;

import java.util.Scanner;

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
}
