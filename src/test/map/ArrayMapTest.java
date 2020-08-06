package test.map;

import main.map.Map;
import main.map.ArrayMap;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class ArrayMapTest {
  private ArrayMap<Integer, Integer> empty; // an empty map
  private ArrayMap<Integer, Integer> singleton; // an single element map
  private ArrayMap<Integer, Integer> threeElements; // an map with three elements
  private ArrayMap<Integer, Integer> hasNullElements; // an map with null keys

  @Before
  public void setupTestMaps() {
    empty = new ArrayMap<>();

    singleton = new ArrayMap<>();
    singleton.put(1, 2);

    threeElements = new ArrayMap<>();
    threeElements.put(5, 2);
    threeElements.put(1, 4);
    threeElements.put(3, 9);

    hasNullElements = new ArrayMap<>();
    hasNullElements.put(1, 5);
    hasNullElements.put(3, 4);
    hasNullElements.put(null, 3);
    hasNullElements.put(3, null);
    hasNullElements.put(null, 2);
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
    assertNull(((Object[]) threeElements.getArray())[3]);
    assertEquals(3, threeElements.size());
    assertEquals("{(5,2), (1,4), (3,9)}", threeElements.toString());
  }

  @Test
  public void testEmptySetSize() {
    assertEquals(0, empty.size());
    assertEquals("{}", empty.toString());
  }

  @Test
  public void testNonEmptySetSize() {
    assertEquals(3, threeElements.size());
    assertEquals("{(5,2), (1,4), (3,9)}", threeElements.toString());
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
    assertEquals("{(1,2)}", singleton.toString());
  }

  @Test
  public void testContainsNullKeyPresent() {
    assertTrue(hasNullElements.containsKey(null));
    assertEquals(3, hasNullElements.size());
    assertEquals("{(1,5), (3,null), (null,2)}", hasNullElements.toString());
  }

  @Test
  public void testContainsNonNullKeyPresent() {
    assertTrue(threeElements.containsKey(1));
    assertEquals(3, threeElements.size());
    assertEquals("{(5,2), (1,4), (3,9)}", threeElements.toString());
  }

  @Test
  public void testContainsKeyNotPresent() {
    assertFalse(threeElements.containsKey(4));
    assertEquals(3, threeElements.size());
    assertEquals("{(5,2), (1,4), (3,9)}", threeElements.toString());
  }

  @Test
  public void testContainsNullValuePresent() {
    assertTrue(hasNullElements.containsValue(null));
    assertEquals(3, hasNullElements.size());
    assertEquals("{(1,5), (3,null), (null,2)}", hasNullElements.toString());
  }

  @Test
  public void testContainsNonValuePresent() {
    assertTrue(threeElements.containsValue(4));
    assertEquals(3, threeElements.size());
    assertEquals("{(5,2), (1,4), (3,9)}", threeElements.toString());
  }

  @Test
  public void testContainsValueNotPresent() {
    assertFalse(threeElements.containsValue(1));
    assertEquals(3, threeElements.size());
    assertEquals("{(5,2), (1,4), (3,9)}", threeElements.toString());
  }

  @Test
  public void testGetNullKey() {
    assertEquals(2, (int) hasNullElements.get(null));
    assertEquals(3, hasNullElements.size());
    assertEquals("{(1,5), (3,null), (null,2)}", hasNullElements.toString());
  }

  @Test
  public void testGetNonNullKey() {
    assertEquals(5, (int) hasNullElements.get(1));
    assertEquals(3, hasNullElements.size());
    assertEquals("{(1,5), (3,null), (null,2)}", hasNullElements.toString());
  }

  @Test
  public void testGetKeyNullValueReturn() {
    assertNull(hasNullElements.get(3));
    assertEquals(3, hasNullElements.size());
    assertEquals("{(1,5), (3,null), (null,2)}", hasNullElements.toString());
  }

  @Test
  public void testGetKeyNotPresent() {
    assertNull(hasNullElements.get(5));
    assertEquals(3, hasNullElements.size());
    assertEquals("{(1,5), (3,null), (null,2)}", hasNullElements.toString());
  }

  @Test
  public void testPutReplaceNullKey() {
    ArrayMap<String, Integer> map = new ArrayMap<>();
    assertNull(map.put("a", 5));
    assertNull(map.put(null, 3));
    assertEquals(3, (int) map.put(null, 2));
    assertEquals(2, ((Object[]) map.getArray()).length);
    assertEquals(2, map.size());
    assertFalse(map.isEmpty());
    assertEquals("{(a,5), (null,2)}", map.toString());
  }

  @Test
  public void testPutReplaceNonNullKey() {
    ArrayMap<String, Integer> map = new ArrayMap<>();
    assertNull(map.put("a", 5));
    assertEquals(5, (int) map.put("a", 3));
    map.put(null, 2);
    assertEquals(2, ((Object[]) map.getArray()).length);
    assertEquals(2, map.size());
    assertFalse(map.isEmpty());
    assertEquals("{(a,3), (null,2)}", map.toString());
  }

  @Test
  public void testPutReplaceNullValue() {
    ArrayMap<String, Integer> map = new ArrayMap<>();
    assertNull(map.put("a", 5));
    assertEquals(5, (int) map.put("a", null));
    assertNull(map.put(null, 2));
    assertEquals(2, ((Object[]) map.getArray()).length);
    assertEquals(2, map.size());
    assertFalse(map.isEmpty());
    assertEquals("{(a,null), (null,2)}", map.toString());
  }

  @Test
  public void testPutResize() {
    ArrayMap<String, Integer> map = new ArrayMap<>();
    assertNull(map.put("a", 5));
    assertNull(map.put(null, 2));
    assertEquals(2, ((Object[]) map.getArray()).length);
    assertEquals(2, map.size());
    assertEquals("{(a,5), (null,2)}", map.toString());
    assertNull(map.put("foo", null));
    assertNull(map.put("bar", null));
    assertEquals(4, ((Object[]) map.getArray()).length);
    assertEquals(4, map.size());
    assertEquals("{(a,5), (null,2), (foo,null), (bar,null)}", map.toString());
    assertNull(map.put("jay", 2));
    assertEquals(8, ((Object[]) map.getArray()).length);
    assertEquals(5, map.size());
    assertEquals("{(a,5), (null,2), (foo,null), (bar,null), (jay,2)}", map.toString());
  }

  @Test
  public void testRemoveNullKey() {
    ArrayMap<String, Integer> map = new ArrayMap<>();
    map.put("a", 5);
    map.put(null, 2);
    map.put("foo", null);
    map.put("bar", null);
    map.put("jay", 2);
    assertEquals(2, (int) map.remove(null));
    assertEquals(8, ((Object[]) map.getArray()).length);
    assertEquals(4, map.size());
    assertEquals("{(a,5), (foo,null), (bar,null), (jay,2)}", map.toString());
  }

  @Test
  public void testRemoveNonNullKey() {
    ArrayMap<String, Integer> map = new ArrayMap<>();
    map.put("a", 5);
    map.put(null, 2);
    map.put("foo", null);
    map.put("bar", null);
    map.put("jay", 2);
    assertEquals(5, (int) map.remove("a"));
    assertEquals(8, ((Object[]) map.getArray()).length);
    assertEquals(4, map.size());
    assertEquals("{(null,2), (foo,null), (bar,null), (jay,2)}", map.toString());
  }

  @Test
  public void testRemoveNullValue() {
    ArrayMap<String, Integer> map = new ArrayMap<>();
    map.put("a", 5);
    map.put(null, 2);
    map.put("foo", null);
    map.put("bar", null);
    map.put("jay", 2);
    assertNull(map.remove("foo"));
    assertEquals(8, ((Object[]) map.getArray()).length);
    assertEquals(4, map.size());
    assertEquals("{(a,5), (null,2), (bar,null), (jay,2)}", map.toString());
  }

  @Test
  public void testRemoveKeyNotPresent() {
    ArrayMap<String, Integer> map = new ArrayMap<>();
    map.put("a", 5);
    map.put(null, 2);
    map.put("foo", null);
    map.put("bar", null);
    map.put("jay", 2);
    assertNull(map.remove("cool"));
    assertEquals(8, ((Object[]) map.getArray()).length);
    assertEquals(5, map.size());
    assertEquals("{(a,5), (null,2), (foo,null), (bar,null), (jay,2)}", map.toString());
  }

  @Test
  public void testRemoveResize() {
    ArrayMap<String, Integer> map = new ArrayMap<>();
    map.put("a", 5);
    map.put(null, 2);
    map.put("foo", null);
    map.put("bar", null);
    map.put("jay", 2);
    assertEquals(2, (int) map.remove("jay"));
    assertNull(map.remove("bar"));
    assertNull(map.remove("foo"));
    assertEquals(2, (int) map.remove(null));
    assertEquals(4, ((Object[]) map.getArray()).length);
    assertEquals(1, map.size());
    assertEquals("{(a,5)}", map.toString());
  }

  @Test
  public void testClear() {
    ArrayMap<String, Integer> map = new ArrayMap<>();
    map.put("a", 2);
    map.put(null, 5);
    map.put("foo", null);
    map.put("baz", 7);
    map.put("bar", null);
    map.clear();
    assertEquals(2, ((Object[]) map.getArray()).length);
    assertEquals(0, map.size());
    assertTrue(map.isEmpty());
    assertEquals("{}", map.toString());
  }

  @Test
  public void testEntrySet() {
    Set<Map.Entry<Integer, Integer>> expected = hasNullElements.entrySet();
    assertEquals(3, expected.size());
    Iterator<Map.Entry<Integer, Integer>> expectedIter = expected.iterator();
    Map.Entry<Integer, Integer> entry1 = expectedIter.next();
    assertNull(entry1.getKey());
    assertEquals(2, (int) entry1.getValue());
    Map.Entry<Integer, Integer> entry2 = expectedIter.next();
    assertEquals(1, (int) entry2.getKey());
    assertEquals(5, (int) entry2.getValue());
    Map.Entry<Integer, Integer> entry3 = expectedIter.next();
    assertEquals(3, (int) entry3.getKey());
    assertNull(entry3.getValue());
    assertEquals(3, hasNullElements.size());
    assertEquals("{(1,5), (3,null), (null,2)}", hasNullElements.toString());
  }

  @Test
  public void testKeySet() {
    Set<Integer> expected = new HashSet<>();
    expected.add(1);
    expected.add(3);
    expected.add(null);
    assertEquals(expected, hasNullElements.keySet());
    assertEquals(3, hasNullElements.size());
    assertEquals("{(1,5), (3,null), (null,2)}", hasNullElements.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testEmptyIterator() {
    Map<String, Integer> map = new ArrayMap<>();
    Iterator<Map.Entry<String, Integer>> mapIter = map.entryIterator();
    mapIter.next();
  }

  @Test(expected = NoSuchElementException.class)
  public void testIteratorCallNextTooManyTimes() {
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
  public void testIteratorConcurrentModificationException() {
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

  @Test(expected = UnsupportedOperationException.class)
  public void testIteratorRemove() {
    Iterator<Map.Entry<Integer, Integer>> mapIter = hasNullElements.entryIterator();
    mapIter.remove();
  }

  @Test
  public void testToStringEmptyMap() {
    assertEquals("{}", empty.toString());
  }

  @Test
  public void testToStringNonEmptyMap() {
    assertEquals("{(1,5), (3,null), (null,2)}", hasNullElements.toString());
  }
}
