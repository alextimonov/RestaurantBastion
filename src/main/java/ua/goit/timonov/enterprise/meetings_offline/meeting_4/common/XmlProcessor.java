package ua.goit.timonov.enterprise.meetings_offline.meeting_4.common;

import java.io.File;
import java.util.List;

/**
 * Created by Alex on 01.07.2016.
 */
public interface XmlProcessor {
    List<Person> readData();

    void writeData(List<Person> list, File file);
}
