package main.map;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * This class implements the map Interface using a resizable generic array, this class supports null
 * keys NOTE: Did not use for-each loop because of null entries (makes things complex) so I used
 * regular for loops
 *
 * @author Jason Tran
 */
@SuppressWarnings("unchecked")
public class ArrayMap<K, V> implements Map<K, V> {
  private int size; // the number key-value pairs in the map
  // an array to store the key-value pair entries in the map
  private Entry<K, V>[] arr = new Entry[2];

  /**
   * This inner class implements the Entry Interface
   *
   * @param <K> the type of keys in the entry
   * @param <V> type of values in the entry
   * @author Jason Tran
   */
  private static class Entry<K, V> implements Map.Entry<K, V> {
    private final K key; // the key in the entry
    private V value; // the value in the entry

    /**
     * Constructor: Creates a new entry in the map
     *
     * @param key key to be stored in the entry
     * @param value the value to be stored in the entry
     */
    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    /** {@inheritDoc} Runtime: O(1) */
    @Override
    public K getKey() {
      return key;
    }

    /** {@inheritDoc} Runtime: O(1) */
    @Override
    public V getValue() {
      return value;
    }

    /** {@inheritDoc} Runtime: O(1) */
    @Override
    public V setValue(V value) {
      V oldValue = this.value;
      this.value = value;
      return oldValue;
    }

    /** {@inheritDoc} Runtime: O(1) */
    @Override
    public int hashCode() {
      return Objects.hash(key, value);
    }

    /** {@inheritDoc} Runtime: O(1) */
    @Override
    public String toString() {
      if (key == null && value == null) {
        return "()";
      } else {
        return "(" + key + "," + value + ")";
      }
    }
  }

  /**
   * This method returns the underlying array
   *
   * @return arr
   */
  public Entry<K, V>[] getArray() {
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

  /** {@inheritDoc} Runtime: O(n) */
  @Override
  public boolean containsKey(Object key) {
    if (key == null) {
      for (int i = 0; i < size; i++) {
        if (arr[i].key == null) {
          return true;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (key.equals(arr[i].key)) {
          return true;
        }
      }
    }
    return false;
  }

  /** {@inheritDoc} Runtime: O(n) */
  @Override
  public boolean containsValue(Object value) {
    if (value == null) {
      for (int i = 0; i < size; i++) {
        if (arr[i].value == null) {
          return true;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (value.equals(arr[i].value)) {
          return true;
        }
      }
    }
    return false;
  }

  /** {@inheritDoc} Runtime: O(n) */
  @Override
  public V get(Object key) {
    if (key == null) {
      for (int i = 0; i < size; i++) {
        if (arr[i].key == null) {
          return arr[i].value;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (key.equals(arr[i].key)) {
          return arr[i].value;
        }
      }
    }
    return null;
  }

  /** {@inheritDoc} Runtime: O(n) */
  @Override
  public V put(K key, V value) {
    // look up the key first
    if (key == null) {
      for (int i = 0; i < size; i++) {
        if (arr[i].key == null) {
          V prev = arr[i].value;
          arr[i].setValue(value);
          return prev;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (key.equals(arr[i].key)) {
          V prev = arr[i].value;
          arr[i].setValue(value);
          return prev;
        }
      }
    }
    // resize the array
    if (size == arr.length) {
      resize(2 * arr.length);
    }
    arr[size++] = new Entry<>(key, value);
    return null;
  }

  /**
   * {@inheritDoc} When the size is less than a quarter of capacity, the array capacity will resize
   * down by half Runtime: O(n)
   */
  @Override
  public V remove(Object key) {
    if (key == null) {
      for (int i = 0; i < size; i++) {
        if (arr[i].key == null) {
          return remove(i);
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (key.equals(arr[i].key)) {
          return remove(i);
        }
      }
    }
    return null;
  }

  /**
   * This is a helper function to remove an entry from the map, when the size is less than a quarter
   * of capacity, the array capacity will resize down by half
   *
   * @param index the position to remove the entry
   * @return the previous value associated with the entry Runtime: O(n)
   */
  private V remove(int index) {
    // shift the elements to the left
    V prev = arr[index].value;
    for (int i = index; i < size; i++) {
      arr[i] = arr[i + 1];
    }
    arr[size - 1] = null;
    size--;
    // resize the array by half is size less than a fourth of capacity
    if (size < arr.length / 4) {
      resize(arr.length / 2);
    }
    return prev;
  }

  /**
   * This is a helper method that resizes the underlying Entry array
   *
   * @param length the length of the array Runtime: O(n)
   */
  private void resize(int length) {
    Entry<K, V>[] temp = new Entry[length];
    for (int i = 0; i < size; i++) {
      temp[i] = arr[i];
    }
    arr = temp;
  }

  /** {@inheritDoc} Runtime: O(1) */
  @Override
  public void clear() {
    arr = new Entry[2];
    size = 0;
  }

  /** {@inheritDoc} Runtime: O(n) */
  @Override
  public Set<Map.Entry<K, V>> entrySet() {
    Set<Map.Entry<K, V>> set = new HashSet<>();
    for (int i = 0; i < size; i++) {
      set.add(arr[i]);
    }
    return set;
  }

  /** {@inheritDoc} Runtime: O(n) */
  @Override
  public Set<K> keySet() {
    Set<K> set = new HashSet<>();
    for (int i = 0; i < size; i++) {
      set.add(arr[i].key);
    }
    return set;
  }

  /** {@inheritDoc} Runtime: O(1) */
  @Override
  public Iterator<Map.Entry<K, V>> entryIterator() {
    return new Iterator<Map.Entry<K, V>>() {
      private int count = 0; // the number of elements in the map visited so far
      private final int expectedSize = size; // check for concurrent Modification Exception

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
      public Map.Entry<K, V> next() {
        if (expectedSize != size) {
          throw new ConcurrentModificationException("ERROR: Cannot modifiy the iterator");
        }
        if (!hasNext()) {
          throw new NoSuchElementException("ERROR: No more elements to iterate");
        } else {
          return arr[count++];
        }
      }

      /** {@inheritDoc} */
      @Override
      public void remove() {
        throw new UnsupportedOperationException(
            "ERROR: Remove not supported by this " + "iterator");
      }
    };
  }

  /** {@inheritDoc} Runtime: O(n) */
  @Override
  public String toString() {
    if (isEmpty()) {
      return "{}";
    } else {
      StringBuilder map = new StringBuilder(size).append("{");
      for (int i = 0; i < size - 1; i++) {
        map.append(arr[i].toString() + ", ");
      }
      return map.append(arr[size - 1].toString() + "}").toString();
    }
  }
}
