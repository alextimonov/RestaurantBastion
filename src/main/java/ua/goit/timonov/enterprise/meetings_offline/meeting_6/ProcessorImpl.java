package ua.goit.timonov.enterprise.meetings_offline.meeting_6;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 12.07.2016.
 */

@WebService
public class ProcessorImpl implements Processor {

    private List<Person> data = new ArrayList<>();

    @WebMethod
    @Override
    public void save(Person person) {
        data.add(person);
    }

    @WebMethod
    public List<Person> getAllPersons() {
        return data;
    }
}
