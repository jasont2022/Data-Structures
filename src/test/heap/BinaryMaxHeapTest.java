package test.heap;

import main.heap.BinaryMaxHeap;
import main.heap.Heap;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Iterator;

public class BinaryMaxHeapTest {
    private BinaryMaxHeap<Integer> empty;
    private BinaryMaxHeap<Integer> singleton;
    private BinaryMaxHeap<Integer> multipleElements;

    @Before
    public void setUpBinaryMaxHeaps() {
        empty = new BinaryMaxHeap<>();

        singleton = new BinaryMaxHeap<>();
        singleton.insert(1);

        multipleElements = new BinaryMaxHeap<>();
    }

    @Test
    public void testFirstConstructor() {
        List<Integer> emptyArr = new ArrayList<>();
        assertEquals(0, empty.size());
        assertEquals(emptyArr, empty.getArray());
        assertEquals("[]", empty.toString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSecondConstructorNegativeSize() {
        new BinaryMaxHeap<>(-5);
    }
    
    @Test
    public void testSecondConstructorNonNegativeSize() {
        List<Integer> emptyArr = new ArrayList<>();
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(4);
        assertEquals(4, heap.size());
        assertEquals(emptyArr, heap.getArray());
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testThirdConstructor() {
    }

    @Test
    public void testFourthConstructor() {
    }

    @Test
    public void testGetArray() {
    }

    @Test
    public void testSize() {
    }

    @Test
    public void testIsEmpty() {
    }

    @Test
    public void testContains() {
    }

    @Test
    public void testInsert() {
    }

    @Test
    public void testPeek() {
    }

    @Test (expected = NoSuchElementException.class)
    public void testExtractEmpty() {
        empty.extract();
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void testClear() {
    }

    @Test
    public void testIterator() {
        Heap<Integer> heap = new BinaryMaxHeap<>();
        Iterator<Integer> heapIter = heap.iterator();
    }

    @Test
    public void testToString() {
    }
}
