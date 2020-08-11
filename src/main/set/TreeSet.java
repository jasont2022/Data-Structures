package main.set;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class TreeSet<E> implements Set<E> {
    private int size; // the number of elements in the set
    private Node<E> root;

    private static class Node<E> {
    }

    /** @return */
    public Node<E> getRoot() {
        return root;
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

    /** {@inheritDoc} */
    @Override
    public boolean contains(Object o) {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean add(E e) {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean remove(Object o) {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public Set<E> union(Set<E> set) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Set<E> intersection(Set<E> set) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Set<E> difference(Set<E> set) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public boolean subset(Set<E> set) {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
    }

    /** {@inheritDoc} */
    @Override
    public Object[] toArray() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedSize = size; // used to check for concurrent modification

            /** {@inheritDoc} */
            @Override
            public boolean hasNext() {
                return false;
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
                    return null;
                }
            }

            /** {@inheritDoc} */
            @Override
            public void remove() {
                throw new UnsupportedOperationException(
                        "ERROR: Remove not supported by this iterator");
            }
        };
    }

    /** {@inheritDoc} Runtime: O() */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "{}";
        } else {
            StringBuilder set = new StringBuilder(size).append("{");
            return set.append("}").toString();
        }
    }
}
