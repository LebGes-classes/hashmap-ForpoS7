package org.example;

public class HashMap<K extends Comparable<K>,V>  implements HashMapRealization<K,V>  {
    private Node<K,V> [] table;
    private int size;
    public int size () {
        return size;
    }
    public Node<K,V> [] getTable () {
        return table;
    }
    HashMap () {
        this.table = new Node[4];
    }
    private void reCapacity (int newCapacity) {
        Node<K,V> [] newTable = new Node[newCapacity];
        // Цикл по ячейкам
        for (Node<K,V> bucket : table) {
            // Цикл по Node в ячейках
            while (bucket != null) {
                int newBucketIndex = bucket.getHash() % newCapacity;
                // Цикл по Node в расширенном HashMap
                while (newTable[newBucketIndex] != null) {
                    newTable[newBucketIndex] = newTable[newBucketIndex].getNext();
                }
                newTable[newBucketIndex] = bucket;
                bucket = bucket.getNext();
            }
        }
        table = newTable;
    }
    public void put (K key, V val) {
        // Высчитывается hash по ключу
        int hash = key.hashCode();
        // Используется хэш-код для определения индекса bucket
        int bucketIndex = hash % table.length;
        // Счетчик по Node
        int count = 0;
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
            count++;
            // Движение по bucket
            curNode = curNode.getNext();
        }
        // Добавление элемента, при уникальности ключа
        Node<K,V> newNode = new Node<>(hash, key, val);
        newNode.setNext(table[bucketIndex]);
        table[bucketIndex] = newNode;
        size++;


        // Расширение массива при увеличении одного из bucket до table.length
        if (count >= table.length) {
            reCapacity(table.length * 2);
        }

    }
    public boolean containsKey (K key) {
        // Высчитывается hash по ключу
        int hash = key.hashCode();
        // Используется хэш-код для определения индекса bucket
        int bucketIndex = hash % table.length;
        // Поиск ключа
        Node<K,V> curNode = table[bucketIndex];
        while (curNode != null) {
            if (curNode.getKey().equals(key)) {
                return true;
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
            if (curNode.getNext().getKey().equals(key)) {
                res = curNode.getNext().getVal();
                curNode.setNext(curNode.getNext().getNext());
                size--;
                return res;
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
            if (curNode.getKey().equals(key)) {
                return curNode.getVal();
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
