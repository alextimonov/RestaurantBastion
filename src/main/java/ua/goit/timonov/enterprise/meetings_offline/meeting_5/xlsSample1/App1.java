package ua.goit.timonov.enterprise.meetings_offline.meeting_5.xlsSample1;

import java.io.File;
import java.util.List;

/**
 * Created by Alex on 05.07.2016.
 */
public class App1 {
    public static void main(String[] args) {
        File xlsFile = new File("persons.xls");
        List<Person> list = init();
        XLSProcessor processor = null;
        processor.readFromXLS(xlsFile);
        processor.saveToXLS(list, xlsFile);

    }



    private static XLSProcessor getProcessor(int type) {
        switch (type) {
            case 0:
                return null;
            default:
                throw new RuntimeException("Incorrect type");
        }
    }

    private static List<Person> init() {

        for (int i = 0; i < 5; i++) {

        }
        return null;
    }
}
