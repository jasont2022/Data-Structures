package test.heap;

//import main.heap.Heap;
import main.heap.BinaryMaxHeap;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;

//import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryMaxHeapTest {
    private BinaryMaxHeap<Integer> empty;
    private List<Integer> emptyArr;
    private BinaryMaxHeap<Integer> multipleElements;
    private List<Integer> multiArr;
    private BinaryMaxHeap<Integer> largeHeap;

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
    }

    @Test
    public void testContainsNullElement() {
        assertFalse(multipleElements.contains(null));
        assertEquals(6, multipleElements.size());
        assertEquals(multiArr, multipleElements.getArray());
    }

    @Test
    public void testContainsElementPresent() {
        assertTrue(multipleElements.contains(2));
        assertEquals(6, multipleElements.size());
        assertEquals(multiArr, multipleElements.getArray());
    }

    @Test
    public void testContainsElementNotPresent() {
        assertFalse(multipleElements.contains(4));
        assertEquals(6, multipleElements.size());
        assertEquals(multiArr, multipleElements.getArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertNullElement() {
        multipleElements.insert(null);
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
    public void testToArray() {

    }

    @Test
    public void testIterator() {
        //Heap<Integer> heap = new BinaryMaxHeap<>();
        //Iterator<Integer> heapIter = heap.iterator();
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
