package test.array;

import main.array.ArrayImpl;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * Testing the ArrayImpl class
 * 
 * @author Jason Tran
 */
public class ArrayImplTest {
    private ArrayImpl<Integer> empty; // an empty array
    private ArrayImpl<Integer> singleton; // an single element array
    private ArrayImpl<Integer> threeElements; // an array with three elements
    private ArrayImpl<Integer> hasNullElements; // an array with null elements

    @Before
    public void setupTestArrays() {
        empty = new ArrayImpl<>();

        singleton = new ArrayImpl<>();
        singleton.add(1);
        
        threeElements = new ArrayImpl<>();
        threeElements.add(5);
        threeElements.add(1);
        threeElements.add(3);
        
        hasNullElements = new ArrayImpl<>();
        hasNullElements.add(1);
        hasNullElements.add(3);
        hasNullElements.add(null);
        hasNullElements.add(3);
        hasNullElements.add(null);
    }

    @Test
    public void testConstructor() {
        assertEquals(2, ((Object[]) empty.getArray()).length);
        assertEquals(0, empty.size());
        assertTrue(empty.isEmpty());
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testSecondConstructor() {
        ArrayImpl<Integer> array = new ArrayImpl<>(16);
        assertEquals(16, ((Object[]) array.getArray()).length);
        assertEquals(0, array.size());
        assertTrue(array.isEmpty());
        assertEquals("[]", array.toString());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testSecondConstructorNegativeLength() {
        new ArrayImpl<>(-5);
    }

    @Test
    public void testGetEmptyArray() {
        assertEquals(2, ((Object[]) empty.getArray()).length);
        assertNull(((Object[]) empty.getArray())[0]);
        assertNull(((Object[]) empty.getArray())[1]);
        assertEquals(0, empty.size());
    }

    @Test
    public void testGetNonEmptyArray() {
        assertEquals(4, ((Object[]) threeElements.getArray()).length);
        assertEquals(5, ((Object[]) threeElements.getArray())[0]);
        assertEquals(1, ((Object[]) threeElements.getArray())[1]);
        assertEquals(3, ((Object[]) threeElements.getArray())[2]);
        assertNull(((Object[]) threeElements.getArray())[3]);
        assertEquals(3, threeElements.size());
        assertEquals("[5, 1, 3]",  threeElements.toString());
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
        assertEquals("[1, 3, null, 3, null]", hasNullElements.toString());
    }

    @Test
    public void testEmptyArraySize() {
        assertEquals(0, empty.size());
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testNonEmptyArraySize() {
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
    public void testContainsElementNotPresent() {
        assertFalse(threeElements.contains(4));
        assertEquals(3, threeElements.size());
        assertEquals("[5, 1, 3]", threeElements.toString());
    }

    @Test
    public void testContainsElementPresent() {
        assertTrue(threeElements.contains(1));
        assertEquals(3, threeElements.size());
        assertEquals("[5, 1, 3]", threeElements.toString());
    }
    
    @Test
    public void testIndexOfNullElement() {
        assertEquals(2, hasNullElements.indexOf(null));
        assertEquals(5, hasNullElements.size());
        assertEquals("[1, 3, null, 3, null]", hasNullElements.toString());
    }
    
    @Test
    public void testIndexOfNonNullElement() {
        assertEquals(0, threeElements.indexOf(5));
        assertEquals(3, threeElements.size());
        assertEquals("[5, 1, 3]", threeElements.toString());
    }
    
    @Test
    public void testIndexOfElementNotPresent() {
        assertEquals(-1, threeElements.indexOf(4));
        assertEquals(3, threeElements.size());
        assertEquals("[5, 1, 3]", threeElements.toString());
    }
    
    @Test
    public void testLastIndexOfNullElement() {
        assertEquals(4, hasNullElements.lastIndexOf(null));
        assertEquals(5, hasNullElements.size());
        assertEquals("[1, 3, null, 3, null]", hasNullElements.toString());
    }
    
    @Test
    public void testLastIndexOfNonNullElement() {
        assertEquals(3, hasNullElements.lastIndexOf(3));
        assertEquals(5, hasNullElements.size());
        assertEquals("[1, 3, null, 3, null]", hasNullElements.toString());
    }
    
    @Test
    public void testLastIndexOfElementNotPresent() {
        assertEquals(-1, threeElements.lastIndexOf(4));
        assertEquals(3, threeElements.size());
        assertEquals("[5, 1, 3]", threeElements.toString());
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
        ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(2);
        array.add(7);
        array.set(0, 2);
        array.set(3, 3);
        assertEquals(5, array.size());
        assertEquals("[2, 4, 5, 3, 7]", array.toString());
    }

    @Test
    public void testAddCreateSingleton() {
        ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(1);
        assertEquals(1, array.size());
        assertEquals(2, ((Object[]) array.getArray()).length);
        assertEquals(1, ((Object[]) array.getArray())[0]);
        assertNull(((Object[]) array.getArray())[1]);
        assertFalse(array.isEmpty());
        assertEquals("[1]", array.toString());
    }

    @Test
    public void testAddExpandArray() {
        ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(2);
        array.add(7);
        assertEquals(5, array.size());
        assertFalse(array.isEmpty());
        assertEquals(8, ((Object[]) array.getArray()).length);
        assertEquals(3, ((Object[]) array.getArray())[0]);
        assertEquals(4, ((Object[]) array.getArray())[1]);
        assertEquals(5, ((Object[]) array.getArray())[2]);
        assertEquals(2, ((Object[]) array.getArray())[3]);
        assertEquals(7, ((Object[]) array.getArray())[4]);
        assertNull(((Object[]) array.getArray())[5]);
        assertNull(((Object[]) array.getArray())[6]);
        assertNull(((Object[]) array.getArray())[7]);
        assertEquals("[3, 4, 5, 2, 7]", array.toString());
    }
    
    @Test (expected = NoSuchElementException.class)
    public void testRemoveAtEmptyArray() {
        empty.remove(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtNegativeIndex() {
        threeElements.remove(-2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtOutOfBoundsEqualsSize() {
        threeElements.remove(3);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtOutOfBoundsPositveIndex() {
        threeElements.remove(5);
    }

    @Test
    public void testRemoveAt() {
        ArrayImpl<String> array = new ArrayImpl<>();
        array.add("a");
        array.add(null);
        array.add("foo");
        array.add("bar");
        assertEquals("foo", array.remove(2));
        assertEquals(4, ((Object[]) array.getArray()).length);
        assertEquals("a", ((Object[]) array.getArray())[0]);
        assertNull(((Object[]) array.getArray())[1]);
        assertEquals("bar", ((Object[]) array.getArray())[2]);
        //assertEquals("bar", ((Object[]) array.getArray())[3]);
        assertNull(((Object[]) array.getArray())[3]);
        assertEquals(3, array.size());
        assertEquals("[a, null, bar]", array.toString());
    }
    
    @Test
    public void testRemoveAtLargerArray() {
        ArrayImpl<String> array = new ArrayImpl<>();
        array.add("a");
        array.add(null);
        array.add("foo");
        array.add("bar");
        array.add("baz");
        array.add("jay");
        array.add("blue");
        assertEquals("foo", array.remove(2));
        assertEquals(8, ((Object[]) array.getArray()).length);
        assertEquals("a", ((Object[]) array.getArray())[0]);
        assertNull(((Object[]) array.getArray())[1]);
        assertEquals("bar", ((Object[]) array.getArray())[2]);
        assertEquals("baz", ((Object[]) array.getArray())[3]);
        assertEquals("jay", ((Object[]) array.getArray())[4]);
        assertEquals("blue", ((Object[]) array.getArray())[5]);
        //assertEquals("blue", ((Object[]) array.getArray())[6]);
        assertNull(((Object[]) array.getArray())[6]);
        assertNull(((Object[]) array.getArray())[7]);
        assertEquals(6, array.size());
        assertEquals("[a, null, bar, baz, jay, blue]", array.toString());
    }
    
    @Test
    public void testRemoveAndAdd() {
        ArrayImpl<String> array = new ArrayImpl<>();
        array.add("a");
        array.add(null);
        array.add("foo");
        array.add("bar");
        array.add("baz");
        array.add("jay");
        array.add("blue");
        assertEquals("foo", array.remove(2));
        array.add("c");
        assertEquals(8, ((Object[]) array.getArray()).length);
        assertEquals("a", ((Object[]) array.getArray())[0]);
        assertNull(((Object[]) array.getArray())[1]);
        assertEquals("bar", ((Object[]) array.getArray())[2]);
        assertEquals("baz", ((Object[]) array.getArray())[3]);
        assertEquals("jay", ((Object[]) array.getArray())[4]);
        assertEquals("blue", ((Object[]) array.getArray())[5]);
        assertEquals("c", ((Object[]) array.getArray())[6]);
        assertNull(((Object[]) array.getArray())[7]);
        assertEquals(7, array.size());
        assertEquals("[a, null, bar, baz, jay, blue, c]", array.toString());
    }
    
    @Test
    public void testRemoveAtResizeDown() {
        ArrayImpl<String> array = new ArrayImpl<>();
        array.add("a");
        array.add(null);
        array.add("foo");
        array.add("bar");
        array.remove(3);
        array.remove(2);
        array.remove(1);
        array.remove(0);
        assertEquals(2, ((Object[]) array.getArray()).length);
        assertNull(((Object[]) array.getArray())[0]);
        assertNull(((Object[]) array.getArray())[1]);
        assertEquals(0, array.size());
        assertEquals("[]", array.toString());
    }

    @Test (expected = NoSuchElementException.class)
    public void testRemoveEmptyArray() {
        ArrayImpl<String> a = new ArrayImpl<>();
        a.remove("a");
    }
    
    
    @Test
    public void testRemoveNullElement() {
        ArrayImpl<String> array = new ArrayImpl<>();
        array.add("a");
        array.add(null);
        array.add("foo");
        array.add("bar");
        assertTrue(array.remove(null));
        assertEquals(4, ((Object[]) array.getArray()).length);
        assertEquals("a", ((Object[]) array.getArray())[0]);
        assertEquals("foo", ((Object[]) array.getArray())[1]);
        assertEquals("bar", ((Object[]) array.getArray())[2]);
        //assertEquals("bar", ((Object[]) array.getArray())[3]);
        assertNull(((Object[]) array.getArray())[3]);
        assertEquals(3, array.size());
        assertEquals("[a, foo, bar]", array.toString());
    }
    
    @Test
    public void testRemoveNonNullElement() {
        ArrayImpl<String> array = new ArrayImpl<>();
        array.add("a");
        array.add(null);
        array.add("foo");
        array.add("bar");
        assertTrue(array.remove("foo"));
        assertEquals(4, ((Object[]) array.getArray()).length);
        assertEquals("a", ((Object[]) array.getArray())[0]);
        assertNull(((Object[]) array.getArray())[1]);
        assertEquals("bar", ((Object[]) array.getArray())[2]);
        // assertEquals("bar", ((Object[]) array.getArray())[3]);
        assertNull(((Object[]) array.getArray())[3]);
        assertEquals(3, array.size());
        assertEquals("[a, null, bar]", array.toString());
    }
    
    @Test
    public void testRemoveElementNotPresent() {
        ArrayImpl<String> array = new ArrayImpl<>();
        array.add("a");
        array.add(null);
        array.add("foo");
        array.add("bar");
        assertFalse(array.remove("jay"));
        assertEquals(4, ((Object[]) array.getArray()).length);
        assertEquals("a", ((Object[]) array.getArray())[0]);
        assertNull(((Object[]) array.getArray())[1]);
        assertEquals("foo", ((Object[]) array.getArray())[2]);
        assertEquals("bar", ((Object[]) array.getArray())[3]);
        assertEquals(4, array.size());
        assertEquals("[a, null, foo, bar]", array.toString());
    }
    
    @Test
    public void testClear() {
        ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(4);
        array.add(6);
        array.add(13);
        array.add(2);
        array.clear();
        assertEquals(0, array.size());
        assertTrue(array.isEmpty());
        assertFalse(array.contains(4));
        assertFalse(array.contains(6));
        assertFalse(array.contains(13));
        assertFalse(array.contains(2));
        assertEquals("[]", array.toString());
    }
    
    @Test
    public void testToArray() {
        Object[] expectedSingleton = {1};
        assertArrayEquals(expectedSingleton, singleton.toArray());
        assertEquals(1, singleton.size());
        assertEquals("[1]", singleton.toString());
        
        Object[] expectedThreeElements = {5, 1, 3};
        assertArrayEquals(expectedThreeElements, threeElements.toArray());
        assertEquals(3, threeElements.size());
        assertEquals("[5, 1, 3]", threeElements.toString());
        
        Object[] expectedHasNullElements = {1, 3, null, 3, null};
        assertArrayEquals(expectedHasNullElements, hasNullElements.toArray());
        assertEquals(5, hasNullElements.size());
        assertEquals("[1, 3, null, 3, null]", hasNullElements.toString());
    }

    @Test (expected = NoSuchElementException.class)
    public void testIteratorEmptyArrayCallNext() {
        Iterator<Integer> arrayIter = empty.iterator();
        arrayIter.next();
    }

    @Test
    public void testIteratorIterateOverElements() {
        Iterator<Integer> arrayIter = hasNullElements.iterator();
        assertTrue(arrayIter.hasNext());
        assertEquals(1, (int) arrayIter.next());
        assertTrue(arrayIter.hasNext());
        assertEquals(3, (int) arrayIter.next());
        assertTrue(arrayIter.hasNext());
        assertNull(arrayIter.next());
        assertTrue(arrayIter.hasNext());
        assertEquals(3, (int) arrayIter.next());
        assertTrue(arrayIter.hasNext());
        assertNull(arrayIter.next());
        assertFalse(arrayIter.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void testIteratorCallNextTooManyTimes() {
        Iterator<Integer> arrayIter = hasNullElements.iterator();
        arrayIter.next();
        arrayIter.next();
        arrayIter.next();
        arrayIter.next();
        arrayIter.next();
        arrayIter.next();
    }
    
    @Test (expected = ConcurrentModificationException.class)
    public void testIteratorConcurrentModificationException() {
        ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(null);
        array.add(5);
        array.add(2);
        Iterator<Integer> arrayIter = array.iterator();
        arrayIter.next();
        array.add(14);
        arrayIter.next();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testIteratorRemove() {
        Iterator<Integer> arrayIter = hasNullElements.iterator();
        arrayIter.remove();
    }

    @Test
    public void testToStringEmptyArray() {
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testToStringNonEmptyArray() {
        assertEquals("[1, 3, null, 3, null]", hasNullElements.toString());
    }
}