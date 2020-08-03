package test.set;
import org.junit.Test;

import main.set.ArraySet;
import main.set.Set;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class ArraySetTest {
  @Test
  public void testConstructor() {
    ArraySet<Integer> set = new ArraySet<>();
    assertEquals(2, ((Object[]) set.getArray()).length);
    assertEquals(0, set.size());
    assertTrue(set.isEmpty());
    assertEquals("{}", set.toString());
  }

  @Test
  public void testNonEmptySet() {
    ArraySet<Integer> set = new ArraySet<>();
    set.add(1);
    set.add(7);
    set.add(8);
    set.add(4);
    set.add(3);
    set.add(1);
    assertEquals(8, ((Object[]) set.getArray()).length);
    assertEquals(5, set.size());
    assertFalse(set.isEmpty());
    assertEquals("{1, 7, 8, 4, 3}", set.toString());
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

  /*
  @Test
  public void testSubset() {
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
  */

  @Test (expected = NoSuchElementException.class)
  public void testEmptyInOrderIterator() {
    Set<Integer> set = new ArraySet<>();
    Iterator<Integer> setIter = set.iterator();
    setIter.next();
  }

  @Test (expected = NoSuchElementException.class)
  public void testInOrderIteratorCallNextTooManyTimes() {
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

  @Test (expected = ConcurrentModificationException.class)
  public void testInOrderIteratorConcurrentModificationException() {
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
}