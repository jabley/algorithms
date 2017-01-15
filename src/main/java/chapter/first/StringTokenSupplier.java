package chapter.first;

import java.io.StringReader;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 */
public class StringTokenSupplier implements Iterator<String> {

    private final Scanner scanner;

    public StringTokenSupplier(String input) {
        this.scanner = ArithmeticExpressionScannerFactory.create(new StringReader(input));
    }

    @Override
    public boolean hasNext() {
        return this.scanner.hasNext();
    }

    @Override
    public String next() {
        return this.scanner.next().trim();
    }
}
