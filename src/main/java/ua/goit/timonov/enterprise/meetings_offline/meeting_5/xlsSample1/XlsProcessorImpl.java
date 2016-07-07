package ua.goit.timonov.enterprise.meetings_offline.meeting_5.xlsSample1;

import java.io.File;
import java.util.List;

/**
 * Created by Alex on 05.07.2016.
 */
public class XlsProcessorImpl implements XLSProcessor {
    @Override
    public void saveToXLS(List<Person> list, File xlsFile) {
//        WorkbookFactory
    }

    @Override
    public List<Person> readFromXLS(File xlsFile) {
        return null;
    }
}
