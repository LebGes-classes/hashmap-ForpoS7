package org.example;
public interface HashMapRealization<K,V> {
    void put(K key, V value);
    V get(K key);
    boolean containsKey(K key);
    boolean containsValue(V value);
    V remove(K key);
    int size();
    void clear();
}
