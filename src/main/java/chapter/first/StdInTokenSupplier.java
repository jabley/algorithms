package chapter.first;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

/**
 *
 */
public class StdInTokenSupplier implements Iterator<String> {
    @Override
    public boolean hasNext() {
        return !StdIn.isEmpty();
    }

    @Override
    public String next() {
        return StdIn.readString();
    }}
