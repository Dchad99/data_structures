package com.ukraine.dc.map;

import java.util.Iterator;

/**
 * The map interface.
 *
 * @param <K> the key
 * @param <V> the value
 */
public interface Map<K, V> extends Iterable<Map.Entry<K, V>> {
    V put(K key, V value);

    V get(K key);

    int size();

    boolean containsKey(K key);

    V remove(K key);

    void clear();

    boolean isEmpty();

    default Iterator<Map.Entry<K, V>> iterator() {
        throw new UnsupportedOperationException();
    }

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }
}
