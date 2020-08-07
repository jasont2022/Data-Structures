package test.heap;

import main.heap.Heap;
import main.heap.BinaryMaxHeap;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
        
    }
    
    @Test
    public void testSecondConstructor() {
        
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
    
    @Test
    public void testExtract() {
        
    }
    
    @Test
    public void testRemove() {
        
    }
    
    @Test
    public void testClear() {
        
    }
    
    @Test
    public void testIterator() {
        
    }
    
    @Test
    public void testToString() {
        
    }
}