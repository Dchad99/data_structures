package com.ukraine.dc;

import com.ukraine.dc.list.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


class ArrayListTest {
    private List<String> array;

    @BeforeEach
    void setUp() {
        array = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        array.clear();
    }

    @Test
    void testAddMethod() {
        assertTrue(array.isEmpty());
        array.add("1");
        assertEquals(1, array.size());
        array.add("2");
        assertEquals(2, array.size());
    }

    @Test
    void testAddByIndexMethod() {
        assertTrue(array.isEmpty());
        array.add("1");
        assertEquals(1, array.size());
        array.add("2");
        assertEquals(2, array.size());
        array.add(1, "3");
        assertEquals(3, array.size());
        assertEquals("3", array.get(1));
    }

    @Test
    void testAddByIndexWithIncorrectIndex() {
        assertTrue(array.isEmpty());
        array.add("1");
        array.add("2");
        assertEquals(2, array.size());
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> array.add(100, "100"));
    }

    @Test
    void testAddWhenWeAlreadyFulfillWithDataDefaultCapacity() {
        assertTrue(array.isEmpty());
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");
        array.add("6");
        assertEquals(6, array.size());
    }

    @Test
    void testRemoveMethod() {
        assertTrue(array.isEmpty());
        array.add("1");
        array.add("2");
        assertEquals(2, array.size());
        String prev = array.remove(1);
        assertEquals(1, array.size());
        assertEquals("2", prev);
    }

    @Test
    void testRemoveBySpecifyingWrongIndex() {
        assertTrue(array.isEmpty());
        array.add("1");
        array.add("2");
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> array.remove(3));
    }

    @Test
    void testGetByIndexMethod() {
        assertTrue(array.isEmpty());
        array.add("1");
        array.add("2");
        assertEquals("2", array.get(1));
    }

    @Test
    void testGetByIndexWithWrongIndex() {
        assertTrue(array.isEmpty());
        array.add("1");
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> array.get(10));
    }

    @Test
    void testSetMethod() {
        assertTrue(array.isEmpty());
        array.add("1");
        array.add("2");
        assertEquals(2, array.size());
        String prev = array.set(1, "22");
        assertEquals(2, array.size());
        assertEquals("2", prev);
        assertEquals("22", array.get(1));
    }

    @Test
    void testSetMethodBySpecifyingInvalidIndex() {
        assertTrue(array.isEmpty());
        array.add("1");
        array.add("2");
        assertEquals(2, array.size());
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> array.set(5, "5"));
    }

    @Test
    void testClearMethod() {
        assertTrue(array.isEmpty());
        array.add("1");
        array.add("2");
        assertEquals(2, array.size());
        array.clear();
        assertEquals(0, array.size());
    }

    @Test
    void testCollectionContains() {
        assertTrue(array.isEmpty());
        array.add("1");
        array.add("2");
        assertEquals(2, array.size());
        assertTrue(array.contains("2"));
    }

    @Test
    void testContainsMethodWithNonExistingData() {
        assertTrue(array.isEmpty());
        array.add("1");
        assertEquals(1, array.size());
        assertFalse(array.contains("David"));
    }

    @Test
    void testIndexOfMethod() {
        assertTrue(array.isEmpty());
        array.add("1");
        array.add("2");
        array.add("1");
        assertEquals(3, array.size());
        assertEquals(0, array.indexOf("1"));
    }

    @Test
    void testIndexOfWithNulls() {
        assertTrue(array.isEmpty());
        array.add(null);
        array.add("1");
        array.add(null);
        assertEquals(3, array.size());
        assertEquals(0, array.indexOf(null));
    }

    @Test
    void testIndexOfOnNonExistingItem() {
        assertTrue(array.isEmpty());
        array.add("1");
        assertEquals(1, array.size());
        assertEquals(-1, array.indexOf("David"));
    }

    @Test
    void testLastIndexOf() {
        assertTrue(array.isEmpty());
        array.add("1");
        array.add("2");
        array.add("2");
        assertEquals(3, array.size());
        assertEquals(2, array.lastIndexOf("2"));
    }

    @Test
    void testLastIndexOfWithNulls() {
        assertTrue(array.isEmpty());
        array.add(null);
        array.add("1");
        array.add(null);
        assertEquals(3, array.size());
        assertEquals(2, array.lastIndexOf(null));
    }

    @Test
    void testLastIndexOfOnNonExistingValue() {
        assertTrue(array.isEmpty());
        array.add(null);
        array.add("1");
        array.add(null);
        assertEquals(3, array.size());
        assertEquals(-1, array.lastIndexOf("David"));
    }

    @Test
    void testIteratorHasNext() {
        assertTrue(array.isEmpty());
        array.add("1");
        Iterator<String> iterator = array.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorHasNextWhenCollectionIsEmpty() {
        assertTrue(array.isEmpty());
        Iterator<String> iterator = array.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorRemoveMethod() {
        assertTrue(array.isEmpty());
        array.add("1");
        Iterator<String> iterator = array.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        iterator.remove();
        assertFalse(iterator.hasNext());
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, iterator::remove);
        assertTrue(array.isEmpty());
    }

}