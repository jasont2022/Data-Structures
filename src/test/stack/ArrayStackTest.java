package test.stack;
import org.junit.Test;

import main.stack.ArrayStack;
import main.stack.Stack;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class ArrayStackTest {
  @Test
  public void testConstructor() {
    ArrayStack<Integer> stack = new ArrayStack<>();
    assertEquals(2, ((Object[]) stack.getArray()).length);
    assertEquals(0, stack.size());
    assertTrue(stack.isEmpty());
    assertEquals("[]", stack.toString());
  }

  @Test
  public void testSingleton() {
    ArrayStack<Integer> stack = new ArrayStack<>();
    stack.push(1);
    assertEquals(2, ((Object[]) stack.getArray()).length);
    assertEquals(1, (int) ((Object[]) stack.getArray())[0]);
    assertNull(((Object[]) stack.getArray())[1]);
    assertEquals(0, stack.getHead());
    assertEquals(1, stack.size());
    assertFalse(stack.isEmpty());
    assertEquals("[1]", stack.toString());
  }

  @Test
  public void testNonEmptyStack() {
    ArrayStack<Integer> stack = new ArrayStack<>();
    stack.push(1);
    stack.push(7);
    stack.push(8);
    stack.push(4);
    stack.push(3);
    stack.push(1);
    stack.push(2);
    assertEquals(8, ((Object[]) stack.getArray()).length);
    assertEquals(1, (int) ((Object[]) stack.getArray())[0]);
    assertEquals(7, (int) ((Object[]) stack.getArray())[1]);
    assertEquals(8, (int) ((Object[]) stack.getArray())[2]);
    assertEquals(4, (int) ((Object[]) stack.getArray())[3]);
    assertEquals(3, (int) ((Object[]) stack.getArray())[4]);
    assertEquals(1, (int) ((Object[]) stack.getArray())[5]);
    assertEquals(2, (int) ((Object[]) stack.getArray())[6]);
    assertNull(((Object[]) stack.getArray())[7]);
    assertEquals(6, stack.getHead());
    assertEquals(7, stack.size());
    assertFalse(stack.isEmpty());
    assertEquals("[2, 1, 3, 4, 8, 7, 1]", stack.toString());
  }

  @Test (expected = NoSuchElementException.class)
  public void testEmptyInOrderIterator() {
    Stack<Integer> stack = new ArrayStack<>();
    Iterator<Integer> stackIter = stack.iterator();
    stackIter.next();
  }

  @Test (expected = NoSuchElementException.class)
  public void testInOrderIteratorCallNextTooManyTimes() {
    Stack<Integer> stack = new ArrayStack<>();
    stack.push(1);
    stack.push(7);
    stack.push(10);
    Iterator<Integer> stackIter = stack.iterator();
    stackIter.next();
    stackIter.next();
    stackIter.next();
    stackIter.next();
  }

  @Test (expected = ConcurrentModificationException.class)
  public void testInOrderIteratorConcurrentModificationException() {
    Stack<Integer> stack = new ArrayStack<>();
    stack.push(1);
    stack.push(7);
    stack.push(10);
    Iterator<Integer> stackIter = stack.iterator();
    stackIter.next();
    stack.push(3);
    stackIter.next();
  }

  @Test
  public void testIterator() {
    Stack<Integer> stack = new ArrayStack<>();
    stack.push(5);
    stack.push(1);
    stack.push(7);
    stack.push(0);
    stack.push(3);
    stack.push(9);
    stack.push(8);
    stack.push(null);
    stack.push(2);
    Iterator<Integer> stackIter = stack.iterator();
    assertTrue(stackIter.hasNext());
    assertEquals(2, (int) stackIter.next());
    assertTrue(stackIter.hasNext());
    assertNull(stackIter.next());
    assertTrue(stackIter.hasNext());
    assertEquals(8, (int) stackIter.next());
    assertTrue(stackIter.hasNext());
    assertEquals(9, (int) stackIter.next());
    assertTrue(stackIter.hasNext());
    assertEquals(3, (int) stackIter.next());
    assertTrue(stackIter.hasNext());
    assertEquals(0, (int) stackIter.next());
    assertTrue(stackIter.hasNext());
    assertEquals(7, (int) stackIter.next());
    assertTrue(stackIter.hasNext());
    assertEquals(1, (int) stackIter.next());
    assertTrue(stackIter.hasNext());
    assertEquals(5, (int) stackIter.next());
    assertFalse(stackIter.hasNext());
  }
}