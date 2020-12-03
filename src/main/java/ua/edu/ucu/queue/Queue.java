package ua.edu.ucu.queue;

import ua.edu.ucu.iterator.Iterator;
import ua.edu.ucu.iterator.KeyContainer;

public class Queue implements Iterable<String> {

    private ImmutableLinkedList items;
    private int queueSize;

    public Queue() {
        this.items = new ImmutableLinkedList();
        this.queueSize = 0;
    }

    public Queue(ImmutableLinkedList items) {
        this.items = items;
        this.queueSize = items.size();
    }

    public Object peek() {
        return items.getFirst();
    }

    public Object dequeue() {
        Object peek = items.getFirst();
        items = items.removeFirst();
        queueSize--;
        return peek;
    }

    public void enqueue(Object e) {
        items = items.addLast(e);
        queueSize++;
    }

    public ImmutableLinkedList getItems() {
        return items;
    }

    public int size() {
        return queueSize;
    }

    @Override
    public Iterator<String> iterator() {
        Queue queue = this;
        KeyContainer container = new KeyContainer(queue);
        return container.getIterator();
    }

}
