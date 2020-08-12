package test.heap;

import main.heap.Heap;
import main.heap.BinaryMaxHeap;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.HashSet;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryMaxHeapTest {
    private BinaryMaxHeap<Integer> empty; // an empty BinaryMaxHeap
    private List<Integer> emptyArr; // the underlying array storing the elements
    private BinaryMaxHeap<Integer> multipleElements; // a heap containing multiple elements
    private List<Integer> multiArr; // the underlying array storing the elements
    private BinaryMaxHeap<Integer> largeHeap; // a large heap
    private List<Integer>  largeArr; // the underlying array storing the elements

    @Before
    public void setUpBinaryMaxHeaps() {
        empty = new BinaryMaxHeap<>();
        emptyArr = new ArrayList<>();

        multipleElements = new BinaryMaxHeap<>();
        multipleElements.insert(7);
        multipleElements.insert(8);
        multipleElements.insert(5);
        multipleElements.insert(3);
        multipleElements.insert(2);
        multipleElements.insert(6);

        multiArr = new ArrayList<>();
        multiArr.add(8);
        multiArr.add(7);
        multiArr.add(6);
        multiArr.add(3);
        multiArr.add(2);
        multiArr.add(5);

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
        
        largeArr = new ArrayList<>();
        largeArr.add(9);
        largeArr.add(8);
        largeArr.add(7);
        largeArr.add(6);
        largeArr.add(5);
        largeArr.add(1);
        largeArr.add(2);
        largeArr.add(2);
        largeArr.add(2);
        largeArr.add(3);
        largeArr.add(4);
        largeArr.add(0);
        largeArr.add(1);
        largeArr.add(2);
        largeArr.add(1);
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
        Integer[] array = { 3, 7, 4, 1, 5, 8, 9 };
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
        assertEquals("[9, 7, 8, 1, 5, 3, 4]", heap.toString());
    }

    @Test
    public void testFourthConstructorArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(10);
        arrayList.add(2);
        arrayList.add(5);
        arrayList.add(0);
        arrayList.add(3);
        arrayList.add(1);
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(arrayList);
        List<Integer> arr = new ArrayList<>();
        arr.add(10);
        arr.add(5);
        arr.add(3);
        arr.add(2);
        arr.add(4);
        arr.add(0);
        arr.add(3);
        arr.add(1);
        assertEquals(8, heap.size());
        assertEquals(arr, heap.getArray());
        assertEquals("[10, 5, 3, 2, 4, 0, 3, 1]", heap.toString());
    }

    @Test
    public void testFourthConstructorHashSet() {
        HashSet<Integer> set = new HashSet<>();
        set.add(4);
        set.add(1);
        set.add(5);
        set.add(2);
        set.add(9);
        set.add(7);
        set.add(3);
        set.add(6);
        set.add(8);
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(set);
        List<Integer> arr = new ArrayList<>();
        arr.add(9);
        arr.add(8);
        arr.add(7);
        arr.add(6);
        arr.add(1);
        arr.add(5);
        arr.add(3);
        arr.add(4);
        arr.add(2);
        assertEquals(9, heap.size());
        assertEquals(arr, heap.getArray());
        assertEquals("[9, 8, 7, 6, 1, 5, 3, 4, 2]", heap.toString());
    }

    @Test
    public void testFourthConstructorLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.add(3);
        list.add(2);
        list.add(5);
        list.add(5);
        list.add(4);
        list.add(0);
        list.add(1);
        list.add(1);
        list.add(3);
        list.add(2);
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(list);
        List<Integer> arr = new ArrayList<>();
        arr.add(5);
        arr.add(5);
        arr.add(4);
        arr.add(3);
        arr.add(3);
        arr.add(3);
        arr.add(2);
        arr.add(0);
        arr.add(1);
        arr.add(1);
        arr.add(3);
        arr.add(2);
        assertEquals(11, heap.size());
        assertEquals(arr, heap.getArray());
        assertEquals("[3, 3, 2, 5, 5, 4, 0, 1, 1, 3, 2]", heap.toString());
    }

    @Test
    public void testGetArrayEmpty() {
        assertEquals(emptyArr, empty.getArray());
        assertEquals(0, empty.size());
    }

    @Test
    public void testGetArrayNonEmpty() {
        assertEquals(multiArr, multipleElements.getArray());
        assertEquals(6, multipleElements.size());
        
        assertEquals(largeArr, largeHeap.getArray());
        assertEquals(15, largeHeap.size());
    }

    @Test
    public void testSizeEmpty() {
        assertEquals(0, empty.size());
        assertEquals(emptyArr, empty.getArray());
    }

    @Test
    public void testSizeNonEmpty() {
        assertEquals(6, multipleElements.size());
        assertEquals(multiArr, multipleElements.getArray());

        assertEquals(15, largeHeap.size());
        assertEquals(largeArr, largeHeap.getArray());
    }

    @Test
    public void testIsEmptyTrue() {
        assertTrue(empty.isEmpty());
        assertEquals(0, empty.size());
        assertEquals(emptyArr, empty.getArray());
    }

    @Test
    public void testIsEmptyFalse() {
        assertFalse(multipleElements.isEmpty());
        assertEquals(6, multipleElements.size());
        assertEquals(multiArr, multipleElements.getArray());

        assertFalse(largeHeap.isEmpty());
        assertEquals(15, largeHeap.size());
        assertEquals(largeArr, largeHeap.getArray());
    }

    @Test
    public void testContainsNullElement() {
        assertFalse(multipleElements.contains(null));
        assertEquals(6, multipleElements.size());
        assertEquals(multiArr, multipleElements.getArray());
        
        assertFalse(largeHeap.contains(null));
        assertEquals(15, largeHeap.size());
        assertEquals(largeArr, largeHeap.getArray());
    }

    @Test
    public void testContainsElementPresent() {
        assertTrue(multipleElements.contains(2));
        assertEquals(6, multipleElements.size());
        assertEquals(multiArr, multipleElements.getArray());
        
        assertTrue(largeHeap.contains(2));
        assertEquals(15, largeHeap.size());
        assertEquals(largeArr, largeHeap.getArray());
    }

    @Test
    public void testContainsElementNotPresent() {
        assertFalse(multipleElements.contains(4));
        assertEquals(6, multipleElements.size());
        assertEquals(multiArr, multipleElements.getArray());
        
        assertFalse(largeHeap.contains(11));
        assertEquals(15, largeHeap.size());
        assertEquals(largeArr, largeHeap.getArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertNullElement() {
        multipleElements.insert(null);
    }
    
    @Test
    public void testInsertSingleton() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        List<Integer> array = new ArrayList<>();
        heap.insert(1);
        array.add(1);
        assertEquals(1, heap.size());
        assertEquals(array, heap.getArray());
        assertEquals("[1]", heap.toString());
    }
    
    @Test
    public void testInsertNonEmpty() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        heap.insert(3);
        heap.insert(1);
        heap.insert(5);
        heap.insert(2);
        heap.insert(1);
        heap.insert(7);
        heap.insert(4);
        heap.insert(5);
        List<Integer> array = new ArrayList<>();
        array.add(7);
        array.add(5);
        array.add(5);
        array.add(2);
        array.add(1);
        array.add(3);
        array.add(4);
        array.add(1);
        assertEquals(7, heap.size());
        assertEquals(array, heap.getArray());
        assertEquals("[7, 5, 5, 2, 1, 3, 4, 1]", heap.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testPeekEmptyHeap() {
        empty.peek();
    }

    @Test
    public void testPeekNonEmptyHeap() {
        assertEquals(8, (int) multipleElements.peek());
        assertEquals(6, multipleElements.size());
        assertEquals(multiArr, multipleElements.getArray());

        assertEquals(9, (int) largeHeap.peek());
        assertEquals(15, largeHeap.size());
        assertEquals(largeArr, largeHeap.getArray());
    }

    @Test(expected = NoSuchElementException.class)
    public void testExtractEmpty() {
        empty.extract();
    }
    
    @Test
    public void testExtractSingleton() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        heap.insert(1);
        assertEquals(1, (int) heap.extract());
        assertEquals(0, heap.size());
        assertEquals(emptyArr, heap.getArray());
        assertEquals("[]", heap.toString());
    }
    
    @Test
    public void testExtractNonEmpty() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        heap.insert(3);
        heap.insert(1);
        heap.insert(5);
        heap.insert(2);
        heap.insert(1);
        heap.insert(7);
        heap.insert(4);
        heap.insert(5);
        assertEquals(7, (int) heap.extract());
        assertEquals(7, heap.size());
        assertEquals("[5, 2, 5, 1, 1, 3, 4]", heap.toString());
        assertEquals(5, (int) heap.extract());
        assertEquals(6, heap.size());
        assertEquals("[5, 2, 4, 1, 1, 3]", heap.toString());
        assertEquals(5, (int) heap.extract());
        assertEquals(5, heap.size());
        assertEquals("[4, 2, 3, 1, 1]", heap.toString());
    }

    @Test
    public void testRemoveSingleton() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        heap.insert(1);
        heap.remove(1);
        assertEquals(0, heap.size());
        assertEquals(emptyArr, heap.getArray());
        assertEquals("[]", heap.toString());
    }
    
    @Test
    public void testRemoveElementPresent() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        heap.insert(7);
        heap.insert(2);
        heap.insert(5);
        heap.insert(1);
        heap.insert(1);
        heap.insert(3);
        heap.insert(4);
        assertTrue(heap.remove(1));
        assertEquals(6, heap.size());
        assertEquals("[7, 4, 5, 1, 2, 3]", heap.toString());
        assertTrue(heap.remove(7));
        assertEquals(5, heap.size());
        assertEquals("[5, 4, 3, 1, 2]", heap.toString());
        assertTrue(heap.remove(2));
        assertEquals(4, heap.size());
        assertEquals("[5, 4, 3, 1]", heap.toString());
    }
    
    @Test
    public void testRemoveElementNonPresent() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        heap.insert(7);
        heap.insert(2);
        heap.insert(5);
        heap.insert(1);
        heap.insert(1);
        heap.insert(3);
        heap.insert(4);
        assertFalse(heap.remove(6));
        assertEquals(7, heap.size());
        assertEquals("[7, 2, 5, 1, 1, 3, 4]", heap.toString());
    }
    
    @Test
    public void testRemoveNullElement() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        heap.insert(7);
        heap.insert(2);
        heap.insert(5);
        heap.insert(1);
        heap.insert(1);
        heap.insert(3);
        heap.insert(4);
        assertFalse(heap.remove(null));
        assertEquals(7, heap.size());
        assertEquals("[7, 2, 5, 1, 1, 3, 4]", heap.toString());
    }

    @Test
    public void testClear() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        heap.insert(3);
        heap.insert(4);
        heap.insert(9);
        heap.insert(7);
        heap.insert(1);
        heap.insert(5);
        heap.remove(4);
        heap.insert(10);
        heap.clear();
        assertEquals(0, heap.size());
        assertEquals(emptyArr, heap.getArray());
        assertEquals("[]", heap.toString());
    }

    @Test
    public void testToArrayEmpty() {
        Object[] array = {};
        assertArrayEquals(array, empty.toArray());
        assertEquals(0, empty.size());
        assertEquals(emptyArr, empty.getArray());
    }
    
    @Test
    public void testToArrayNonEmpty() {
        Object[] multiArray = {8, 7, 6, 3, 2, 5};
        assertArrayEquals(multiArray, multipleElements.toArray());
        assertEquals(6, multipleElements.size());
        assertEquals(multiArr, multipleElements.getArray());
        
        Object[] largeArray = {9, 8, 7, 6, 5, 1, 2, 2, 2, 3, 4, 0, 1 , 2, 1};
        assertArrayEquals(largeArray, largeHeap.toArray());
        assertEquals(15, largeHeap.size());
        assertEquals(largeArr, largeHeap.getArray());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testEmptyIterator() {
        Heap<Integer> heap = new BinaryMaxHeap<>();
        Iterator<Integer> heapIter = heap.iterator();
        heapIter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorCallNextTooManyTimes() {
        Heap<Integer> heap = new BinaryMaxHeap<>();
        heap.insert(1);
        heap.insert(7);
        heap.insert(10);
        Iterator<Integer> heapIter = heap.iterator();
        heapIter.next();
        heapIter.next();
        heapIter.next();
        heapIter.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testIteratorConcurrentModificationException() {
        Heap<Integer> heap = new BinaryMaxHeap<>();
        heap.insert(1);
        heap.insert(7);
        heap.insert(10);
        Iterator<Integer> heapIter = heap.iterator();
        heapIter.next();
        heap.insert(3);
        heapIter.next();
    }

    @Test
    public void testIterator() {
        Iterator<Integer> heapIter = multipleElements.iterator();
        assertTrue(heapIter.hasNext());
        assertEquals(8, (int) heapIter.next());
        assertTrue(heapIter.hasNext());
        assertEquals(7, (int) heapIter.next());
        assertTrue(heapIter.hasNext());
        assertEquals(6, (int) heapIter.next());
        assertTrue(heapIter.hasNext());
        assertEquals(3, (int) heapIter.next());
        assertTrue(heapIter.hasNext());
        assertEquals(2, (int) heapIter.next());
        assertTrue(heapIter.hasNext());
        assertEquals(5, (int) heapIter.next());
        assertFalse(heapIter.hasNext());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemove() {
        Iterator<Integer> heapIter = multipleElements.iterator();
        heapIter.remove();
    }

    @Test
    public void testToStringEmpty() {
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testToStringNonEmpty() {
        assertEquals("[8, 7, 6, 3, 2, 5]", multipleElements.toString());
    }
}
