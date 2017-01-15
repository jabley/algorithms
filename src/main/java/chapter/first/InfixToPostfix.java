package chapter.first;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 *
 */
public class InfixToPostfix {

    public String convert(String input) {
        StringBuilder buf = new StringBuilder();

        fixup(new StringTokenSupplier(input), buf::append);

        return buf.toString();
    }

    private void fixup(Iterator<String> supplier, Consumer<String> writer) {
        Stack<String> items = new Stack<>();

        while (supplier.hasNext()) {
            String token = supplier.next();

            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                items.push(token);
            } else if (")".equals(token)) {
                writer.accept(items.pop() + " ");
            } else if (!"(".equals(token)) {
                writer.accept(token + " ");
            }
        }
    }

    public static void main(String[] args) {
        new InfixToPostfix().fixup(new StdInTokenSupplier(), StdOut::print);
    }
}
