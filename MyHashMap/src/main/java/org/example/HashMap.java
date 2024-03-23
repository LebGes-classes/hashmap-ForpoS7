package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<K extends Comparable<K>,V>  implements HashMapRealization<K,V> {
    private Node<K,V> [] table;
    private int size;
    public int size () {
        return size;
    }
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    public Node<K,V> [] getTable () {
        return table;
    }
    HashMap () {
        this.table = new Node[4];
    }
    private void reCapacity (int newCapacity) {
        Node<K,V> [] newTable = new Node[newCapacity];
        System.arraycopy(table, 0, newTable, 0, table.length);
        table = newTable;
    }
    public void put (K key, V val) {
        // Расширение мапа
        if ((float) size / table.length >= DEFAULT_LOAD_FACTOR){
            reCapacity(table.length * 2);
        }
        // Высчитывается hash по ключу
        int hash = key.hashCode();
        // Используется хэш-код для определения индекса bucket
        int bucketIndex = hash % table.length;
        // Обработка потенциальной коллизии
        Node<K, V> curNode = table[bucketIndex];
        while (curNode != null) {
            if (curNode.getHash() == hash) {
                if (curNode.getKey().equals(key)) {
                    // Обновляем значение, если ключ уже существует
                    curNode.setVal(val);
                    return;
                }
            }
            // Движение по bucket
            curNode = curNode.getNext();
        }
        // Добавление элемента, при уникальности ключа
        Node<K,V> newNode = new Node<>(hash, key, val);
        newNode.setNext(table[bucketIndex]);
        table[bucketIndex] = newNode;
        table[bucketIndex].setPrev(newNode);
        size++;

    }
    public boolean containsKey (K key) {
        // Высчитывается hash по ключу
        int hash = key.hashCode();
        // Используется хэш-код для определения индекса bucket
        int bucketIndex = hash % table.length;
        // Поиск ключа
        Node<K,V> curNode = table[bucketIndex];
        while (curNode != null) {
            if (curNode.getHash() == hash) {
                if (curNode.getKey().equals(key)) {
                    return true;
                }
            }
            curNode = curNode.getNext();
        }
        return false;
    }
    public boolean containsValue (V val) {
        // Цикл по buckets
        for (Node<K,V> bucket : table) {
            Node<K,V> curNode = bucket;
            // Цикл по всей ячейке
            while (curNode != null) {
                if (curNode.getVal().equals(val)) {
                    return true;
                }
                curNode = curNode.getNext();
            }
        }
        return false;
    }
    public V remove (K key) {
        // Высчитывается hash по ключу
        int hash = key.hashCode();
        // Используется хэш-код для определения индекса bucket
        int bucketIndex = hash % table.length;
        // Поиск ключа
        Node<K,V> curNode = table[bucketIndex];
        // результат
        V res;
        if (curNode.getKey().equals(key)) {
            res = curNode.getVal();
            table[bucketIndex] = table[bucketIndex].getNext();
            size--;
            return res;
        }
        while (curNode.getNext() != null) {
            if (curNode.getNext().getHash() == hash) {
                if (curNode.getNext().getKey().equals(key)) {
                    res = curNode.getNext().getVal();
                    curNode.setNext(curNode.getNext().getNext());
                    if (curNode.getNext() != null) {
                        curNode.getNext().setPrev(curNode);
                    }
                    size--;
                    return res;
                }
            }
            curNode = curNode.getNext();
        }
        return null;
    }
    public V get (K key) {
        // Высчитывается hash по ключу
        int hash = key.hashCode();
        // Используется хэш-код для определения индекса bucket
        int bucketIndex = hash % table.length;
        // Поиск ключа
        Node<K,V> curNode = table[bucketIndex];
        while (curNode != null) {
            if (curNode.getHash() == hash) {
                if (curNode.getKey().equals(key)) {
                    return curNode.getVal();
                }
            }
            curNode = curNode.getNext();
        }
        return null;
    }
    public void clear () {
        table = new Node[4];
        size = 0;
    }
}
