import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImplTest {
    private ArrayImpl<Integer> empty;
    private ArrayImpl<Integer> singleton;

    @Before
    public void setupTestArrays() {
        empty = new ArrayImpl<>();

        singleton = new ArrayImpl<>();
        singleton.add(1);
    }

    @Test
    public void testConstructor() {
        assertEquals(2, ((Object[]) empty.getArray()).length);
        assertEquals(0, empty.size());
        assertTrue(empty.isEmpty());
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testAnotherConstructor() {
        final ArrayImpl<Integer> array = new ArrayImpl<>(16);
        assertEquals(16, ((Object[]) array.getArray()).length);
        assertEquals(0, array.size());
        assertTrue(array.isEmpty());
        assertEquals("[]", array.toString());
    }

    @Test
    public void testGetEmptyArray() {
        assertEquals(2, ((Object[]) empty.getArray()).length);
        assertNull(((Object[]) empty.getArray())[0]);
        assertNull(((Object[]) empty.getArray())[1]);
        assertEquals(0, empty.size());
        assertTrue(empty.isEmpty());
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testGetNonEmptyArray() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(5);
        array.add(1);
        array.add(3);
        assertEquals(4, ((Object[]) array.getArray()).length);
        assertEquals(5, ((Object[]) array.getArray())[0]);
        assertEquals(1, ((Object[]) array.getArray())[1]);
        assertEquals(3, ((Object[]) array.getArray())[2]);
        assertNull(((Object[]) array.getArray())[3]);
        assertEquals(3, array.size());
        assertFalse(array.isEmpty());
        assertEquals("[5, 1, 3]", array.toString());
    }

    @Test
    public void testEmptyArraySize() {
        assertEquals(0, empty.size());
        assertTrue(empty.isEmpty());
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testNonEmptyArraySize() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(9);
        array.add(5);
        array.add(3);
        assertEquals(3, array.size());
        assertFalse(array.isEmpty());
        assertEquals("[9, 5, 3]", array.toString());
    }

    @Test
    public void testIsEmptyTrue() {
        assertTrue(empty.isEmpty());
        assertEquals(0, empty.size());
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testIsEmptyFalse() {
        assertFalse(singleton.isEmpty());
        assertEquals(1, singleton.size());
        assertEquals("[1]", singleton.toString());
    }

    @Test
    public void testContainsElementNotPresent() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(1);
        array.add(3);
        assertFalse(array.contains(4));
        assertEquals("[1, 3]", array.toString());
        assertEquals(2, array.size());
    }

    @Test
    public void testContainsElementPresent() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(3);
        array.add(1);
        assertTrue(array.contains(1));
        assertEquals("[3, 1]", array.toString());
        assertEquals(2, array.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNegativeIndex() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(3);
        array.get(-1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetOutOfBoundsPositiveIndex() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(3);
        array.get(6);
    }

    @Test
    public void testGetIndecies() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(2);
        array.add(7);
        assertEquals(3, (int) array.get(0));
        assertEquals(4, (int) array.get(1));
        assertEquals(5, (int) array.get(2));
        assertEquals(2, (int) array.get(3));
        assertEquals(7, (int) array.get(4));
        assertEquals(5, array.size());
        assertFalse(array.isEmpty());
        assertEquals("[3, 4, 5, 2, 7]", array.toString());
    }

    @Test
    public void testIndexOfNonPresentElement() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(3);
        array.add(5);
        assertEquals(-1, array.indexOf(7));
        assertEquals(2, array.size());
        assertEquals("[3, 5]", array.toString());
    }

    @Test
    public void testIndexOfPresentElement() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(3);
        array.add(5);
        assertEquals(0, array.indexOf(3));
        assertEquals(1, array.indexOf(5));
        assertEquals(2, array.size());
        assertEquals("[3, 5]", array.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeIndex() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(1);
        array.add(2);
        array.set(-1, 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetOutOfBoundsPositiveIndex() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(1);
        array.add(2);
        array.set(3, 4);
    }

    @Test
    public void testSetElements() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(2);
        array.add(7);
        array.set(0, 2);
        array.set(3, 3);
        assertEquals(5, array.size());
        assertEquals("[2, 4, 5, 3, 7]", array.toString());
    }

    @Test
    public void testAddCreateSingleton() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(1);
        assertEquals(1, array.size());
        assertEquals(2, ((Object[]) array.getArray()).length);
        assertEquals(1, ((Object[]) array.getArray())[0]);
        assertNull(((Object[]) array.getArray())[1]);
        assertFalse(array.isEmpty());
        assertEquals("[1]", array.toString());
    }

    @Test
    public void testAddExpandArray() {
        final ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(2);
        array.add(7);
        assertEquals(5, array.size());
        assertFalse(array.isEmpty());
        assertEquals(8, ((Object[]) array.getArray()).length);
        assertEquals(3, ((Object[]) array.getArray())[0]);
        assertEquals(4, ((Object[]) array.getArray())[1]);
        assertEquals(5, ((Object[]) array.getArray())[2]);
        assertEquals(2, ((Object[]) array.getArray())[3]);
        assertEquals(7, ((Object[]) array.getArray())[4]);
        assertNull(((Object[]) array.getArray())[5]);
        assertNull(((Object[]) array.getArray())[6]);
        assertNull(((Object[]) array.getArray())[7]);
        assertEquals("[3, 4, 5, 2, 7]", array.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtNegativeIndex() {
        ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(1);
        array.remove(-4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtOutOfBoundsPositveIndex() {
        ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(1);
        array.add(2);
        array.remove(2);
    }

    @Test
    public void testRemoveAt() {

    }

    @Test
    public void testRemove() {

    }

    @Test
    public void testClear() {
        ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(4);
        array.add(6);
        array.add(13);
        array.add(2);
        array.clear();
        assertEquals(0, array.size());
        assertTrue(array.isEmpty());
        assertFalse(array.contains(4));
        assertFalse(array.contains(6));
        assertFalse(array.contains(13));
        assertFalse(array.contains(2));
        assertEquals("[]", array.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorEmptyArrayCallNext() {
        ArrayImpl<Integer> array = new ArrayImpl<>();
        Iterator<Integer> arrayIter = array.iterator();
        arrayIter.next();
    }

    @Test
    public void testIteratorCallNext() {
        ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(2);
        array.add(4);
        array.add(5);
        array.add(1);
        array.add(2);
        array.add(16);
        Iterator<Integer> arrayIter = array.iterator();
        arrayIter.hasNext();
        arrayIter.hasNext();
        arrayIter.hasNext();
        arrayIter.hasNext();
        arrayIter.hasNext();
        arrayIter.hasNext();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorCallNextTooManyTimes() {
        ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(2);
        array.add(4);
        array.add(5);
        array.add(1);
        array.add(2);
        array.add(16);
        Iterator<Integer> arrayIter = array.iterator();
        arrayIter.next();
        arrayIter.next();
        arrayIter.next();
        arrayIter.next();
        arrayIter.next();
        arrayIter.next();
        arrayIter.next();
    }

    @Test
    public void testToStringEmptyArray() {
        ArrayImpl<Integer> array = new ArrayImpl<>();
        assertEquals("[]", array.toString());
    }

    @Test
    public void testToStringNonEmptyArray() {
        ArrayImpl<Integer> array = new ArrayImpl<>();
        array.add(1);
        array.add(5);
        array.add(8);
        array.add(9);
        array.add(10);
        assertEquals("[1, 5, 8, 9, 10]", array.toString());
    }
}