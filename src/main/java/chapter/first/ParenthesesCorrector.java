package chapter.first;

import edu.princeton.cs.algs4.Stack;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ParenthesesCorrector {

    private final String input;
    private final Stack<String> read;

    public ParenthesesCorrector(String input) {
        this.input = input;
        this.read = new Stack<>();
    }

    public String correct() {
        StringBuilder buf = new StringBuilder(input.length() + 16);

        Scanner scanner = ArithmeticExpressionScannerFactory.create(new StringReader(this.input));

        while (scanner.hasNext()) {
            String token = scanner.next().trim();

            if (")".equals(token)) {
                rewindAndReplace();
            } else {
                this.read.push(token);
            }
        }

        while (!this.read.isEmpty()) {
            buf.append(this.read.pop());
        }

        return buf.toString();
    }

    private void rewindAndReplace() {
        String second = this.read.pop();
        String op = this.read.pop();
        String first = this.read.pop();

        this.read.push(String.format("(%s%s%s)", first, op, second));
    }

}
