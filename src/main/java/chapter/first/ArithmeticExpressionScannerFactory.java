package chapter.first;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 */
public class ArithmeticExpressionScannerFactory {

    // See http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
    private static final String WITH_DELIMITER = "((?<=\\%1$s)|(?=\\%1$s))";

    public static Scanner create(Readable readable) {
          return new Scanner(readable).useDelimiter(createDelimiterPattern());
    }

    /**
     * Returns a regex pattern for tokenising the input
     * @return a non-null regex
     */
    private static String createDelimiterPattern() {
        /*
         * We want to tokenise the input, but also keep the delimiters in the token stream. This uses regex lookahead
         * and lookbehind to do that.
         *
         * Handcrafting a parser would be more efficient in terms of computering, but less efficient in terms of my
         * time, and it's not the point of this exercise.
         */

        return Arrays.stream(new String[]{" ", "+", "-", "/", "*", ")", "("})
                .map(token -> String.format(WITH_DELIMITER, token))
                .collect(Collectors.joining("|"));
    }

}
