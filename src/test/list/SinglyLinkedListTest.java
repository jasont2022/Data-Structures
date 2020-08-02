package test.list;

import main.list.List;
import main.list.SinglyLinkedList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class SinglyLinkedListTest {
    private SinglyLinkedList<Integer> empty;
    private SinglyLinkedList<Integer> singleton;
    private SinglyLinkedList<Integer> threeElements;
    private SinglyLinkedList<Integer> hasNullElements;
    
    @Before
    public void setUpTestLinkedLists() {
        empty = new SinglyLinkedList<>();
        
        singleton = new SinglyLinkedList<>();
        singleton.add(1);
        
        threeElements = new SinglyLinkedList<>();
        threeElements.add(3);
        threeElements.add(2);
        threeElements.add(8);
        
        hasNullElements = new SinglyLinkedList<>();
        hasNullElements.add(1);
        hasNullElements.add(null);
        hasNullElements.add(4);
        hasNullElements.add(null);
        hasNullElements.add(3);
        hasNullElements.add(4);
    }
    
    @Test
    public void testConstructor() {
        assertEquals(0, empty.size());
        assertNull(empty.getHead());
        assertNull(empty.getTail());
        assertTrue(empty.isEmpty());
        assertEquals("null", empty.toString());
    }
    
    @Test
    public void testEmptyListSize() {
        assertEquals(0, empty.size());
        assertEquals("null", empty.toString());
    }
    
    @Test
    public void testNonEmptyListSize() {
        assertEquals(3, threeElements.size());
        assertEquals("[3]->[2]->[8]", threeElements.toString());
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
        assertEquals("[1]", singleton.toString());
    }
    
    @Test
    public void testContainsElementNotPresent() {
        assertFalse(threeElements.contains(4));
        assertEquals(3, threeElements.size());
        assertEquals("[3]->[2]->[8]", threeElements.toString());
    }

    @Test
    public void testContainsElementPresent() {
        assertTrue(threeElements.contains(2));
        assertEquals(3, threeElements.size());
        assertEquals("[3]->[2]->[8]", threeElements.toString());
    }
    
    @Test
    public void testIndexOfNullElement() {
        assertEquals(1, hasNullElements.indexOf(null));
        assertEquals(6, hasNullElements.size());
        assertEquals("[1]->[null]->[4]->[null]->[3]->[4]", hasNullElements.toString());
    }
    
    @Test
    public void testIndexOfNonNullElement() {
        assertEquals(1, threeElements.indexOf(2));
        assertEquals(3, threeElements.size());
        assertEquals("[3]->[2]->[8]", threeElements.toString());
    }
    
    @Test
    public void testIndexOfElementNotPresent() {
        assertEquals(-1, threeElements.indexOf(4));
        assertEquals(3, threeElements.size());
        assertEquals("[3]->[2]->[8]", threeElements.toString());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetNegativeIndex() {
        singleton.get(-2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetOutOfBoundsEqualsSize() {
        threeElements.get(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetOutOfBoundsPositiveIndex() {
        threeElements.get(5);
    }

    @Test
    public void testGetIndecies() {
        assertEquals(5, (int) threeElements.get(0));
        assertEquals(1, (int) threeElements.get(1));
        assertEquals(3, (int) threeElements.get(2));
        assertEquals(3,threeElements.size());
        assertEquals("[5, 1, 3]",threeElements.toString());
    }


    @Test(expected = NoSuchElementException.class)
    public void testEmptyIterator() {
        List<Integer> list = new SinglyLinkedList<>();
        Iterator<Integer> listIter = list.iterator();
        listIter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testInOrderIteratorCallNextTooManyTimes() {
        List<Integer> list = new SinglyLinkedList<>();
        list.add(4);
        list.add(7);
        list.add(2);
        Iterator<Integer> listIter = list.iterator();
        listIter.next();
        listIter.next();
        listIter.next();
        listIter.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testInOrderIteratorConcurrentModificationException() {
        List<Integer> list = new SinglyLinkedList<>();
        list.add(4);
        list.add(7);
        list.add(2);
        Iterator<Integer> listIter = list.iterator();
        listIter.next();
        list.add(9);
        listIter.next();
    }

    @Test
    public void testIterator() {
        List<Integer> list = new SinglyLinkedList<>();
        list.add(4);
        list.add(7);
        list.add(2);
        list.add(9);
        Iterator<Integer> listIter = list.iterator();
        assertTrue(listIter.hasNext());
        assertEquals(4, (int) listIter.next());
        assertTrue(listIter.hasNext());
        assertEquals(7, (int) listIter.next());
        assertTrue(listIter.hasNext());
        assertEquals(2, (int) listIter.next());
        assertTrue(listIter.hasNext());
        assertEquals(9, (int) listIter.next());
        assertFalse(listIter.hasNext());
    }
}