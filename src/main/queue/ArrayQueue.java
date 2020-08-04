package main.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * This class implements the queue interface using a resizable generic array,
 * supports null elements to be insert, search, and delete in the array
 * 
 * @author Jason Tran
 */
@SuppressWarnings("unchecked")
public class ArrayQueue<E> implements Queue<E> {
    private int size; // the number of elements in the queue
    private int head, tail; // the head and tail pointers in this queue
    private E[] arr; // a generic array to store the collection of elements

    /**
     * Constructor: Creates an empty ArrayQueue
     */
    public ArrayQueue() {
        head = tail = -1; // set the head and tail pointers to index -1
        arr = (E[]) new Object[2]; // default length of 2
    }

    /**
     * Returns the underlying array
     * 
     * @return the underlying array 
     * Runtime: O(1)
     */
    public E[] getArray() {
        return arr;
    }

    /**
     * Returns the head of the queue
     * 
     * @return the position of the head in the queue 
     * Runtime: O(1)
     */
    public int getHead() {
        return head;
    }

    /**
     * Returns the tail of the queue
     * 
     * @return the position of the head in the queue 
     * Runtime: O(1)
     */
    public int getTail() {
        return tail;
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(1)
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc} 
     * When the size is at capacity, the array will resize double its
     * capacity 
     * Runtime: Amortized O(1), Worst Case O(n)
     */
    @Override
    public boolean add(E e) {
        // double the length of the array if size is at capacity
        if (size == arr.length) {
            resize(2 * arr.length);
        }
        if (isEmpty()) {
            head = tail = 0;
        } else {
            tail = (tail + 1) % arr.length;
        }
        arr[tail] = e;
        size++;
        return true;
    }

    /**
     * {@inheritDoc} 
     * Runtime: Amortized O(1), Worst Case O(n)
     */
    @Override
    public boolean offer(E e) {
        return add(e);
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(1)
     */
    @Override
    public E element() {
        return peek();
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(1)
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot peek from empty queue");
        }
        return arr[head];
    }

    /**
     * {@inheritDoc} 
     * When the size is less than a quarter of capacity, the array
     * capacity will resize down by half
     * Runtime: Amortized O(1), Worst Case O(n)
     */
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E prev = arr[head];
        arr[head] = null;
        if (size == 1) {
            head = tail = -1;
        } else {
            head = (head + 1) % arr.length;
        }
        size--;
        // resize the array by half is size less than a fourth of capacity
        if (size < arr.length / 4) {
            resize(arr.length / 2);
        }
        return prev;
    }

    /**
     * {@inheritDoc} 
     * Runtime: Amortized O(1), Worst Case O(n)
     */
    @Override
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot remove from empty queue");
        }
        return poll();
    }
    
    /**
     * Helper method that resizes the underlying array and unwinds the
     * queue, so that head points at the beginning of the array
     * 
     * @param length the length of the array 
     * Runtime: O(n)
     */
    private void resize(int length) {
        E[] temp = (E[]) new Object[length];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[(head + i) % arr.length];
        }
        // unwind the queue and make head point to index 0
        arr = temp;
        head = 0;
        tail = size - 1;
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(1)
     */
    @Override
    public void clear() {
        arr = (E[]) new Object[2];
        size = 0;
        head = tail = -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int count = 0; // the counter of elements traveled so far
            final private int expectedSize = size; // check for concurrent modification

            /**
             * {@inheritDoc}             
             */
            @Override
            public boolean hasNext() {
                return count < expectedSize;
            }

            /**
             * {@inheritDoc}
             * @throws ConcurrentModificationException if size does not equal expectedSize
             */
            @Override
            public E next() {
                if (expectedSize != size) {
                    throw new ConcurrentModificationException("ERROR: Cannot modifiy the iterator");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("ERROR: No more elements to iterate");
                } else {
                    E prev = arr[(count + head) % arr.length];
                    count++;
                    return prev;
                }
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("ERROR: Remove not Supported by this " + 
                        "Iterator");
            }
        };
    }

    /**
     * {@inheritDoc} Runtime: O(n)
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else if (size == 1) {
            return "[" + arr[head] + "]";
        } else {
            StringBuilder queue = new StringBuilder(size).append("[");
            for (int i = 0; i < size - 1; i++) {
                queue.append(arr[(i + head) % arr.length] + ", ");
            }
            return queue.append(arr[tail] + "]").toString();
        }
    }
}