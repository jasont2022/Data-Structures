package main.heap;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements the Heap Interface using a Binary Max Heap with
 * ArrayList and HashMap for faster operations (in terms of Runtime), this
 * implementation does not support null elements
 * 
 * @param <E> the type of elements in the binary max heap
 * @author Jason Tran
 */
public class BinaryMaxHeap<E extends Comparable<E>> implements Heap<E> {
    private int size; // the number of elements in the BinaryMaxHeap
    private List<E> arr; // an array list to store the collection of elements
    // a HashMap to map elements from the ArrayList to the set of indices in the
    // ArrayList
    private Map<E, HashSet<Integer>> map = new HashMap<>();

    /**
     * Constructor: Creates an empty BinaryMaxHeap
     */
    public BinaryMaxHeap() {
        this(0);
    }

    /**
     * Constructor: Creates a BinaryMaxHeap with the specified size
     * 
     * @param size the number of elements for the heap
     */
    public BinaryMaxHeap(int size) {
        arr = new ArrayList<>(size);
    }

    /**
     * Constructor: Creates a BinaryMaxHeap with the array, similar to the Build
     * Heap Algorithm
     * 
     * @param array Runtime: O(n)
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
     * Constructor: Creates a BinaryMaxHeap with the collection
     * 
     * @param collection Runtime: O(nlg(n))
     */
    public BinaryMaxHeap(Collection<E> collection) {
        this(collection.size());
        for (E e : collection) {
            insert(e);
        }
    }

    /**
     * {@inheritDoc} Runtime: O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc} Runtime: O(1)
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc} Runtime: Expected O(1)
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        return map.containsKey(o);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot peek an empty heap");
        }
        return arr.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E extract() {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot extract an empty heap");
        }
        return null;
    }

    /**
     * {@inheritDoc} Runtime O(n)
     */
    @Override
    public boolean remove(Object o) {
        return false;
    }

    /**
     * 
     * @param i Runtime: O(lg n)
     */
    private void shiftUp(int i) {

    }

    /**
     * 
     * @param i Runtime: O(lg n)
     */
    private void shiftDown(int i) {

    }

    /**
     * A helper method that puts a key-value pair into the HashMap map
     * 
     * @param e an element to be added to the map
     * @param i the index to be added to the set of indices Runtime: Expected O(1)
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
     * 
     * @param e
     * @param i
     */
    private void mapRemove(E e, int i) {

    }

    /**
     * 
     * @param e
     * @return
     */
    private Integer mapGet(E e) {
        return null;
    }

    /**
     * 
     * @param i
     * @param j
     */
    private void mapSwap(int i, int j) {

    }

    /**
     * 
     * @param i
     * @param j
     */
    private void swap(int i, int j) {

    }

    /**
     * {@inheritDoc} Runtime O(1)
     */
    @Override
    public void clear() {
        arr = new ArrayList<>();
        map = new HashMap<>();
        size = 0;
    }

    /**
     * {@inheritDoc} Runtime: O(1)
     */
    @Override
    public Iterator<E> iterator() {
        return arr.iterator();
    }

    /**
     * {@inheritDoc} Runtime O(n)
     */
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