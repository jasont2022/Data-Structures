package main.heap;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/** 
 * Implements the Heap Interface using a BinaryMinHeap with ArrayList and
 * HashMap for faster operations (in terms of Runtime), this implementation does
 * not support null elements
 *
 * @author Jason Tran
 */
public class BinaryMinHeap<E extends Comparable<E>> implements Heap<E> {
    private List<E> arr; // an array list to store the collection of elements
    // a HashMap to map elements from the ArrayList to the set of indices in the ArrayList
    private Map<E, TreeSet<Integer>> map = new HashMap<>();

    /** Constructor: Creates an empty BinaryMinHeap */
    public BinaryMinHeap() {
        this(0);
    }
    
    /**
     * Constructor: Creates a BinaryMinHeap with the specified size
     *
     * @param size the number of elements for the heap
     * @throws IllegalArgumentException if size is negative
     */
    public BinaryMinHeap(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("ERROR: Illegal length to create heap: " + size);
        }
        arr = new ArrayList<>(size);
    }
    
    /**
     * Constructor: Creates a BinaryMinHeap with the array, similar to the Build
     * Heap Algorithm, Runtime: O(n)
     *
     * @param array an array to be constructed into a heap
     */
    public BinaryMinHeap(E[] array) {
        this(array.length);

        // first add the elements into the array and hash map
        for (int i = 0; i < array.length; i++) {
            mapAdd(array[i], i);
            arr.add(array[i]);
        }

        // perform the build heap algorithm
        for (int i = Math.max(0, (array.length / 2) - 1); i >= 0; i--) {
            shiftDown(i);
        }
    }

    /**
     * Constructor: Creates a BinaryMinHeap with the collection, Runtime: O(nlg(n))
     * Do not get this confused with the O(n) Build Heap Algorithm
     *
     * @param collection a collection of elements
     */
    public BinaryMinHeap(Collection<E> collection) {
        this(collection.size());
        for (E e : collection) {
            insert(e);
        }
    }

    /**
     * Returns the underlying array that stores the collection of elements,
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
        return arr.size();
    }

    /** {@inheritDoc}, Runtime: O(1) */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /** {@inheritDoc}, Runtime: Expected O(1) */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        return map.containsKey(o);
    }
    
    /** {@inheritDoc}, Runtime: O(lg n) */
    @Override
    public boolean insert(E e) {
        if (e == null) {
            throw new IllegalArgumentException("ERROR: Cannot add null elements into the heap");
        }
        // add the element to the end of the array
        arr.add(e);
        int index = size() - 1;
        // update the hash map
        mapAdd(e, index);

        // shift the element up to the correct position
        shiftUp(index);
        return true;
    }

   /**
    * {@inheritDoc}, Runtime: O(1)
    */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot peek an empty heap");
        }
        return arr.get(0);
    }

    /**
     * {@inheritDoc} Runtime: O(lg n)
     */
    @Override
    public E extract() {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot extract an empty heap");
        }
        return removeAt(0);
    }

    /**
     * {@inheritDoc} Runtime: O(lg n)
     */
    @Override
    public boolean remove(E e) {
        // if the object is null return false
        if (e == null) {
            return false;
        }
        // get the largest index of e
        Integer index = mapGet(e);
        // if the index is not null remove the element
        if (index != null) {
            removeAt(index);
        }
        return index != null;
    }
    
    /**
     * Helper method to remove the node at the index, 
     * Runtime: O(lg n)
     * 
     * @param index the index of the node
     * @return the prev value of the node
     */
    private E removeAt(int index) {
        if (isEmpty()) {
            return null;
        }
        int last = size() - 1; // the last index
        E prev = arr.get(index);
        // swap the elements
        swap(index, last);

        // delete node from the heap
        arr.remove(last);
        mapRemove(prev, last);

        // removed last element
        if (index == last) {
            return prev;
        }

        E elem = arr.get(index);
        // attempt to shift down the element
        shiftDown(index);

        // if shifting down does not work try to shift up
        if (arr.get(index).equals(elem)) {
            shiftUp(index);
        }
        return prev;
    }
    
    /**
     * Helper method that tests if the value of node i <= node j, assumes that i and
     * j are valid indices, Runtime: O(1)
     * 
     * @param i an index in the heap
     * @param j another index in the heap
     * @return true if node.value i <= node.value j, otherwise false
     */
    private boolean min(int i, int j) {
        E value1 = arr.get(i);
        E value2 = arr.get(j);
        return value1.compareTo(value2) <= 0;
    }
    
    /**
     * Helper method that pushes the node up to the correct position in the heap to
     * satisfy the min heap property, assume i is a valid index, Runtime: O(lg n)
     * 
     * @param i an index in the heap
     */
    private void shiftUp(int i) {
        // get the parent index of the current index i
        int parent = (int) (Math.ceil(i / 2.0) - 1);

        // base case: parent < 0 || min(parent, i)
        if (parent >= 0 && !min(parent, i)) {
            // update the array and hashmap
            swap(parent, i);
            // continue to fix the heap
            shiftUp(parent);
        }
    }
    
    /**
     * Helper method that performs MinHeapfiy operation, pushes the node down to the
     * correct position in the heap to satisfy the min heap property, assume i is a
     * valid index, Runtime: O(lg n)
     * 
     * @param i an index in the heap
     */
    private void shiftDown(int i) {
        int left = 2 * i + 1; // left child index
        int right = 2 * i + 2;
        int min = 0;
        if (left < arr.size() && arr.get(left).compareTo(arr.get(i)) < 0) {
            min = left;
        } else {
            min = i;
        }
        if (right < arr.size() && arr.get(right).compareTo(arr.get(min)) < 0) {
            min = right;
        }
        if (min != i) {
            // update the array and hashmap
            swap(i, min);
            // continue to fix the heap
            shiftDown(min);
        }
    }
    
    /**
     * Helper method that puts a key-value pair into the HashMap, Runtime: Expected
     * O(1)
     *
     * @param e an element to be added to the map
     * @param i the index to be added to the set of indices
     */
    private void mapAdd(E e, int i) {
        TreeSet<Integer> set = map.get(e);
        if (set == null) {
            set = new TreeSet<>();
            set.add(i);
            map.put(e, set);
        } else {
            set.add(i);
        }
    }
    
    /**
     * Helper method to remove the index corresponding to e in the hashmap, Runtime:
     * O(lg n)
     * 
     * @param e an element to be remove
     * @param i an index of e
     */
    private void mapRemove(E e, int i) {
        // get the set corresponding to e
        TreeSet<Integer> set = map.get(e);
        // remove the index of e
        set.remove(i);
        // if its empty remove the whole set corresponding to e
        if (set.isEmpty()) {
            map.remove(e);
        }
    }
    
    /**
     * Helper method to get the index corresponding to e, by convention grab the
     * largest index corresponding to e
     * 
     * @param e an element in the heap
     * @return largest index of e
     */
    private Integer mapGet(E e) {
        TreeSet<Integer> set = map.get(e);
        if (set != null) {
            return set.last();
        }
        return null;
    }
    
    /**
     * Helper method to swap the indices in the hashmap, assume i and j are valid
     * indices, Runtime O(1)
     * 
     * @param val1 a value in the heap
     * @param val2 another value in the heap
     * @param i    an index in the heap
     * @param j    another index in the heap
     */
    private void mapSwap(E val1, E val2, int i, int j) {
        // get the sets of the indices
        Set<Integer> set1 = map.get(val1);
        Set<Integer> set2 = map.get(val2);

        // remove the old indices
        set1.remove(i);
        set2.remove(j);

        // add the new indices
        set1.add(j);
        set2.add(i);
    }
    
    /**
     * Helper method to swap the indices in the array and hash map, assume i and j
     * are valid indices, Runtime O(1)
     * 
     * @param i an index in the heap
     * @param j another index in the heap
     */
    private void swap(int i, int j) {
        // get the nodes of the positions
        E node1 = arr.get(i);
        E node2 = arr.get(j);

        // swap them in the array
        arr.set(i, node2);
        arr.set(j, node1);

        // swap them in the map
        mapSwap(node1, node2, i, j);
    }

    /** {@inheritDoc} Runtime O(1) */
    @Override
    public void clear() {
        arr = new ArrayList<>();
        map = new HashMap<>();
    }

    /** {@inheritDoc} Runtime O(n) */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            array[i] = arr.get(i);
        }
        return array;
    }

    /** {@inheritDoc} Runtime: O(1) */
    @Override
    public Iterator<E> iterator() {
        // custom iterator 
        return new Iterator<E>() {
            private int count; // the counter of elements traveled so far
            private final int expectedSize = arr.size(); // check for concurrent modification

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
                if (expectedSize != arr.size()) {
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
        // could also write this line below
        // return arr.iterator();
    }
    
    /** {@inheritDoc} Runtime O(n) */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder heap = new StringBuilder(arr.size()).append("[");
            for (int i = 0; i < arr.size() - 1; i++) {
                heap.append(arr.get(i) + ", ");
            }
            return heap.append(arr.get(arr.size() - 1) + "]").toString();
        }
    }
}
