package linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private Node current;

    private class Node {

        private final Item item;
        private Node next;

        public Node(Item item) {
            this.item = item;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    private Node getPrevious(Node last) {
        current = first;
        while (current != null) {
            if (current.next == last) {
                return current;
            } else {
                current = current.next;
            }
        }
        return null;
    }

    public void addLast(Item item) {
        Node node = new Node(item);

        if (isEmpty()) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    public void addFirst(Item item) {
        Node node = new Node(item);

        if (isEmpty()) {
            first = last = node;
        } else {
            node.next = first;
            first = node;
        }
    }

    public int indexOf(Item item) {
        int index = 0;
        current = first;
        while (current != null) {
            if (current.item == item) {
                return index;
            } else {
                current = current.next;
                index++;
            }
        }
        return -1;
    }

    public boolean contains(Item item) {
        return indexOf(item) != -1;
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("LinkedList is empty.");
        }
        if (first == last) {
            first = last = null;
            return;

        }
        Node second = first.next;
        first.next = null;
        first = second;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("LinkedList is empty.");
        }

        if (first == last) {
            first = last = null;
            return;
        }

        Node previous = getPrevious(last);
        last = previous;
        last.next = null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedlist = new LinkedList<>();
        linkedlist.addFirst(1);
        linkedlist.addFirst(2);
        linkedlist.addFirst(3);
        linkedlist.addLast(4);
        linkedlist.addLast(5);
        linkedlist.removeLast();
        linkedlist.removeFirst();
        for (int i : linkedlist) {
            System.out.println(i);
        }
        System.out.println(linkedlist.indexOf(4));
        System.out.println(linkedlist.contains(4));
    }
}
