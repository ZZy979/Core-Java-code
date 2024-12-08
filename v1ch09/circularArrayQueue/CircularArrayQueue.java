package circularArrayQueue;

import java.util.AbstractQueue;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A first-in, first-out bounded collection.
 */
public class CircularArrayQueue<E> extends AbstractQueue<E> {
    private Object[] elements;
    private int head;
    private int tail;
    private int size;
    private int modCount;

    /**
     * Constructs an empty queue.
     * @param capacity the maximum capacity of the queue
     */
    public CircularArrayQueue(int capacity) {
        elements = new Object[capacity];
        size = 0;
        head = 0;
        tail = 0;
    }

    @Override
    public boolean offer(E e) {
        assert e != null;
        if (size < elements.length) {
            elements[tail] = e;
            tail = (tail + 1) % elements.length;
            size++;
            modCount++;
            return true;
        }
        else
            return false;
    }

    @Override
    public E poll() {
        if (size == 0) return null;
        E r = peek();
        head = (head + 1) % elements.length;
        size--;
        modCount++;
        return r;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        if (size == 0) return null;
        return (E) elements[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {
        private int offset;
        private int modCountAtConstruction;

        public QueueIterator() {
            modCountAtConstruction = modCount;
        }

        @Override
        public boolean hasNext() {
            if (modCount != modCountAtConstruction)
                throw new ConcurrentModificationException();
            return offset < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            var r = (E) elements[(head + offset) % elements.length];
            offset++;
            return r;
        }
    }
}
