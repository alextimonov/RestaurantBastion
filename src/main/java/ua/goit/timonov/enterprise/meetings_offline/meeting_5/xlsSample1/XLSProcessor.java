package ua.goit.timonov.enterprise.meetings_offline.meeting_5.xlsSample1;

import java.io.File;
import java.util.List;

/**
 * Created by Alex on 05.07.2016.
 */
public interface XLSProcessor {
    public void saveToXLS(List<Person> list, File xlsFile);

    public List<Person> readFromXLS(File xlsFile);
}