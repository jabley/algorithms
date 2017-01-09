package chapter.first;

import edu.princeton.cs.algs4.Stack;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Parentheses {

    private final Stack<String> parens;
    private final Reader source;

    private final Map<String, String> pairs;

    public Parentheses(Reader source) {
        this.parens = new Stack<>();
        this.source = source;
        this.pairs = createPairsTable();
    }

    private Map<String,String> createPairsTable() {
        Map<String, String> result = new HashMap<>(3);
        result.put("[", "]");
        result.put("(", ")");
        result.put("{", "}");
        return result;
    }

    public Result parse() {
        int c;
        try {
            while ((c = source.read()) != -1) {
                switch (c) {
                    case '[':
                        // fall-through
                    case '{':
                        // fall-through
                    case '(':
                        parens.push(String.valueOf((char) c));
                        break;
                    case ']':
                        // fall-through
                    case '}':
                        // fall-through
                    case ')':
                        String previous = parens.pop();
                        String actual = String.valueOf((char) c);
                        if (!actual.equals(pairs.get(previous))) {
                            return new Result(String.format("Expected %s, actual: %s", pairs.get(previous), actual));
                        }
                        break;
                    default:
                        // continue
                }
            }
            if (!parens.isEmpty()) {
                return new Result("Unbalanced");
            }
        } catch (IOException e) {
            return new Result(e.getMessage());
        }

        return Result.SUCCESS;
    }

    static class Result {

        private final String failureReason;

        static final Result SUCCESS  = new Result(null);

        Result(String failureReason) {
            this.failureReason = failureReason;
        }

        String getFailureReason() {
            return this.failureReason;
        }

    }

}
