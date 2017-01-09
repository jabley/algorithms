package chapter.first;

import org.junit.Test;

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
}
