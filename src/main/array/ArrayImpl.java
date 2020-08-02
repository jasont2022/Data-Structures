package main.array;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * This class implements the array interface using a resizable generic array,
 * supports null elements to be insert, search, and delete in the array
 * 
 * @author Jason Tran
 * @see Array
 */
@SuppressWarnings("unchecked")
public class ArrayImpl<E> implements Array<E> {
    private int size; // the number of elements in arr
    private E[] arr; // a static generic array

    /**
     * Constructor: Creates a new empty ArrayImpl of default capacity 2
     */
    public ArrayImpl() {
        this(2);
    }

    /**
     * Constructor: Creates a new empty ArrayImpl with a specified capacity
     * 
     * @param length the length of the array
     * @throws IllegalArgumentException if the size is negative
     */
    public ArrayImpl(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("ERROR: Illegal length to create array: " + length);
        }
        arr = (E[]) new Object[length];
    }

    /**
     * This method returns the actual underlying array
     * 
     * @return arr 
     * Runtime: O(1)
     */
    public E[] getArray() {
        return arr;
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
     * Runtime: O(n)
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(n)
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arr[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    /**
     * Returns the index of o at the last occurrence in
     * the array, otherwise return -1 if o is not in the array
     * 
     * @param o an element to search the last occurring index
     * @return the index of the last occurring o or -1 if not present
     */
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (arr[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(arr[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    /**
     * A helper function to check if index is in range,
     * if not, throws an IllegalArgumentException
     * 
     * @throws IllegalArgumentException if index is out of bounds
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("ERROR: Illegal index to get element: " + index);
        }
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(1)
     */
    @Override
    public E get(int index) {
        checkIndex(index); // check if index is within the appropriate range
        return arr[index];
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(1)
     */
    @Override
    public void set(int index, E e) {
        checkIndex(index); // check if index is within the appropriate range
        arr[index] = e;
    }

    /**
     * {@inheritDoc} 
     * When the size is at capacity, the array will resize double its 
     * capacity 
     * Runtime: Amortized O(1), Worst Case O(n)
     */
    @Override
    public void add(E e) {
        // double the length of the array if size is at capacity
        if (size == arr.length) {
            resize(2 * arr.length);
        }
        arr[size++] = e;
    }

    /**
     * {@inheritDoc} 
     * When the size is less than a quarter of capacity, the array
     * capacity will resize down by half 
     * Runtime: O(n)
     */
    @Override
    public E remove(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot remove from an empty array");
        } 
        checkIndex(index); // check if index is in bounds to remove
        E prev = arr[index];
        // shift the elements towards the left
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[size - 1] = null;
        size--;
        
        // resize the array by half is size less than a fourth of capacity
        if (size < arr.length / 4) {
            resize(arr.length / 2);
        }
        return prev;
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(n)
     */
    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot remove from an empty array");
        }
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arr[i])) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This is a helper method that resizes the underlying generic dynamic array
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
        size = 0;
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(n)
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = arr[i];
        }
        return array;
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
             * 
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
                    return arr[count++];
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
            StringBuilder array = new StringBuilder(size).append("[");
            for (int i = 0; i < size - 1; i++) {
                array.append(arr[i] + ", ");
            }
            return array.append(arr[size - 1] + "]").toString();
        }
    }
}