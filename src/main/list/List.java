package main.list;

import java.util.Iterator;

/**
 * A List Interface that supports generics
 *
 * @author Jason Tran
 */
public interface List<E> extends Iterable<E> {
  /**
   * Returns the number of elements in the list
   *
   * @return the number of elements in the list
   */
  public int size();

  /**
   * Tells whether the list has elements or not
   *
   * @return true if the list contains no elements
   */
  public boolean isEmpty();

  /**
   * Tells whether o is present in list or not
   *
   * @param o an element to be found in the list
   * @return true if this list contains the specified element false otherwise
   */
  public boolean contains(Object o);

  /**
   * Returns the index of element o, if there are duplicates return the first index of o
   *
   * @param o an element to be found in the list
   * @return the index of the first occurrence of the specified element in the list otherwise return
   *     -1
   */
  public int indexOf(Object o);

  /**
   * This method gets the specific element at index
   *
   * @param index the position to find the element
   * @return the element at the specified position in this list, otherwise return null
   * @throws IllegalArgumentException if index is out of bounds
   */
  public E get(int index);

  /**
   * Replaces the element at the specified position in this list with the specified element
   *
   * @param index the position to find the element
   * @param element the element to be changed
   * @return the element previously at the specified position
   * @throws IllegalArgumentException if index is out of bounds
   */
  public E set(int index, E element);

  /**
   * Appends the specified element to the end of this list
   *
   * @param e an element to be added into the list
   * @return true if the collection changed as a result of the call otherwise false
   */
  public boolean add(E e);

  /**
   * Inserts the specified element at the specified position in this list
   *
   * @param index the position to insert the element
   * @param element the element to insert in the list
   * @throws IllegalArgumentException if index is out of bounds
   */
  public void add(int index, E element);

  /**
   * Removes the element specified at the index in this list
   *
   * @param index the position to remove the element
   * @return the element previously at the specified position
   * @throws IllegalArgumentException if index is out of bounds
   * @throws NoSuchElementExpection if the list is empty
   */
  public E remove(int index);

  /**
   * Deletes the element o from the list, removes the first occurrence of e in the list, if it is
   * present
   *
   * @param o an element to be removed
   * @return true if the element is in the list otherwise false
   * @throws NoSuchElementExpection if the list is empty
   */
  public boolean remove(Object o);

  /** Removes all of the elements from this list */
  public void clear();

  /**
   * Returns an array containing all of the elements in this list in proper sequence (from first to
   * last element)
   *
   * @return an array containing all of the elements in this list in proper sequence
   */
  public Object[] toArray();

  /**
   * Returns an iterator over the elements in this list, ordered from head to tail
   *
   * @return an iterator over the elements in this list
   */
  public Iterator<E> iterator();

  /**
   * This method returns a string representation of the whole list in the format [e_1]->[e_2]-> ....
   * ->[e_n] for singly linked list [e_1]<->[e_2]<-> ... <->[e_n] or null if list is empty
   *
   * @return the string format of the list
   */
  public String toString();
}
