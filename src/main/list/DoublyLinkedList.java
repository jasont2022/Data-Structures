package main.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * This class implements the List, Queue, and Stack interface, doubly linked list
 * 
 * @author Jason Tran
 */
public class DoublyLinkedList<E> implements List<E> {
    private int size = 0; // the number of elements in the list
    private Node<E> head, tail = null; // the head and tail pointer of the list

    /**
     * Private Inner Class to represent a Node<E> in a doubly linked list
     */
    private static class Node<E> {
        private E value; // data or value to store
        private Node<E> prev, next; // the previous and next pointers

        /**
         * Constructor: Creates a new node
         * 
         * @param value the data or value to store in the node
         * @param prev  the next pointer to the pervious node in the list
         * @param next  the next pointer to the next node in the list
         */
        public Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * This method returns the head of this list
     * 
     * @return the head of this list Runtime: O(1)
     */
    public Node<E> getHead() {
        return head;
    }

    /**
     * This method returns the tail of this list
     * 
     * @return the tail of this list Runtime: O(1)
     */
    public Node<E> getTail() {
        return tail;
    }

    /**
     * {@inheritDoc} Runtime: O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc} Runtime: O(1)
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc} Runtime: O(n)
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * {@inheritDoc} Runtime: O(n)
     */
    @Override
    public int indexOf(Object o) {
        int index = 0; // index to be increment
        if (o == null) {
            for (Node<E> curr = head; curr != null; curr = curr.next, index++) {
                if (curr.value == null) {
                    return index;
                }
            }
        } else {
            for (Node<E> curr = head; curr != null; curr = curr.next, index++) {
                if (curr.value.equals(o)) {
                    return index;
                }
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this
     * list, or -1 if this list does not contain the element. More formally, returns
     * the highest index {@code i} such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>, or
     * -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in this
     *         list, or -1 if this list does not contain the element
     */
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> curr = tail; curr != null; curr = curr.prev, index++) {
                if (curr.value == null) {
                    return index;
                }
            }
        } else {
            for (Node<E> curr = tail; curr != null; curr = curr.prev, index++) {
                if (curr.value == null) {
                    return index;
                }
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc} Runtime: O(n)
     */
    @Override
    public E get(int index) {
        // check if the index is valid
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("ERROR: Illegal Index: " + index);
        }
        Node<E> curr = head; // traveling node
        for (int i = 0; i < index; i++, curr = curr.next) {
        }
        return curr.value;
    }

    /**
     * {@inheritDoc} Runtime: O(n)
     */
    @Override
    public E set(int index, E element) {
        // check if the index is valid
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("ERROR: Illegal Index: " + index);
        }
        Node<E> curr = head; // traveling node
        for (int i = 0; i < index; i++, curr = curr.next) {
        }
        E prev = curr.value;
        curr.value = element;
        return prev;
    }

    /**
     * A private helper function that inserts a new element into an empty list
     * 
     * @param e an element to insert into the list Runtime: O(1)
     */
    private void insertToEmptyList(E e) {
        head = tail = new Node<E>(e, null, null);
        size++;
    }

    /**
     * {@inheritDoc} Runtime: O(1)
     */
    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    /**
     * {@inheritDoc} Runtime: O(n)
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("ERROR: Illegal Index: " + index);
        } else if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<E> curr = head; // traveling node
            for (int i = 0; i < index - 1; i++, curr = curr.next) {
            }
            Node<E> newNode = new Node<E>(element, curr, curr.next);
            curr.next.prev = newNode;
            curr.next = newNode;
            size++;
        }
    }

    /**
     * Inserts the specified element at the beginning of this list
     * 
     * @param e an element to insert at the head of the list Runtime: O(1)
     */
    public void addFirst(E e) {
        if (isEmpty()) {
            insertToEmptyList(e);
        } else {
            Node<E> newNode = new Node<E>(e, null, head);
            head.prev = newNode;
            head = head.prev;
            size++;
        }
    }

    /**
     * Appends the specified element to the end of this list
     * 
     * @param e an element to insert at the tail of the list Runtime: O(1)
     */
    public void addLast(E e) {
        if (isEmpty()) {
            insertToEmptyList(e);
        } else {
            Node<E> newNode = new Node<E>(e, tail, null);
            tail.next = newNode;
            tail = tail.next;
            size++;
        }
    }

    /**
     * {@inheritDoc} Runtime: O(1)
     */
    //@Override
    public void push(E item) {
        addFirst(item);
    }

    /**
     * {@inheritDoc} Runtime: O(1)
     */
    //@Override
    public boolean offer(E e) {
        addFirst(e);
        return true;
    }

    /**
     * {@inheritDoc} Runtime: O(1)
     */
    //@Override
    public E peek() {
        return peekFirst();
    }

    /**
     * {@inheritDoc} Runtime: O(1)
     */
    //@Override
    public E element() {
        return peek();
    }

    /**
     * Retrieves, but does not remove, the first element of this list
     * 
     * @return the first element of this list, otherwise null
     * @throws NoSuchElementException if the list is empty Runtime: O(1)
     */
    public E peekFirst() {
        final Node<E> curr = head;
        if (curr == null) {
            throw new NoSuchElementException("ERROR: Can not peek an empty list");
        }
        return curr.value;
    }

    /**
     * Retrieves, but does not remove, the last element of this list
     * 
     * @return the last element of this list, otherwise null
     * @throws NoSuchElementExpection if the list is empty Runtime: O(1)
     */
    public E peekLast() {
        final Node<E> curr = tail;
        if (curr == null) {
            throw new NoSuchElementException("ERROR: Can not peek an empty list");
        }
        return curr.value;
    }

    /**
     * Retrieves and removes the head (first element) of this list
     * 
     * @return the head of this list, otherwise null
     * @throws NoSuchElementExpection if the list is empty Runtime: O(1)
     */
    public E remove() {
        return removeFirst();
    }

    /**
     * {@inheritDoc} Runtime: O(n)
     */
    @Override
    public E remove(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot remove from empty list");
        } else if (index < 0 || index >= size) {
            throw new IllegalArgumentException("ERROR: Index out of bounds: " + index);
        } else if (index == 0) {
            return removeFirst();
        } else {
            Node<E> curr = head; // traveling node 1
            for (int i = 0; i < index; i++, curr = curr.next) {
            }
            E prev = curr.value;
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            curr.prev = curr.next = null;
            size--;
            return prev;
        }
    }

    /**
     * {@inheritDoc} Runtime: O(n)
     */
    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot remove from empty list");
        }
        int index = 0; // the index
        if (o == null) {
            for (Node<E> curr = head; curr != null; curr = curr.next, index++) {
                if (curr.value == null) {
                    remove(index);
                    return true;
                }
            }
        } else {
            for (Node<E> curr = head; curr != null; curr = curr.next, index++) {
                if (curr.value.equals(o)) {
                    remove(index);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes the first occurrence of the specified element in this list (when
     * traversing the list from head to tail). If the list does not contain the
     * element, it is unchanged.
     * 
     * @param o element to be removed from this list, if present
     * @return true if the list contained the specified element Runtime: O(n)
     */
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    /**
     * Removes the last occurrence of the specified element in this list (when
     * traversing the list from head to tail). If the list does not contain the
     * element, it is unchanged.
     * 
     * @param o element to be removed from this list, if present
     * @return true if the list contained the specified element Runtime: O(n)
     */
    public boolean removeLastOccurrence(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> curr = tail; curr != null; curr = curr.prev, index++) {
                if (curr.value == null) {
                    remove(index);
                    return true;
                }
            }
        } else {
            for (Node<E> curr = tail; curr != null; curr = curr.prev, index++) {
                if (curr.value.equals(o)) {
                    remove(index);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes and returns the first element from this list
     * 
     * @return head element of this list, otherwise null
     * @throws NoSuchElementExpection if the list is empty Runtime: O(1)
     */
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot remove from an empty list");
        }
        Node<E> curr = head;
        head = head.next;
        head.prev = curr.next = null;
        size--;
        return curr.value;
    }

    /**
     * Removes and returns the last element from this list
     * 
     * @return tail element of this list, otherwise null
     * @throws NoSuchElementExpection if the list is empty Runtime: O(1)
     */
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot remove from an empty list");
        }
        Node<E> curr = tail;
        tail = tail.prev;
        curr.prev = tail.next = null;
        size--;
        return curr.value;
    }

    /**
     * {@inheritDoc} Runtime: O(1)
     */
    //@Override
    public E pop() {
        return removeFirst();
    }

    /**
     * {@inheritDoc} Runtime: O(1)
     */
    //@Override
    public E poll() {
        return removeFirst();
    }

    /**
     * {@inheritDoc} Runtime: O(1)
     */
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * {@inheritDoc} Runtime: O(n)
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<E> curr = head; // traveling node
        for (int i = 0; i < size; i++, curr = curr.next) {
            array[i] = curr.value;
        }
        return array;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> curr = head; // the current element pointing to in the list
            final private int expectedSize = size; // used to check for concurrent modification

            /**
             * {@inheritDoc} Checks for concurrent modification
             * 
             * @throws ConcurrentModificationException
             */
            @Override
            public boolean hasNext() {
                if (expectedSize != size) {
                    throw new ConcurrentModificationException("ERROR: Cannot modifiy the iterator");
                }
                return curr != null;
            }

            /**
             * {@inheritDoc} Checks for concurrent modification
             * 
             * @throws ConcurrentModificationException
             */
            @Override
            public E next() {
                if (expectedSize != size) {
                    throw new ConcurrentModificationException("ERROR: Cannot modifiy the iterator");
                }
                if (hasNext()) {
                    E next = curr.value;
                    curr = curr.next;
                    return next;
                } else {
                    throw new NoSuchElementException("ERROR: No more elements to iterate");
                }
            }
            
            /**
             * {@inheritDoc}
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("ERROR: Remove not supported by this " 
                        + "iterator");
            }
        };
    }

    /**
     * {@inheritDoc} Runtime: O(n)
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "null";
        } else {
            StringBuilder list = new StringBuilder(size);
            for (Node<E> curr = head; curr != tail; curr = curr.next) {
                list.append("[" + curr.value + "]" + "<->");
            }
            return list.append("[" + tail.value + "]").toString();
        }
    }
}