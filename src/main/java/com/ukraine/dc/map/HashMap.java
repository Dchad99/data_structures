package com.ukraine.dc.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The type HashMap.
 *
 * @param <K> the key
 * @param <V> the value
 */
public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private Entry<K, V>[] buckets;
    private int size;

    /**
     * Initialize HashMap with default capacity;
     */
    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initialize HashMap with custom capacity.
     *
     * @param capacity the capacity
     */
    public HashMap(int capacity) {
        buckets = new Entry[capacity];
    }

    /**
     * Puts new element by its key.
     *
     * @param key   the unique value
     * @param value the value
     * @return the value
     */
    @Override
    public V put(K key, V value) {
        if (size >= buckets.length * 0.75) {
            buckets = expandBucketsSize();
        }
        int index = identifyBucketIndex(key);
        Entry<K, V> entry = buckets[index];
        Entry<K, V> prevEntry = null;

        while (entry != null) {
            if (entry.getKey() == null) {
                entry.setValue(value);
                return value;
            } else if (entry.getKey().equals(key)) {
                V prevValue = entry.getValue();
                entry.setValue(value);
                return prevValue;
            }
            prevEntry = entry;
            entry = entry.next;
        }
        if (prevEntry != null) {
            prevEntry.next = new Entry<>(key, value);
            size++;
            return value;
        }
        entry = new Entry<>(key, value);
        buckets[index] = entry;
        size++;
        return value;
    }

    /**
     * Gets the data by its unique key.
     *
     * @param key the key
     * @return the value
     */
    @Override
    public V get(K key) {
        int index = identifyBucketIndex(key);
        Entry<K, V> entry = buckets[index];

        if (!isEmpty()) {
            if (index == 0) {
                return entry.getValue();
            } else {
                for (Map.Entry<K, V> item : this) {
                    if (key.equals(item.getKey())) {
                        return item.getValue();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Checks if the collection empty or not.
     *
     * @return the boolean value
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the current size.
     *
     * @return the int value
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if map contains such key or not.
     *
     * @param key the key
     * @return the boolean value
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Removes data by its key.
     *
     * @param key the key
     * @return the value
     */
    @Override
    public V remove(K key) {
        int index = identifyBucketIndex(key);
        Entry<K, V> entry = buckets[index];
        if (!isEmpty() || entry == null) {
            Iterator<Map.Entry<K, V>> iterator = this.iterator();
            while (iterator.hasNext()) {
                Map.Entry<K, V> item = iterator.next();
                if (key == null) {
                    size--;
                    return item.getValue();
                } else if (key.equals(item.getKey())) {
                    V removed = item.getValue();
                    iterator.remove();
                    return removed;
                }
            }
        }
        return null;
    }

    public void clear() {
        buckets = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    public Entry<K,V>[] getBuckets() {
        return buckets;
    }

    @Override
    public String toString() {
        return "HashMap{" +
                "buckets=" + Arrays.toString(buckets) +
                ", size=" + size +
                '}';
    }

    private Entry<K,V>[] expandBucketsSize() {
        var iterator = this.iterator();
        var newMap = new HashMap(buckets.length * 2);
        while (iterator.hasNext()) {
            var item = iterator.next();
            newMap.put(item.getKey(), item.getValue());
        }
        return newMap.getBuckets();
    }

    private int identifyBucketIndex(K key) {
        return key == null ? 0 : Math.abs(key.hashCode() % buckets.length);
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    /**
     * Initialize map iterator.
     *
     * @return the Iterator impl
     */
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new MapIterator();
    }

    /**
     * The type MapIterator.
     */
    private class MapIterator implements Iterator<Map.Entry<K, V>> {
        private Entry<K, V> entry;
        private int index;
        private boolean isNextInvoked;

        /**
         * The method checks if map has one more element.
         *
         * @return the boolean value
         */
        @Override
        public boolean hasNext() {
            while (index != buckets.length && entry == null) {
                entry = buckets[index];
                index++;
            }
            return entry != null;
        }

        /**
         * The method based on hasNext method result returns data.
         *
         * @return the data type
         */
        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more element in the collection.");
            }
            isNextInvoked = true;
            Entry<K, V> current = entry;
            entry = current.next;
            return current;
        }

        /**
         * The method based on hasNext() and next() methods removes data.
         */
        @Override
        public void remove() {
            if (!isNextInvoked) {
                throw new IllegalStateException("Incorrect behavior for the iterator, when called remove() previously next() wasn't called");
            }
            Entry<K, V> removed = buckets[index - 1];
            Entry<K, V> prev = null;

            while (removed.next != entry) {
                prev = entry;
                removed = removed.next;
            }
            if (prev != null) {
                prev.next = removed.next;
                return;
            }
            buckets[index - 1] = null;
            size--;
        }
    }
}
