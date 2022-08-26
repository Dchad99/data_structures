package com.ukraine.dc.list.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The LinkedList implementation.
 *
 * @param <T> the data type
 */
public class LinkedList<T> extends AbstractList<T> {
    private Node<T> head;
    private Node<T> tail;

    /**
     * The method stands for adding data by index.
     *
     * @param index the index
     * @param data  the data
     */
    @Override
    public void add(T data, int index) {
        validateIndexOnAdd(index);
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
            Node<T> dataByIndex = getNode(index);
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
        Node<T> oldValue = getNode(index);
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
        return getNode(index).value;
    }

    /**
     * Sets new data by specified index.
     *
     * @param index the index
     * @param data  the newData
     * @return the previousValue
     */
    @Override
    public T set(T data, int index) {
        validateIndex(index);
        Node<T> oldValue = getNode(index);
        oldValue.value = data;
        return oldValue.value;
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
     * Finds the first match from the beginning of the collection.
     *
     * @param data the searchingData
     * @return the index of searchingData
     */
    @Override
    public int indexOf(T data) {
        int currentIndex = 0;
        for (Node<T> current = head; current != null; current = current.next) {
            if (Objects.equals(data, current.value)) {
                return currentIndex;
            }
            currentIndex++;
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
        int currentIndex = size;
        for (Node<T> current = tail; current != null; current = current.prev) {
            currentIndex--;
            if (Objects.equals(data, current.value)) {
                return currentIndex;
            }
        }
        return -1;
    }

    private Node<T> getNode(int index) {
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
     * The node, as an element of the collection.
     *
     * @param <T> the data type
     */
    private static final class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
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
        private Node<T> currentNode = head;
        private Node<T> prevNode = null;
        private boolean canRemove;

        /**
         * Checks if collection has next element or not.
         *
         * @return the boolean value
         */
        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        /**
         * Returns the data based on result of the hasNext() method.
         *
         * @return the data
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more element in the collection.");
            }
            canRemove = true;
            prevNode = currentNode;
            currentNode = currentNode.next;
            return prevNode.value;
        }

        /**
         * Removes data from the collection based on previous method implemented in MyIterator: hasNext, next.
         */
        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Incorrect behavior for the iterator, when called remove() previously next() wasn't called");
            }
            canRemove = false;
            prevNode = null;
            size--;
        }
    }
}
