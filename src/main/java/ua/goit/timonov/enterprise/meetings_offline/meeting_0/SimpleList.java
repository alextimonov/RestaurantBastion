package ua.goit.timonov.enterprise.meetings_offline.meeting_0;

import java.util.Collection;

/**
 * Created by Alex on 04.06.2016.
 */
public interface SimpleList<E> extends Iterable<E> {

    void add(E element);

    E get(int index);

    E set(int index, E element);

    int remove(E element);

    void addAll(Collection<? extends E> source);

    int size();

    boolean contains(E element);

    boolean isEmpty();
}
