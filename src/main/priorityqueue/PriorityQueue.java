package main.priorityqueue;
import java.util.Set;
import java.util.Iterator;

/**
 * A Priority Queue that supports generics
 * 
 * @param <K>
 * @param <V>
 * @author Jason Tran
 */
public interface PriorityQueue<K extends Comparable<K>, V> {
    public int size();

    public boolean isEmpty();

    public boolean containsValue(Object value);

    public V add(K key, V value);

    public V peek();

    public V extract();

    public Set<V> values();

    public Iterator<PriorityQueue.Entry<K, V>> iterator();

    public String toString();

    /**
     * A Priority Queue Entry (key-value) pair Interface
     * 
     * @param <K> the type of keys in the entry
     * @param <V> the type of values in the entry
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
         * Replaces the value corresponding to this entry with the specified value
         * 
         * @param value the value to be replaced with another
         * @return old value corresponding to the entry
         */
        public V setValue(V value);

        /**
         * This method returns a string representation of an entry in the map in the
         * format (k, v) or () if empty
         * 
         * @return the string format of the entry
         */
        public String toString();
    }
}