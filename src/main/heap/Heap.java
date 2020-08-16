package main.heap;

import java.util.Iterator;

/**
 * A Heap Interface that supports generics
 *
 * @author Jason Tran
 */
public interface Heap<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Returns the number of elements in the heap
     *
     * @return the number of elements in the heap
     */
    public int size();

    /**
     * Tells whether the heap has elements or not
     *
     * @return true if empty otherwise false
     */
    public boolean isEmpty();

    /**
     * Tells whether o is present in list or not
     *
     * @param o an element to be found in the heap
     * @return true if this heap contains the specified element false otherwise
     */
    public boolean contains(Object o);

    /**
     * Inserts the specified element to the correct position in the heap
     *
     * @param e an element to be added into the heap
     * @return true if the collection changed as a result of the call otherwise
     *         false
     * @throws IllegalArgumentException e is null
     */
    public boolean insert(E e);

    /**
     * Retrieves, but does not remove, the root of this heap
     *
     * @return the root of this heap
     * @throws NoSuchElementException if heap is empty
     */
    public E peek();

    /**
     * Removes the head element of this heap
     *
     * @return the head element of this queue
     * @throws NoSuchElementException if heap is empty
     */
    public E extract();

    /**
     * Deletes the element o from the heap
     *
     * @param o an element to be remove
     * @return true if removal of o is successful otherwise false
     * @throws NoSuchElementExpection if the heap is empty
     */
    public boolean remove(E e);

    /**
     * Deletes all the elements in the heap, the heap will be empty after this call
     * returns
     */
    public void clear();

    /**
     * Returns an array containing all of the elements in this heap in proper
     * sequence in order traversal
     *
     * @return an array containing all of the elements in this heap in proper
     *         sequence
     */
    public Object[] toArray();

    /**
     * Returns an iterator over the elements in this heap, performing a BFS/level
     * order traversal
     *
     * @return an iterator over the elements in this heap
     */
    public Iterator<E> iterator();

    /**
     * Returns a string representation of the whole heap in the format [h_1, h_2,
     * ...., h_n]
     *
     * @return the string format of the heap
     */
    public String toString();
}
