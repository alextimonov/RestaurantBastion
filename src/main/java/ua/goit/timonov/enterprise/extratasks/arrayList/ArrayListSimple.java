package ua.goit.timonov.enterprise.extratasks.arrayList;

import java.util.*;

/**
 * Simple implementation of ArrayList
 */
public class ArrayListSimple<E> implements List<E>  {

    // default initial capacity of list
    public static final int INITIAL_CAPACITY = 10;
    public static final String NOT_IMPLEMENTED = "Method is not implemented";

    /* current number of elements in ArrayList */
    private int currentSize;

    /* data of elements */
    private Object[] data;

    /* version of list's change */
    private long version;

    public ArrayListSimple(int capacity) {
        data = new Object[capacity];
    }

    public ArrayListSimple() {
        data = new Object[INITIAL_CAPACITY];
    }

    public ArrayListSimple(Collection<? extends E> collection) {
        data = new Object[INITIAL_CAPACITY];
        currentSize = 0;
        for (E element : collection) {
            data[currentSize++] = element;
            if (currentSize + 1 > data.length) {
                increaseCapacity();
            }
        }
    }


    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return currentSize;
    }


    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }


    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param element       element to search for
     * @return              the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     */
    @Override
    public int indexOf(Object element) {
        if (currentSize == 0 || element == null)
            return -1;
        for (int i = 0; i < data.length; i++) {
            if (element.equals(data[i]))
                return i;
        }
        return -1;
    }


    /**
     * Returns true if this list contains the specified element.
     *
     * @param element   element whose presence in this list is to be tested
     * @return          true if this list contains the specified element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     */
    @Override
    public boolean contains(Object element) {
        return indexOf(element) >= 0;
    }


    /**
     * Appends the specified element to the end of this list
     *
     * @param element element to be appended to this list
     * @return true if element is added
     */
    @Override
    public boolean add(E element) {
        if (currentSize + 1 >= data.length) {
            increaseCapacity();
        }
        data[currentSize] = element;
        currentSize++;
        return true;
    }


    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index          index at which the specified element is to be inserted
     * @param element        element to be inserted
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (currentSize + 1 >= data.length) {
            increaseCapacity();
        }
        if (index < currentSize) {
            System.arraycopy(data, index, data, index + 1, currentSize - index);
        }
        data[index] = element;
        currentSize++;
        version++;
    }


    // checks if index is in appropriate range for add operation
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > currentSize) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds: [0.." + currentSize + "]!");
        }
    }


    // increases capacity of list
    private void increaseCapacity() {
        Object[] newData = new Object[data.length * 3 / 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }


    /**
     * decreases capacity of list to value of it's current size
     */
    public void trimToSize() {
        Object[] newData = new Object[currentSize];
        System.arraycopy(data, 0, newData, 0, currentSize);
        data = newData;
    }


    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator
     *
     * @param collection        collection containing elements to be added to this list
     * @return                  true if this list changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        Object[] newArray = collection.toArray();
        int newLength = newArray.length;
        if (newLength > 0) {
            while (newLength + currentSize > data.length ) {
                increaseCapacity();
            }
            System.arraycopy(newArray, 0, data, currentSize, newLength);
            currentSize += newLength;
            version++;
            return true;
        }
        return false;
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list at the specified position.  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param collection     collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        rangeCheckForAdd(index);
        Object[] insertedArray = collection.toArray();
        int insertedLength = insertedArray.length;
        if (insertedLength > 0) {
            while (insertedLength + currentSize > data.length) {
                increaseCapacity();
            }
            if (index < currentSize)
                System.arraycopy(data, index, data, index + insertedLength, currentSize - index);

            System.arraycopy(insertedArray, 0, data, index, insertedLength);
            currentSize += insertedLength;
            version++;
            return true;
        }
        return false;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present. If this list does not contain
     * the element, it is unchanged.
     *
     * @param objectToRemove    element to be removed from this list, if present
     * @return                  true if this list contained the specified element
     */
    @Override
    public boolean remove(Object objectToRemove) {
        int index = indexOf(objectToRemove);
        if (index >= 0) {
            System.arraycopy(data, index + 1, data, index, currentSize - index - 1);
            data[--currentSize] = null;
            version++;
            return true;
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
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
                    throw new ConcurrentModificationException
                            (String.format("Not equal versions: %s vs %s", iteratorVersion, version));
                }
            }
        };
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        for (int i = 0; i < currentSize; i++) {
            data[i] = null;
        }
        currentSize = 0;
        version++;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public E get(int index) {
        rangeCheckForGetSetRemove(index);
        return (E) data[index];
    }

    // checks if the index is in the appropriate range for get, set and remove operations
    private void rangeCheckForGetSetRemove(int index) {
        if (index < 0 || index >= currentSize) {
            int rightBound = currentSize - 1;
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds: [0.." + rightBound + "]!");
        }
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public E set(int index, E element) {
        rangeCheckForGetSetRemove(index);
        E oldValue = (E) data[index];
        data[index] = element;
        version++;
        return oldValue;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one
     * from their indices). Returns the element that was removed from the
     * list.
     *
     * @param index     the index of the element to be removed
     * @return          the element previously at the specified position
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public E remove(int index) {
        rangeCheckForGetSetRemove(index);
        E removedValue = (E) data[index];
        System.arraycopy(data, index + 1, data, index, currentSize - index - 1);
        data[--currentSize] = null;
        version++;
        return removedValue;
    }

    /**
     * Returns an data containing all of the elements in this list in proper
     * sequence (from first to last element).
     * @return an data containing all of the elements in this list in proper
     * sequence
     */
    @Override
    public Object[] toArray() {
        Object[] usefulData = new Object[currentSize];
        System.arraycopy(data, 0, usefulData, 0, currentSize);
        return usefulData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayListSimple<?> that = (ArrayListSimple<?>) o;

        if (currentSize != that.currentSize) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(data, that.data);

    }

    @Override
    public int hashCode() {
        int result = currentSize;
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArrayListSimple{");
        sb.append(", currentSize=").append(currentSize);
        sb.append(", data=").append(Arrays.toString(data));
        sb.append('}');
        return sb.toString();
    }


    // =========== Not implemented ================

    /**
     * @param c collection containing elements to be removed from this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws UnsupportedOperationException as method is not implemented
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    /**
     * Returns an data containing all of the elements in this list in
     * proper sequence (from first to last element)
     * @param a the data into which the elements of this list are to
     *          be stored, if it is big enough; otherwise, a new data of the
     *          same runtime type is allocated for this purpose.
     * @return an data containing the elements of this list
     * @throws UnsupportedOperationException as method is not implemented
     */
    @Override
    public <E> E[] toArray(E[] a) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws UnsupportedOperationException as method is not implemented
     */
    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this list all of its elements that are not contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws UnsupportedOperationException as method is not implemented
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    /**
     * Returns <tt>true</tt> if this list contains all of the elements of the
     * specified collection.
     *
     * @param c collection to be checked for containment in this list
     * @return <tt>true</tt> if this list contains all of the elements of the
     * specified collection
     * @throws UnsupportedOperationException as method is not implemented
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     *
     * @return a list iterator over the elements in this list (in proper
     * sequence)
     * @throws UnsupportedOperationException as method is not implemented
     */
    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     *
     * @param index index of the first element to be returned from the
     *              list iterator (by a call to {@link ListIterator#next next})
     * @return a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list
     * @throws UnsupportedOperationException as method is not implemented
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    /**
     * Returns a view of the portion of this list between the specified
     * <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive.
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex   high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws UnsupportedOperationException as method is not implemented
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

}
