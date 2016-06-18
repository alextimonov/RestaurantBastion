package ua.goit.timonov.enterprise.extratasks.arrayList;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Testing class for ArrayListSimple
 */
public class ArrayListSimpleTest {
    private List<Integer> list1 = new ArrayListSimple<>(Arrays.asList(1, 5, 3, 7, 6, 3, 2, 1));
    private List<Integer> list2 = new ArrayListSimple<>(Arrays.asList(1, 2, 3, 4, 5));
    List<Integer> list3 = new ArrayListSimple<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    private List<Integer> emptyList = new ArrayListSimple<>();

    @Test
    public void testSizeNormal_1() {
        assertEquals(8, list1.size());
    }

    @Test
    public void testSizeNormal_2() {
        assertEquals(5, list2.size());
    }

    @Test
    public void testSizeNoElements() {
        assertEquals(0, emptyList.size());
    }

    @Test
    public void testIsEmptyNormal_1() {
        assertEquals(false, list1.isEmpty());
    }

    @Test
    public void testIsEmptyNormal_2() {
        assertEquals(false, list2.isEmpty());
    }

    @Test
    public void testIsEmptyIndeed() {
        assertEquals(true, emptyList.isEmpty());
    }

    @Test
    public void testIndexOfNormal_1() {
        assertEquals(2, list1.indexOf(3));
    }

    @Test
    public void testIndexOfNormal_2() {
        assertEquals(3, list2.indexOf(4));
    }

    @Test
    public void testIndexOfNoElements_1() {
        assertEquals(-1, list1.indexOf(0));
    }

    @Test
    public void testIndexOfNoElements_2() {
        assertEquals(-1, emptyList.indexOf(5));
    }

    @Test
    public void testIndexOfNoElements_3() {
        assertEquals(-1, list1.indexOf(null));
    }

    @Test
    public void testContainsNormal_1() {
        assertEquals(true, list1.contains(3));
    }

    @Test
    public void testContainsNormal_2() {
        assertEquals(true, list2.contains(5));
    }

    @Test
    public void testContainsNormal_3() {
        assertEquals(false, list2.contains(10));
    }

    @Test
    public void testContainsAbnormal_1() {
        assertEquals(false, emptyList.contains(10));
    }

    @Test
    public void testAddNormal_1() {
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 2, 3, 4, 5, 7));
        list2.add(7);
        assertEquals(expectedList, list2);
    }

    @Test
    public void testAddNormal_2() {
        List<Integer> expectedList = new ArrayListSimple<>(Collections.singletonList(5));
        emptyList.add(5);
        assertEquals(expectedList, emptyList);
    }

    @Test
    public void testAddNormal_3() {

        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        list3.add(10);
        assertEquals(expectedList, list3);
    }

    @Test
    public void testAddIndexNormal_1() {
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(7, 1, 2, 3, 4, 5));
        list2.add(0, 7);
        assertEquals(expectedList, list2);
    }

    @Test
    public void testAddIndexNormal_2() {
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 2, 3, 7, 4, 5));
        list2.add(3, 7);
        assertEquals(expectedList, list2);
    }

    @Test
    public void testAddIndexNormal_3() {
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 2, 3, 4, 5, 7));
        list2.add(5, 7);
        assertEquals(expectedList, list2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexAbnormal_1() {
        list2.add(6, 7);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexAbnormal_2() {
        list2.add(-1, 7);
    }

    @Test
    public void testAddAllNormal_1() {
        List<Integer> addedList = Arrays.asList(6, 7, 8, 9);
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        list2.addAll(addedList);
        assertEquals(expectedList, list2);
    }

    @Test
    public void testAddAllNormal_2() {
        List<Integer> addedList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 2, 3, 4, 5));
        emptyList.addAll(addedList);
        assertEquals(expectedList, emptyList);
    }

    @Test
    public void testAddAllNormal_3() {
        List<Integer> addedList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 5, 3, 7, 6, 3, 2, 1, 1, 2, 3, 4, 5));
        list1.addAll(addedList);
        assertEquals(expectedList, list1);
    }

    @Test
    public void testAddAllIndexNormal_1() {
        List<Integer> addedList = Arrays.asList(6, 7, 8, 9);
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 2, 6, 7, 8, 9, 3, 4, 5));
        list2.addAll(2, addedList);
        assertEquals(expectedList, list2);
    }

    @Test
    public void testAddAllIndexNormal_2() {
        List<Integer> addedList = Arrays.asList(6, 7, 8, 9);
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(6, 7, 8, 9, 1, 2, 3, 4, 5));
        list2.addAll(0, addedList);
        assertEquals(expectedList, list2);
    }

    @Test
    public void testAddAllIndexNormal_3() {
        List<Integer> addedList = Arrays.asList(6, 7, 8, 9);
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        list2.addAll(5, addedList);
        assertEquals(expectedList, list2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllIndexAbnormal_1() {
        List<Integer> addedList = Arrays.asList(6, 7, 8, 9);
        list2.addAll(6, addedList);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllIndexAbnormal_2() {
        List<Integer> addedList = Arrays.asList(6, 7, 8, 9);
        list2.addAll(-1, addedList);
    }

    @Test
    public void testRemoveNormal_1() {
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 2, 4, 5));
        list2.remove(new Integer(3));
        assertEquals(expectedList, list2);
    }

    @Test
    public void testRemoveNormal_2() {
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 2, 3, 4));
        list2.remove(new Integer(5));
        assertEquals(expectedList, list2);
    }

    @Test
    public void testRemoveNormal_3() {
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(2, 3, 4, 5));
        list2.remove(new Integer(1));
        assertEquals(expectedList, list2);
    }

    @Test
    public void testRemoveAbnormal_1() {
        boolean actual = list2.remove(new Integer(7));
        assertEquals(false, actual);
    }

    @Test
    public void testRemoveAbnormal_2() {
        boolean actual = list2.remove(null);
        assertEquals(false, actual);
    }

    @Test
    public void testRemoveAbnormal_3() {
        boolean actual = emptyList.remove(new Integer(1));
        assertEquals(false, actual);
    }

    @Test
    public void testClearNormal_1() {
        List<Integer> expectedList = new ArrayListSimple<>();
        list2.clear();
        assertEquals(expectedList, list2);
    }

    @Test
    public void testClearNormal_2() {
        List<Integer> expectedList = new ArrayListSimple<>();
        emptyList.clear();
        assertEquals(expectedList, emptyList);
    }

    @Test
    public void testGetNormal_1() {
        Integer gotElement = list2.get(2);
        assertEquals(new Integer(3), gotElement);
    }

    @Test
    public void testGetNormal_2() {
        Integer gotElement = list2.get(0);
        assertEquals(new Integer(1), gotElement);
    }

    @Test
    public void testGetNormal_3() {
        Integer gotElement = list2.get(4);
        assertEquals(new Integer(5), gotElement);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetAbnormal_1() {
        list2.get(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetAbnormal_2() {
        list2.get(-1);
    }

    @Test
    public void testSetNormal_1() {
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 2, 7, 4, 5));
        Integer oldValue = list2.set(2, 7);
        assertEquals(expectedList, list2);
        assertEquals(new Integer(3), oldValue);
    }

    @Test
    public void testSetNormal_2() {
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(7, 2, 3, 4, 5));
        Integer oldValue = list2.set(0, 7);
        assertEquals(expectedList, list2);
        assertEquals(new Integer(1), oldValue);
    }

    @Test
    public void testSetNormal_3() {
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 2, 3, 4, 7));
        Integer oldValue = list2.set(4, 7);
        assertEquals(expectedList, list2);
        assertEquals(new Integer(5), oldValue);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetAbnormal_1() {
        list2.set(5, 7);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetAbnormal_2() {
        list2.set(-2, 7);
    }

    @Test
    public void testRemoveIndexNormal_1() {
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 2, 4, 5));
        Integer removedValue = list2.remove(2);
        assertEquals(expectedList, list2);
        assertEquals(new Integer(3), removedValue);
    }

    @Test
    public void testRemoveIndexNormal_2() {
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(2, 3, 4, 5));
        Integer removedValue = list2.remove(0);
        assertEquals(expectedList, list2);
        assertEquals(new Integer(1), removedValue);
    }

    @Test
    public void testRemoveIndexNormal_3() {
        List<Integer> expectedList = new ArrayListSimple<>(Arrays.asList(1, 2, 3, 4));
        Integer removedValue = list2.remove(4);
        assertEquals(expectedList, list2);
        assertEquals(new Integer(5), removedValue);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexAbnormal_1() {
        list2.remove(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexAbnormal_2() {
        list2.remove(-1);
    }

    @Test
    public void testToArrayNormal_1() {
        Object[] expected = new Object[] {1, 2, 3, 4, 5};
        Object[] actual = list2.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testToArrayNormal_2() {
        Object[] expected = new Object[] {1, 5, 3, 7, 6, 3, 2, 1};
        Object[] actual = list1.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testToArrayNormal_3() {
        Object[] expected = new Object[] {};
        Object[] actual = emptyList.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator = list2.iterator();
        boolean actualHasNext;
        for (int i = 0; i < list2.size(); i++) {
            actualHasNext = iterator.hasNext();
            assertEquals(true, actualHasNext);
            Integer nextElement = iterator.next();
            assertEquals(new Integer(i + 1), nextElement);
        }
        actualHasNext = iterator.hasNext();
        assertEquals(false, actualHasNext);
    }
}