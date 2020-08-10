package main.heap;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * Implements the Heap Interface using a Binary Max Heap with ArrayList and
 * HashMap for faster operations (in terms of Runtime), this implementation does
 * not support null elements
 *
 * @author Jason Tran
 */
public class BinaryMaxHeap<E extends Comparable<E>> implements Heap<E> {
    private int size; // the number of elements in the BinaryMaxHeap
    private List<E> arr; // an array list to store the collection of elements
    // a HashMap to map elements from the ArrayList to the set of indices in the
    // ArrayList
    private Map<E, HashSet<Integer>> map = new HashMap<>();

    /** Constructor: Creates an empty BinaryMaxHeap */
    public BinaryMaxHeap() {
        this(0);
    }

    /**
     * Constructor: Creates a BinaryMaxHeap with the specified size
     *
     * @param size the number of elements for the heap
     * @throws IllegalArgumentException if size is negative
     */
    public BinaryMaxHeap(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("ERROR: Illegal length to create heap: " + size);
        }
        arr = new ArrayList<>(size);
    }

    /**
     * Constructor: Creates a BinaryMaxHeap with the array, similar to the Build
     * Heap Algorithm, Runtime: O(n)
     *
     * @param array an array to be constructed into a heap
     */
    public BinaryMaxHeap(E[] array) {
        arr = new ArrayList<>(array.length);

        // first add the elements into the array list and hash map
        for (int i = 0; i < size; i++) {
            mapAdd(array[i], i);
            arr.add(array[i]);
        }

        // perform the build heap algorithm
        for (int i = Math.max(0, (array.length / 2) - 1); i >= 0; i--) {
            shiftDown(i);
        }
    }

    /**
     * Constructor: Creates a BinaryMaxHeap with the collection, Runtime: O(n)
     *
     * @param collection a collection of elements
     */
    public BinaryMaxHeap(Collection<E> collection) {
        arr = new ArrayList<>(collection.size());
        arr.addAll(collection);

        // perform the build heap algorithm
        for (int i = Math.max(0, collection.size() / 2) - 1; i >= 0; i--) {
            shiftDown(i);
        }
    }

    /**
     * This returns the underlying array that stores the collection of elements,
     * Runtime: O(1)
     *
     * @return the underlying array
     */
    public List<E> getArray() {
        return arr;
    }

    /** {@inheritDoc}, Runtime: O(1) */
    @Override
    public int size() {
        return size;
    }

    /** {@inheritDoc}, Runtime: O(1) */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** {@inheritDoc}, Runtime: Expected O(1) */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        return map.containsKey(o);
    }

    /** {@inheritDoc} */
    @Override
    public boolean insert(E e) {
        if (e == null) {
            throw new IllegalArgumentException("ERROR: Cannot add null elements into the heap");
        }
        // add the element to end of array list

        // update the hashmap

        // shift up operation
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot peek an empty heap");
        }
        return arr.get(0);
    }

    /** {@inheritDoc} */
    @Override
    public E extract() {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot extract an empty heap");
        }
        return null;
    }

    /** {@inheritDoc} Runtime O(n) */
    @Override
    public boolean remove(Object o) {
        return false;
    }

    /** @param i Runtime: O(lg n) */
    private void shiftUp(int i) {
        
    }

    /** 
     * Helper method that performs MaxHeapfiy operation, assume i is a
     * valid index, Runtime: O(lg n) 
     * 
     * @param i an index in the heap
     */
    private void shiftDown(int i) {
        
    }

    /**
     * Helper method that puts a key-value pair into the HashMap, 
     * Runtime: Expected O(1)
     *
     * @param e an element to be added to the map
     * @param i the index to be added to the set of indices
     */
    private void mapAdd(E e, int i) {
        HashSet<Integer> set = map.get(e);
        if (set == null) {
            set = new HashSet<>();
            set.add(i);
            map.put(e, set);
        } else {
            set.add(i);
        }
    }

    /**
     * @param e
     * @param i
     */
    private void mapRemove(E e, int i) {
    }

    /**
     * @param e
     * @return
     */
    private Integer mapGet(E e) {
        return null;
    }

    /**
     * @param i
     * @param j
     */
    private void mapSwap(int i, int j) {
    }

    /**
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
    }

    /** {@inheritDoc} Runtime O(1) */
    @Override
    public void clear() {
        arr = new ArrayList<>();
        map = new HashMap<>();
        size = 0;
    }
    
    /** {@inheritDoc} Runtime O(n) */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = arr.get(i);
        }
        return array;
    }

    /** {@inheritDoc} Runtime: O(1) */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int count; // the counter of elements traveled so far
            private final int expectedSize = size; // check for concurrent modification

            /** {@inheritDoc} */
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
                    E prev = arr.get(count);
                    count++;
                    return prev;
                }
            }

            /** {@inheritDoc} */
            @Override
            public void remove() {
                throw new UnsupportedOperationException(
                        "ERROR: Remove not Supported by this Iterator");
            }
        };
        // return arr.iterator();
    }

    /** {@inheritDoc} Runtime O(n) */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder heap = new StringBuilder(size).append("[");
            for (int i = 0; i < size - 1; i++) {
                heap.append(arr.get(i) + ", ");
            }
            return heap.append(arr.get(size - 1) + "]").toString();
        }
    }
}
