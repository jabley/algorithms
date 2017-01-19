package chapter.first;

/**
 *
 */
public class LinkedListGeneralizedQueue<T> implements GeneralizedQueue<T> {

    private int size = 0;

    private Node<T> first;

    public LinkedListGeneralizedQueue() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void insert(T item) {
        size++;
        if (first == null) {
            first = new Node<T>(item, null, null);
            return;
        }

        Node<T> last = getLast();
        last.next = new Node<T>(item, last, null);
    }

    @Override
    public T delete(int k) {
        rangeCheck(k);
        return unlink(node(k));
    }

    private T unlink(Node<T> node) {
        Node<T> prev = node.prev;
        Node<T> next = node.next;

        if (prev != null) {
            prev.next = next;
        } else {
            // we're removing the first node.
            first = next;
        }
        if (next != null) {
            next.prev = prev;
        } else {
            // we're removing the last node
        }
        size--;
        return node.item;
    }

    private Node<T> node(int k) {
        Node<T> n = first;

        for (int i = 0; i < k; i++) {
            n = n.next;
        }

        return n;
    }

    private void rangeCheck(int k) {
        if (k >= size) {
            throw new IllegalArgumentException(String.format("Index: %d, Size: %d", k, size));
        }
    }

    public Node<T> getLast() {
        Node<T> current = first;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    private static class Node<T> {

        T item;
        Node<T> prev;
        Node<T> next;

        public Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

}
