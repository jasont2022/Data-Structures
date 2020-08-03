package main.set;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * This class implements the set interface using a resizable generic
 * array, also supports null elements to be insert, search, and delete
 * in the array
 * @param <E> the type of the elements in the set
 * @author Jason Tran
 */
@SuppressWarnings("unchecked")
public class ArraySet<E> implements Set<E> {
  private int size; // the number of elements in the set
  private E[] arr = (E[]) new Object[2]; // a static generic array

  /**
   * This method returns the actual underlying array
   * @return arr
   * Runtime: O(1)
   */
  public E[] getArray() {
    return arr;
  }

  /**
   * {@inheritDoc}
   * Runtime: O(1)
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * {@inheritDoc}
   * Runtime: O(1)
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * {@inheritDoc}
   * Runtime: O(n)
   */
  @Override
  public boolean contains(Object o) {
    return indexOf(o) != -1;
  }

  /**
   * This helper function returns the index of element o 
   * @param o an element to search the index for
   * @return the index if o is present in the set otherwise -1
   */
  private int indexOf(Object o) {
    if (o == null) {
      for (int i = 0; i < size; i++) {
        if (arr[i] == null) {
          return i;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (arr[i].equals(o)) {
          return i;
        }
      }
    }
    return -1;
  }

  /**
   * {@inheritDoc}
   * When the size is at capacity, the array will resize 
   * double its capacity
   * Runtime: O(n)
   */
  @Override
  public boolean add(E e) {
    if (contains(e)) {
      return false;
    } else {
      // double the length if size is at capacity
      if (size == arr.length) {
        resize(2 * arr.length);
      }
      arr[size++] = e;
      return true;
    }
  }

  /**
   * {@inheritDoc}
   * Runtime: O(n)
   */
  @Override
  public boolean remove(Object o) {
    if (o == null) {
      for (int i = 0; i < size; i++) {
        if (arr[i] == null) {
          remove(i);
          return true;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (arr[i].equals(o)) {
          remove(i);
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method removes the element at the specified index, 
   * shifts any subsequent elements to the left
   * @param index the index in the array
   * @return the element at that index
   * @throws NoSuchElementExpection if the array is empty
   * Runtime: O(n)
   */
  private E remove(int index) {
    if (isEmpty()) { 
      throw new NoSuchElementException("ERROR: can not remove from an empty array");
    } else {
      E prev = arr[index];
      // shift the elements to the left
      for (int i = index; i < size; i++) {
        arr[i] = arr[i + 1];
      }
      size--;
      // resize the array by half is size less than a fourth of capacity
      if (size < arr.length / 4) {
        resize(arr.length / 2);
      }
      return prev;
    }
  }

  /**
   * This is a helper method that resizes the 
   * underyling Entry array 
   * @param length the length of the array
   * Runtime: O(n)
   */
  private void resize(int length) {
    E[] temp = (E[]) new Object[length];
    for (int i = 0; i < size; i++) {
      temp[i] = arr[i];
    }
    arr = temp;
  }

  /**
   * {@inheritDoc}
   * Runtime: O(n^2)
   */
  @Override
  public Set<E> union(Set<E> set) {
    Set<E> unionSet = new ArraySet<>();
    for (E e : arr) {
      unionSet.add(e);
    }
    for (E e : set) {
      unionSet.add(e);
    }
    return unionSet;
  }

  /**
   * {@inheritDoc}
   * Runtime: O(n^2)
   */
  @Override
  public Set<E> intersection(Set<E> set) {
    Set<E> intersectionSet = new ArraySet<>();
    for (E e : arr) {
      if (set.contains(e)) {
        intersectionSet.add(e);
      }
    }
    return intersectionSet;
  }

  /**
   * {@inheritDoc}
   * Runtime: O(n^2)
   */
  @Override
  public Set<E> difference(Set<E> set) {
    Set<E> differenceSet = new ArraySet<>();
    for (E e : arr) {
      if (!set.contains(e)) {
        differenceSet.add(e);
      }
    }
    return differenceSet;
  }

  /**
   * {@inheritDoc}
   * Runtime: O(n^2)
   */
  @Override
  public boolean subset(Set<E> set) {
    /**
    for (E e : arr) {
      if (!set.contains(e)) {
        return false;
      }
    }
    return true;
     */
    return false;
  }

  /**
   * {@inheritDoc}
   * Runtime: O(1)
   */
  @Override
  public void clear() {
    arr = (E[]) new Object[2];
    size = 0;
  }

  /**
   * {@inheritDoc}
   * Runtime: O(n)
   */
  @Override
  public Object[] toArray() {
    Object[] array = new Object[size];
    for (int i = 0; i < size; i++) {
      array[i] = arr[i];
    }
    return array;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private int count = 0; // the counter of elements traveled so far
      final private int expectedSize = size; // to check for ConcurrentModificationException

      /**
       * {@inheritDoc}
       * Checks for concurrent modification
       * @throws ConcurrentModificationException
       */
      @Override
      public boolean hasNext() {
        if (expectedSize != size) {
          throw new ConcurrentModificationException("ERROR: Cannot modifiy the iterator");
        }
        return count < expectedSize;
      }

      /**
       * {@inheritDoc}
       * Checks for concurrent modification
       * @throws ConcurrentModificationException
       */
      @Override
      public E next() {
        if (expectedSize != size) {
          throw new ConcurrentModificationException("ERROR: Cannot modifiy the iterator");
        }
        if (hasNext()) {
          return arr[count++];
        } else {
          throw new NoSuchElementException("ERROR: No more elements to iterate");
        }
      }
      
      /**
       * {@inheritDoc}
       */
      @Override
      public void remove() {
        throw new UnsupportedOperationException("ERROR: Remove not Supported by this Iterator");
      }
    };
  }

  /**
   * {@inheritDoc}
   * Runtimee: O(n)
   */
  @Override
  public String toString() {
    if (isEmpty()) {
      return "{}";
    } else {
      StringBuilder set = new StringBuilder(size).append("{");
      for (int i = 0; i < size - 1; i++) {
        set.append(arr[i] + ", ");
      }
      return set.append(arr[size - 1] + "}").toString();
    }
  }
}