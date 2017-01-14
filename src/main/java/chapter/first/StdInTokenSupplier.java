package chapter.first;

import edu.princeton.cs.algs4.StdIn;

/**
 *
 */
public class StdInTokenSupplier implements TokenSupplier {
    @Override
    public boolean hasNext() {
        return !StdIn.isEmpty();
    }

    @Override
    public String next() {
        return StdIn.readString();
    }}
