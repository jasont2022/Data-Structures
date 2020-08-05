package main.tree;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * This class implements the Tree Interface, a Binary Search Tree 
 * NOTE: No Duplicates of element values are allowed
 * 
 * @author Jason Tran
 */
public class BinarySearchTree<E extends Comparable<E>> implements Tree<E> {
    private int size; // the number nodes in the BST
    private Node<E> root; // the root of the BST

    /**
     * Private Inner Class represent a Node<E> in a binary search tree
     * 
     * @param <E> the type of the elements in node
     */
    private static class Node<E> {
        private E value; // data or value to store
        private Node<E> left, right; // the children of the node

        /**
         * Constructor: Creates a new node
         * 
         * @param left  the left child of the node
         * @param value the data in the node
         * @param right the right child of the node
         */
        public Node(Node<E> left, E value, Node<E> right) {
            this.left = left;
            this.value = value;
            this.right = right;
        }
    }
    
    /**
     * Returns the root of the binary search tree
     * 
     * @return the root of the binary search tree
     */
    public Node<E> getRoot() {
        return root;
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
    public int height() {
        return height(root);
    }

    /**
     * Helper function that calculates the height of the BST
     * 
     * @param node a node in the tree
     * @return the height of the tree 
     * Runtime: O(n)
     */
    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(height(node.left), height(node.right));
        }
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(n)
     */
    @Override
    public boolean contains(E e) {
        return contains(e, root);
    }

    /**
     * Helper function to check if e is in the BST
     * 
     * @param e    an element to lookup in the tree
     * @param node a node in the tree
     * @return true if e is in the tree 
     * Runtime: O(n)
     */
    private boolean contains(E e, Node<E> node) {
        if (node == null) {
            return false;
        } else {
            int value = e.compareTo(node.value);
            if (value < 0) {
                return contains(e, node.left);
            } else if (value > 0) {
                return contains(e, node.right);
            } else {
                return true;
            }
        }
    }

    /**
     * Returns the minimum value in the BST
     * 
     * @return the minimum value in the BST 
     * Runtime: O(n)
     */
    public E getMinimum() {
        return getMinimum(root).value;
    }

    /**
     * Helper function to get the minimum value in the BST
     * 
     * @param node a node in the tree
     * @return the node containing the minimum value in the BST 
     * Runtime: O(n)
     */
    private Node<E> getMinimum(Node<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Returns the maximum value in the BST
     * 
     * @return the maximum value in the BST 
     * Runtime: O(n)
     */
    public E getMaximum() {
        return getMaximum(root).value;
    }

    /**
     * Helper function to get the maximum value in the BST
     * 
     * @param node a node in the tree
     * @return the node containing the maximum value in the BST 
     * Runtime: O(n)
     */
    private Node<E> getMaximum(Node<E> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * Tells if the BST is balanced or not
     * 
     * @return true if the tree is balanced 
     * Runtime: O(n)
     */
    public boolean isBalanced() {
        return false;
    }

    /**
     * Tells whether the BST is perfect (full and complete)
     * 
     * @return true if the BST is perfect 
     * Runtime: O(n)
     */
    public boolean isPerfect() {
        return isComplete() && isFull();
    }

    /**
     * Tells whether the BST is complete (Each level of the BST is filled from left
     * to right, the last level may only be filled from the far left as possible)
     * 
     * @return true if the BST is complete, otherwise false 
     * Runtime: O(n)
     */
    public boolean isComplete() {
        return isComplete(root);
    }

    /**
     * Tells whether the BST is complete or not
     * 
     * @param node the node to start recursing over
     * @return true if the BST is complete, otherwise false 
     * Runtime: O(n)
     */
    private boolean isComplete(Node<E> node) {
        if (node == null) {
            return true;
        }
        boolean isNullChild = false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node<E> curr = queue.poll();
            if (curr.left != null) {
                if (isNullChild) {
                    return false;
                }
                queue.offer(curr.left);
            } else {
                isNullChild = true;
            }

            if (curr.right != null) {
                if (isNullChild) {
                    return false;
                }
                queue.offer(curr.right);
            } else {
                isNullChild = true;
            }
        }
        return true;
    }

    /**
     * Tells whether the BST is full (Every node other than the leaves has two
     * children)
     * 
     * @return true if the BST is full, otherwise false 
     * Runtime: O(n)
     */
    public boolean isFull() {
        return isFull(root);
    }

    /**
     * Tells whether the BST is full or not
     * 
     * @param node the node to start recursing over
     * @return true if the BST is full, otherwise false 
     * Runtime: O(n)
     */
    private boolean isFull(Node<E> node) {
        if (node == null) {
            return true;
        } else if (node.left == null && node.right == null) {
            return true;
        } else if (node.left != null && node.right != null) {
            return (isFull(node.left) && isFull(node.right));
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(n)
     */
    @Override
    public boolean insert(E e) {
        if (contains(e)) {
            return false;
        } else {
            root = insert(e, root);
            size++;
            return true;
        }
    }

    /**
     * Inserts a new node into the BST at the correct level and 
     * position and does not insert new nodes with duplicate values
     * 
     * @param e    a new element to insert into the BST
     * @param node a node to start recursing from
     * @return the node that is inserted
     * Runtime: O(n)
     */
    private Node<E> insert(E e, Node<E> node) {
        if (node == null) {
            node = new Node<>(null, e, null);
        } else {
            int value = e.compareTo(node.value);
            if (value < 0) {
                node.left = insert(e, node.left);
            } else {
                node.right = insert(e, node.right);
            }
        }
        return node;
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(n)
     */
    @Override
    public boolean delete(E e) {
        if (contains(e)) {
            root = delete(e, root);
            size--;
            return true;
        }
        return false;
    }

    /**
     * Deletes a node from the BST at the correct level and position
     * 
     * @param e    an element to delete from the BST
     * @param node a node to start recursing from
     * @return the node to be deleted
     * Runtime: O(n)
     */
    private Node<E> delete(E e, Node<E> node) {
        if (node == null) {
            return null;
        } else {
            int value = e.compareTo(node.value);
            if (value < 0) {
                node.left = delete(e, node.left);
            } else if (value > 0) {
                node.right = delete(e, node.right);
            } else {
                if (node.left == null) {
                    Node<E> right = node.right;
                    node = null;
                    return right;
                } else if (node.right == null) {
                    Node<E> left = node.left;
                    node = null;
                    return left;
                } else {
                    Node<E> temp = getMinimum(node.right);
                    node.value = temp.value;
                    node.right = delete(temp.value, node.right);
                }
            }
        }
        return node;
    }

    /**
     * Returns a list with elements in order from the BST
     * 
     * @return a list that contains the elements of the BST in order 
     * Runtime: O(n)
     */
    public List<E> inOrder() {
        List<E> list = new LinkedList<>();
        return inOrder(list, root);
    }

    private List<E> inOrder(List<E> list, Node<E> node) {
        if (node == null) {
            return list;
        } else {
            inOrder(list, node.left);
            list.add(node.value);
            inOrder(list, node.right);
            return list;
        }
    }

    /**
     * Returns a list with elements pre-order from the BST
     * 
     * @return a list that contains the elements of the BST in pre-order 
     * Runtime: O(n)
     */
    public List<E> preOrder() {
        List<E> list = new LinkedList<>();
        return preOrder(list, root);
    }

    private List<E> preOrder(List<E> list, Node<E> node) {
        if (node == null) {
            return list;
        } else {
            list.add(node.value);
            preOrder(list, node.left);
            preOrder(list, node.right);
            return list;
        }
    }

    /**
     * Returns a list with elements post-order from the BST
     * 
     * @return a list that contains the elements of the BST in post-order 
     * Runtime: O(n)
     */
    public List<E> postOrder() {
        List<E> list = new LinkedList<>();
        return postOrder(list, root);
    }

    private List<E> postOrder(List<E> list, Node<E> node) {
        if (node == null) {
            return list;
        } else {
            postOrder(list, node.left);
            postOrder(list, node.right);
            list.add(node.value);
            return list;
        }
    }

    /**
     * Returns a list with elements level-order from the BST
     * 
     * @return a list that contains the elements of the BST in the level-order
     * Runtime: O(n)
     */
    public List<E> levelOrder() {
        List<E> list = new LinkedList<>();
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            list.add(node.value);

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return list;
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(1)
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(n)
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        List<E> list = inOrder();
        int index = 0; // index for array
        for (E e : list) {
            array[index] = e;
            index++;
        }
        return array;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator(IteratorType type) {
        switch (type) {
            case IN_ORDER:
                return inOrderIterator();
            case PRE_ORDER:
                return preOrderIterator();
            case POST_ORDER:
                return postOrderIterator();
            case LEVEL_ORDER:
                return levelOrderIterator();
            default:
                return null;
        }
    }

    /**
     * Returns a in-order iterator
     * 
     * @return an inorder iterator for the BST
     */
    private Iterator<E> inOrderIterator() {
        final int expectedSize = size; // used to check for concurrent modification
        final Stack<Node<E>> stack = new Stack<>();
        stack.push(root);

        return new Iterator<E>() {
            private Node<E> curr = root; // the current element pointing in the BST

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean hasNext() {
                return root != null && !stack.isEmpty();
            }

            /**
             * {@inheritDoc}
             * 
             * @throws ConcurrentModificationException if size does not equal expectedSize
             */
            @Override
            public E next() {
                if (expectedSize != size) {
                    throw new ConcurrentModificationException("ERROR: Cannot modifiy the iterator");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("ERROR: No more elements to iterate");
                } else {
                    // travel down far left as possible
                    while (curr != null && curr.left != null) {
                        stack.push(curr.left);
                        curr = curr.left;
                    }

                    Node<E> node = stack.pop();

                    if (node.right != null) {
                        stack.push(node.right);
                        curr = node.right;
                    }

                    return node.value;
                }
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("ERROR: Remove not Supported by this " + 
                        "Iterator");
            }
        };
    }

    /**
     * Returns a pre-order iterator
     * 
     * @return a pre-order iterator for the BST
     */
    private Iterator<E> preOrderIterator() {
        final int expectedSize = size; // used to check for concurrent modification
        final Stack<Node<E>> stack = new Stack<>();
        stack.push(root);

        return new Iterator<E>() {
            /**
             * {@inheritDoc}
             */
            @Override
            public boolean hasNext() {
                return root != null && !stack.isEmpty();
            }

            /**
             * {@inheritDoc}
             * 
             * @throws ConcurrentModificationException if size does not equal expectedSize
             */
            @Override
            public E next() {
                if (expectedSize != size) {
                    throw new ConcurrentModificationException("ERROR: Cannot modifiy the iterator");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("ERROR: No more elements to iterate");
                } else {
                    Node<E> node = stack.pop();
                    if (node.right != null) {
                        stack.push(node.right);
                    }
                    if (node.left != null) {
                        stack.push(node.left);
                    }
                    return node.value;
                }
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("ERROR: Remove not Supported by this " + 
                        "Iterator");
            }
        };
    }

    /**
     * This private method returns a post-order iterator
     * 
     * @return a post-order iterator for the BST
     */
    private Iterator<E> postOrderIterator() {
        final int expectedSize = size; // used to check for concurrent modification
        final Stack<Node<E>> stack1 = new Stack<>();
        final Stack<Node<E>> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node<E> node = stack1.pop();
            if (node != null) {
                stack2.push(node);
                if (node.left != null) {
                    stack1.push(node.left);
                }
                if (node.right != null) {
                    stack1.push(node.right);
                }
            }
        }

        return new Iterator<E>() {
            /**
             * {@inheritDoc} 
             */
            @Override
            public boolean hasNext() {
                return root != null && !stack2.isEmpty();
            }

            /**
             * {@inheritDoc}
             * 
             * @throws ConcurrentModificationException if size does not equal expectedSize
             */
            @Override
            public E next() {
                if (expectedSize != size) {
                    throw new ConcurrentModificationException("ERROR: Cannot modifiy the iterator");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("ERROR: No more elements to iterate");
                } else {
                    return stack2.pop().value;
                }
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("ERROR: Remove not Supported by this " + 
                        "Iterator");
            }
        };
    }

    /**
     * This private method returns a level order iterator
     * 
     * @return a level order iterator for the BST
     */
    private Iterator<E> levelOrderIterator() {
        final int expectedSize = size; // used to check for concurrent modification
        final Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        return new Iterator<E>() {
            /**
             * {@inheritDoc}
             */
            @Override
            public boolean hasNext() {
                return root != null && !queue.isEmpty();
            }

            /**
             * {@inheritDoc}
             * 
             * @throws ConcurrentModificationException if size does not equal expectedSize
             */
            @Override
            public E next() {
                if (expectedSize != size) {
                    throw new ConcurrentModificationException("ERROR: Cannot modifiy the iterator");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("ERROR: No more elements to iterate");
                } else {
                    Node<E> node = queue.poll();
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    return node.value;
                }
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("ERROR: Remove not Supported by this " + 
                        "Iterator");
            }
        };
    }

    /**
     * {@inheritDoc} 
     * Runtime: O(n)
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "null";
        } else {
            StringBuilder tree = new StringBuilder(size);
            Queue<Node<E>> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                Node<E> node = queue.poll();
                tree.append(node.value + ", ");

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            return tree.toString();
        }
    }
}