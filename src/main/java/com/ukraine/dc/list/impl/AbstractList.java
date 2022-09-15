package com.ukraine.dc.list.impl;

import com.ukraine.dc.list.List;

import java.util.StringJoiner;

public abstract class AbstractList<T> implements List<T> {
    protected int size;

    /**
     * Add element to list.
     *
     * @param data the data
     */
    public void add(T data) {
        add(data, size);
    }

    /**
     * The method checks if collection is empty or no, returns boolean value.
     *
     * @return the boolean value
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * The method returns collection actual size.
     *
     * @return the data type
     */
    public int size() {
        return size;
    }

    /**
     * Check if collection contains such data inside or not.
     *
     * @param data the searchingData
     * @return the status reflected in boolean value
     */
    @Override
    public boolean contains(T data) {
        return indexOf(data) != -1;
    }

    /**
     * Display list in a simple string.
     *
     * @return the String value
     */
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (T item : this) {
            joiner.add(String.valueOf(item));
        }
        return joiner.toString();
    }

    /**
     * Validate index when we processed add method.
     *
     * @param index the index
     */
    protected void validateIndexOnAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Incorrect index, it should be from 0 to " + size);
        }
    }

    /**
     * Validate index.
     *
     * @param index the index
     */
    protected void validateIndex(int index) {
        int maxIndex = size - 1;
        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException("Incorrect index, it should be from 0 to " + maxIndex);
        }
    }
}
