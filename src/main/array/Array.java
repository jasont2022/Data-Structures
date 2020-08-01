package main.array;

import java.util.Iterator;

/**
 * A Dynamic Array (resizable) Interface that supports generics
 * 
 * @author Jason Tran
 * @param <E> the type of the elements in the array
 */
public interface Array<E> extends Iterable<E> {
    /**
     * Returns the size of array (number of elements in Array)
     * 
     * @return the number of elements in the array
     */
    public int size();

    /**
     * Tells whether the array has elements or not
     * 
     * @return true if empty otherwise false
     */
    public boolean isEmpty();

    /**
     * Tells whether o is in Array or not
     * 
     * @param o an element to be found in the array
     * @return true if the array contains o otherwise false
     */
    public boolean contains(Object o);

    /**
     * This method returns the index of element o If there are duplicates return the
     * first index of o
     * 
     * @param o an element to search the index for
     * @return the index of o or -1 if o is not in Array
     */
    public int indexOf(Object o);

    /**
     * This method gets the specfic element at index array[index]
     * 
     * @param index the index in the array
     * @return array[index], the element at that index
     * @throws IllegalArgumentException if index is out of bounds
     */
    public E get(int index);

    /**
     * This method changes the element at the specified index
     * 
     * @param index the index in the array
     * @param e     the element to be changed
     * @throws IllegalArgumentException if index is out of bounds
     */
    public void set(int index, E e);

    /**
     * This method appends the element e at the end of this array
     * 
     * @param e the element to be added into the array
     */
    public void add(E e);

    /**
     * This method removes the element at the specified index, shifts any subsequent
     * elements to the left
     * 
     * @param index the index in the array
     * @return the element at that index
     * @throws IllegalArgumentException if index is out of bounds
     * @throws NoSuchElementExpection   if the array is empty
     */
    public E remove(int index);

    /**
     * This method deletes the element o from the array, removes the first
     * occurancee of e in the array
     * 
     * @param o an element to be remove
     * @return true if removal of o is successful otherwise false
     * @throws NoSuchElementExpection if the array is empty
     */
    public boolean remove(Object o);

    /**
     * This method deletes all the elements in the array, the array will be empty
     * after this call returns
     */
    public void clear();

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element), must allocate a new array
     * 
     * @return an array containing all of the elements in this list in proper
     *         sequence
     */
    public Object[] toArray();

    /**
     * Returns an iterator over the elements in this array, ordered from first to
     * last
     * 
     * @return an iterator over the elements in this array
     */
    public Iterator<E> iterator();

    /**
     * This method returns a string representation of the whole array in the format
     * [a_1, a_2, ...., a_n]
     * 
     * @return the string format of the array
     */
    public String toString();
}