package com.ukraine.dc.list;

import com.ukraine.dc.list.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class ListTest<T> {
    List<String> list;

    @BeforeEach
    void setUp() {
        list = getList();
    }

    protected abstract List<String> getList();

    @Test
    void testAddMethodWhenTheListIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("1");
        assertEquals(1, list.size());
    }

    @Test
    void testAddMethodWithNulls() {
        assertTrue(list.isEmpty());
        list.add(null);
        list.add(null);
        assertEquals(2, list.size());
    }

    @Test
    void whenWeAddAtFirstPositionOfTheList() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        list.add(0, "3");
        assertEquals(3, list.size());
        assertEquals("3", list.get(0));
    }

    @Test
    void whenWeOnLastPositionOfTheList() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        list.add(2, "3");
        assertEquals(3, list.size());
        assertEquals("3", list.get(2));
    }

    @Test
    void testAddMethodWhenWeAddDataInMiddleOfTheList() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        list.add("3");
        list.add(1, "4");
        assertEquals(4, list.size());
        assertEquals("4", list.get(1));
        assertEquals("2", list.get(2));
        assertEquals("3", list.get(3));
    }

    @Test
    void testAddMethodAndSpecifyIncorrectIndex() {
        assertTrue(list.isEmpty());
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.add(10, "10"));
    }

    @Test
    void testRemoveMethodWhenListContainsOnlyOneRecord() {
        assertTrue(list.isEmpty());
        list.add("1");
        assertEquals(1, list.size());
        list.remove(0);
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveMethodWhenRemoveTheTail() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(3, list.size());
        list.remove(2);
        assertEquals(2, list.size());
    }

    @Test
    void testRemoveMethodBySpecifyingInvalidIndex() {
        assertTrue(list.isEmpty());
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(10));
    }

    @Test
    void testRemoveHeadElement() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(3, list.size());
        list.remove(0);
        assertEquals(2, list.size());
        assertEquals("2", list.get(0));
    }

    @Test
    void testGetMethodBySpecifyingInvalidIndex() {
        assertTrue(list.isEmpty());
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(100));
    }

    @Test
    void testGetMethod() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(3, list.size());
        assertEquals("2", list.get(1));
    }

    @Test
    void testSetMethodBySpecifyingInvalidIndex() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        list.set(1, "22");
        assertEquals("22", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    void testContainsMethodOnList() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        assertEquals(2, list.size());
        assertTrue(list.contains("1"));
        assertFalse(list.contains("111"));
    }

    @Test
    void testIndexOfOnList() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        list.add("2");
        assertEquals(1, list.indexOf("2"));
    }

    @Test
    void testIndexOfWithNulls() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add(null);
        list.add("3");
        list.add(null);
        assertEquals(1, list.indexOf(null));
    }

    @Test
    void testIndexOfWithNonExistsElement() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        assertEquals(-1, list.indexOf("10"));
    }


    @Test
    void testLastIndexOf() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("2");
        assertEquals(3, list.lastIndexOf("2"));
    }

    @Test
    void testLastIndexOfWithNulls() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add(null);
        list.add("3");
        list.add(null);
        assertEquals(3, list.lastIndexOf(null));
    }

    @Test
    void testLastIndexOfElementWithNonExists() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        assertEquals(-1, list.lastIndexOf("10"));
    }

    @Test
    void testClearMethod() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        assertEquals(2, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void testIteratorHasNextMethodOnList() {
        assertTrue(list.isEmpty());
        list.add("1");
        assertEquals(1, list.size());
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testIteratorHasNextOnEmptyList() {
        assertTrue(list.isEmpty());
        Iterator<String> iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testHasNextWithNextMethodUsingIteratorOnList() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("1", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("2", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testNextOnEmptyCollection() {
        assertTrue(list.isEmpty());
        Iterator<String> iterator = list.iterator();
        Assertions.assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testRemoveMethodUsingIteratorOnList() {
        assertTrue(list.isEmpty());
        list.add("1");
        Iterator<String> iterator = list.iterator();
        assertEquals(1, list.size());
        assertTrue(iterator.hasNext());
        assertEquals("1", iterator.next());
        iterator.remove();
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveOnCaseWhenCallRemoveBeforeNext() {
        assertTrue(list.isEmpty());
        list.add("1");
        Iterator<String> iterator = list.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    void testToString() {
        assertTrue(list.isEmpty());
        list.add("1");
        list.add("2");
        assertEquals("[1, 2]", list.toString());
    }
}
