package main.map;

import java.util.Set;
import java.util.Iterator;

/**
 * A Map Interface that supports generics
 *
 * @author Jason Tran
 */
public interface Map<K, V> {
  /**
   * Returns the number of elements (key-value pairs) in the map
   *
   * @return the number of elements in the map
   */
  public int size();

  /**
   * Tells whether the map has elements or not
   *
   * @return true if the map contains no elements
   */
  public boolean isEmpty();

  /**
   * Returns true if this map contains a mapping for the specified key
   *
   * @param key the key to look up in the map
   * @return true if the map contains a mapping of the key
   */
  public boolean containsKey(Object key);

  /**
   * Returns true if the specified value is present in the map
   *
   * @param value the value to look up in the map
   * @return true if the map contains the specified value
   */
  public boolean containsValue(Object value);

  /**
   * Returns the value to which the specified key is mapped, or null if this map contains no mapping
   * for the key
   *
   * @param key the key to lookup in the map
   * @return the value to which the key is mapped otherwise null if no mapping for the key
   */
  public V get(Object key);

  /**
   * Associates the specified value with the specified key in this map, if the map previously
   * contained a mapping for the key, the old value is replaced
   *
   * @param key a key to be paired with the value in the map
   * @param value a value to be paired with the key in the map
   * @return the previous value associated with the key otherwise null if no mapping for the key
   */
  public V put(K key, V value);

  /**
   * Removes the mapping for a key from this map if it is present
   *
   * @param key the key to lookup in the map to remove the mapping
   * @return the previous value associated with the key otherwise null if no mapping for the key
   */
  public V remove(Object key);

  /** Deletes all the elements in the map, the map will be empty after this call returns */
  public void clear();

  /**
   * Returns a set view of the mappings contained in this map
   *
   * @return a set view of the mappings in this map
   */
  public Set<Map.Entry<K, V>> entrySet();

  /**
   * Returns a set view of the keys contained in this map
   *
   * @return a set view of the keys in this map
   */
  public Set<K> keySet();

  /**
   * Returns an iterator over the elements in this map
   *
   * @return an iterator over the elements in this map
   */
  public Iterator<Map.Entry<K, V>> entryIterator();

  /**
   * This method returns a string representation of the whole map in the format {(k_1,v_1),
   * (k_2,v_2), ... (k_n,v_n)} or {} if the map is empty
   *
   * @return the string format of the map
   */
  public String toString();

  /**
   * A Map Entry (key-value) pair Interface
   *
   * @param <K> the type of keys in the entry
   * @param <V> the type of values in the entry
   * @author Jason Tran
   */
  public static interface Entry<K, V> {
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
     * Returns the hash code value for this map entry
     *
     * @return the hash code value for this map entry
     */
    public int hashCode();

    /**
     * This method returns a string representation of an entry in the map in the format (k, v) or ()
     * if empty
     *
     * @return the string format of the entry
     */
    public String toString();
  }
}
