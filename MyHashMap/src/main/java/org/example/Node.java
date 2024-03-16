package org.example;
public class Node<K,V> {
    private int hash;
    private K key;
    private V val;
    private Node<K,V> next;
    Node(int hash, K key, V val) {
        this.hash = hash;
        this.key = key;
        this.val = val;
    }
    public K getKey() {
        return key;
    }
    public Node<K,V> getNext(){
        return next;
    }
    public void setNext(Node<K,V> next) {
        this.next = next;
    }
    public void setVal(V val) {
        this.val = val;
    }
    public V getVal() {
        return val;
    }
    public int getHash() {
        return hash;
    }
}
