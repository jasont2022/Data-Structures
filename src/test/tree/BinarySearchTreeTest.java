package test.tree;

import main.tree.Tree;
import main.tree.BinarySearchTree;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class BinarySearchTreeTest {
    @Test
    public void testConstructor() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        assertTrue(bst.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyInOrderIterator() {
        Tree<Integer> bst = new BinarySearchTree<>();
        Iterator<Integer> bstInOrder = bst.iterator(Tree.IteratorType.IN_ORDER);
        bstInOrder.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testInOrderIteratorCallNextTooManyTimes() {
        Tree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(1);
        bst.insert(7);
        Iterator<Integer> bstInOrder = bst.iterator(Tree.IteratorType.IN_ORDER);
        bstInOrder.next();
        bstInOrder.next();
        bstInOrder.next();
        bstInOrder.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testInOrderIteratorConcurrentModificationException() {
        Tree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(1);
        bst.insert(7);
        Iterator<Integer> bstInOrder = bst.iterator(Tree.IteratorType.IN_ORDER);
        bstInOrder.next();
        bst.insert(3);
        bstInOrder.next();
    }

    @Test
    public void testInOrderIterator() {
        Tree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(1);
        bst.insert(7);
        bst.insert(0);
        bst.insert(3);
        bst.insert(9);
        bst.insert(8);
        Iterator<Integer> bstInOrder = bst.iterator(Tree.IteratorType.IN_ORDER);
        assertTrue(bstInOrder.hasNext());
        assertEquals(0, (int) bstInOrder.next());
        assertTrue(bstInOrder.hasNext());
        assertEquals(1, (int) bstInOrder.next());
        assertTrue(bstInOrder.hasNext());
        assertEquals(3, (int) bstInOrder.next());
        assertTrue(bstInOrder.hasNext());
        assertEquals(5, (int) bstInOrder.next());
        assertTrue(bstInOrder.hasNext());
        assertEquals(7, (int) bstInOrder.next());
        assertTrue(bstInOrder.hasNext());
        assertEquals(8, (int) bstInOrder.next());
        assertTrue(bstInOrder.hasNext());
        assertEquals(9, (int) bstInOrder.next());
        assertFalse(bstInOrder.hasNext());
    }
}