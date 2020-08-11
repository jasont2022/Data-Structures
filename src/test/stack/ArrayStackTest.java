package test.stack;

import main.stack.Stack;
import main.stack.ArrayStack;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.EmptyStackException;

public class ArrayStackTest {
    private ArrayStack<Integer> empty; // an empty stack
    private ArrayStack<Integer> singleton; // an single element stack
    private ArrayStack<Integer> threeElements; // an stack with three elements
    private ArrayStack<Integer> hasNullElements; // an stack with null keys

    @Before
    public void setupTestStacks() {
        empty = new ArrayStack<>();

        singleton = new ArrayStack<>();
        singleton.push(1);

        threeElements = new ArrayStack<>();
        threeElements.push(5);
        threeElements.push(1);
        threeElements.push(3);

        hasNullElements = new ArrayStack<>();
        hasNullElements.push(1);
        hasNullElements.push(3);
        hasNullElements.push(null);
        hasNullElements.push(3);
        hasNullElements.push(null);
    }

    @Test
    public void testConstructor() {
        assertEquals(2, ((Object[]) empty.getArray()).length);
        assertEquals(0, empty.size());
        assertEquals(-1, empty.getHead());
        assertTrue(empty.isEmpty());
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testGetEmptyArray() {
        assertEquals(2, ((Object[]) empty.getArray()).length);
        assertNull(((Object[]) empty.getArray())[0]);
        assertNull(((Object[]) empty.getArray())[1]);
        assertEquals(0, empty.size());
        assertEquals(-1, empty.getHead());
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testGetNonEmptyArray() {
        assertEquals(4, ((Object[]) threeElements.getArray()).length);
        assertEquals(5, ((Object[]) threeElements.getArray())[0]);
        assertEquals(1, ((Object[]) threeElements.getArray())[1]);
        assertEquals(3, ((Object[]) threeElements.getArray())[2]);
        assertNull(((Object[]) threeElements.getArray())[3]);
        assertEquals(3, threeElements.size());
        assertEquals(2, threeElements.getHead());
        assertEquals("[3, 1, 5]", threeElements.toString());
    }

    @Test
    public void testGetNonEmptyNullElementsArray() {
        assertEquals(8, ((Object[]) hasNullElements.getArray()).length);
        assertEquals(1, ((Object[]) hasNullElements.getArray())[0]);
        assertEquals(3, ((Object[]) hasNullElements.getArray())[1]);
        assertNull(((Object[]) hasNullElements.getArray())[2]);
        assertEquals(3, ((Object[]) hasNullElements.getArray())[3]);
        assertNull(((Object[]) hasNullElements.getArray())[4]);
        assertNull(((Object[]) hasNullElements.getArray())[5]);
        assertNull(((Object[]) hasNullElements.getArray())[6]);
        assertNull(((Object[]) hasNullElements.getArray())[7]);
        assertEquals(5, hasNullElements.size());
        assertEquals(4, hasNullElements.getHead());
        assertEquals("[null, 3, null, 3, 1]", hasNullElements.toString());
    }

    @Test
    public void testEmptyStackSize() {
        assertEquals(0, empty.size());
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testNonEmptyStackSize() {
        assertEquals(3, threeElements.size());
        assertEquals("[3, 1, 5]", threeElements.toString());
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
    public void testPushSingleton() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        assertEquals(2, ((Object[]) stack.getArray()).length);
        assertEquals(1, stack.size());
        assertEquals(0, stack.getHead());
        assertEquals("[1]", stack.toString());
    }

    @Test
    public void testPushNonEmptyStack() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, ((Object[]) stack.getArray()).length);
        assertEquals(2, stack.size());
        assertEquals(1, stack.getHead());
        assertEquals("[2, 1]", stack.toString());
    }

    @Test
    public void testPushResizeStack() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, ((Object[]) stack.getArray()).length);
        assertEquals(2, stack.size());
        assertEquals(1, stack.getHead());
        assertEquals("[2, 1]", stack.toString());
        stack.push(3);
        stack.push(4);
        assertEquals(4, ((Object[]) stack.getArray()).length);
        assertEquals(4, stack.size());
        assertEquals(3, stack.getHead());
        assertEquals("[4, 3, 2, 1]", stack.toString());
    }

    @Test(expected = EmptyStackException.class)
    public void testPeekEmptyStack() {
        empty.peek();
    }

    @Test
    public void testPeekNonEmptyStack() {
        assertEquals(3, (int) threeElements.peek());
        assertEquals(3, threeElements.size());
        assertEquals(2, threeElements.getHead());
        assertEquals("[3, 1, 5]", threeElements.toString());
    }

    @Test(expected = EmptyStackException.class)
    public void testPopEmptyStack() {
        empty.pop();
    }

    @Test
    public void testPopNonEmptyStack() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(4, (int) stack.pop());
        assertEquals(3, (int) stack.pop());
        assertEquals(4, ((Object[]) stack.getArray()).length);
        assertEquals(2, stack.size());
        assertEquals(1, stack.getHead());
        assertEquals("[2, 1]", stack.toString());
    }

    @Test
    public void testPopResizeStack() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(4, (int) stack.pop());
        assertEquals(3, (int) stack.pop());
        assertEquals(2, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
        assertEquals(2, ((Object[]) stack.getArray()).length);
        assertEquals(0, stack.size());
        assertEquals(-1, stack.getHead());
        assertEquals("[]", stack.toString());
    }

    @Test
    public void testPushAndPop() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        assertEquals(2, ((Object[]) stack.getArray()).length);
        assertEquals(1, stack.size());
        assertEquals(0, stack.getHead());
        assertEquals("[1]", stack.toString());
        stack.pop();
        assertEquals(2, ((Object[]) stack.getArray()).length);
        assertEquals(0, stack.size());
        assertEquals(-1, stack.getHead());
        assertEquals("[]", stack.toString());
        stack.push(1);
        assertEquals(2, ((Object[]) stack.getArray()).length);
        assertEquals(1, stack.size());
        assertEquals(0, stack.getHead());
        assertEquals("[1]", stack.toString());
    }

    @Test
    public void testClear() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(4);
        stack.push(6);
        stack.push(13);
        stack.push(2);
        stack.clear();
        assertEquals(0, stack.size());
        assertEquals(2, ((Object[]) empty.getArray()).length);
        assertTrue(stack.isEmpty());
        assertEquals(-1, stack.getHead());
        assertEquals("[]", stack.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyIterator() {
        Stack<Integer> stack = new ArrayStack<>();
        Iterator<Integer> stackIter = stack.iterator();
        stackIter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorCallNextTooManyTimes() {
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

    @Test(expected = ConcurrentModificationException.class)
    public void testIteratorConcurrentModificationException() {
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

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemove() {
        Iterator<Integer> setIter = hasNullElements.iterator();
        setIter.remove();
    }

    @Test
    public void testToStringEmptyStack() {
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testToStringNonEmptyStack() {
        assertEquals("[null, 3, null, 3, 1]", hasNullElements.toString());
    }
}
