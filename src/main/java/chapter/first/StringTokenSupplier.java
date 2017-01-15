package chapter.first;

import java.io.StringReader;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 */
public class StringTokenSupplier implements Iterator<String> {

    private final Scanner scanner;

    private String next;

    public StringTokenSupplier(String input) {
        this.scanner = ArithmeticExpressionScannerFactory.create(new StringReader(input));
    }

    @Override
    public boolean hasNext() {
        peekToken();
        return next != null;
    }

    private void peekToken() {
        if (next != null) {
            return;
        }

        while (next == null) {
            if (!scanner.hasNext()) {
                break;
            }

            next = this.scanner.next().trim();

            // Filter empty spaces
            if (next.length() == 0) {
                next = null;
            }
        }
    }

    @Override
    public String next() {
        String result = next;
        next = null;
        return result;
    }
}
