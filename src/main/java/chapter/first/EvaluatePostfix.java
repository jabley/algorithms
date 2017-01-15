package chapter.first;

import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;

/**
 *
 */
public class EvaluatePostfix {
    public int eval(String input) {
        return eval(new StringTokenSupplier(input));
    }

    private int eval(Iterator<String> supplier) {
        Stack<Integer> values = new Stack<>();

        while (supplier.hasNext()) {
            String token = supplier.next();

            if (token.length() == 0) {
                continue;
            }
            if ("*".equals(token)) {
                values.push(values.pop() * values.pop());
            } else if ("+".equals(token)) {
                 values.push(values.pop() + values.pop());
            } else {
                values.push(Integer.valueOf(token));
            }
        }

        return values.pop();
    }
}
