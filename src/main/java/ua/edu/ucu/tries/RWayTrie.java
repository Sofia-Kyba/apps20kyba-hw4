package ua.edu.ucu.tries;
import ua.edu.ucu.queue.Queue;

public class RWayTrie implements Trie {

    private static final int R = 256;
    private Node root = new Node();
    private int size = 0;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    @Override
    public void add(Tuple t) {
        root = put(root, t.term, t.weight, 0);
        size++;
    }

    private Node put(Node x, String key, int val, int d) {
        // Change value associated with key if in
        // subtrie rooted at x.

        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        // Use dth key char to identify subtrie
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    @Override
    public boolean contains(String word) {
        Node x = get(root, word, 0);
        return x != null;
    }

    public Node get(String word)
    {
        Node x = get(root, word, 0);
        return x;
    }

    private Node get(Node x, String key, int d) {
        // Return value associated with key in
        // the subtrie rooted at x.
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        // Use dth key char to identify subtrie.
        return get(x.next[c], key, d+1);
    }

    @Override
    public boolean delete(String word) {
        if (contains(word)) {
            Node x = delete(root, word, 0);
            size--;
            return true;
        }
        return false;
    }

    private Node delete(Node x, String key, int d)
    {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.val != null) {
            return x;
        }
        for (char c = 0; c < R; c++)
            if (x.next[c] != null) {
                return x;
            }
        return null;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue queue = new Queue();
        collect(get(s), s, queue);
        return queue;
    }

    private void collect(Node x, String s, Queue q)
    {
        if (x == null) {
            return;
        }
        if (x.val != null) {
            q.enqueue(s);
        }
        for (char c = 0; c < R; c++) {
            collect(x.next[c], s + c, q);
        }
    }

    @Override
    public int size() {
        return size;
    }

}
