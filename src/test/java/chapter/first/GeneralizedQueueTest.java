package chapter.first;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public abstract class GeneralizedQueueTest {

    private GeneralizedQueue<String> queue;

    @Before
    public void setup() {
        queue = createQueue();
    }

    @Test
    public void emptyGeneralizedQueueIsEmpty() {
        assertThat(queue.isEmpty(), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteFromEmptyQueueFails() {
        queue.delete(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void attemptingToDeleteIndexLargerThanSizeFails() {
        queue.insert("hello");
        queue.delete(2);
    }

    @Test
    public void insertAndDeleteGivesEmptyQueue() {
        queue.insert("hello");
        queue.insert("world");
        queue.delete(0);
        queue.delete(0);
        assertThat(queue.isEmpty(), is(true));
    }

    protected abstract GeneralizedQueue<String> createQueue();

}
