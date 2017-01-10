package chapter.first;

import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ParenthesesCorrector {

    // See http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
    private static final String WITH_DELIMITER = "((?<=\\%1$s)|(?=\\%1$s))";

    private final String input;
    private final Stack<String> read;

    public ParenthesesCorrector(String input) {
        this.input = input;
        this.read = new Stack<>();
    }

    public String correct() {
        StringBuilder buf = new StringBuilder(input.length() + 16);

        Scanner scanner = new Scanner(this.input).useDelimiter(createDelimiterPattern());

        while (scanner.hasNext()) {
            String token = scanner.next();

            if (")".equals(token)) {
                rewindAndReplace();
            } else {
                this.read.push(token.trim());
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

    /**
     * Returns a regex pattern for tokenising the input
     * @return a non-null regex
     */
    private String createDelimiterPattern() {
        /*
         * We want to tokenise the input, but also keep the delimiters in the token stream. This uses regex lookahead
         * and lookbehind to do that.
         *
         * Handcrafting a parser would be more efficient in terms of computering, but less efficient in terms of my
         * time, and it's not the point of this exercise.
         */

        return Arrays.stream(new String[]{"+", "-", "/", "*", ")"})
                .map(token -> String.format(WITH_DELIMITER, token))
                .collect(Collectors.joining("|"));
    }
}
