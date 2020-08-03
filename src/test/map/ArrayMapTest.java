package test.map;

import main.map.ArrayMap;
import main.map.Map;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class ArrayMapTest {
    @Test
    public void testConstructor() {
        ArrayMap<String, Integer> map = new ArrayMap<>();
        assertEquals(2, ((Object[]) map.getArray()).length);
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertEquals("{}", map.toString());
    }

    @Test
    public void testSingleton() {
        ArrayMap<String, Integer> map = new ArrayMap<>();
        map.put("a", 1);
        assertEquals(2, ((Object[]) map.getArray()).length);
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertEquals("{(a,1)}", map.toString());
    }

    @Test
    public void testNonEmptyMap() {
        ArrayMap<String, Integer> map = new ArrayMap<>();
        map.put("a", 1);
        map.put("b", 7);
        map.put("c", 8);
        map.put("d", 4);
        map.put("e", 3);
        map.put("f", 1);
        map.put("a", 2);
        assertEquals(8, ((Object[]) map.getArray()).length);
        assertEquals(6, map.size());
        assertFalse(map.isEmpty());
        assertEquals("{(a,2), (b,7), (c,8), (d,4), (e,3), (f,1)}", map.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyInOrderIterator() {
        Map<String, Integer> map = new ArrayMap<>();
        Iterator<Map.Entry<String, Integer>> mapIter = map.entryIterator();
        mapIter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testInOrderIteratorCallNextTooManyTimes() {
        Map<String, Integer> map = new ArrayMap<>();
        map.put("a", 1);
        map.put("b", 7);
        map.put("c", 10);
        Iterator<Map.Entry<String, Integer>> mapIter = map.entryIterator();
        mapIter.next();
        mapIter.next();
        mapIter.next();
        mapIter.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testInOrderIteratorConcurrentModificationException() {
        Map<String, Integer> map = new ArrayMap<>();
        map.put("a", 1);
        map.put("b", 7);
        map.put("c", 10);
        Iterator<Map.Entry<String, Integer>> mapIter = map.entryIterator();
        mapIter.next();
        map.put("d", 3);
        mapIter.next();
    }

    @Test
    public void testIterator() {
        Map<String, Integer> map = new ArrayMap<>();
        map.put("a", 5);
        map.put("b", 1);
        map.put("c", 7);
        map.put("d", 0);
        map.put("e", 3);
        map.put("f", 9);
        map.put("g", 8);
        map.put(null, 9);
        map.put("b", 2);
        Iterator<Map.Entry<String, Integer>> mapIter = map.entryIterator();
        assertTrue(mapIter.hasNext());
        Map.Entry<String, Integer> entry1 = mapIter.next();
        assertEquals("a", entry1.getKey());
        assertEquals(5, (int) entry1.getValue());
        assertTrue(mapIter.hasNext());
        Map.Entry<String, Integer> entry2 = mapIter.next();
        assertEquals("b", entry2.getKey());
        assertEquals(2, (int) entry2.getValue());
        assertTrue(mapIter.hasNext());
        Map.Entry<String, Integer> entry3 = mapIter.next();
        assertEquals("c", entry3.getKey());
        assertEquals(7, (int) entry3.getValue());
        assertTrue(mapIter.hasNext());
        Map.Entry<String, Integer> entry4 = mapIter.next();
        assertEquals("d", entry4.getKey());
        assertEquals(0, (int) entry4.getValue());
        assertTrue(mapIter.hasNext());
        Map.Entry<String, Integer> entry5 = mapIter.next();
        assertEquals("e", entry5.getKey());
        assertEquals(3, (int) entry5.getValue());
        assertTrue(mapIter.hasNext());
        Map.Entry<String, Integer> entry6 = mapIter.next();
        assertEquals("f", entry6.getKey());
        assertEquals(9, (int) entry6.getValue());
        assertTrue(mapIter.hasNext());
        Map.Entry<String, Integer> entry7 = mapIter.next();
        assertEquals("g", entry7.getKey());
        assertEquals(8, (int) entry7.getValue());
        assertTrue(mapIter.hasNext());
        Map.Entry<String, Integer> entry8 = mapIter.next();
        assertNull(entry8.getKey());
        assertEquals(9, (int) entry8.getValue());
        assertFalse(mapIter.hasNext());
    }
}