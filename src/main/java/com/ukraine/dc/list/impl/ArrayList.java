package com.ukraine.dc.list.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The ArrayList.
 *
 * @param <T> the data type
 */
public class ArrayList<T> extends AbstractList<T> {
    private static final int INITIAL_CAPACITY = 5;
    private T[] array;

    /**
     * Initialize ArrayList with default capacity.
     */
    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Initialize ArrayList with custom capacity.
     *
     * @param capacity the capacity
     */
    public ArrayList(int capacity) {
        array = (T[]) new Object[capacity];
    }

    /**
     * Enrich collection via adding data by index into the collection.
     *
     * @param index th index
     * @param data  the data
     */
    @Override
    public void add(T data, int index) {
        validateIndexOnAdd(index);
        if (size == array.length) {
            expandArray();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = data;
        size++;
    }

    /**
     * The method removes data by its index and returns the previous value.
     *
     * @param index the index
     * @return the data type
     */
    @Override
    public T remove(int index) {
        validateIndex(index);
        T prevValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        array[size] = null;
        return prevValue;
    }

    /**
     * Gets the data by its id.
     *
     * @param index the index
     * @return the datatype
     */
    @Override
    public T get(int index) {
        validateIndex(index);
        return array[index];
    }

    /**
     * Sets the new value by index and return the previous one.
     *
     * @param index the index
     * @param data  the data
     * @return the data type
     */
    @Override
    public T set(T data, int index) {
        validateIndex(index);
        T prevValue = array[index];
        array[index] = data;
        return prevValue;
    }

    /**
     * The method clear the whole collection.
     */
    @Override
    public void clear() {
        size = 0;
        array = null;
    }

    /**
     * The method try to find the data from the beginning of the collection. Also, it maintains handling of the NPE.
     *
     * @param data the data.
     * @return the index of data
     */
    @Override
    public int indexOf(T data) {
        if (data == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (data.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * The method try to find the data from the end of the collection. Also, it maintains handling of the NPE.
     *
     * @param data the data
     * @return the index of data
     */
    @Override
    public int lastIndexOf(T data) {
        if (data == null) {
            for (int i = size - 1; i > 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i > 0; i--) {
                if (data.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void expandArray() {
        int newCapacity = array.length * 2;
        T[] newArray = (T[]) new Object[(int) newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    /**
     * Trim the capacity of the list.
     */
    public void trimToSize() {
        array = Arrays.copyOf(array, size);
    }

    /**
     * Initialize iterator.
     *
     * @return the MIterator instance
     */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    /**
     * The type MyIterator.
     */
    public class MyIterator implements Iterator<T> {
        private int index;
        private boolean canRemove;

        /**
         * The method checks if collection has one more element.
         *
         * @return the boolean value
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * The method based on hasNext method result returns data.
         *
         * @return the data type
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more element in the collection.");
            }
            canRemove = true;
            return array[index++];
        }

        /**
         * The method based on hasNext() and next() methods removes data.
         */
        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Incorrect behavior for the iterator, when called remove() previously next() wasn't called");
            }
            ArrayList.this.remove(--index);
            canRemove = false;
        }
    }
}
