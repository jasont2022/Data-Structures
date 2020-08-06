package main.map;

import java.util.Set;
import java.util.Iterator;

public class TreeMap<K, V> implements Map<K, V> {

  /** {@inheritDoc} */
  @Override
  public int size() {
    return 0;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEmpty() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public boolean containsKey(Object key) {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public boolean containsValue(Object value) {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public V get(Object key) {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public V put(K key, V value) {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public V remove(Object key) {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public void clear() {}

  /** {@inheritDoc} */
  @Override
  public Set<Entry<K, V>> entrySet() {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public Set<K> keySet() {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public Iterator<Entry<K, V>> entryIterator() {
    return null;
  }

  /** {@inheritDoc} Runtime: O(n) */
  @Override
  public String toString() {
    return null;
  }
}
