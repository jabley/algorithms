package chapter.first;

public class FixedCapacityStackOfStrings {
    private final String a[];
    private int N;

    public FixedCapacityStackOfStrings(int capacity) {
      this.a = new String[capacity];
    }

    public boolean isEmpty() {
      return N == 0;
    }

    public boolean isFull() {
      return N == this.a.length;
    }

    public int size() {
      return N;
    }

    public void push(String item) {
      a[N++] = item;
    }

    public String pop() {
      return a[--N];
    }
}
