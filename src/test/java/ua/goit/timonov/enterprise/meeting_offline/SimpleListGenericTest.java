package ua.goit.timonov.enterprise.meeting_offline;

import org.junit.Test;
import ua.goit.timonov.enterprise.meetings_offline.meeting_0.SimpleList;
import ua.goit.timonov.enterprise.meetings_offline.meeting_0.SimpleListGeneric;

import static org.junit.Assert.*;

/**
 * Created by Alex on 04.06.2016.
 */
public class SimpleListGenericTest {
    SimpleList<Double> list = new SimpleListGeneric<>();

    @Test
    public void testAdd() {
        list = new SimpleListGeneric<>();
        list.add(2d);
        list.add(4d);
        list.add(6d);
        int k = 1;
        for (Double doubleValue : list) {
            assertEquals(doubleValue, k * 2, 1E-6);
            k++;
        }
    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testSet() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {

    }

    @Test
    public void testAddAll() throws Exception {

    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testContains() throws Exception {

    }

    @Test
    public void testIsEmpty() throws Exception {

    }

    @Test
    public void testIterator() throws Exception {

    }
}