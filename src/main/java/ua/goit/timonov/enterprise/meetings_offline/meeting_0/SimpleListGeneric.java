package ua.goit.timonov.enterprise.meetings_offline.meeting_0;

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by Alex on 04.06.2016.
 */

public class SimpleListGeneric<E> implements SimpleList<E> {
    private static final int CAPACITY = 10;
    private Object[] data = new Object[CAPACITY];
    private int size;
    private long version;

    @Override
    public void add(E element) {
        if (size == data.length) {
            increaseCapacity(0);
        } else {
            data[size++] = (Object) element;
        }
        version++;
    }

    @Override
    public E get(int index) {
        E element = (E) data[index];
        return element;
    }

    @Override
    public E set(int index, E element) {
        E oldValue = (E) data[index];
        data[index] = element; // redundant (Object)
        version++;
        return oldValue;
    }

    @Override
    public int remove(E element) {
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

    @Override
    public void addAll(Collection<? extends E> source) {
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
    public boolean contains(E element) {
        return getIndex(element) >= 0;
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

    private int getIndex(Object element) {
        for (int i = 0; i < data.length; i++) {
            if (element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentPosition;
            private long iteratorVersion = version;

            @Override
            public boolean hasNext() {
                validateVersion();
                return currentPosition < size();
            }

            @Override
            public E next() {
                validateVersion();
                return get(currentPosition++);
            }

            private void validateVersion() {
                if (iteratorVersion != version) {
                    throw new ConcurrentModificationException("String.format(\"Not eqaul versions: %s vs %s\", iteratorVersion, version)");
                }
            }
        };
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SimpleListGeneric{");
        sb.append("data=").append(Arrays.toString(data));
        sb.append('}');
        return sb.toString();
    }
}
