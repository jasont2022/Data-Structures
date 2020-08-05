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
    private BinarySearchTree<Integer> empty; 
    private BinarySearchTree<Integer> singleton;
    private BinarySearchTree<Integer> threeElements; 
    private BinarySearchTree<Integer> multipleElements;
    private BinarySearchTree<Integer> perfect;
    
    @Before
    public void setupTestStacks() {
        empty = new BinarySearchTree<>();

        singleton = new BinarySearchTree<>();
        singleton.insert(1);
        
        threeElements = new BinarySearchTree<>();
        threeElements.insert(5);
        threeElements.insert(1);
        threeElements.insert(3);
        
        multipleElements = new BinarySearchTree<>();
        multipleElements.insert(5);
        multipleElements.insert(1);
        multipleElements.insert(7);
        multipleElements.insert(0);
        multipleElements.insert(3);
        multipleElements.insert(8);
        multipleElements.insert(9);
        
        perfect = new BinarySearchTree<>();
        perfect.insert(5);
        perfect.insert(1);
        perfect.insert(0);
        perfect.insert(3);
        perfect.insert(8);
        perfect.insert(7);
        perfect.insert(9);
    }
    
    @Test
    public void testConstructor() {
        assertEquals(0, empty.size());
        assertNull(empty.getRoot());
        assertTrue(empty.isEmpty());
        assertEquals("null", empty.toString());
    }
    
    @Test
    public void testEmptyBSTSize() {
        assertEquals(0, empty.size());
        assertEquals("null", empty.toString());
    }

    @Test
    public void testNonEmptyBSTSize() {
        assertEquals(3, threeElements.size());
        assertEquals("5, 1, 3, ", threeElements.toString());
    }
    
    @Test
    public void testIsEmptyTrue() {
        assertTrue(empty.isEmpty());
        assertEquals(0, empty.size());
        assertEquals("null", empty.toString());
    }

    @Test
    public void testIsEmptyFalse() {
        assertFalse(singleton.isEmpty());
        assertEquals(1, singleton.size());
        assertEquals("1, ", singleton.toString());
    }
    
    @Test
    public void testHeightEmpty() {
        assertEquals(0, empty.height());
        assertEquals(0, empty.size());
        assertEquals("null", empty.toString());
    }
    
    @Test
    public void testHeightSingleton() {
        assertEquals(1, singleton.height());
        assertEquals(1, singleton.size());
        assertEquals("1, ", singleton.toString());
    }
    
    @Test
    public void testEqualHeightNonEmpty() {
        assertEquals(3, perfect.height());
        assertEquals(7, perfect.size());
        assertEquals("5, 1, 8, 0, 3, 7, 9, ", perfect.toString());
    }
    
    @Test
    public void testHeightNonEmpty() {
        assertEquals(4, multipleElements.height());
        assertEquals(7, multipleElements.size());
        assertEquals("5, 1, 7, 0, 3, 8, 9, ", multipleElements.toString());
    }
    
    @Test
    public void testContainsEmpty() {
        assertFalse(empty.contains(1));
        assertEquals(0, empty.size());
        assertEquals("null", empty.toString());
    }
    
    @Test
    public void testContainsFalse() {
        assertFalse(multipleElements.contains(4));
        assertEquals(7, multipleElements.size());
        assertEquals("5, 1, 7, 0, 3, 8, 9, ", multipleElements.toString());
    }
    
    @Test
    public void testContainsTrue() {
        assertTrue(perfect.contains(7));
        assertEquals(7, perfect.size());
        assertEquals("5, 1, 8, 0, 3, 7, 9, ", perfect.toString());
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
        Iterator<Integer> bstInOrder = multipleElements.iterator(Tree.IteratorType.IN_ORDER);
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
    
    @Test (expected = UnsupportedOperationException.class)
    public void testInOrderIteratorRemove() {
        Iterator<Integer> bstInOrder = threeElements.iterator(Tree.IteratorType.IN_ORDER);
        bstInOrder.remove();
        
    }
}