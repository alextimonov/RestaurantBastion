package ua.goit.timonov.enterprise.meetings_offline.meeting_4.xml.xsd;

import ua.goit.timonov.enterprise.meetings_offline.meeting_4.common.Person;
import ua.goit.timonov.enterprise.meetings_offline.meeting_4.common.Persons;
import ua.goit.timonov.enterprise.meetings_offline.meeting_4.common.XmlProcessor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * Created by Alex on 01.07.2016.
 */
public class XsdProcessorImpl implements XmlProcessor {
    @Override
    public List<Person> readData() {
        try {
            JAXBContext ctx = JAXBContext.newInstance(Persons.class);
            // XML -> Java Unmarshall
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
//            Persons persons = new Persons(datau
            File file = new File("res.xml");
            Persons persons  = (Persons) unmarshaller.unmarshal(file);
            System.out.println("res = " + persons);
            return persons.getPersons();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeData(List<Person> data, File file) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(Persons.class);
            // Java -> Xml
            Marshaller marshaller = ctx.createMarshaller();
            Persons persons = new Persons(data);

            marshaller.marshal(data, file);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
