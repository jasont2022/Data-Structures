package main.priorityqueue;

import java.util.Set;
import java.util.Iterator;

/**
 * A Priority Queue Interface that supports generics
 *
 * @author Jason Tran
 */
public interface PriorityQueue<K extends Comparable<K>, V> {
    /**
     * Returns the number of elements (key-value pairs) in the priority queue
     *
     * @return the number of elements in the priority queue
     */
    public int size();

    /**
     * Tells whether the priority queue has elements or not
     *
     * @return true if the priority queue contains no elements
     */
    public boolean isEmpty();

    /**
     * Returns true if the specified value is present in the map
     *
     * @param value the value to look up in the map
     * @return true if the map contains the specified value
     */
    public boolean containsValue(Object value);

    /**
     * Inserts the key-value pair into the priority queue at the 
     * correct position
     * 
     * @param key a priority key to associate with the value
     * @param value a value to insert into the heap
     * @return true if collection is modified as a result of this call
     * @throws IllegalArgumentException if key is null
     * @throws IllegalArgumentException if value is already in the priority queue
     */
    public boolean add(K key, V value);

    /**
     * Retrieves, but does not remove, the first entry of this priority queue
     * 
     * @return the first entry in this priority queue
     * @throws NoSuchElementException if the priority queue is empty
     */
    public Entry<K, V> peek();

    /**
     * Removes the head entry of this priority queue, ties broken arbitrarily
     * 
     * @return the head entry of this priority queue
     * @throws NoSuchElementException if the priority queue is empty
     */
    public Entry<K, V> poll();

    /**
     * Returns the set of values in this priority queue
     * 
     * @return an unordered set of the values in this priority queue
     */
    public Set<V> values();

    /**
     * Returns an iterator over the elements in this priority queue, ordered from head to
     * last
     *
     * @return an iterator over the elements in this priority queue
     */
    public Iterator<PriorityQueue.Entry<K, V>> iterator();

    /**
     * Returns a string representation of the whole priority queue in the format [q_1, q_2,
     * ...., q_n] or [] if the queue is empty
     * 
     * @return the string format of the priority queue
     */
    public String toString();

    /**
     * A Priority Queue Entry (key-value) pair Interface
     *
     * @author Jason Tran
     */
    public interface Entry<K, V> {
        /**
         * Returns the key corresponding to this entry
         *
         * @return the key corresponding to this entry
         */
        public K getKey();

        /**
         * Returns the value corresponding to this entry
         *
         * @return the value corresponding to this entry
         */
        public V getValue();

        /**
         * This method returns a string representation of an entry in the map in the
         * format (k, v) or () if empty
         *
         * @return the string format of the entry
         */
        public String toString();
    }
}
