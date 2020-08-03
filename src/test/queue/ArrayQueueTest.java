package test.queue;

import main.queue.ArrayQueue;
import main.queue.Queue;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class ArrayQueueTest {
  @Test
  public void testConstructor() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(-1, queue.getHead());
    assertEquals(-1, queue.getTail());
    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());
    assertEquals("[]", queue.toString());
  }

  @Test
  public void testSingleton() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(1, (int) ((Object[]) queue.getArray())[0]);
    assertNull(((Object[]) queue.getArray())[1]);
    assertEquals(0, queue.getHead());
    assertEquals(0, queue.getTail());
    assertEquals(1, queue.size());
    assertFalse(queue.isEmpty());
    assertEquals("[1]", queue.toString());
  }

  @Test
  public void testAddAndRemoveSingleton() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(1, (int) ((Object[]) queue.getArray())[0]);
    assertNull(((Object[]) queue.getArray())[1]);
    assertEquals(0, queue.getHead());
    assertEquals(0, queue.getTail());
    assertEquals(1, queue.size());
    assertFalse(queue.isEmpty());
    assertEquals("[1]", queue.toString());
    assertEquals(1, (int) queue.remove());
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertNull(((Object[]) queue.getArray())[0]);
    assertNull(((Object[]) queue.getArray())[1]);
    assertEquals(-1, queue.getHead());
    assertEquals(-1, queue.getTail());
    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());
    assertEquals("[]", queue.toString());
    queue.add(1);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(1, (int) ((Object[]) queue.getArray())[0]);
    assertNull(((Object[]) queue.getArray())[1]);
    assertEquals(0, queue.getHead());
    assertEquals(0, queue.getTail());
    assertEquals(1, queue.size());
    assertFalse(queue.isEmpty());
    assertEquals("[1]", queue.toString());
    queue.add(2);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(1, (int) ((Object[]) queue.getArray())[0]);
    assertEquals(2, (int) ((Object[]) queue.getArray())[1]);
    assertEquals(2, queue.size());
    assertFalse(queue.isEmpty());
    assertEquals("[1, 2]", queue.toString());
  }

  @Test
  public void testNonEmptyQueue() {
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.add(1);
    queue.add(7);
    assertEquals(2, ((Object[]) queue.getArray()).length);
    assertEquals(1, (int) ((Object[]) queue.getArray())[0]);
    assertEquals(7, (int) ((Object[]) queue.getArray())[1]);
    assertEquals(0, queue.getHead());
    assertEquals(1, queue.getTail());
    assertEquals(2, queue.size());
    assertFalse(queue.isEmpty());
    assertEquals("[1, 7]", queue.toString());
    queue.add(8);
    queue.add(4);
    assertEquals(4, ((Object[]) queue.getArray()).length);
    assertEquals(1, (int) ((Object[]) queue.getArray())[0]);
    assertEquals(7, (int) ((Object[]) queue.getArray())[1]);
    assertEquals(8, (int) ((Object[]) queue.getArray())[2]);
    assertEquals(4, (int) ((Object[]) queue.getArray())[3]);
    assertEquals(0, queue.getHead());
    assertEquals(3, queue.getTail());
    assertEquals(4, queue.size());
    assertFalse(queue.isEmpty());
    assertEquals("[1, 7, 8, 4]", queue.toString());
    queue.add(3);
    queue.add(1);
    queue.add(2);
    assertEquals(8, ((Object[]) queue.getArray()).length);
    assertEquals(1, (int) ((Object[]) queue.getArray())[0]);
    assertEquals(7, (int) ((Object[]) queue.getArray())[1]);
    assertEquals(8, (int) ((Object[]) queue.getArray())[2]);
    assertEquals(4, (int) ((Object[]) queue.getArray())[3]);
    assertEquals(3, (int) ((Object[]) queue.getArray())[4]);
    assertEquals(1, (int) ((Object[]) queue.getArray())[5]);
    assertEquals(2, (int) ((Object[]) queue.getArray())[6]);
    assertNull(((Object[]) queue.getArray())[7]);
    assertEquals(0, queue.getHead());
    assertEquals(6, queue.getTail());
    assertEquals(7, queue.size());
    assertFalse(queue.isEmpty());
    assertEquals("[1, 7, 8, 4, 3, 1, 2]", queue.toString());
  }

  @Test
  public void testWrapAroundAndResize() {
    ArrayQueue<Integer> queue =  new ArrayQueue<>();
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
    assertEquals(3, (int) ((Object[]) queue.getArray())[0]);
    assertEquals(4, (int) ((Object[]) queue.getArray())[1]);
    assertEquals(5, (int) ((Object[]) queue.getArray())[2]);
    assertEquals(6, (int) ((Object[]) queue.getArray())[3]);
    assertEquals(7, (int) ((Object[]) queue.getArray())[4]);
    assertNull(((Object[]) queue.getArray())[5]);
    assertNull(((Object[]) queue.getArray())[6]);
    assertNull(((Object[]) queue.getArray())[7]);
    assertEquals(0, queue.getHead());
    assertEquals(4, queue.getTail());
    assertEquals(5, queue.size());
    assertEquals("[3, 4, 5, 6, 7]", queue.toString());
  }

  @Test
  public void testMultipleAddAndPolls() {
    ArrayQueue<Integer> queue =  new ArrayQueue<>();
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
    assertEquals(8, (int) ((Object[]) queue.getArray())[0]);
    assertNull(((Object[]) queue.getArray())[1]);
    assertNull(((Object[]) queue.getArray())[2]);
    assertNull(((Object[]) queue.getArray())[3]);
    assertEquals(0, queue.getHead());
    assertEquals(0, queue.getTail());
    assertEquals(1, queue.size());
    assertEquals("[8]", queue.toString());
  }

  @Test
  public void testPollEmpty() {
    ArrayQueue<Integer> queue =  new ArrayQueue<>();
    assertEquals(-1, queue.getHead());
    assertEquals(-1, queue.getTail());
    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());
    assertNull(queue.poll());
  }

  @Test (expected = NoSuchElementException.class)
  public void testEmptyInOrderIterator() {
    Queue<Integer> queue = new ArrayQueue<>();
    Iterator<Integer> queueIter = queue.iterator();
    queueIter.next();
  }

  @Test (expected = NoSuchElementException.class)
  public void testInOrderIteratorCallNextTooManyTimes() {
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

  @Test (expected = ConcurrentModificationException.class)
  public void testInOrderIteratorConcurrentModificationException() {
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
}