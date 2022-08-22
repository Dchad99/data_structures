package com.ukraine.dc.map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

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
        if (size > buckets.length * 0.75) {
            expandArrayOfNodes();
        }
        int index = identifyBucketIndex(key.hashCode());
        Entry<K, V> entry = buckets[index];
        Entry<K, V> prevEntry = null;

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                V prevValue = entry.getValue();
                entry.setValue(value);
                return prevValue;
            }
            prevEntry = entry;
            entry = entry.next;
        }
        if (prevEntry != null) {
            prevEntry.next = new Entry<>(key, key.hashCode(), value);
            size++;
            return value;
        }
        entry = new Entry<>(key, key.hashCode(), value);
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
        int index = identifyBucketIndex(key.hashCode());
        Entry<K, V> entry = buckets[index];

        if (entry == null) {
            throw new NoSuchElementException();
        } else {
            while (entry != null) {
                if (key.equals(entry.key)) {
                    return entry.value;
                }
                entry = entry.next;
            }
        }
        return null;
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
        int index = identifyBucketIndex(key.hashCode());
        Entry<K, V> entry = buckets[index];
        Entry<K, V> prev = null;

        if (entry == null) {
            throw new NoSuchElementException();
        }

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                size--;
                if (prev != null) {
                    prev.next = entry.next;
                    entry.next = null;
                    return entry.value;
                }
                return entry.value;
            }
            prev = entry;
            entry = entry.next;
        }
        return null;
    }

    public void clear() {
        buckets = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }


    private void expandArrayOfNodes() {
        Entry<K, V>[] newNodes = new Entry[buckets.length * 2];
        var iterator = new MapIterator();
        while (iterator.hasNext()) {
            var entry = iterator.next();
            int indexOfBucket = identifyBucketIndex(entry.hash);
            if (newNodes[indexOfBucket] != null) {
                newNodes[indexOfBucket].next = entry;
            }
            newNodes[indexOfBucket] = entry;
        }
        buckets = newNodes;
    }

    private int identifyBucketIndex(int hash) {
        return Math.abs(hash % buckets.length);
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private final int hash;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, int hash, V value) {
            this.key = key;
            this.hash = hash;
            this.value = value;
            this.next = null;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry<?, ?> entry = (Entry<?, ?>) obj;
            return hash == entry.hash
                    && Objects.equals(key, entry.key)
                    && Objects.equals(value, entry.value)
                    && Objects.equals(next, entry.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(hash, key, value, next);
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
            if (hasNext()) {
                isNextInvoked = true;
                Entry<K, V> current = entry;
                entry = current.next;
                return current;
            }
            throw new NoSuchElementException();
        }

        /**
         * The method based on hasNext() and next() methods removes data.
         */
        @Override
        public void remove() {
            if (!isNextInvoked) {
                throw new IllegalStateException();
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
