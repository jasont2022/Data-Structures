package test.set;

import main.set.Set;
import main.set.ArraySet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class ArraySetTest {
    private ArraySet<Integer> empty; // an empty array
    private ArraySet<Integer> singleton; // an single element array
    private ArraySet<Integer> threeElements; // an array with three elements
    private ArraySet<Integer> hasNullElements; // an array with null elements
    
    @Before
    public void setupTestArrays() {
        empty = new ArraySet<>();

        singleton = new ArraySet<>();
        singleton.add(1);
        
        threeElements = new ArraySet<>();
        threeElements.add(5);
        threeElements.add(1);
        threeElements.add(3);
        
        hasNullElements = new ArraySet<>();
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
        assertEquals("{}", empty.toString());
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
        assertEquals("{5, 1, 3}",  threeElements.toString());
    }
    
    
    @Test
    public void testGetNonEmptyNullElementsArray() {
        assertEquals(4, ((Object[]) hasNullElements.getArray()).length);
        assertEquals(1, ((Object[]) hasNullElements.getArray())[0]);
        assertEquals(3, ((Object[]) hasNullElements.getArray())[1]);
        assertNull(((Object[]) hasNullElements.getArray())[2]);
        assertNull(((Object[]) hasNullElements.getArray())[3]);
        assertEquals(3, hasNullElements.size());
        assertEquals("{1, 3, null}", hasNullElements.toString());
    }
    
    @Test
    public void testEmptySetSize() {
        assertEquals(0, empty.size());
        assertEquals("{}", empty.toString());
    }

    @Test
    public void testNonEmptySetSize() {
        assertEquals(3, threeElements.size());
        assertEquals("{5, 1, 3}", threeElements.toString());
    }
    
    @Test
    public void testIsEmptyTrue() {
        assertTrue(empty.isEmpty());
        assertEquals(0, empty.size());
        assertEquals("{}", empty.toString());
    }

    @Test
    public void testIsEmptyFalse() {
        assertFalse(singleton.isEmpty());
        assertEquals(1, singleton.size());
        assertEquals("{1}", singleton.toString());
    }
    
    @Test
    public void testContainsElementNotPresent() {
        assertFalse(threeElements.contains(4));
        assertEquals(3, threeElements.size());
        assertEquals("{5, 1, 3}", threeElements.toString());
    }

    @Test
    public void testContainsElementPresent() {
        assertTrue(threeElements.contains(1));
        assertEquals(3, threeElements.size());
        assertEquals("{5, 1, 3}", threeElements.toString());
    }
    
    @Test
    public void testAddCreateSingleton() {
        ArraySet<Integer> set = new ArraySet<>();
        assertTrue(set.add(1));
        assertEquals(1, set.size());
        assertEquals(2, ((Object[]) set.getArray()).length);
        assertEquals(1, ((Object[]) set.getArray())[0]);
        assertNull(((Object[]) set.getArray())[1]);
        assertFalse(set.isEmpty());
        assertEquals("{1}", set.toString());
    }

    @Test
    public void testAddExpandArray() {
        ArraySet<Integer> set = new ArraySet<>();
        assertTrue(set.add(3));
        assertTrue(set.add(4));
        assertTrue(set.add(5));
        assertTrue(set.add(2));
        assertTrue(set.add(7));
        assertEquals(5, set.size());
        assertFalse(set.isEmpty());
        assertEquals(8, ((Object[]) set.getArray()).length);
        assertEquals(3, ((Object[]) set.getArray())[0]);
        assertEquals(4, ((Object[]) set.getArray())[1]);
        assertEquals(5, ((Object[]) set.getArray())[2]);
        assertEquals(2, ((Object[]) set.getArray())[3]);
        assertEquals(7, ((Object[]) set.getArray())[4]);
        assertNull(((Object[]) set.getArray())[5]);
        assertNull(((Object[]) set.getArray())[6]);
        assertNull(((Object[]) set.getArray())[7]);
        assertEquals("{3, 4, 5, 2, 7}", set.toString());
    }
    
    @Test
    public void testAddNoDuplicates() {
        ArraySet<Integer> set = new ArraySet<>();
        assertTrue(set.add(3));
        assertTrue(set.add(4));
        assertTrue(set.add(5));
        assertTrue(set.add(2));
        assertTrue(set.add(7));
        assertFalse(set.add(3));
        assertFalse(set.add(2));
        assertEquals(5, set.size());
        assertFalse(set.isEmpty());
        assertEquals(8, ((Object[]) set.getArray()).length);
        assertEquals(3, ((Object[]) set.getArray())[0]);
        assertEquals(4, ((Object[]) set.getArray())[1]);
        assertEquals(5, ((Object[]) set.getArray())[2]);
        assertEquals(2, ((Object[]) set.getArray())[3]);
        assertEquals(7, ((Object[]) set.getArray())[4]);
        assertNull(((Object[]) set.getArray())[5]);
        assertNull(((Object[]) set.getArray())[6]);
        assertNull(((Object[]) set.getArray())[7]);
        assertEquals("{3, 4, 5, 2, 7}", set.toString());
    }
    
    @Test (expected = NoSuchElementException.class)
    public void testRemoveEmptyArray() {
        ArraySet<String> a = new ArraySet<>();
        a.remove("a");
    }
    
    @Test
    public void testRemoveNullElement() {
        ArraySet<String> set = new ArraySet<>();
        set.add("a");
        set.add(null);
        set.add("foo");
        set.add("bar");
        assertTrue(set.remove(null));
        assertEquals(4, ((Object[]) set.getArray()).length);
        assertEquals("a", ((Object[]) set.getArray())[0]);
        assertEquals("foo", ((Object[]) set.getArray())[1]);
        assertEquals("bar", ((Object[]) set.getArray())[2]);
        //assertEquals("bar", ((Object[]) set.getArray())[3]);
        assertNull(((Object[]) set.getArray())[3]);
        assertEquals(3, set.size());
        assertEquals("{a, foo, bar}", set.toString());
    }
    
    @Test
    public void testRemoveNonNullElement() {
        ArraySet<String> set = new ArraySet<>();
        set.add("a");
        set.add(null);
        set.add("foo");
        set.add("bar");
        assertTrue(set.remove("foo"));
        assertEquals(4, ((Object[]) set.getArray()).length);
        assertEquals("a", ((Object[]) set.getArray())[0]);
        assertNull(((Object[]) set.getArray())[1]);
        assertEquals("bar", ((Object[]) set.getArray())[2]);
        // assertEquals("bar", ((Object[]) set.getArray())[3]);
        assertNull(((Object[]) set.getArray())[3]);
        assertEquals(3, set.size());
        assertEquals("{a, null, bar}", set.toString());
    }
    
    @Test
    public void testRemoveElementNotPresent() {
        ArraySet<String> set = new ArraySet<>();
        set.add("a");
        set.add(null);
        set.add("foo");
        set.add("bar");
        assertFalse(set.remove("jay"));
        assertEquals(4, ((Object[]) set.getArray()).length);
        assertEquals("a", ((Object[]) set.getArray())[0]);
        assertNull(((Object[]) set.getArray())[1]);
        assertEquals("foo", ((Object[]) set.getArray())[2]);
        assertEquals("bar", ((Object[]) set.getArray())[3]);
        assertEquals(4, set.size());
        assertEquals("{a, null, foo, bar}", set.toString());
    }
    
    @Test
    public void testRemoveResizeDown() {
        ArraySet<String> set = new ArraySet<>();
        set.add("a");
        set.add(null);
        set.add("foo");
        set.add("bar");
        set.remove("a");
        set.remove(null);
        set.remove("foo");
        set.remove("bar");
        assertEquals(2, ((Object[]) set.getArray()).length);
        assertNull(((Object[]) set.getArray())[0]);
        assertNull(((Object[]) set.getArray())[1]);
        assertEquals(0, set.size());
        assertEquals("{}", set.toString());
    }

    @Test
    public void testUnion() {
        Set<Integer> set1 = new ArraySet<>();
        Set<Integer> set2 = new ArraySet<>();
        set1.add(1);
        set1.add(7);
        set1.add(8);
        set1.add(10);
        set2.add(1);
        set2.add(3);
        set2.add(5);
        set2.add(7);
        Set<Integer> union = set1.union(set2);
        assertEquals(6, union.size());
        assertFalse(union.isEmpty());
        assertEquals("{1, 7, 8, 10, 3, 5}", union.toString());
    }
    
    @Test
    public void testIntersection() {
        Set<Integer> set1 = new ArraySet<>();
        Set<Integer> set2 = new ArraySet<>();
        set1.add(1);
        set1.add(7);
        set1.add(8);
        set1.add(10);
        set2.add(1);
        set2.add(3);
        set2.add(5);
        set2.add(7);
        Set<Integer> intersection = set1.intersection(set2);
        assertEquals(2, intersection.size());
        assertFalse(intersection.isEmpty());
        assertEquals("{1, 7}", intersection.toString());
    }

    @Test
    public void testEmptyIntersection() {
        Set<Integer> set1 = new ArraySet<>();
        Set<Integer> set2 = new ArraySet<>();
        set1.add(1);
        set1.add(7);
        set1.add(8);
        set1.add(10);
        set2.add(2);
        set2.add(3);
        set2.add(5);
        set2.add(11);
        Set<Integer> intersection = set1.intersection(set2);
        assertEquals(0, intersection.size());
        assertTrue(intersection.isEmpty());
        assertEquals("{}", intersection.toString());
    }

    @Test
    public void testDifference() {
        Set<Integer> set1 = new ArraySet<>();
        Set<Integer> set2 = new ArraySet<>();
        set1.add(1);
        set1.add(7);
        set1.add(10);
        set1.add(8);
        set2.add(1);
        set2.add(3);
        set2.add(5);
        set2.add(7);
        Set<Integer> difference = set1.difference(set2);
        assertEquals(2, difference.size());
        assertFalse(difference.isEmpty());
        assertEquals("{10, 8}", difference.toString());
    }
    
    @Test
    public void testEmptyDifference() {
        Set<Integer> set1 = new ArraySet<>();
        Set<Integer> set2 = new ArraySet<>();
        set1.add(1);
        set1.add(7);
        set2.add(1);
        set2.add(3);
        set2.add(5);
        set2.add(7);
        Set<Integer> difference = set1.difference(set2);
        assertEquals(0, difference.size());
        assertTrue(difference.isEmpty());
        assertEquals("{}", difference.toString());
    }

    @Test
    public void testSubsetTrue() {
        Set<Integer> set1 = new ArraySet<>();
        set1.add(1);
        set1.add(7);
        Set<Integer> set2 = new ArraySet<>();
        set2.add(1);
        set2.add(7);
        set2.add(3);
        set2.add(5);
        boolean subset = set1.subset(set2);
        assertTrue(subset);
    }
    
    @Test
    public void testSubsetFalse() {
        Set<Integer> set1 = new ArraySet<>();
        set1.add(1);
        set1.add(7);
        Set<Integer> set2 = new ArraySet<>();
        set2.add(1);
        set2.add(7);
        set2.add(3);
        set2.add(5);
        boolean subset = set2.subset(set1);
        assertFalse(subset);
    }
  
    @Test
    public void testClear() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(4);
        set.add(6);
        set.add(13);
        set.add(2);
        set.clear();
        assertEquals(0, set.size());
        assertTrue(set.isEmpty());
        assertFalse(set.contains(4));
        assertFalse(set.contains(6));
        assertFalse(set.contains(13));
        assertFalse(set.contains(2));
        assertEquals("{}", set.toString());
    }
    
    @Test
    public void testToArray() {
        Object[] expectedSingleton = {1};
        assertArrayEquals(expectedSingleton, singleton.toArray());
        assertEquals(1, singleton.size());
        assertEquals("{1}", singleton.toString());
        
        Object[] expectedThreeElements = {5, 1, 3};
        assertArrayEquals(expectedThreeElements, threeElements.toArray());
        assertEquals(3, threeElements.size());
        assertEquals("{5, 1, 3}", threeElements.toString());
        
        Object[] expectedHasNullElements = {1, 3, null};
        assertArrayEquals(expectedHasNullElements, hasNullElements.toArray());
        assertEquals(3, hasNullElements.size());
        assertEquals("{1, 3, null}", hasNullElements.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyIterator() {
        Set<Integer> set = new ArraySet<>();
        Iterator<Integer> setIter = set.iterator();
        setIter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorCallNextTooManyTimes() {
        Set<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(7);
        set.add(10);
        Iterator<Integer> setIter = set.iterator();
        setIter.next();
        setIter.next();
        setIter.next();
        setIter.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testIteratorConcurrentModificationException() {
        Set<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(7);
        set.add(10);
        Iterator<Integer> setIter = set.iterator();
        setIter.next();
        set.add(3);
        setIter.next();
    }

    @Test
    public void testIterator() {
        Set<Integer> set = new ArraySet<>();
        set.add(5);
        set.add(1);
        set.add(7);
        set.add(0);
        set.add(3);
        set.add(9);
        set.add(8);
        Iterator<Integer> setIter = set.iterator();
        assertTrue(setIter.hasNext());
        assertEquals(5, (int) setIter.next());
        assertTrue(setIter.hasNext());
        assertEquals(1, (int) setIter.next());
        assertTrue(setIter.hasNext());
        assertEquals(7, (int) setIter.next());
        assertTrue(setIter.hasNext());
        assertEquals(0, (int) setIter.next());
        assertTrue(setIter.hasNext());
        assertEquals(3, (int) setIter.next());
        assertTrue(setIter.hasNext());
        assertEquals(9, (int) setIter.next());
        assertTrue(setIter.hasNext());
        assertEquals(8, (int) setIter.next());
        assertFalse(setIter.hasNext());
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testIteratorRemove() {
        Iterator<Integer> setIter = hasNullElements.iterator();
        setIter.remove();
    }
    
    @Test
    public void testToStringEmptyArray() {
        assertEquals("{}", empty.toString());
    }

    @Test
    public void testToStringNonEmptyArray() {
        assertEquals("{1, 3, null}", hasNullElements.toString());
    }
}