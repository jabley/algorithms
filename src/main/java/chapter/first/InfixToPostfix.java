package chapter.first;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.StringReader;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 *
 */
public class InfixToPostfix {

    interface TokenSupplier {
        boolean hasNext();

        String next();
    }

    public String convert(String input) {
        Scanner scanner = ArithmeticExpressionScannerFactory.create(new StringReader(input));

        StringBuilder buf = new StringBuilder();

        Consumer<String> writer = buf::append;

        TokenSupplier supplier = new TokenSupplier() {
            @Override
            public boolean hasNext() {
                return scanner.hasNext();
            }

            @Override
            public String next() {
                return scanner.next().trim();
            }
        };

        fixup(supplier, writer);

        return buf.toString();
    }

    private void fixup(TokenSupplier supplier, Consumer<String> writer) {
        Stack<String> items = new Stack<>();

        while (supplier.hasNext()) {
            String token = supplier.next();

            if (token.length() == 0) {
                continue;
            }

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
        Consumer<String> writer = StdOut::print;

        TokenSupplier supplier = new TokenSupplier() {
            @Override
            public boolean hasNext() {
                return !StdIn.isEmpty();
            }

            @Override
            public String next() {
                return StdIn.readString();
            }
        };

        new InfixToPostfix().fixup(supplier, writer);
    }
}
