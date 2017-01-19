package chapter.first;

public class LinkedListGeneralizedQueueTest extends GeneralizedQueueTest {

    @Override
    protected GeneralizedQueue<String> createQueue() {
        return new LinkedListGeneralizedQueue<>();
    }
}
