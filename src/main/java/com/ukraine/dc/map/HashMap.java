package com.ukraine.dc.map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The type HashMap.
 *
 * @param <K> the key
 * @param <V> the value
 */
public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
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
        if (size >= buckets.length * LOAD_FACTOR) {
            buckets = expandBucketsSize();
        }
        int index = getIndex(key);
        Entry<K, V> entry = buckets[index];
        int hash = hash(key);
        Entry<K, V> prevEntry = null;

        while (entry != null) {
            if (hash == entry.hash) {
                if (Objects.equals(entry.getKey(), key)) {
                    V prevValue = entry.getValue();
                    entry.setValue(value);
                    return prevValue;
                }
            }
            prevEntry = entry;
            entry = entry.next;
        }
        if (prevEntry != null) {
            prevEntry.next = new Entry<>(hash(key), key, value);
            size++;
            return value;
        }
        entry = new Entry<>(hash(key), key, value);
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
        int index = getIndex(key);
        int hash = hash(key);
        Entry<K, V> entry = buckets[index];

        if (!isEmpty()) {
            if (index == 0) {
                return entry.getValue();
            }
            while (entry != null) {
                if (hash == entry.hash && entry.getKey().equals(key)) {
                    return entry.getValue();
                }
                entry = entry.next;
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
        int index = getIndex(key);
        Entry<K, V> entry = buckets[index];

        if (!isEmpty()) {
            if (entry.getKey() == key) {
                buckets[index] = entry.next;
                entry.next = null;
                size--;
                return entry.getValue();
            }

            Entry<K, V> prev = entry;
            entry = entry.next;

            while (entry != null) {
                if (entry.getKey() == key) {
                    size--;
                    prev.next = entry.next;
                    entry.next = null;
                    return entry.getValue();
                }
                prev = entry;
                entry = entry.next;
            }
        }
        return null;
    }

    /**
     * Clear the collection.
     */
    public void clear() {
        buckets = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Expand bucket size and shrink the collection.
     *
     * @return the newBuckets array.
     */
    private Entry<K, V>[] expandBucketsSize() {
        var iterator = this.iterator();
        var newMap = new Entry[buckets.length * 2];
        while (iterator.hasNext()) {
            var item = iterator.next();
            innerPut(newMap, item.getKey(), item.getValue());
        }
        return newMap;
    }

    /**
     * The method stands for the enriching collection with new elements.
     *
     * @param newBuckets the newBuckets
     * @param key        the key
     * @param value      the value
     */
    private void innerPut(Entry<K, V>[] newBuckets, K key, V value) {
        int bucketIndex = getIndex(key);
        Entry<K, V> entry = newBuckets[bucketIndex];
        Entry<K, V> prevEntry = null;
        Entry<K, V> nextEntry;

        if (entry != null) {
            while (entry != null) {
                prevEntry = entry;
                entry = entry.next;
            }
        }
        if (prevEntry != null) {
            nextEntry = new Entry<>(hash(key), key, value);
            prevEntry.next = nextEntry;
        } else {
            entry = new Entry<>(hash(key), key, value);
            newBuckets[bucketIndex] = entry;
        }
    }

    /**
     * The method stands for the logic of identifying the bucket index.
     *
     * @param key the key
     * @return int value
     */
    private int getIndex(K key) {
        return key == null ? 0 : Math.abs(key.hashCode() % buckets.length);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (Map.Entry<K, V> entry : this) {
            joiner.add(entry.toString());
        }
        return joiner.toString();
    }

    /**
     * The type Entry.
     *
     * @param <K> the key
     * @param <V> the value
     */
    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private final int hash;
        private Entry<K, V> next;

        private Entry(int hash, K key, V value) {
            this.hash = hash;
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry)) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return key.equals(entry.key)
                    && value.equals(entry.value);
        }

        @Override
        public final int hashCode() {
            return Objects.hash(key, value);
        }

        public int getHash() {
            return hash;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private int hash(Object key) {
        return key == null ? 0 : key.hashCode();
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
        private int bucketIndex;
        private Entry<K, V> current;
        private Entry<K, V> nextElement;
        private boolean canRemove;

        /**
         * The method checks if map has one more element.
         *
         * @return the boolean value.
         */
        @Override
        public boolean hasNext() {
            while (bucketIndex < buckets.length && current == null) {
                current = buckets[bucketIndex];
                bucketIndex++;
            }
            return current != null;
        }

        /**
         * The method based on hasNext() return element of the collection.
         *
         * @return the Entry instance
         */
        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more element in the collection.");
            }
            canRemove = true;
            nextElement = current;
            var entry = current;
            current = current.next;
            return entry;
        }

        /**
         * The method based on hasNext() and next() methods removes data.
         */
        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Incorrect behavior for the iterator, when called remove() previously next() wasn't called");
            }
            HashMap.this.remove(nextElement.getKey());
            canRemove = false;
        }
    }
}
