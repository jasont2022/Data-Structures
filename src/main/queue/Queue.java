package main.queue;

import java.util.Iterator;

/**
 * A Queue interface that supports generics
 * 
 * @author Jason Tran
 */
public interface Queue<E> extends Iterable<E> {

    /**
     * Returns the number of elements in the queue
     * 
     * @return the number of elements in the queue
     */
    public int size();

    /**
     * Tells whether the queue has elements or not
     * 
     * @return true if the queue contains no elements
     */
    public boolean isEmpty();

    /**
     * Adds the specified element to the end of this queue
     * 
     * @param e an element to be added into the queue
     * @return true
     */
    public boolean add(E e);

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restriction
     * 
     * @param e an element to be added into the queue
     * @return true if the element was added to this queue otherwise false
     */
    public boolean offer(E e);

    /**
     * Retrieves, but does not remove, the first element of this queue
     * 
     * @return the head of this queue
     * @throws NoSuchElementException if queue is empty
     */
    public E element();

    /**
     * Retrieves, but does not remove, the head of this queue
     * 
     * @return the head of this queue
     * @throws NoSuchElementException if queue is empty
     */
    public E peek();

    /**
     * Removes the head element of this queue or returns null if the queue is empty
     * 
     * @return the head element of this queue otherwise return null if queue if
     *         empty
     */
    public E poll();

    /**
     * Retrieves and removes the head of this queue
     * 
     * @return
     * @throws NoSuchElementException if queue is empty
     */
    public E remove();

    /**
     * Deletes all the elements in the queue, the queue will be empty
     * after this call returns
     */
    public void clear();

    /**
     * Returns an iterator over the elements in this queue, ordered from head to
     * last
     * 
     * @return an iterator over the elements in this queue
     */
    public Iterator<E> iterator();

    /**
     * Returns a string representation of the whole queue in the format
     * [q_1, q_2, ...., q_n] or [] if the queue is empty
     * 
     * @return the string format of the stack
     */
    public String toString();
}