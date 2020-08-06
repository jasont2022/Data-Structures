package test.list;

import main.list.List;
import main.list.DoublyLinkedList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class DoublyLinkedListTest {
  private DoublyLinkedList<Integer> empty;
  private DoublyLinkedList<Integer> singleton;
  private DoublyLinkedList<Integer> threeElements;
  private DoublyLinkedList<Integer> hasNullElements;

  @Before
  public void setUpTestLinkedLists() {
    empty = new DoublyLinkedList<>();

    singleton = new DoublyLinkedList<>();
    singleton.add(1);

    threeElements = new DoublyLinkedList<>();
    threeElements.add(3);
    threeElements.add(2);
    threeElements.add(8);

    hasNullElements = new DoublyLinkedList<>();
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
    assertEquals("[3]<->[2]<->[8]", threeElements.toString());
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
    assertEquals("[3]<->[2]<->[8]", threeElements.toString());
  }

  @Test
  public void testContainsElementPresent() {
    assertTrue(threeElements.contains(2));
    assertEquals(3, threeElements.size());
    assertEquals("[3]<->[2]<->[8]", threeElements.toString());
  }

  @Test
  public void testIndexOfNullElement() {
    assertEquals(1, hasNullElements.indexOf(null));
    assertEquals(6, hasNullElements.size());
    assertEquals("[1]<->[null]<->[4]<->[null]<->[3]<->[4]", hasNullElements.toString());
  }

  @Test
  public void testIndexOfNonNullElement() {
    assertEquals(1, threeElements.indexOf(2));
    assertEquals(3, threeElements.size());
    assertEquals("[3]<->[2]<->[8]", threeElements.toString());
  }

  @Test
  public void testIndexOfElementNotPresent() {
    assertEquals(-1, threeElements.indexOf(4));
    assertEquals(3, threeElements.size());
    assertEquals("[3]<->[2]<->[8]", threeElements.toString());
  }

  @Test
  public void testLastIndexOfNullElement() {
    assertEquals(3, hasNullElements.lastIndexOf(null));
    assertEquals(6, hasNullElements.size());
    assertEquals("[1]<->[null]<->[4]<->[null]<->[3]<->[4]", hasNullElements.toString());
  }

  @Test
  public void testLastIndexOfNonNullElement() {
    assertEquals(5, hasNullElements.lastIndexOf(4));
    assertEquals(6, hasNullElements.size());
    assertEquals("[1]<->[null]<->[4]<->[null]<->[3]<->[4]", hasNullElements.toString());
  }

  @Test
  public void testLastIndexOfElementNotPresent() {
    assertEquals(-1, threeElements.lastIndexOf(4));
    assertEquals(3, threeElements.size());
    assertEquals("[3]<->[2]<->[8]", threeElements.toString());
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
    assertEquals(3, (int) threeElements.get(0));
    assertEquals(2, (int) threeElements.get(1));
    assertEquals(8, (int) threeElements.get(2));
    assertEquals(3, threeElements.size());
    assertEquals("[3]<->[2]<->[8]", threeElements.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNegativeIndex() {
    singleton.set(-1, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetOutOfBoundsEqualsSize() {
    threeElements.set(3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetOutOfBoundsPositiveIndex() {
    threeElements.set(5, 7);
  }

  @Test
  public void testSetElements() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(2);
    list.add(7);
    list.set(0, 2);
    list.set(3, 3);
    assertEquals(5, list.size());
    assertEquals("[2]<->[4]<->[5]<->[3]<->[7]", list.toString());
  }

  @Test
  public void testAddCreateSingleton() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.add(1);
    assertEquals(1, list.size());
    assertFalse(list.isEmpty());
    assertEquals("[1]", list.toString());
  }

  @Test
  public void testAddNonSingleton() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(2);
    list.add(7);
    assertEquals(5, list.size());
    assertFalse(list.isEmpty());
    assertEquals("[3]<->[4]<->[5]<->[2]<->[7]", list.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddAtNegativeIndex() {
    threeElements.add(-2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddAtIndexGreaterThanSize() {
    threeElements.add(5, 2);
  }

  @Test
  public void testAddAtZeroIndex() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(2);
    list.add(7);
    list.add(0, 1);
    assertEquals(6, list.size());
    assertEquals("[1]<->[3]<->[4]<->[5]<->[2]<->[7]", list.toString());
  }

  @Test
  public void testAddAtIndexEqaulsToSize() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(2);
    list.add(7);
    list.add(5, 1);
    assertEquals(6, list.size());
    assertEquals("[3]<->[4]<->[5]<->[2]<->[7]<->[1]", list.toString());
  }

  @Test
  public void testAddAtMiddleIndex() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(2);
    list.add(7);
    list.add(3, 1);
    assertEquals(6, list.size());
    assertEquals("[3]<->[4]<->[5]<->[1]<->[2]<->[7]", list.toString());
  }

  @Test
  public void testAddFirstCreateSingleton() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.addFirst(1);
    assertEquals(1, list.size());
    assertEquals("[1]", list.toString());
  }

  @Test
  public void testAddFirstNonSingleton() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.addFirst(3);
    list.addFirst(4);
    list.addFirst(5);
    list.addFirst(2);
    list.addFirst(7);
    assertEquals(5, list.size());
    assertEquals("[7]<->[2]<->[5]<->[4]<->[3]", list.toString());
  }

  @Test
  public void testAddLastCreateSingleton() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.addLast(1);
    assertEquals(1, list.size());
    assertEquals("[1]", list.toString());
  }

  @Test
  public void testAddLastNonSingleton() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.addLast(3);
    list.addLast(4);
    list.addLast(5);
    list.addLast(2);
    list.addLast(7);
    assertEquals(5, list.size());
    assertEquals("[3]<->[4]<->[5]<->[2]<->[7]", list.toString());
  }

  @Test
  public void testPushCreateSingleton() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.push(1);
    assertEquals(1, list.size());
    assertEquals("[1]", list.toString());
  }

  @Test
  public void testPushNonSingleton() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.push(3);
    list.push(4);
    list.push(5);
    list.push(2);
    list.push(7);
    assertEquals(5, list.size());
    assertEquals("[7]<->[2]<->[5]<->[4]<->[3]", list.toString());
  }

  @Test
  public void testOfferCreateSingleton() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.offer(1);
    assertEquals(1, list.size());
    assertEquals("[1]", list.toString());
  }

  @Test
  public void testOfferNonSingleton() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.offer(3);
    list.offer(4);
    list.offer(5);
    list.offer(2);
    list.offer(7);
    assertEquals(5, list.size());
    assertEquals("[7]<->[2]<->[5]<->[4]<->[3]", list.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testPeekEmptyList() {
    empty.peek();
  }

  @Test
  public void testPeekNonEmptyList() {
    assertEquals(3, (int) threeElements.peek());
    assertEquals(3, threeElements.size());
    assertEquals("[3]<->[2]<->[8]", threeElements.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testPeekFirstEmptyList() {
    empty.peekFirst();
  }

  @Test
  public void testPeekFirstNonEmptyList() {
    assertEquals(3, (int) threeElements.peekFirst());
    assertEquals(3, threeElements.size());
    assertEquals("[3]<->[2]<->[8]", threeElements.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testPeekLastEmptyList() {
    empty.peekLast();
  }

  @Test
  public void testPeekLastNonEmptyList() {
    assertEquals(8, (int) threeElements.peekLast());
    assertEquals(3, threeElements.size());
    assertEquals("[3]<->[2]<->[8]", threeElements.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testElementEmptyList() {
    empty.element();
  }

  @Test
  public void testElementNonEmptyList() {
    assertEquals(3, (int) threeElements.element());
    assertEquals(3, threeElements.size());
    assertEquals("[3]<->[2]<->[8]", threeElements.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveEmptyList() {
    empty.remove();
  }

  @Test
  public void testRemoveNonEmptyList() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.addLast(3);
    list.addLast(4);
    list.addLast(5);
    list.addLast(2);
    list.addLast(7);
    assertEquals(3, (int) list.remove());
    assertEquals(4, list.size());
    assertEquals("[4]<->[5]<->[2]<->[7]", list.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveAtEmptyList() {
    empty.remove(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveAtNegativeIndex() {
    threeElements.remove(-2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveAtIndexEqualToSize() {
    threeElements.remove(3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveAtIndexGreaterThanSize() {
    threeElements.remove(5);
  }

  @Test
  public void testRemoveAtZeroIndex() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(2);
    list.add(7);
    list.remove(0);
    assertEquals(4, list.size());
    assertEquals("[4]<->[5]<->[2]<->[7]", list.toString());
  }

  @Test
  public void testRemoveAtLastIndex() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(2);
    list.add(7);
    list.remove(4);
    assertEquals(4, list.size());
    assertEquals("[3]<->[4]<->[5]<->[2]", list.toString());
  }

  @Test
  public void testRemoveAtMiddleIndex() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(2);
    list.add(7);
    list.remove(2);
    assertEquals(4, list.size());
    assertEquals("[3]<->[4]<->[2]<->[7]", list.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveObjectFromEmptyList() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.remove("a");
  }

  @Test
  public void testRemoveObjectNullElement() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add("bar");
    assertTrue(list.remove(null));
    assertEquals("a", list.peekFirst());
    assertEquals("bar", list.peekLast());
    assertEquals(3, list.size());
    assertEquals("[a]<->[foo]<->[bar]", list.toString());
  }

  @Test
  public void testRemoveObjectNonNullElement() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add("bar");
    assertTrue(list.remove("foo"));
    assertEquals(3, list.size());
    assertEquals("a", list.peekFirst());
    assertEquals("bar", list.peekLast());
    assertEquals("[a]<->[null]<->[bar]", list.toString());
  }

  @Test
  public void testRemoveObjectElementNotPresent() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add("bar");
    assertFalse(list.remove("jay"));
    assertEquals("a", list.peekFirst());
    assertEquals("bar", list.peekLast());
    assertEquals(4, list.size());
    assertEquals("[a]<->[null]<->[foo]<->[bar]", list.toString());
  }

  @Test
  public void testRemoveFirstOccurrenceElementPresent() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add("a");
    list.add("bar");
    assertTrue(list.remove("a"));
    assertNull(list.peekFirst());
    assertEquals("bar", list.peekLast());
    assertEquals(4, list.size());
    assertEquals("[null]<->[foo]<->[a]<->[bar]", list.toString());
  }

  @Test
  public void testRemoveFirstOccurrenceNullElementPresent() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add("a");
    list.add("bar");
    list.add(null);
    assertTrue(list.remove(null));
    assertEquals("a", list.peekFirst());
    assertNull(list.peekLast());
    assertEquals(5, list.size());
    assertEquals("[a]<->[foo]<->[a]<->[bar]<->[null]", list.toString());
  }

  @Test
  public void testRemoveFirstOccurrenceElementNotPresent() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add(null);
    list.add("bar");
    assertFalse(list.remove("jay"));
    assertEquals("a", list.peekFirst());
    assertEquals("bar", list.peekLast());
    assertEquals(5, list.size());
    assertEquals("[a]<->[null]<->[foo]<->[null]<->[bar]", list.toString());
  }

  @Test
  public void testRemoveLastOccurrenceElementPresent() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add("a");
    list.add("bar");
    assertTrue(list.removeLastOccurrence("a"));
    assertEquals("a", list.peekFirst());
    assertEquals("bar", list.peekLast());
    assertEquals(4, list.size());
    assertEquals("[a]<->[null]<->[foo]<->[bar]", list.toString());
  }

  @Test
  public void testRemoveLastOccurrenceNullElementPresent() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add("a");
    list.add("bar");
    list.add(null);
    assertTrue(list.removeLastOccurrence(null));
    assertEquals("a", list.peekFirst());
    assertEquals("bar", list.peekLast());
    assertEquals(5, list.size());
    assertEquals("[a]<->[null]<->[foo]<->[a]<->[bar]", list.toString());
  }

  @Test
  public void testRemoveLastOccurrenceElementNotPresent() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add(null);
    list.add("bar");
    assertFalse(list.removeLastOccurrence("jay"));
    assertEquals("a", list.peekFirst());
    assertEquals("bar", list.peekLast());
    assertEquals(5, list.size());
    assertEquals("[a]<->[null]<->[foo]<->[null]<->[bar]", list.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveFirstEmptyList() {
    empty.removeFirst();
  }

  @Test
  public void testRemoveFirstNonEmptyList() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add("a");
    list.add("bar");
    list.add(null);
    assertEquals("a", list.removeFirst());
    assertNull(list.peekFirst());
    assertNull(list.peekLast());
    assertEquals(5, list.size());
    assertEquals("[null]<->[foo]<->[a]<->[bar]<->[null]", list.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveLastEmptyList() {
    empty.removeLast();
  }

  @Test
  public void testRemoveLastNonEmptyList() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add("a");
    list.add("bar");
    list.add(null);
    assertNull(list.removeLast());
    assertEquals("a", list.peekFirst());
    assertEquals("bar", list.peekLast());
    assertEquals(5, list.size());
    assertEquals("[a]<->[null]<->[foo]<->[a]<->[bar]", list.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testPopEmptyList() {
    empty.pop();
  }

  @Test
  public void testPopNonEmptyList() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add("a");
    list.add("bar");
    list.add(null);
    assertEquals("a", list.pop());
    assertNull(list.peekFirst());
    assertNull(list.peekLast());
    assertEquals(5, list.size());
    assertEquals("[null]<->[foo]<->[a]<->[bar]<->[null]", list.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testPollEmptyList() {
    empty.poll();
  }

  @Test
  public void testPollNonEmptyList() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add("a");
    list.add("bar");
    list.add(null);
    assertEquals("a", list.poll());
    assertNull(list.peekFirst());
    assertNull(list.peekLast());
    assertEquals(5, list.size());
    assertEquals("[null]<->[foo]<->[a]<->[bar]<->[null]", list.toString());
  }

  @Test
  public void testClear() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    list.add("a");
    list.add(null);
    list.add("foo");
    list.add("a");
    list.add("bar");
    list.add(null);
    list.clear();
    assertEquals(0, list.size());
    assertEquals("null", list.toString());
  }

  @Test
  public void testToArray() {
    Object[] array = {1, null, 4, null, 3, 4};
    assertArrayEquals(array, hasNullElements.toArray());
    assertEquals(6, hasNullElements.size());
    assertEquals("[1]<->[null]<->[4]<->[null]<->[3]<->[4]", hasNullElements.toString());
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

  @Test(expected = UnsupportedOperationException.class)
  public void testIteratorRemove() {
    Iterator<Integer> listIter = hasNullElements.iterator();
    listIter.remove();
  }

  @Test
  public void testToStringEmpty() {
    assertEquals(0, empty.size());
    assertEquals("null", empty.toString());
  }

  @Test
  public void testToStringNonEmptyList() {
    assertEquals(3, threeElements.size());
    assertEquals("[3]<->[2]<->[8]", threeElements.toString());
  }

  @Test
  public void testToStringNullList() {
    assertEquals(6, hasNullElements.size());
    assertEquals("[1]<->[null]<->[4]<->[null]<->[3]<->[4]", hasNullElements.toString());
  }
}
