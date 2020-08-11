package main.set;

import java.util.Iterator;

/**
 * A Set Interface that supports generics
 *
 * @author Jason Tran
 */
public interface Set<E> extends Iterable<E> {
    /**
     * Returns the number of elements in the set
     *
     * @return the number of elements in the set
     */
    public int size();

    /**
     * Tells whether the set has elements or not
     *
     * @return true if the set contains no elements
     */
    public boolean isEmpty();

    /**
     * Tells whether o is present in set or not
     *
     * @param o an element to be found in the set
     * @return true if this set contains the specified element, false otherwise
     */
    public boolean contains(Object o);

    /**
     * Adds the specified element to the set, if the set contains the element, the
     * set will not be modify
     *
     * @param e an element to be added into the set
     * @return true if collection changed as a result of the call otherwise false
     */
    public boolean add(E e);

    /**
     * Deletes the element o from the set, if it is present
     *
     * @param o an element to be removed
     * @return true if the element is in the set otherwise false
     * @throws NoSuchElementExpection if the array is empty
     */
    public boolean remove(Object o);

    /**
     * Takes the union between two sets, the union of a set is the combination of
     * two sets, but not including intersecting elements
     *
     * @param set another set to be union
     * @return the union set of this set and the other set
     */
    public Set<E> union(Set<E> set);

    /**
     * Takes the intersection between two sets, the intersection of a set is the
     * combination of two sets, taking elements that are present in both sets
     *
     * @param set another set to be intersect with
     * @return the union set of this set and the other set
     */
    public Set<E> intersection(Set<E> set);

    /**
     * Takes the difference between two sets, elements that are in this set and not
     * the other set are to be returned in a set
     *
     * @param set another set to be differenced with
     * @return the difference set of this set and the other set
     */
    public Set<E> difference(Set<E> set);

    /**
     * Takes the subset between two sets, test whether this set is a subset of a
     * different set
     *
     * @param set another set to be subset with
     * @return true if this set is a subset of set otherwise false
     */
    public boolean subset(Set<E> set);

    /** Removes all of the elements from this list */
    public void clear();

    /**
     * Returns an array containing all of the elements in this set
     *
     * @return an array containing all of the elements in this set
     */
    public Object[] toArray();

    /**
     * Returns an iterator over the elements in this set
     *
     * @return an iterator over the elements in this set
     */
    public Iterator<E> iterator();

    /**
     * This method returns a string representation of the whole set in the format
     * {s_1, s_2, ...., s_n} or {} if set is empty
     *
     * @return the string format of the set
     */
    public String toString();
}
