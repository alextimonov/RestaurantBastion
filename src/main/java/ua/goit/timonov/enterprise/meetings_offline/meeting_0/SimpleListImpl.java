package ua.goit.timonov.enterprise.meetings_offline.meeting_0;

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by Alex on 04.06.2016.
 */
public class SimpleListImpl implements SimpleList {
    private static final int CAPACITY = 10;
    private Object[] data = new Object[CAPACITY];
    private int size;
    private long version;

    @Override
    public void add(Object element) {
        if (size == data.length) {
            increaseCapacity(0);
        } else {
            data[size++] = element;
        }
        version++;
    }

    @Override
    public Object get(int index) {
        return data[index];
    }

    @Override
    public Object set(int index, Object element) {
        Object oldValue = data[index];
        data[index] = element;
        version++;
        return oldValue;
    }

    @Override
    public int remove(Object element) {
        int index = getIndex(element);
        if (index == -1) {
            return index;
        }
        if (index == 0) {
            data = Arrays.copyOfRange(data, 1, data.length);
        } else {
            for (int i = index; i < data.length - 1; i++) {
                data[i] = data[i + 1];
            }

        }
        size--;
        version++;
        return index;
    }

    private int getIndex(Object element) {
        for (int i = 0; i < data.length; i++) {
            if (element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void addAll(Collection source) {
        if (size + source.size() > data.length) {
            increaseCapacity(source.size());
        }
        for (Object item : source) {
            data[size++] = item;
        }
        version++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object element) {
        return getIndex(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseCapacity(int size) {
        Object[] newData1 = new Object[data.length + CAPACITY + size];
        System.arraycopy(data, 0, newData1, 0, data.length);
        data = newData1;
    }

    @Override
    public Iterator iterator() {
        return new Iterator(){
            private int currentIndex;
            private long iteratorVersion=version;

            @Override
            public boolean hasNext() {
                validateVersion();
                return currentIndex <size();
            }

            @Override
            public Object next() {
                validateVersion();
                return get(currentIndex++);
            }

            private void validateVersion(){
                if(iteratorVersion!=version){
                    throw new ConcurrentModificationException(String.format("Not eqaul versions: %s vs %s", iteratorVersion, version));
                }
            }
        };
    }
}
