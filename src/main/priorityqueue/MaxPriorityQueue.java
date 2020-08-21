package main.priorityqueue;

import main.heap.*;

import java.util.Collection;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * Implements the Priority Queue Interface using a Binary Max Heap with an ArrayList and HashMap for
 * faster operations (in terms of Runtime), this implementation does not support null keys, but 
 * supports null values to be inserted, searched, and deleted
 * 
 * @author Jason Tran
 */
public class MaxPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {
    private List<Entry<K, V>> arr; // an ArrayList to store the collection of elements
    // a HashMap to map entries from the ArrayList to indices in the ArrayList
    private Map<V, Integer> map = new HashMap<>();
    
    /**
     * This Inner Class implements the Entry Interface
     *
     * @author Jason Tran
     */
    private static class Entry<K, V> implements PriorityQueue.Entry<K, V> {
        private final K key; // the key in the entry
        private final V value; // the value in the entry

        /**
         * Constructor: Creates a new entry in the queue
         *
         * @param key   key to be stored in the entry
         * @param value the value to be stored in the entry
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /** {@inheritDoc}, Runtime: O(1) */
        @Override
        public K getKey() {
            return key;
        }

        /** {@inheritDoc}, Runtime: O(1) */
        @Override
        public V getValue() {
            return value;
        }
        
        /** {@inheritDoc}, Runtime: O(1) */
        @Override
        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }
    
    /**
     * Constructor: Creates an empty priority queue
     */
    public MaxPriorityQueue() {
        checkSize(0);
    }
    
    /**
     * Constructor: Creates a Priority Queue with the specified size
     *
     * @param size the number of elements for the queue
     * @throws IllegalArgumentException if size is negative
     */
    public MaxPriorityQueue(int size) {
        // check if the length is not negative and create an arraylist
        checkSize(size);
    }
    
    /**
     * Constructor: Creates a Priority Queue with the key array, similar to the Build
     * Heap Algorithm, Runtime: O(n)
     * 
     * @param keys a key array to be constructed in a queue
     * @param values a value array to be paired with the key array
     * @throws IllegalArgumentException if two array lengths are not equal
     * @throws IllegalArgumentException if size is negative
     */
    public MaxPriorityQueue(K[] keys, V[] values) {
        // check if the lengths of both are valid, if so create an empty array list
        checkInputLengths(keys.length, values.length);
        
        // first add the elements into the array and hashmap
        for (int i = 0; i < keys.length; i++) {
            Entry<K, V> entry = new Entry<>(keys[i], values[i]);
            map.put(values[i], i);
            arr.add(entry);
        }
        
        // perform the build algorithm
        buildHeap(keys.length);
    }
    
    /**
     * Constructor: Creates a Priority Queue with the entry array, similar to the Build
     * Heap Algorithm, Runtime: O(n)
     *
     * @param array an entry array to be constructed into a priority queue
     * @throws IllegalArgumentException if size is negative
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public MaxPriorityQueue(Entry[] array) {
        // check if the length is not negative and create an arraylist
        checkSize(array.length);
        
        // first add the elements into the array and hashmap
        for (int i = 0; i < array.length; i++)  {
            map.put((V) array[i].value, i);
            arr.add(array[i]);
        }
        
        // perform the build heap algorithm
        buildHeap(array.length);
    }
    
    /**
     * Constructor: Creates a Priority Queue with an existing heap, similar to the Build
     * Heap Algorithm, Runtime: O(n)
     * 
     * @param heap a key type heap to construct a priority queue
     * @param values a value type array to be paired with the keys
     * @throws IllegalArgumentException if two collection lengths are not equal
     * @throws IllegalArgumentException if heap size is negative
     */
    public MaxPriorityQueue(Heap<K> heap, V[] values) {
        // check if the lengths of both are valid, if so create an empty array list
        checkInputLengths(heap.size(), values.length);
        
        // create an iterator for the heap
        Iterator<K> heapIter = heap.iterator();
        
        // first add the elements into the ArrayList and HashMap
        int index = 0;
        while (heapIter.hasNext()) {
            Entry<K, V> entry = new Entry<>(heapIter.next(), values[index]);
            map.put(values[index], index);
            arr.add(entry);
            index++;
        }
        
        // perform the build heap algorithm
        buildHeap(heap.size());
    }
    
    /**
     * Constructor: Creates a Priority Queue with an existing heap, Runtime: O()
     * 
     * @param heap a key type heap to construct a priority queue
     * @param values a value type collection to be paired with the keys
     * @throws IllegalArgumentException if two collection lengths are not equal
     * @throws IllegalArgumentException if heap size is negative
     */
    public MaxPriorityQueue(Heap<K> heap, Collection<V> values) {
        // check if the lengths of both are valid, if so create an empty array list
        checkInputLengths(heap.size(), values.size());
        
        // create an iterator for the heap and collection
        Iterator<K> heapIter = heap.iterator();
        Iterator<V> valuesIter = values.iterator();
        
        // add the elements into the queue
        while (heapIter.hasNext()) {
            add(heapIter.next(), valuesIter.next());
        }
    }
    
    /**
     * Constructor: Creates a Priority Queue with the collections, Runtime: O(nlg(n))
     * Do not get this confused with the O(n) Build Heap Algorithm
     *
     * @param keys a collection of key elements
     * @param values a collection of value elements
     * @throws IllegalArgumentException if two collection lengths are not equal
     * @throws IllegalArgumentException if size is negative
     */
    public MaxPriorityQueue(Collection<K> keys, Collection<V> values) {
        // check if the lengths of both are valid, if so create an empty array list
        checkInputLengths(keys.size(), values.size());
        
        /*
         * O(n^2) construction
         * for (K e : keys) {
         *   for (V v: values) {
         *       add(e, v);
         *   }
         * }
         */
        
        // this step below is O(nlg(n))
        // create iterators for the two collection
        Iterator<K> keysIter = keys.iterator();
        Iterator<V> valuesIter = values.iterator();
        
        // add the elements into the queue
        while (keysIter.hasNext()) {
            add(keysIter.next(), valuesIter.next());
        }
    }
    
    /**
     * Constructor: Creates a Priority Queue with the collection, Runtime: O(nlg(n))
     * Do not get this confused with the O(n) Build Heap Algorithm
     *
     * @param collection a collection of type entry elements
     * @throws IllegalArgumentException if size is negative
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public MaxPriorityQueue(Collection<Entry> collection) {
        // check if the length is not negative and create an arraylist
        checkSize(collection.size());
        
        // add the elements into the queue
        for (Entry e : collection) {
            add((K) e.key, (V) e.value);
        } 
    }
    
    /**
     * Constructor: Creates a Priority Queue with the map, similar to the Build
     * Heap Algorithm, Runtime: O(n)
     * 
     * @param  entryMap a map of key-value pair elements
     * @throws IllegalArgumentException if size is negative
     */
    @SuppressWarnings("unchecked")
    public MaxPriorityQueue(Map<K, V> entryMap) {
        // check if the length is not negative and create an ArrayList
        checkSize(map.size());
        
        // create an iterator for the map
        Iterator<Map.Entry<K, V>> mapIter = entryMap.entrySet().iterator();
        
        // first add the elements into the array and HashMap
        int index = 0;
        while (mapIter.hasNext()) {
            map.put(mapIter.next().getValue(), index);
            arr.add((Entry<K, V>) mapIter.next());
            index++;
        }
        
        // perform the build heap algorithm
        buildHeap(map.size());
    }
    
    /**
     * Helper method to check if the lengths of both data structures
     * are valid and create an empty ArrayList if they are, Runtime O(1)
     * 
     * @param size1 the size of the first data structure
     * @param size2 the size of the second data structure
     * @throws IllegalArgumentException if two array lengths are not equal
     * @throws IllegalArgumentException if size is negative
     */
    private void checkInputLengths(int size1, int size2) {
        // check if the sizes are equal
        if (size1 != size2) {
            throw new IllegalArgumentException("ERROR: Two sizes are not equal");
        }
        
        // check if the size is not negative
        checkSize(size1);
    }
    
    /**
     * Helper method to check if the size is valid and create a new 
     * array list if the size is valid, Runtime O(1)
     * 
     * @param size the number of elements in the data structure
     * @throws IllegalArgumentException if size is negative
     */
    private void checkSize(int size) {
        // check if the length is valid
        if (size < 0) {
            throw new IllegalArgumentException("ERROR: Illegal length to create queue: " + size);
        }
        arr = new ArrayList<>(size);
    }
    
    /**
     * Helper method that performs the Build Heap Algorithm, Runtime O(n)
     * 
     * @param length the number of elements in the collection
     */
    private void buildHeap(int length) {
        for (int i = Math.max(0, (length / 2) - 1); i >= 0; i--) {
            shiftDown(i);
        }
    }
    
    /**
     * Returns the underlying array that stores the collection of elements,
     * Runtime: O(1)
     *
     * @return the underlying array
     */
    public List<Entry<K, V>> getArray() {
        return arr;
    }
    
    /**
     * {@inheritDoc}, Runtime: O(1)
     */
    @Override
    public int size() {
        return arr.size();
    }

    /**
     * {@inheritDoc}, Runtime: O(1)
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * {@inheritDoc}, Runtime: O(1)
     */
    @Override
    public boolean containsValue(Object value) {
        return map.containsKey(value);
    }

    /**
     * {@inheritDoc}, Runtime: O(lg n)
     */
    @Override
    public boolean add(K key, V value) {
        if (key == null || containsValue(value)) {
            throw new IllegalArgumentException();
        }
        // add the element to the end of the array
        Entry<K, V> entry = new Entry<>(key, value);
        arr.add(entry);
        int index =  size() - 1;
        // update the hashmap
        map.put(value, index);
        
        //shift the element up to the correct position
        shiftUp(index);
        return true;
    }

    /**
     * {@inheritDoc}, Runtime: O(lg n)
     */
    @Override
    public void pushKey(K key, V value) {
        if (!containsValue(value)) {
            throw new NoSuchElementException("ERROR: Queue does not contain that value: " + value);
        }
        int index = map.get(value);
        K oldKey = arr.get(index).key;
        if (key == null || key.compareTo(oldKey) < 0) {
            throw new IllegalArgumentException("ERROR: Invalid key input: " + key);
        }
        Entry<K, V> entry = new Entry<>(key, value);
        // update both arraylist and hashmap
        arr.set(index, entry);
        map.put(value, index);
        // fix the max heap property
        shiftUp(index);
    }

    /**
     * {@inheritDoc}, Runtime: O(1)
     */
    @Override
    public Entry<K, V> peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot peek an empty queue");
        }
        return arr.get(0);
    }

    /**
     * {@inheritDoc}, Runtime: O(lg n)
     */
    @Override
    public Entry<K, V> poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("ERROR: Cannot extract an empty queue");
        }
        int last = size() - 1;
        Entry<K, V> prev = arr.get(0);
        // swap the elements
        swap(0, last);
        
        // delete node from queue
        arr.remove(0);
        map.remove(prev.value);
        
        // fix the max heap property
        shiftDown(0);
        return prev;
    }

    /**
     * {@inheritDoc}, Runtime: O(1)
     */
    @Override
    public Set<V> values() {
        return map.keySet();
    }
    
    /**
     * Helper method that tests if the key of node i >= node j, assumes that i and
     * j are valid indices, Runtime: O(1)
     * 
     * @param i an index in the heap
     * @param j another index in the heap
     * @return true if node.key i >= node.key j, otherwise false
     */
    private boolean max(int i, int j) {
        K value1 = arr.get(i).getKey();
        K value2 = arr.get(j).getKey();
        return value1.compareTo(value2) >= 0;
    }
    /**
     * Helper method that pushes the node up to the correct position in the heap to
     * satisfy the max heap property, assume i is a valid index, Runtime: O(lg n)
     * 
     * @param i an index in the heap
     */
    private void shiftUp(int i) {
        // get the parent index of the current index i
        int parent = (int) (Math.ceil(i / 2.0) - 1);

        // base case: parent < 0 || max(parent, i)
        if (parent >= 0 && !max(parent, i)) {
            // update the array and hashmap
            swap(parent, i);
            // continue to fix the heap
            shiftUp(parent);
        }
    }
    
    /**
     * Helper method that performs MaxHeapfiy operation, pushes the node down to the
     * correct position in the heap to satisfy the max heap property, assume i is a
     * valid index, Runtime: O(lg n)
     * 
     * @param i an index in the heap
     */
    private void shiftDown(int i) {
        int left = 2 * i + 1; // left child index
        int right = 2 * i + 2;
        int max = 0;
        if (left < arr.size() && arr.get(left).key.compareTo(arr.get(i).key) > 0) {
            max = left;
        } else {
            max = i;
        }
        if (right < arr.size() && arr.get(right).key.compareTo(arr.get(max).key) > 0) {
            max = right;
        }
        if (max != i) {
            // update the array and hashmap
            swap(i, max);
            // continue to fix the heap
            shiftDown(max);
        }
    }
    
    /**
     * Helper method to swap the indices in the array and hash map, assume i and j
     * are valid indices, Runtime O(1)
     * 
     * @param i an index in the heap
     * @param j another index in the heap
     */
    private void swap(int i, int j) {
        // get the nodes of the positions
        Entry<K, V> node1 = arr.get(i);
        Entry<K, V> node2 = arr.get(j);
        
        // swap them in the array
        arr.set(i, node2);
        arr.set(j, node1);
        
        // swap them in the map
        map.put(node2.value, i);
        map.put(node1.value, j);
    }
    
    /**
     * {@inheritDoc}, Runtime: O(1)
     */
    @Override
    public void clear() {
        arr = new ArrayList<>();
        map = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<PriorityQueue.Entry<K, V>> iterator() {
        return new Iterator<PriorityQueue.Entry<K, V>>() {
            private int count = 0; // the number of elements in the PriorityQueue visited so far
            // check for concurrent Modification Exception
            private final int expectedSize = arr.size(); 

            /** {@inheritDoc} */
            @Override
            public boolean hasNext() {
                return count < expectedSize;
            }

            /**
             * {@inheritDoc}
             *
             * @throws ConcurrentModificationException if size does not equal expectedSize
             */
            @Override
            public PriorityQueue.Entry<K, V> next() {
                if (expectedSize != arr.size()) {
                    throw new ConcurrentModificationException("ERROR: Cannot modifiy the iterator");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("ERROR: No more elements to iterate");
                } else {
                    return arr.get(count++);
                }
            }

            /** {@inheritDoc} */
            @Override
            public void remove() {
                throw new UnsupportedOperationException(
                        "ERROR: Remove not supported by this iterator");
            }
        };
    }
    
    /**
     * {@inheritDoc}, Runtime: O(n)
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder queue = new StringBuilder(arr.size()).append("[");
            for (int i = 0; i < arr.size() - 1; i++) {
                queue.append(arr.get(i).toString() + ", ");
            }
            return queue.append(arr.get(arr.size() - 1) + "]").toString();
        }
    }
}