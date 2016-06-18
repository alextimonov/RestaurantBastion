package ua.goit.timonov.enterprise.extratasks.arrayList;

import java.util.*;

/**
 * Simple implementation of ArrayList
 */
public class ArrayListSimple<E> implements List<E>  {

    // default initial capacity of list
    public static final int INITIAL_CAPACITY = 10;

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
     * Removes from this list all of its elements that are contained in the
     * specified collection (optional operation).
     *
     * @param c collection containing elements to be removed from this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>removeAll</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of this list
     *                                       is incompatible with the specified collection
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this list contains a null element and the
     *                                       specified collection does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    /**
     * Returns an data containing all of the elements in this list in
     * proper sequence (from first to last element); the runtime type of
     * the returned data is that of the specified data.  If the list fits
     * in the specified data, it is returned therein.  Otherwise, a new
     * data is allocated with the runtime type of the specified data and
     * the size of this list.
     * <p>
     * <p>If the list fits in the specified data with room to spare (i.e.,
     * the data has more elements than the list), the element in the data
     * immediately following the end of the list is set to <tt>null</tt>.
     * (This is useful in determining the length of the list <i>only</i> if
     * the caller knows that the list does not contain any null elements.)
     * <p>
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * data-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output data, and may,
     * under certain circumstances, be used to save allocation costs.
     * <p>
     * <p>Suppose <tt>x</tt> is a list known to contain only strings.
     * The following code can be used to dump the list into a newly
     * allocated data of <tt>String</tt>:
     * <p>
     * <pre>{@code
     *     String[] y = x.toArray(new String[0]);
     * }</pre>
     * <p>
     * Note that <tt>toArray(new Object[0])</tt> is identical in function to
     * <tt>toArray()</tt>.
     *
     * @param a the data into which the elements of this list are to
     *          be stored, if it is big enough; otherwise, a new data of the
     *          same runtime type is allocated for this purpose.
     * @return an data containing the elements of this list
     * @throws ArrayStoreException  if the runtime type of the specified data
     *                              is not a supertype of the runtime type of every element in
     *                              this list
     * @throws NullPointerException if the specified data is null
     */
    @Override
    public <E> E[] toArray(E[] a) {
        return null;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this list all of its elements that are not contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of this list
     *                                       is incompatible with the specified collection
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this list contains a null element and the
     *                                       specified collection does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    /**
     * Returns <tt>true</tt> if this list contains all of the elements of the
     * specified collection.
     *
     * @param c collection to be checked for containment in this list
     * @return <tt>true</tt> if this list contains all of the elements of the
     * specified collection
     * @throws ClassCastException   if the types of one or more elements
     *                              in the specified collection are incompatible with this
     *                              list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection contains one
     *                              or more null elements and this list does not permit null
     *                              elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>),
     *                              or if the specified collection is null
     * @see #contains(Object)
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     *
     * @return a list iterator over the elements in this list (in proper
     * sequence)
     */
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     *
     * @param index index of the first element to be returned from the
     *              list iterator (by a call to {@link ListIterator#next next})
     * @return a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    /**
     * Returns a view of the portion of this list between the specified
     * <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive.  (If
     * <tt>fromIndex</tt> and <tt>toIndex</tt> are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported
     * by this list.<p>
     * <p>
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).  Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * <pre>{@code
     *      list.subList(from, to).clear();
     * }</pre>
     * Similar idioms may be constructed for <tt>indexOf</tt> and
     * <tt>lastIndexOf</tt>, and all of the algorithms in the
     * <tt>Collections</tt> class can be applied to a subList.<p>
     * <p>
     * The semantics of the list returned by this method become undefined if
     * the backing list (i.e., this list) is <i>structurally modified</i> in
     * any way other than via the returned list.  (Structural modifications are
     * those that change the size of this list, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex   high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     *                                   (<tt>fromIndex &lt; 0 || toIndex &gt; size ||
     *                                   fromIndex &gt; toIndex</tt>)
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

}
