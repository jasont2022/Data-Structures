package main.stack;

import java.util.Iterator;

/**
 * A Stack Interface that supports generics
 *
 * @author Jason Tran
 */
public interface Stack<E> extends Iterable<E> {
    /**
     * Returns the number of elements in the stack
     *
     * @return the number of elements in the stack
     */
    public int size();

    /**
     * Tells whether the stack has elements or not
     *
     * @return true if the stack contains no elements
     */
    public boolean isEmpty();

    /**
     * Adds an element into the top of the stack
     *
     * @param item an element to be added into the top
     */
    public void push(E item);

    /**
     * Retrieves, but does not remove, the first element of this stack
     *
     * @return the object at the top of this stack
     * @throws EmptyStackException    if the stack is empty
     * @throws NoSuchElementException if the stack is empty (if implemented as
     *                                another collection ex: linkedlist)
     */
    public E peek();

    /**
     * Removes the object at the top of the stack
     *
     * @return the object at the top of this stack
     * @throws EmptyStackException    if the stack is empty
     * @throws NoSuchElementException if the stack is empty (if implemented as
     *                                another collection ex: linkedlist)
     */
    public E pop();

    /**
     * Deletes all the elements in the stack, the stack will be empty after this
     * call returns
     */
    public void clear();

    /**
     * Returns an iterator over the elements in this stack, ordered from head to
     * last
     *
     * @return an iterator over the elements in this stack
     */
    public Iterator<E> iterator();

    /**
     * This method returns a string representation of the whole stack in the format
     * [s_1, s_2, ...., s_n] or [] if the stack is empty left most is the top of the
     * stack (s_1 is the top of stack) right most is the bottom of the stack (s_n is
     * the bottom of the stack)
     *
     * @return the string format of the stack
     */
    public String toString();
}
