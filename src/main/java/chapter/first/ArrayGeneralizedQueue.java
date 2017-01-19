package chapter.first;

import java.util.Arrays;

public class ArrayGeneralizedQueue<T> implements GeneralizedQueue<T> {

    /**
     * Ensure we don't try to grow beyond Java JLS limits.
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 128;

    private int size;

    private Object[] elementData;

    public ArrayGeneralizedQueue() {
        this.elementData = new Object[0];
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void insert(T item) {
        ensureCapacity(size + 1);
        elementData[size++] = item;
    }

    private void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity - elementData.length > 0)
            grow(minimumCapacity);
        }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    @Override
    public T delete(int k) {
        rangeCheck(k);

        @SuppressWarnings("unchecked")
        T oldValue = (T) elementData[k];

        if (k != size - 1) {
            // We didn't just delete the last entry. Need to shuffle the array along
            System.arraycopy(elementData, k+1, elementData, k,
                    size - k - 1);
        }

        // Zero out the entry so that no dangling reference preventing GC
        elementData[--size] = null;

        return oldValue;
    }

    private void rangeCheck(int k) {
        if (k >= size) {
            throw new IllegalArgumentException(String.format("Index: %d, Size: %d", k, size));
        }
    }
}
