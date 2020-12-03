package ua.edu.ucu.queue;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {

    private Node head;
    private int size;

    private static class Node {
        private final Object data;
        private Node next;

        private Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    public ImmutableLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public ImmutableLinkedList(Object [] c) {
        if (c.length != 0) {
            this.size = c.length;
            this.head = new Node(c[0]);
            Node currentN = this.head;
            for (int i = 1; i < c.length; i++) {
                Node newN = new Node(c[i]);
                currentN.next = newN;
                currentN =  newN;
            }
        }
    }

    @Override
    public ImmutableList add(Object e) {
        return add(this.size, e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        Object [] element = {e};
        return addAll(index, element);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(this.size, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Object[] newList = new Object[size + c.length];
        Node currentN = this.head;

        int counter = 0;
        int counterSecond = 0;
        while (counter < index && currentN != null) {
            newList[counter] = currentN.data;
            currentN = currentN.next;
            counter++;
        }

        int ind = index + c.length;
        while (counter < ind) {
            newList[counter] = c[counterSecond];
            counter++;
            counterSecond++;
        }

        while (currentN != null) {
            newList[counter] = currentN.data;
            currentN = currentN.next;
            counter++;
        }

        return new ImmutableLinkedList(newList);
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (isEmpty()) {
            return null;
        }

        int counter = 0;
        Node currentN = this.head;
        while (currentN != null) {
            if (counter == index) {
                return currentN.data;
            }
            counter++;
            currentN = currentN.next;
        }
        return -1;
    }

    @Override
    public ImmutableList remove(int index) {
        if (index < 0 || index > this.size || isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        Object[] newList = new Object[this.size - 1];
        int i = 0;
        int counter = 0;
        Node currentN = this.head;
        while (currentN != null) {
            if (counter != index) {
                newList[i] = currentN.data;
                i++;
            }
            counter++;
            currentN = currentN.next;
        }

        return new ImmutableLinkedList(newList);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        Object[] newList = new Object[this.size];
        Node currentN = this.head;

        if (index == 0) {
            newList[0] = e;
        }
        else {
            int counter = 0;
            while (currentN != null) {
                if (counter != index) {
                    newList[counter] = currentN.data;
                }
                else {
                    newList[counter] = e;
                }
                counter++;
                currentN = currentN.next;
            }
        }

        return new ImmutableLinkedList(newList);
    }

    @Override
    public int indexOf(Object e) {
        int counter = 0;
        Node currentN = this.head;
        while (currentN != null) {
            if (currentN.data == e) {
                return counter;
            } else {
                currentN = currentN.next;
            }
            counter++;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public Object[] toArray() {
        Object [] newList = new Object[this.size];
        Node currentN = this.head;

        int counter = 0;
        while (currentN != null) {
            newList[counter] = currentN.data;
            currentN = currentN.next;
            counter++;
        }
        return newList;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) add(this.size, e);
    }

    public Object getFirst() {
        return get(0);
    }

    public Object getLast() {
        return get(this.size - 1);
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) remove(this.size - 1);
    }


}

