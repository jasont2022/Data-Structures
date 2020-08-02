package test.list;

import org.junit.Test;

import main.list.List;
import main.list.DoublyLinkedList;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class DoublyLinkedListTest {
    @Test
    public void testConstructor() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertTrue(list.isEmpty());
        assertEquals("null", list.toString());
    }

    @Test
    public void testNonEmptyList() {
        List<Integer> list = new DoublyLinkedList<>();
        list.add(4);
        list.add(7);
        list.add(2);
        list.add(9);
        assertEquals(4, list.size());
        assertFalse(list.isEmpty());
        assertEquals("[4]<->[7]<->[2]<->[9]", list.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyIterator() {
        List<Integer> list = new DoublyLinkedList<>();
        Iterator<Integer> listIter = list.iterator();
        listIter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testInOrderIteratorCallNextTooManyTimes() {
        List<Integer> list = new DoublyLinkedList<>();
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
        List<Integer> list = new DoublyLinkedList<>();
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
        List<Integer> list = new DoublyLinkedList<>();
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