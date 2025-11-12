package com.epam.rd.autocode.dllist;

import java.util.Optional;

public class DoublyLinkedListImpl implements DoublyLinkedList {

    private Node head;
    private Node tail;

    private static class Node {
        Object element;
        Node next;
        Node prev;

        Node(Object obj, Node prev, Node next) {
            this.element = obj;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public boolean addFirst(Object element) {
        if (element == null) {
            return false;
        }

        Node newNode = new Node(element, null, head);

        if (head != null) {
            head.prev = newNode;
        } else {
            // List was empty, so new node is also the tail
            tail = newNode;
        }

        head = newNode;
        return true;
    }

    @Override
    public boolean addLast(Object element) {
        if (element == null) {
            return false;
        }

        Node newNode = new Node(element, tail, null);

        if (tail != null) {
            tail.next = newNode;
        } else {
            // List was empty, so new node is also the head
            head = newNode;
        }

        tail = newNode;
        return true;
    }

    @Override
    public void delete(int index) {
        Node current = getNodeAtIndex(index);

        if (current == null) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        // Remove the node by updating surrounding nodes
        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            // Removing head
            head = current.next;
        }

        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            // Removing tail
            tail = current.prev;
        }
    }

    @Override
    public Optional<Object> remove(Object element) {
        if (element == null) {
            return Optional.empty();
        }

        Node current = head;
        while (current != null) {
            if (element.equals(current.element)) {
                // Found the node to remove
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    // Removing head
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    // Removing tail
                    tail = current.prev;
                }

                return Optional.of(current.element);
            }
            current = current.next;
        }

        return Optional.empty();
    }

    @Override
    public boolean set(int index, Object element) {
        if (element == null) {
            return false;
        }

        Node current = getNodeAtIndex(index);
        if (current == null) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        current.element = element;
        return true;
    }

    @Override
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public Object[] toArray() {
        int size = size();
        Object[] array = new Object[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.element;
            current = current.next;
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.element.toString());
            if (current.next != null) {
                sb.append(" ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    // Helper method to get node at specified index
    private Node getNodeAtIndex(int index) {
        if (index < 0) {
            return null;
        }

        Node current = head;
        int currentIndex = 0;

        while (current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }

        return current;
    }
}