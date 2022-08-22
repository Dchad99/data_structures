package com.ukraine.dc.list;

import java.util.Iterator;

/**
 * The List interface.
 *
 * @param <T> the data type
 */
public interface List<T> extends Iterable<T> {

    void add(T data);

    void add(int index, T data);

    T remove(int index);

    T get(int index);

    T set(int index, T data);

    boolean isEmpty();

    int size();

    void clear();

    boolean contains(T data);

    int indexOf(T data);

    int lastIndexOf(T data);

    String toString();

    Iterator<T> iterator();
}
