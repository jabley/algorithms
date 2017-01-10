package chapter.first;

import edu.princeton.cs.algs4.Stack;

public class ParenthesesCorrector {

    private final String input;
    private final Stack<String> read;

    public ParenthesesCorrector(String input) {
        this.input = input;
        this.read = new Stack<>();
    }

    public String correct() {
        StringBuilder buf = new StringBuilder(input.length() + 16);

        // TODO: better tokenisation
        for (char c : input.toCharArray()) {
            switch (c) {
                case ')':
                    rewindAndInsert();
                    break;
                default:
                    this.read.push(String.valueOf(c));
                    break;
            }
        }

        while (!this.read.isEmpty()) {
            buf.append(this.read.pop());
        }

        return buf.toString();
    }

    private void rewindAndInsert() {
        String second = this.read.pop();
        String op = this.read.pop();
        String first = this.read.pop();

        this.read.push(String.format("(%s%s%s)", first, op, second));
    }
}
