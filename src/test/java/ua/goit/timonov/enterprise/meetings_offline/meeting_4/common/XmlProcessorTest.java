package ua.goit.timonov.enterprise.meetings_offline.meeting_4.common;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alex on 01.07.2016.
 */
public class XmlProcessorTest {
    private static final File resultXmlFile = new File("/res.xml");

    @Test
    public void testReadData() throws Exception {

    }

    @Test
    public void testSAX_1() throws Exception {
        test(XmlMain.getProcessor(0));
        testRead(XmlMain.getProcessor(0));

    }

    @Test
    public void testDOM() throws Exception {
        test(XmlMain.getProcessor(1));
        testRead(XmlMain.getProcessor(1));

    }

    @Test
    public void testXSD() throws Exception {
        test(XmlMain.getProcessor(2));
        testRead(XmlMain.getProcessor(2));

    }

    @Test
    public void testWriteData() throws Exception {
        test(XmlMain.getProcessor(0));
//        test(XmlMain.getProcessor(1));
//        test(XmlMain.getProcessor(2));
    }

    private void test(XmlProcessor processor) {
        List<Person> list = initTestData();
        processor.writeData(list, resultXmlFile);
    }

    private void testRead(XmlProcessor processor) {
        List<Person> expectedList = initTestData();
        List<Person> actualList = processor.readData();
        assertEquals(expectedList.size(), actualList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i), actualList.get(i));
        }
        assertTrue(Arrays.equals(expectedList.toArray(), actualList.toArray()));
    }

    private void testSax_3() {
        XmlProcessor processor = XmlMain.getProcessor(0);
        List<Person> list = initTestData();
        processor.writeData(list, resultXmlFile);
    }

    private void testSax_4() {
        XmlProcessor processor = XmlMain.getProcessor(0);
        List<Person> list = initTestData();
        processor.writeData(list, resultXmlFile);
    }

    private List<Person> initTestData() {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Person("name"+i, i));
        }
        return list;
    }
}