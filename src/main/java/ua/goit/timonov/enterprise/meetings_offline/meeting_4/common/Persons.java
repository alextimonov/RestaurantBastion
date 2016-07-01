package ua.goit.timonov.enterprise.meetings_offline.meeting_4.common;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Alex on 01.07.2016.
 */

@XmlRootElement
public class Persons {
    private List<Person> persons;

    public Persons() {
    }

    public Persons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
