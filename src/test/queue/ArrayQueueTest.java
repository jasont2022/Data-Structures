package test.queue;

import main.queue.Queue;
import main.queue.ArrayQueue;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class ArrayQueueTest {
  private ArrayQueue<Integer> empty; // an empty stack
  private ArrayQueue<Integer> singleton; // an single element stack
  private ArrayQueue<Integer> threeElements; // an stack with three elements
  private ArrayQueue<Integer> hasNullElements; // an stack with null keys

  @Before
  public void setupTestStacks() {
    empty = new ArrayQueue<>();

    singleton = new ArrayQueue<>();
    singleton.add(1);

    threeElements = new ArrayQueue<>();
    threeElements.add(5);
    threeElements.add(1);
    threeElements.add(3);

    hasNullElements = new ArrayQueue<>();
    hasNullElements.add(1);
    hasNullElements.add(3);
    hasNullElements.add(null);
    hasNullElements.add(3);
    hasNullElements.add(null);
  }

  @Test
  public void testConstructor() {
    assertEquals(2, ((Object[]) empty.getArray()).length);
    assertEquals(-1, empty.getHead());
    assertEquals(-1, empty.getTail());
    assertEquals(0, empty.size());
    assertTrue(empty.isEmpty());
    assertEquals("[]", empty.toString());
  }

  @Test
  public void testEmptyQueueSize() {
    assertEquals(0, empty.size());
    assertEquals("[]", empty.toString());
  }

  @Test
  public void testNonEmptyQueueSize() {
    assertEquals(3, threeElements.size());
    assertEquals("[5, 1, 3]", threeElements.toString());
  }

  @Test
  public void testIsEmptyTrue() {
    assertTrue(empty.isEmpty());
    assertEquals(0, empty.size());
    assertEquals("[]", empty.toString());
  }

  @Test
  public void testIsEmptyFalse() {
    assertFalse(singleton.isEmpty());
    assertEquals(1, singleton.size());
    assertEquals("[1]", singleton.toString());
  }

  @Test
  public void testAddSingleton() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(1, queue.size());
    assertEquals(0, queue.getHead());
    assertEquals(0, queue.getTail());
    assertEquals("[1]", queue.toString());
  }

  @Test
  public void testAddNonEmptyQueue() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(2);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(2, queue.size());
    assertEquals(0, queue.getHead());
    assertEquals(1, queue.getTail());
    assertEquals("[1, 2]", queue.toString());
  }

  @Test
  public void testAddResizeQueue() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(7);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(0, queue.getHead());
    assertEquals(1, queue.getTail());
    assertEquals(2, queue.size());
    assertFalse(queue.isEmpty());
    assertEquals("[1, 7]", queue.toString());
    queue.add(8);
    queue.add(4);
    assertEquals(4, ((Object[]) queue.getArray()).length);
    assertEquals(0, queue.getHead());
    assertEquals(3, queue.getTail());
    assertEquals(4, queue.size());
    assertFalse(queue.isEmpty());
    assertEquals("[1, 7, 8, 4]", queue.toString());
  }

  @Test
  public void testOfferSingleton() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.offer(1);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(1, queue.size());
    assertEquals(0, queue.getHead());
    assertEquals(0, queue.getTail());
    assertEquals("[1]", queue.toString());
  }

  @Test
  public void testOfferNonEmptyQueue() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.offer(1);
    queue.offer(2);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(2, queue.size());
    assertEquals(0, queue.getHead());
    assertEquals(1, queue.getTail());
    assertEquals("[1, 2]", queue.toString());
  }

  @Test
  public void testOfferResizeQueue() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.offer(1);
    queue.offer(7);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(0, queue.getHead());
    assertEquals(1, queue.getTail());
    assertEquals(2, queue.size());
    assertFalse(queue.isEmpty());
    assertEquals("[1, 7]", queue.toString());
    queue.offer(8);
    queue.offer(4);
    assertEquals(4, ((Object[]) queue.getArray()).length);
    assertEquals(0, queue.getHead());
    assertEquals(3, queue.getTail());
    assertEquals(4, queue.size());
    assertFalse(queue.isEmpty());
    assertEquals("[1, 7, 8, 4]", queue.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testElementEmptyQueue() {
    empty.element();
  }

  @Test
  public void testElementNonEmptyQueue() {
    assertEquals(5, (int) threeElements.element());
    assertEquals(3, threeElements.size());
    assertEquals(0, threeElements.getHead());
    assertEquals(2, threeElements.getTail());
    assertEquals("[5, 1, 3]", threeElements.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testPeekEmptyQueue() {
    empty.peek();
  }

  @Test
  public void testPeekNonEmptyQueue() {
    assertEquals(5, (int) threeElements.peek());
    assertEquals(3, threeElements.size());
    assertEquals(0, threeElements.getHead());
    assertEquals(2, threeElements.getTail());
    assertEquals("[5, 1, 3]", threeElements.toString());
  }

  @Test
  public void testPollEmptyQueue() {
    assertEquals(-1, empty.getHead());
    assertEquals(-1, empty.getTail());
    assertEquals(0, empty.size());
    assertTrue(empty.isEmpty());
    assertNull(empty.poll());
    assertEquals("[]", empty.toString());
  }

  @Test
  public void testPollSingleton() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    assertEquals(1, (int) queue.poll());
    assertEquals(-1, queue.getHead());
    assertEquals(-1, queue.getTail());
    assertEquals(0, queue.size());
    assertEquals("[]", queue.toString());
  }

  @Test
  public void testPollNonEmptyQueue() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);
    assertEquals(1, (int) queue.poll());
    assertEquals(2, (int) queue.poll());
    assertEquals(4, ((Object[]) queue.getArray()).length);
    assertEquals(2, queue.getHead());
    assertEquals(3, queue.getTail());
    assertEquals("[3, 4]", queue.toString());
  }

  @Test
  public void testPollResizeQueue() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);
    assertEquals(1, (int) queue.poll());
    assertEquals(2, (int) queue.poll());
    assertEquals(3, (int) queue.poll());
    assertEquals(4, (int) queue.poll());
    assertEquals(0, queue.getHead());
    assertEquals(-1, queue.getTail());
    assertEquals(0, queue.size());
    assertEquals("[]", queue.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveEmptyQueue() {
    empty.remove();
  }

  @Test
  public void testRemoveSingleton() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    assertEquals(1, (int) queue.remove());
    assertEquals(-1, queue.getHead());
    assertEquals(-1, queue.getTail());
    assertEquals(0, queue.size());
    assertEquals("[]", queue.toString());
  }

  @Test
  public void testRemoveNonEmptyQueue() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);
    assertEquals(1, (int) queue.remove());
    assertEquals(2, (int) queue.remove());
    assertEquals(4, ((Object[]) queue.getArray()).length);
    assertEquals(2, queue.getHead());
    assertEquals(3, queue.getTail());
    assertEquals("[3, 4]", queue.toString());
  }

  @Test
  public void testRemoveResizeQueue() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);
    assertEquals(1, (int) queue.remove());
    assertEquals(2, (int) queue.remove());
    assertEquals(3, (int) queue.remove());
    assertEquals(4, (int) queue.remove());
    assertEquals(0, queue.getHead());
    assertEquals(-1, queue.getTail());
    assertEquals(0, queue.size());
    assertEquals("[]", queue.toString());
  }

  @Test
  public void testAddAndPollSingleton() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(0, queue.getHead());
    assertEquals(0, queue.getTail());
    assertEquals(1, queue.size());
    assertEquals("[1]", queue.toString());
    assertEquals(1, (int) queue.remove());
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(-1, queue.getHead());
    assertEquals(-1, queue.getTail());
    assertEquals(0, queue.size());
    assertEquals("[]", queue.toString());
    queue.add(1);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(0, queue.getHead());
    assertEquals(0, queue.getTail());
    assertEquals(1, queue.size());
    assertEquals("[1]", queue.toString());
    queue.add(2);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(2, queue.size());
    assertEquals(0, queue.getHead());
    assertEquals(1, queue.getTail());
    assertEquals("[1, 2]", queue.toString());
  }

  @Test
  public void testAddWrapAroundAndResize() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);
    assertEquals(4, queue.size());
    assertEquals(0, queue.getHead());
    assertEquals(3, queue.getTail());
    assertEquals("[1, 2, 3, 4]", queue.toString());
    assertEquals(1, (int) queue.poll());
    assertEquals(2, (int) queue.poll());
    assertEquals(2, queue.size());
    assertEquals(2, queue.getHead());
    assertEquals(3, queue.getTail());
    assertEquals("[3, 4]", queue.toString());
    queue.add(5);
    queue.add(6);
    assertEquals(4, queue.size());
    assertEquals(2, queue.getHead());
    assertEquals(1, queue.getTail());
    assertEquals("[3, 4, 5, 6]", queue.toString());
    queue.add(7);
    assertEquals(8, ((Object[]) queue.getArray()).length);
    assertEquals(0, queue.getHead());
    assertEquals(4, queue.getTail());
    assertEquals(5, queue.size());
    assertEquals("[3, 4, 5, 6, 7]", queue.toString());
  }

  @Test
  public void testMultipleAddAndPolls() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);
    queue.add(5);
    queue.add(6);
    queue.add(7);
    queue.add(8);
    assertEquals(8, queue.size());
    assertEquals(0, queue.getHead());
    assertEquals(7, queue.getTail());
    assertEquals("[1, 2, 3, 4, 5, 6, 7, 8]", queue.toString());
    assertEquals(1, (int) queue.poll());
    assertEquals(2, (int) queue.poll());
    assertEquals(3, (int) queue.poll());
    assertEquals(4, (int) queue.poll());
    assertEquals(5, (int) queue.poll());
    assertEquals(6, (int) queue.poll());
    assertEquals(7, (int) queue.poll());
    assertEquals(4, ((Object[]) queue.getArray()).length);
    assertEquals(0, queue.getHead());
    assertEquals(0, queue.getTail());
    assertEquals(1, queue.size());
    assertEquals("[8]", queue.toString());
  }

  @Test
  public void testAnotherMultipleAddAndPolls() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);
    assertEquals(1, (int) queue.poll());
    assertEquals(2, (int) queue.poll());
    assertEquals(4, ((Object[]) queue.getArray()).length);
    assertEquals(2, queue.getHead());
    assertEquals(3, queue.getTail());
    assertEquals(2, queue.size());
    queue.add(5);
    queue.add(6);
    assertEquals(4, ((Object[]) queue.getArray()).length);
    assertEquals(2, queue.getHead());
    assertEquals(1, queue.getTail());
    assertEquals(4, queue.size());
    assertEquals(3, (int) queue.poll());
    assertEquals(4, (int) queue.poll());
    assertEquals(0, queue.getHead());
    assertEquals(1, queue.getTail());
    assertEquals(2, queue.size());
    assertEquals("[5, 6]", queue.toString());
  }

  @Test
  public void testClear() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(3);
    queue.add(2);
    queue.add(7);
    queue.add(9);
    queue.clear();
    assertEquals(0, queue.size());
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(-1, queue.getHead());
    assertEquals(-1, queue.getTail());
    assertEquals("[]", empty.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testEmptyIterator() {
    Queue<Integer> queue = new ArrayQueue<>();
    Iterator<Integer> queueIter = queue.iterator();
    queueIter.next();
  }

  @Test(expected = NoSuchElementException.class)
  public void testIteratorCallNextTooManyTimes() {
    Queue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(7);
    queue.add(10);
    Iterator<Integer> queueIter = queue.iterator();
    queueIter.next();
    queueIter.next();
    queueIter.next();
    queueIter.next();
  }

  @Test(expected = ConcurrentModificationException.class)
  public void testIteratorConcurrentModificationException() {
    Queue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(7);
    queue.add(10);
    Iterator<Integer> queueIter = queue.iterator();
    queueIter.next();
    queue.add(3);
    queueIter.next();
  }

  @Test
  public void testIterator() {
    Queue<Integer> queue = new ArrayQueue<>();
    queue.add(5);
    queue.add(1);
    queue.add(7);
    queue.add(0);
    queue.add(3);
    queue.add(9);
    queue.add(8);
    queue.add(null);
    queue.add(2);
    Iterator<Integer> queueIter = queue.iterator();
    assertTrue(queueIter.hasNext());
    assertEquals(5, (int) queueIter.next());
    assertTrue(queueIter.hasNext());
    assertEquals(1, (int) queueIter.next());
    assertTrue(queueIter.hasNext());
    assertEquals(7, (int) queueIter.next());
    assertTrue(queueIter.hasNext());
    assertEquals(0, (int) queueIter.next());
    assertTrue(queueIter.hasNext());
    assertEquals(3, (int) queueIter.next());
    assertTrue(queueIter.hasNext());
    assertEquals(9, (int) queueIter.next());
    assertTrue(queueIter.hasNext());
    assertEquals(8, (int) queueIter.next());
    assertTrue(queueIter.hasNext());
    assertNull(queueIter.next());
    assertTrue(queueIter.hasNext());
    assertEquals(2, (int) queueIter.next());
    assertFalse(queueIter.hasNext());
  }

  @Test
  public void testIteratorWrapAround() {
    Queue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);
    queue.poll();
    queue.poll();
    queue.add(5);
    queue.add(6);
    Iterator<Integer> queueIter = queue.iterator();
    assertTrue(queueIter.hasNext());
    assertEquals(3, (int) queueIter.next());
    assertTrue(queueIter.hasNext());
    assertEquals(4, (int) queueIter.next());
    assertTrue(queueIter.hasNext());
    assertEquals(5, (int) queueIter.next());
    assertTrue(queueIter.hasNext());
    assertEquals(6, (int) queueIter.next());
    assertFalse(queueIter.hasNext());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testIteratorRemove() {
    Iterator<Integer> setIter = hasNullElements.iterator();
    setIter.remove();
  }

  @Test
  public void testToStringEmptyQueue() {
    assertEquals("[]", empty.toString());
  }

  @Test
  public void testToStringSingletonQueue() {
    assertEquals("[1]", singleton.toString());
  }

  @Test
  public void testToStringNonEmptyQueue() {
    assertEquals("[1, 3, null, 3, null]", hasNullElements.toString());
  }
}
