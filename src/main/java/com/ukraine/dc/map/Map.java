package com.ukraine.dc.map;

public interface Map<K, V> extends Iterable<Map.Entry<K, V>> {
    V put(K key, V value);

    V get(K key);

    int size();

    boolean containsKey(K key);

    V remove(K key);

    void clear();

    interface Entry<K, V> {
        K key();
        V value();
    }
}
