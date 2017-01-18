package chapter.first;

import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * In the Josephus problem from antiquity, N people are in dire straits and agree to the following strategy to reduce
 * the population.
 * They arrange themselves in a circle (at positions numbered from 0 to N-1) and proceed around the circle, eliminating
 * every Mth person until only one person is left. Legend has it that Josephus figured out where to sit to avoid being
 * eliminated.
 *
 * This is a Queue-based implementation which calculates the sequence in which people are elimated, and thus can be used
 * to show Josephus where to sit in the circle.
 */
public class Josephus {
    private final int m;
    private final Queue<Integer> circle;

    public Josephus(int m, int n) {
        this.m = m;
        this.circle = newCircle(n);
    }

    private Queue<Integer> newCircle(int n) {
        Queue<Integer> result = new Queue<>();
        for (int pos = 0; pos < n; pos++) {
            result.enqueue(pos);
        }
        return result;
    }

    public Integer[] eliminationSequence() {
        List<Integer> sequence = new ArrayList<>(circle.size());

        while (!this.circle.isEmpty()) {
            sequence.add(getNext());
        }

        return sequence.toArray(new Integer[0]);
    }

    private Integer getNext() {
        int count = 0;
        for (;count < (this.m - 1); count++) {
            this.circle.enqueue(this.circle.dequeue());
        }
        return this.circle.dequeue();
    }
}
