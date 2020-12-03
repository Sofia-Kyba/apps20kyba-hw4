package ua.edu.ucu.iterator;
import ua.edu.ucu.queue.Queue;

public class KeyContainer implements Container{
    private Queue queue;

    public KeyContainer(Queue elements) {
        this.queue = elements;
    }

    @Override
    public Iterator<String> getIterator() {
        return new KeyIterator(queue);
    }

    private static class KeyIterator implements Iterator<String> {
        private Queue queue;

        public KeyIterator(Queue elements) {
            this.queue = elements;
        }

        @Override
        public boolean hasNext() {
            if (queue.size() != 0) {
                return true;
            }
            return false;
        }

        @Override
        public String next() {
            if (hasNext()) {
                return (String) queue.dequeue();
            }
            return null;
        }
    }
}
