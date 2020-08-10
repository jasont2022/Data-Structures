package test.heap;

import main.heap.Heap;
import main.heap.BinaryMaxHeap;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryMaxHeapTest {
    private BinaryMaxHeap<Integer> empty;
    private List<Integer> emptyArr;
    private BinaryMaxHeap<Integer> singleton;
    private BinaryMaxHeap<Integer> multipleElements;
    private BinaryMaxHeap<Integer> largeHeap;

    @Before
    public void setUpBinaryMaxHeaps() {
        empty = new BinaryMaxHeap<>();
        emptyArr = new ArrayList<>();

        singleton = new BinaryMaxHeap<>();
        singleton.insert(1);

        multipleElements = new BinaryMaxHeap<>();
        multipleElements.insert(7);
        multipleElements.insert(8);
        multipleElements.insert(5);
        multipleElements.insert(3);
        multipleElements.insert(2);
        multipleElements.insert(6);
        
        largeHeap = new BinaryMaxHeap<>();
        largeHeap.insert(9);
        largeHeap.insert(8);
        largeHeap.insert(7);
        largeHeap.insert(6);
        largeHeap.insert(5);
        largeHeap.insert(1);
        largeHeap.insert(2);
        largeHeap.insert(2);
        largeHeap.insert(2);
        largeHeap.insert(3);
        largeHeap.insert(4);
        largeHeap.insert(0);
        largeHeap.insert(1);
        largeHeap.insert(2);
        largeHeap.insert(1);
    }

    @Test
    public void testFirstConstructor() {
        assertEquals(0, empty.size());
        assertEquals(emptyArr, empty.getArray());
        assertEquals("[]", empty.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSecondConstructorNegativeSize() {
        new BinaryMaxHeap<>(-5);
    }

    @Test
    public void testSecondConstructorNonNegativeSize() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(4);
        assertEquals(4, heap.size());
        assertEquals(emptyArr, heap.getArray());
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testThirdConstructorEmptyArray() {
        Integer[] array = {};
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(array);
        assertEquals(0, heap.size());
        assertEquals(emptyArr, heap.getArray());
        assertEquals("[]", heap.toString());
    }
    
    @Test
    public void testThirdConstructorNonEmptyArray() {
        Integer[] array = {3, 7, 4, 1, 5, 8, 9};
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(array);
        List<Integer> arr = new ArrayList<>();
        arr.add(9);
        arr.add(7);
        arr.add(8);
        arr.add(1);
        arr.add(5);
        arr.add(3);
        arr.add(4);
        assertEquals(7, heap.size());
        assertEquals(arr, heap.getArray());
        assertEquals("[]", heap.toString());
    }

    @Test
    public void testFourthConstructorArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
    }
    
    @Test
    public void testFourthConstructorHashSet() {
        HashSet<Integer> set = new HashSet<>();
    }
    
    @Test
    public void testFourthConstructorLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
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

    @Test(expected = NoSuchElementException.class)
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
