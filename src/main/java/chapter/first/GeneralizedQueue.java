package chapter.first;

public interface GeneralizedQueue<T> {

    boolean isEmpty();

    void insert(T item);

    T delete(int k);
}
