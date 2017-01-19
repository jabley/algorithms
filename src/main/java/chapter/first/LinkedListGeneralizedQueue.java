package chapter.first;

/**
 *
 */
public class LinkedListGeneralizedQueue<T> implements GeneralizedQueue<T> {

    private int size = 0;

    private Node<T> first;

    private Node<T> last;

    public LinkedListGeneralizedQueue() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void insert(T item) {
        linkLast(item);
    }

    private void linkLast(T item) {
        Node<T> l = last;
        Node<T> newNode = new Node<T>(item, l, null);

        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }

        last = newNode;
        size++;
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

        // Check whether we will be quicker walking forwards or backwards
        if (k < (size >> 1)) {
            Node<T> n = first;

            for (int i = 0; i < k; i++) {
                n = n.next;
            }

            return n;
        } else {
           Node<T> n = last;
           for (int i = size - 1; i > k; i--) {
               n = n.prev;
           }

           return n;
        }
    }

    private void rangeCheck(int k) {
        if (k >= size) {
            throw new IllegalArgumentException(String.format("Index: %d, Size: %d", k, size));
        }
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
