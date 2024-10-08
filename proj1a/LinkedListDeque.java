
/*
 *@title LinkedListDeque
 *@description
 *@author pika
 *@version 1.0
 *@create 2024/9/28 20:05
 */

import org.w3c.dom.Node;

import java.util.NoSuchElementException;

public class LinkedListDeque<T> {
    // 循环 sentinel
    private Node sentinel;
    int size;

    private class Node {
        Node prev;
        T item;
        Node next;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        // 创建sentinel node 用的
        public Node(Node prev, Node next) {

        }
    }

    // create empty list
    public LinkedListDeque() {
        Node sentinel = new Node(null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    // copy constructor
    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = other.sentinel;
        size = other.size;
    }

    /**
     * add item of type T to the front of the deque.
     * Not involve loop or recursion.
     * Operation time is constant.
     *
     * @param item
     */
    public void addFirst(T item) {
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    /**
     * add item of type T to the back of the deque.
     * * Not involve loop or recursion.11
     * * Operation time is constant.
     *
     * @param item
     */
    public void addLast(T item) {
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    /**
     * return true if deque is empty, false otherwise
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items in deque
     * must take constant time
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * prints the items in the deque from first to last
     * separated by a space
     */
    public void printDeque() {
        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
    }

    /**
     * remove and return the item at the front of the deque,
     * if no such item return null
     * * Not involve loop or recursion.
     * * Operation time is constant.
     *
     * @return <T>
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T result = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return result;
    }

    /**
     * remove and return item at the back of the deque,
     * if no such item return null
     * * Not involve loop or recursion.
     * * Operation time is constant.
     *
     * @return
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node removed = sentinel.prev;
        sentinel.prev = removed.prev;
        removed.next = sentinel;
        size--;
        return removed.item;
    }

    /**
     * get the item at the given index,
     * where 0 is the front, 1 is the next item
     * If no such item exists, returns null.
     *
     * @param index
     * @return
     */
    public T get(int index) {
        // 前置检查
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    /**
     * Like get method but use recursion
     * Must use iteration, not recursion
     *
     * @param index
     * @return T
     */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecurHelper(index, sentinel.next);
    }

    private T getRecurHelper(int index, Node current) {
        if (index == 0) {
            return current.item;
        }
        return getRecurHelper(index - 1, current.next);
    }
}
