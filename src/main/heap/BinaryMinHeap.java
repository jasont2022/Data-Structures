package main.heap;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Iterator;

/** @author Jason Tran */
public class BinaryMinHeap<E extends Comparable<E>> implements Heap<E> {
    private int size; // the number of elements in the BinaryMinHeap
    private List<E> arr;
    private Map<E, HashSet<Integer>> map = new HashMap<>();

    /** Constructor: */
    public BinaryMinHeap() {
    }

    /**
     * Constructor:
     *
     * @param size
     */
    public BinaryMinHeap(int size) {
        arr = new ArrayList<>(size);
    }

    /**
     * Constructor:
     *
     * @param array Runtime: O(n)
     */
    public BinaryMinHeap(E[] array) {
    }

    /**
     * Constructor:
     *
     * @param collection
     */
    public BinaryMinHeap(Collection<E> collection) {
    }

    public List<E> getList() {
        return arr;
    }

    /** {@inheritDoc} Runtime: O(1) */
    @Override
    public int size() {
        return size;
    }

    /** {@inheritDoc} Runtime: O(1) */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    /** {@inheritDoc} Runtime: O(lg n) */
    @Override
    public boolean insert(E e) {
        return false;
    }

    /** {@inheritDoc} Runtime: O(1) */
    @Override
    public E peek() {
        return null;
    }

    /** {@inheritDoc} Runtime: O(lg n) */
    @Override
    public E extract() {
        return null;
    }

    /** {@inheritDoc} Runtime: O(lg n) */
    @Override
    public boolean remove(Object o) {
        return false;
    }

    /** {@inheritDoc} Runtime: O() */
    @Override
    public void clear() {
    }

    /** {@inheritDoc} Runtime: O(n) */
    @Override
    public Object[] toArray() {
        return null;
    }

    /** {@inheritDoc} Runtime: O(1) */
    @Override
    public Iterator<E> iterator() {
        return arr.iterator();
    }

    /** {@inheritDoc} Runtime: O(n) */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder heap = new StringBuilder(size).append("[");
            for (int i = 0; i < size - 1; i++) {
                heap.append(arr.get(i) + ", ");
            }
            return heap.append("]").toString();
        }
    }
}
