package test.tree;

import main.tree.Tree;
import main.tree.BinarySearchTree;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> empty; // an empty BST
    private BinarySearchTree<Integer> singleton; // a single element BST
    private BinarySearchTree<Integer> multipleElements;
    private BinarySearchTree<Integer> linearRight; // a right leaning BST
    private BinarySearchTree<Integer> linearLeft; // a left leaning BST
    private BinarySearchTree<Integer> complete; // complete but not full BST
    private BinarySearchTree<Integer> full; // a full but not complete BST
    private BinarySearchTree<Integer> perfect; // a complete and full BST
    
    @Before
    public void setupTestBinarySearchTree() {
        empty = new BinarySearchTree<>();

        singleton = new BinarySearchTree<>();
        singleton.insert(1);
        
        multipleElements = new BinarySearchTree<>();
        multipleElements.insert(5);
        multipleElements.insert(1);
        multipleElements.insert(7);
        multipleElements.insert(0);
        multipleElements.insert(3);
        multipleElements.insert(8);
        multipleElements.insert(9);
        
        linearRight = new BinarySearchTree<>();
        linearRight.insert(1);
        linearRight.insert(2);
        linearRight.insert(3);
        linearRight.insert(4);
        linearRight.insert(5);
        
        linearLeft = new BinarySearchTree<>();
        linearLeft.insert(5);
        linearLeft.insert(4);
        linearLeft.insert(3);
        linearLeft.insert(2);
        linearLeft.insert(1);
         
        complete = new BinarySearchTree<>();
        complete.insert(4);
        complete.insert(1);
        complete.insert(6);
        complete.insert(5);
        complete.insert(0);
        complete.insert(3);
        
        full = new BinarySearchTree<>();
        full.insert(1);
        full.insert(0);
        full.insert(3);
        full.insert(2);
        full.insert(5);
        full.insert(4);
        full.insert(6);
        
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
        assertEquals(7, perfect.size());
        assertEquals("5, 1, 8, 0, 3, 7, 9, ", perfect.toString());
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
    
    @Test
    public void testGetMinimumEmptyBST() {
        assertNull(empty.getMinimum());
        assertEquals(0, empty.size());
        assertEquals("null", empty.toString());
    }
    
    @Test
    public void testGetMinimumSingletonBST() {
        assertEquals(1, (int) singleton.getMinimum());
        assertEquals(1, singleton.size());
        assertEquals("1, ", singleton.toString());
    }
    
    @Test
    public void testGetMinimumNonEmptyBST() {
        assertEquals(0, (int) multipleElements.getMinimum());
        assertEquals(7, multipleElements.size());
        assertEquals("5, 1, 7, 0, 3, 8, 9, ", multipleElements.toString());
        
        assertEquals(0, (int) perfect.getMinimum());
        assertEquals(7, perfect.size());
        assertEquals("5, 1, 8, 0, 3, 7, 9, ", perfect.toString());
        
        assertEquals(1, (int) linearRight.getMinimum());
        assertEquals(5, linearRight.size());
        assertEquals("1, 2, 3, 4, 5, ", linearRight.toString());
        
        assertEquals(1, (int) linearLeft.getMinimum());
        assertEquals(5, linearLeft.size());
        assertEquals("5, 4, 3, 2, 1, ", linearLeft.toString());
    }
    
    @Test
    public void testGetMaximumEmptyBST() {
        assertNull(empty.getMaximum());
        assertEquals(0, empty.size());
        assertEquals("null", empty.toString());
    }
    
    @Test
    public void testGetMaximumSingletonBST() {
        assertEquals(1, (int) singleton.getMaximum());
        assertEquals(1, singleton.size());
        assertEquals("1, ", singleton.toString());
    }
    
    @Test
    public void testGetMaximumNonEmptyBST() {
        assertEquals(9, (int) multipleElements.getMaximum());
        assertEquals(7, multipleElements.size());
        assertEquals("5, 1, 7, 0, 3, 8, 9, ", multipleElements.toString());
        
        assertEquals(9, (int) perfect.getMaximum());
        assertEquals(7, perfect.size());
        assertEquals("5, 1, 8, 0, 3, 7, 9, ", perfect.toString());
        
        assertEquals(5, (int) linearRight.getMaximum());
        assertEquals(5, linearRight.size());
        assertEquals("1, 2, 3, 4, 5, ", linearRight.toString());
        
        assertEquals(5, (int) linearLeft.getMaximum());
        assertEquals(5, linearLeft.size());
        assertEquals("5, 4, 3, 2, 1, ", linearLeft.toString());
    }
    
    /*
    @Test
    public void testIsBalanced() {}
    */
    
    @Test
    public void testIsCompleteEmpty() {
        assertTrue(empty.isComplete());
        assertEquals(0, empty.size());
        assertEquals("null", empty.toString());
    }
    
    @Test
    public void testIsCompleteTrue() {
        assertTrue(perfect.isComplete());
        assertEquals(7, perfect.size());
        assertEquals("5, 1, 8, 0, 3, 7, 9, ", perfect.toString());
        
        assertTrue(complete.isComplete());
        assertEquals(6, complete.size());
        assertEquals("4, 1, 6, 0, 3, 5, ", complete.toString());
    }
    
    @Test
    public void testIsCompleteFalse() {
        assertFalse(multipleElements.isComplete());
        assertEquals(7, multipleElements.size());
        assertEquals("5, 1, 7, 0, 3, 8, 9, ", multipleElements.toString());
        
        assertFalse(linearRight.isComplete());
        assertEquals(5, linearRight.size());
        assertEquals("1, 2, 3, 4, 5, ", linearRight.toString());
        
        assertFalse(linearLeft.isComplete());
        assertEquals(5, linearLeft.size());
        assertEquals("5, 4, 3, 2, 1, ", linearLeft.toString());
        
        assertFalse(full.isComplete());
        assertEquals(7, full.size());
        assertEquals("1, 0, 3, 2, 5, 4, 6, ", full.toString());
    }
    
    @Test
    public void testIsFullEmpty() {
        assertTrue(empty.isFull());
        assertEquals(0, empty.size());
        assertEquals("null", empty.toString());
    }
    
    @Test
    public void testIsFullTrue() {
        assertTrue(perfect.isFull());
        assertEquals(7, perfect.size());
        assertEquals("5, 1, 8, 0, 3, 7, 9, ", perfect.toString());
        
        assertTrue(full.isFull());
        assertEquals(7, full.size());
        assertEquals("1, 0, 3, 2, 5, 4, 6, ", full.toString());
    }
    
    @Test
    public void testIsFullFalse() {
        assertFalse(multipleElements.isFull());
        assertEquals(7, multipleElements.size());
        assertEquals("5, 1, 7, 0, 3, 8, 9, ", multipleElements.toString());
        
        assertFalse(linearRight.isFull());
        assertEquals(5, linearRight.size());
        assertEquals("1, 2, 3, 4, 5, ", linearRight.toString());
        
        assertFalse(linearLeft.isFull());
        assertEquals(5, linearLeft.size());
        assertEquals("5, 4, 3, 2, 1, ", linearLeft.toString());
        
        assertFalse(complete.isFull());
        assertEquals(6, complete.size());
        assertEquals("4, 1, 6, 0, 3, 5, ", complete.toString());
    }
    
    @Test
    public void testIsPerfectEmpty() {
        assertTrue(empty.isPerfect());
        assertEquals(0, empty.size());
        assertEquals("null", empty.toString());
    }
    
    @Test
    public void testIsPerfectTrue() {
        assertTrue(perfect.isPerfect());
        assertEquals(7, perfect.size());
        assertEquals("5, 1, 8, 0, 3, 7, 9, ", perfect.toString());
    }
    
    @Test
    public void testIsPerfectFalse() {
        assertFalse(multipleElements.isPerfect());
        assertEquals(7, multipleElements.size());
        assertEquals("5, 1, 7, 0, 3, 8, 9, ", multipleElements.toString());
        
        assertFalse(linearRight.isPerfect());
        assertEquals(5, linearRight.size());
        assertEquals("1, 2, 3, 4, 5, ", linearRight.toString());
        
        assertFalse(linearLeft.isPerfect());
        assertEquals(5, linearLeft.size());
        assertEquals("5, 4, 3, 2, 1, ", linearLeft.toString());
        
        assertFalse(complete.isPerfect());
        assertEquals(6, complete.size());
        assertEquals("4, 1, 6, 0, 3, 5, ", complete.toString());
        
        assertFalse(full.isPerfect());
        assertEquals(7, full.size());
        assertEquals("1, 0, 3, 2, 5, 4, 6, ", full.toString());
    }
    
    @Test
    public void testInsertSingleton() {
        
    }
    
    @Test
    public void testInsertValueExists() {
        
    }
    
    @Test
    public void testDeleteEmpty() {
        
    }
    
    @Test
    public void testDeleteNonEmpty() {
        
    }
    
    @Test
    public void testInOrderEmpty() {
        List<Integer> expected = new LinkedList<>();
        assertEquals(expected, empty.inOrder());
    }
    @Test
    public void testInOrderNonEmpty() {
        List<Integer> expected0 = new LinkedList<>();
        expected0.add(1);
        assertEquals(expected0, singleton.inOrder());
        
        List<Integer> expected1 = new LinkedList<>();
        expected1.add(0);
        expected1.add(1);
        expected1.add(3);
        expected1.add(5);
        expected1.add(7);
        expected1.add(8);
        expected1.add(9);
        assertEquals(expected1, multipleElements.inOrder());
        
        List<Integer> expected2 = new LinkedList<>();
        expected2.add(1);
        expected2.add(2);
        expected2.add(3);
        expected2.add(4);
        expected2.add(5);
        assertEquals(expected2, linearRight.inOrder());
        
        assertEquals(expected2, linearLeft.inOrder());
        
        List<Integer> expected4 = new LinkedList<>();
        expected4.add(0);
        expected4.add(1);
        expected4.add(3);
        expected4.add(4); 
        expected4.add(5);
        expected4.add(6);
        assertEquals(expected4, complete.inOrder());
        
        List<Integer> expected5 = new LinkedList<>();
        expected5.add(0);
        expected5.add(1);
        expected5.add(2);
        expected5.add(3);
        expected5.add(4); 
        expected5.add(5);
        expected5.add(6);
        assertEquals(expected5, full.inOrder());
        
        assertEquals(expected1, perfect.inOrder());
    }
    
    
    @Test
    public void testPreOrderEmpty() {
        List<Integer> expected = new LinkedList<>();
        assertEquals(expected, empty.preOrder());
    }
    
    @Test
    public void testPreOrderNonEmpty() {
        List<Integer> expected0 = new LinkedList<>();
        expected0.add(1);
        assertEquals(expected0, singleton.preOrder());
        
        List<Integer> expected1 = new LinkedList<>();
        expected1.add(5);
        expected1.add(1);
        expected1.add(0);
        expected1.add(3);
        expected1.add(7);
        expected1.add(8);
        expected1.add(9);
        assertEquals(expected1, multipleElements.preOrder());
        
        List<Integer> expected2 = new LinkedList<>();
        expected2.add(1);
        expected2.add(2);
        expected2.add(3);
        expected2.add(4);
        expected2.add(5);
        assertEquals(expected2, linearRight.preOrder());
        
        List<Integer> expected3 = new LinkedList<>();
        expected3.add(5);
        expected3.add(4);
        expected3.add(3);
        expected3.add(2);
        expected3.add(1);
        assertEquals(expected3, linearLeft.preOrder());
        
        List<Integer> expected4 = new LinkedList<>();
        expected4.add(4);
        expected4.add(1);
        expected4.add(0);
        expected4.add(3);
        expected4.add(6);
        expected4.add(5);
        assertEquals(expected4, complete.preOrder());
        
        List<Integer> expected5 = new LinkedList<>();
        expected5.add(1);
        expected5.add(0);
        expected5.add(3);
        expected5.add(2);
        expected5.add(5);
        expected5.add(4);
        expected5.add(6);
        assertEquals(expected5, full.preOrder());
        
        List<Integer> expected6 = new LinkedList<>();
        expected6.add(5);
        expected6.add(1);
        expected6.add(0);
        expected6.add(3);
        expected6.add(8);
        expected6.add(7);
        expected6.add(9);
        assertEquals(expected6, perfect.preOrder());
    }
    
    @Test
    public void testPostOrderEmpty() {
        List<Integer> expected = new LinkedList<>();
        assertEquals(expected, empty.postOrder());
    }
    
    @Test
    public void testPostOrderNonEmpty() {
        List<Integer> expected0 = new LinkedList<>();
        expected0.add(1);
        assertEquals(expected0, singleton.postOrder());
        
        List<Integer> expected1 = new LinkedList<>();
        expected1.add(0);
        expected1.add(3);
        expected1.add(1);
        expected1.add(9);
        expected1.add(8);
        expected1.add(7);
        expected1.add(5);
        assertEquals(expected1, multipleElements.postOrder());
        
        List<Integer> expected2 = new LinkedList<>();
        expected2.add(5);
        expected2.add(4);
        expected2.add(3);
        expected2.add(2);
        expected2.add(1);
        assertEquals(expected2, linearRight.postOrder());
        
        List<Integer> expected3 = new LinkedList<>();
        expected3.add(1);
        expected3.add(2);
        expected3.add(3);
        expected3.add(4);
        expected3.add(5);
        assertEquals(expected3, linearLeft.postOrder());
        
        List<Integer> expected4 = new LinkedList<>();
        expected4.add(0);
        expected4.add(3);
        expected4.add(1);
        expected4.add(5);
        expected4.add(6);
        expected4.add(4);
        assertEquals(expected4, complete.postOrder());
        
        List<Integer> expected5 = new LinkedList<>();
        expected5.add(0);
        expected5.add(2);
        expected5.add(4);
        expected5.add(6);
        expected5.add(5);
        expected5.add(3);
        expected5.add(1);
        assertEquals(expected5, full.postOrder());
        
        List<Integer> expected6 = new LinkedList<>();
        expected6.add(0);
        expected6.add(3);
        expected6.add(1);
        expected6.add(7);
        expected6.add(9);
        expected6.add(8);
        expected6.add(5);
        assertEquals(expected6, perfect.postOrder());
    }
    
    @Test
    public void testLevelOrderEmpty() {
        List<Integer> expected = new LinkedList<>();
        assertEquals(expected, empty.levelOrder());
    }
    
    @Test
    public void testLevelOrderNonEmpty() {
        List<Integer> expected0 = new LinkedList<>();
        expected0.add(1);
        assertEquals(expected0, singleton.levelOrder());
        
        List<Integer> expected1 = new LinkedList<>();
        expected1.add(5);
        expected1.add(1);
        expected1.add(7);
        expected1.add(0);
        expected1.add(3);
        expected1.add(8);
        expected1.add(9);
        assertEquals(expected1, multipleElements.levelOrder());
        
        List<Integer> expected2 = new LinkedList<>();
        expected2.add(1);
        expected2.add(2);
        expected2.add(3);
        expected2.add(4);
        expected2.add(5);
        assertEquals(expected2, linearRight.levelOrder());
        
        List<Integer> expected3 = new LinkedList<>();
        expected3.add(5);
        expected3.add(4);
        expected3.add(3);
        expected3.add(2);
        expected3.add(1);
        assertEquals(expected3, linearLeft.levelOrder());
        
        List<Integer> expected4 = new LinkedList<>();
        expected4.add(4);
        expected4.add(1);
        expected4.add(6);
        expected4.add(0);
        expected4.add(3);
        expected4.add(5);
        assertEquals(expected4, complete.levelOrder());
        
        List<Integer> expected5 = new LinkedList<>();
        expected5.add(1);
        expected5.add(0);
        expected5.add(3);
        expected5.add(2);
        expected5.add(5);
        expected5.add(4);
        expected5.add(6);
        assertEquals(expected5, full.levelOrder());
        
        List<Integer> expected6 = new LinkedList<>();
        expected6.add(5);
        expected6.add(1);
        expected6.add(8);
        expected6.add(0);
        expected6.add(3);
        expected6.add(7);
        expected6.add(9);
        assertEquals(expected6, perfect.levelOrder());
    }
    
    @Test
    public void testToArrayEmpty() {
        Object[] expected = {};
        assertArrayEquals(expected, empty.toArray());
    }
    
    @Test
    public void testClear() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(1);
        bst.insert(3);
        bst.insert(2);
        bst.insert(8);
        bst.insert(6);
        bst.insert(9);
        bst.clear();
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        assertEquals("null", bst.toString());
    }
    
    @Test
    public void testToArrayNonEmpty() {
        Object[] expected0 = {1};
        assertArrayEquals(expected0, singleton.toArray());
        
        Object[] expected1 = {0, 1, 3, 5, 7, 8, 9};
        assertArrayEquals(expected1, multipleElements.toArray());
        
        Object[] expected2 = {1, 2, 3, 4, 5};
        assertArrayEquals(expected2, linearRight.toArray());
       
        assertArrayEquals(expected2, linearLeft.toArray());
        
        Object[] expected4 = {0, 1, 3, 4, 5, 6};
        assertArrayEquals(expected4, complete.toArray());
        
        Object[] expected5 = {0, 1, 2, 3, 4, 5, 6};
        assertArrayEquals(expected5, full.toArray());
        
        assertArrayEquals(expected1, perfect.toArray());
    }
       
    @Test(expected = NoSuchElementException.class)
    public void testEmptyInOrderIterator() {
        Iterator<Integer> bstInOrder = empty.iterator(Tree.IteratorType.IN_ORDER);
        bstInOrder.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testInOrderIteratorCallNextTooManyTimes() {
        Iterator<Integer> bstInOrder = linearRight.iterator(Tree.IteratorType.IN_ORDER);
        bstInOrder.next();
        bstInOrder.next();
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
        Iterator<Integer> bstInOrder = perfect.iterator(Tree.IteratorType.IN_ORDER);
        bstInOrder.remove();
    }
    
    @Test
    public void testToStringEmpty() {
        assertEquals("null", empty.toString());
    }
    
    @Test
    public void testToStringNonEmpty() {
        assertEquals("5, 1, 8, 0, 3, 7, 9, ", perfect.toString());
    }
}