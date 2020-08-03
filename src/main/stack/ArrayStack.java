package main.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.EmptyStackException;

/**
 * This class implements the stack interface using a resizable generic array,
 * supports null elements to be insert, search, and delete in the array
 * 
 * @author Jason Tran
 */
@SuppressWarnings("unchecked")
public class ArrayStack<E> implements Stack<E> {
    private int size; // the number of elements in the stack
    private int head; // the position of the head (head index pointer)
    // a generic array to store elements of the stack
    private E[] arr = (E[]) new Object[2];

    /**
     * This returns the underlying array
     * 
     * @return the underlying array 
     * Runtime: O(1)
     */
    public E[] getArray() {
        return arr;
    }

    /**
     * This returns the head of the stack
     * 
     * @return the position of the head in the stack 
     * Runtime: O(1)
     */
    public int getHead() {
        return head;
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
    public void push(E item) {
        // double the length of the array
        if (size == arr.length) {
            resize(2 * arr.length);
        }
        if (!isEmpty()) {
            head++;
        }
        arr[head] = item;
        size++;
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(1)
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return arr[head];
    }

    /**
     * {@inheritDoc} 
     * When the size is less than a 1/4 of capacity, the array will
     * resize a half of its capacity 
     * Runtime: Amortized O(1), Worst Case O(n)
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E prev = arr[head];
        arr[head--] = null;
        size--;
        // cut down the array size by a half
        if (size < arr.length / 4) {
            resize(arr.length / 2);
        }
        return prev;
    }

    /**
     * This is a helper method that resizes the underlying 
     * generic dynamic array
     * 
     * @param length the length of the array 
     * Runtime: O(n)
     */
    private void resize(int length) {
        E[] temp = (E[]) new Object[length];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(1)
     */
    @Override
    public void clear() {
        arr = (E[]) new Object[2];
        head = size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int count = 0; // the counter of elements traveled so far
            private int position = head; // a pointer to the current element
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
                    count++;
                    return arr[position--];
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
     * {@inheritDoc} 
     * Runtime: O(n)
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder stack = new StringBuilder(size).append("[");
            for (int i = head; i > 0; i--) {
                stack.append(arr[i] + ", ");
            }
            return stack.append(arr[0] + "]").toString();
        }
    }
}