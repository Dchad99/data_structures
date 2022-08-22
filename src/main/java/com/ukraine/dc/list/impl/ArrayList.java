package com.ukraine.dc.list.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.ukraine.dc.util.Constants.HAS_NOT_NEXT_ELEMENT;
import static com.ukraine.dc.util.Constants.ITERATOR_INCORRECT;

/**
 * The ArrayList.
 *
 * @param <T> the data type
 */
public class ArrayList<T> extends AbstractList<T> {
    private static final int DEFAULT_CAPACITY = 5;
    private double capacity = DEFAULT_CAPACITY;
    private T[] array;

    /**
     * Initialize ArrayList with default capacity.
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
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
     * Add data to the end of the list.
     *
     * @param data the data.
     */
    @Override
    public void add(T data) {
        array = initArray(array);
        add(size, data);
    }

    /**
     * Enrich collection via adding data by index into the collection.
     *
     * @param index th index
     * @param data  the data
     */
    @Override
    public void add(int index, T data) {
        validateIndex(index);
        array = initArray(array);
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
     * @return th datatype
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
    public T set(int index, T data) {
        validateIndex(index);
        T prevValue = array[index];
        array[index] = data;
        return prevValue;
    }

    /**
     * The method checks if collection is empty or no, returns boolean value.
     *
     * @return the boolean value
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * The method returns collection actual size.
     *
     * @return the data type
     */
    @Override
    public int size() {
        return size;
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
     * Went through the collection and try to find the searching element.
     *
     * @param value the value
     * @return the boolean value
     */
    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
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
        capacity = capacity * 1.5;
        T[] newArray = (T[]) new Object[(int) capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private T[] initArray(T[] array) {
        if (array == null) {
            return (T[]) new Object[(int) capacity];
        }
        return array;
    }

    /**
     * The method reflects collection into the string.
     *
     * @return the String value
     */
    public String toString() {
        return super.toString(this);
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
        private boolean isNextInvoked;

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
            if (hasNext()) {
                isNextInvoked = true;
                return array[index++];
            }
            throw new NoSuchElementException(HAS_NOT_NEXT_ELEMENT);
        }

        /**
         * The method based on hasNext() and next() methods removes data.
         */
        @Override
        public void remove() {
            if (!isNextInvoked) {
                throw new IllegalStateException(ITERATOR_INCORRECT);
            }
            ArrayList.this.remove(--index);
            isNextInvoked = false;
        }
    }
}
