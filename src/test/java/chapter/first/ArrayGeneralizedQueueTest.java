package chapter.first;

public class ArrayGeneralizedQueueTest extends GeneralizedQueueTest {

    @Override
    protected GeneralizedQueue<String> createQueue() {
        return new ArrayGeneralizedQueue<>();
    }
}
