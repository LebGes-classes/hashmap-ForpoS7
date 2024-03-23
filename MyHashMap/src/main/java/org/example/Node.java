package org.example;
public class Node<K,V> {
    private int hash;
    private K key;
    private V val;
    private Node<K,V> next;
    private Node<K,V> prev;
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
    public Node<K, V> getPrev() {
        return prev;
    }
    public void setPrev(Node<K, V> prev) {
        this.prev = prev;
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
