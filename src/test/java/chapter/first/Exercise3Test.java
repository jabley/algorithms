package chapter.first;

import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class Exercise3Test {

    @Test
    public void characteriseFixedCapacityStackOfStrings() {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(5);
        assertThat(stack.isEmpty(), is(equalTo(true)));
        assertThat(stack.isFull(), is(equalTo(false)));
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        assertThat(stack.isFull(), is(equalTo(true)));
        assertThat(stack.isEmpty(), is(equalTo(false)));
        assertThat(stack.size(), is(equalTo(5)));
        String value = stack.pop();
        assertThat(value, is(equalTo("5")));
    }

    @Test
    public void balancedParethesesSucceed() {
        String input = "[()]{}{[()()]()}";
        Parentheses p = new Parentheses(new StringReader(input));
        assertThat(p.parse().getFailureReason(), is(nullValue()));
    }

    @Test
    public void unbalancedParethesesAreDetected() {
        String input = "[(])";
        Parentheses p = new Parentheses(new StringReader(input));
        assertThat(p.parse().getFailureReason(), is(notNullValue()));
    }

    @Test
    public void missingLeadingParenthesesCanBeCorrected() {
        String input = "1 +2)*3+4)*5-6)))";
        String output = new ParenthesesCorrector(input).correct();
        assertThat(output, is(equalTo("((1+2)*((3+4)*(5-6)))")));
    }

    @Test
    public void infixToPostfix() {
        String input = "( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )";
        String output = new InfixToPostfix().convert(input);
        assertThat(output, is(equalTo("2 3 4 + 5 6 * * + ")));
    }

    @Test
    public void evaluatePostFix() {
        String input = "2 3 4 + 5 6 * * + ";
        int output = new EvaluatePostfix().eval(input);
        assertThat(output, is(equalTo(212)));
    }

    @Test
    public void pipeInfixToPostfixToEvaluate() {
        String input = "( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )";
        int output = new EvaluatePostfix().eval(new InfixToPostfix().convert(input));
        assertThat(output, is(equalTo(212)));
    }

    @Test
    public void josephusSurvivesTheCull() {
        String output = Arrays.toString(new Josephus(2, 7).eliminationSequence());
        assertThat(output, is(equalTo("[1, 3, 5, 0, 4, 2, 6]")));
    }

}
