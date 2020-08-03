package main.tree;

import java.util.Iterator;

/**
 * A Tree Interface that supports generics
 * 
 * @param <E> the type of the elements in the array
 * @author Jason Tran
 */
public interface Tree<E extends Comparable<E>> {
    /**
     * The number of the nodes in the tree
     * 
     * @return number of elements in the tree
     */
    public int size();

    /**
     * Tells whether the tree has elements or not
     * 
     * @return true if the tree contains no elements
     */
    public boolean isEmpty();

    /**
     * Calculates the height of the tree which is the length longest path from the
     * root to a leaf in the tree
     * 
     * @return the length longest path in the in the tree
     */
    public int height();

    /**
     * Tells whether o is present in tree or not
     * 
     * @param o an element to be found in the tree
     * @return true if this tree contains the specified element false otherwise
     */
    public boolean contains(E e);

    /**
     * Adds the specified element into the tree
     * 
     * @param e an element to be added into the tree
     * @return true if the collection changed as a result of the call otherwise
     *         false
     */
    public boolean insert(E e);

    /**
     * Deletes the element e from the tree, if it is present
     * 
     * @param e an element to be removed
     * @return true if the collection changed as a result of the call otherwise
     *         false
     */
    public boolean delete(E e);

    /**
     * Removes all of the elements from this tree
     */
    public void clear();

    /**
     * Returns an array containing all of the elements in this tree in proper
     * sequence (in sorted order), in order traversal
     * 
     * @return an array containing all of the elements in this list in proper
     *         sequence
     */
    public Object[] toArray();

    /**
     * An enum class for the iterator type Depending on which type the iterator will
     * iterate in that order
     */
    public enum IteratorType {
        IN_ORDER, PRE_ORDER, POST_ORDER, LEVEL_ORDER
    }

    /**
     * Returns an iterator over the elements in this tree, depending on the order of
     * iteration
     * 
     * @param type the type of iteration order to iterator over
     * @return an iterator over the elements in this list
     */
    public Iterator<E> iterator(IteratorType type);

    /**
     * This method returns a string representation of the whole tree in the format:
     *      root 
     *      / \ 
     *   node node 
     *    / \ / \ 
     * .............. 
     * or null if tree is empty
     * 
     * @return the string format of the tree
     */
    public String toString();
}