package com.ukraine.dc.list.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.ukraine.dc.util.Constants.HAS_NOT_NEXT_ELEMENT;
import static com.ukraine.dc.util.Constants.ITERATOR_INCORRECT;

/**
 * The LinkedList implementation.
 *
 * @param <T> the data type
 */
public class LinkedList<T> extends AbstractList<T> {
    private Node<T> head;
    private Node<T> tail;

    /**
     * The method stands for adding element to the list.
     *
     * @param data the data
     */
    @Override
    public void add(T data) {
        add(size, data);
    }

    /**
     * The method stands for adding data by index.
     *
     * @param index the index
     * @param data the data
     */
    @Override
    public void add(int index, T data) {
        validateIndex(index);
        Node<T> value = new Node<>(data);
        if (isEmpty()) {
            head = tail = value;
        } else if (index == 0) {
            head.prev = value;
            value.next = head;
            head = value;
        } else if (index == size) {
            tail.next = value;
            value.prev = tail;
            tail = value;
        } else {
            Node<T> dataByIndex = getNodeByIndex(index);
            dataByIndex.prev.next = value;
            value.next = dataByIndex;
            dataByIndex.prev = value;
        }
        size++;
    }

    /**
     * The method stands for the removing element from the list by its id.
     *
     * @param index the index
     * @return the removeData
     */
    @Override
    public T remove(int index) {
        validateIndex(index);
        Node<T> oldValue = getNodeByIndex(index);
        if (size == 1) {
            head = tail = null;
        } else if (index == size - 1) {
            tail.prev.next = null;
            tail = tail.prev;
        } else if (index == 0) {
            head.next.prev = null;
            head = head.next;
        } else {
            oldValue.prev.next = oldValue.next;
            oldValue.next.prev = oldValue.prev;
        }
        size--;
        return oldValue.value;
    }

    /**
     * The method returns data by its index in the list.
     *
     * @param index the index
     * @return the data by index
     */
    @Override
    public T get(int index) {
        validateIndex(index);
        return getNodeByIndex(index).value;
    }

    /**
     * Sets new data by specified index.
     *
     * @param index the index
     * @param data the newData
     * @return the previousValue
     */
    @Override
    public T set(int index, T data) {
        validateIndex(index);
        Node<T> oldValue = getNodeByIndex(index);
        oldValue.value = data;
        return oldValue.value;
    }

    /**
     * Checks if collection isEmpty or not.
     *
     * @return the boolean value
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the current size of the collection.
     *
     * @return the size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Clears thw whole collection.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
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
     * Finds the first match from the beginning of the collection.
     *
     * @param data the searchingData
     * @return the index of searchingData
     */
    @Override
    public int indexOf(T data) {
        if (data == null) {
            for (int i = 0; i < size; i++) {
                if (get(i) == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (data.equals(get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Finds the first match from the end of the collection.
     *
     * @param data the searchingData
     * @return the index of searchingData
     */
    @Override
    public int lastIndexOf(T data) {
        if (data == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (get(i) == null)
                    return i;
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (data.equals(get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Reflects data to the string.
     *
     * @return the string
     */
    public String toString() {
        return super.toString(this);
    }

    /**
     * The node, as an element of the collection.
     *
     * @param <T> the data type
     */
    private static final class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> node;
        if (index < size / 2) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    /**
     * Initialize the iterator.
     *
     * @return the Iterator implementation for collection
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
         * Checks if collection has next element or not.
         *
         * @return the boolean value
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * Returns the data based on result of the hasNext() method.
         *
         * @return the data
         */
        @Override
        public T next() {
            if (hasNext()) {
                isNextInvoked = true;
                return get(index++);
            }
            throw new NoSuchElementException(HAS_NOT_NEXT_ELEMENT);
        }

        /**
         * Removes data from the collection based on previous method implemented in MyIterator: hasNext, next.
         */
        @Override
        public void remove() {
            if (!isNextInvoked) {
                throw new IllegalStateException(ITERATOR_INCORRECT);
            }
            LinkedList.this.remove(index - 1);
            isNextInvoked = false;
        }
    }
}
