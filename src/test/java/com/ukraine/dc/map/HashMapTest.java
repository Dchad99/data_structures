package com.ukraine.dc.map;

import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    private Map<Integer, String> map;

    @BeforeEach
    void setUp() {
        map = new HashMap<>();
    }

    @AfterEach
    void tearDown() {
        map.clear();
    }

    @Test
    void testPut() {
        assertEquals(0, map.size());
        map.put(1, "D");
        assertEquals(1, map.size());
    }

    @Test
    void testGetReturnsCorrectData() {
        map.put(1, "D");
        map.put(2, "R");
        assertEquals(2, map.size());
        assertEquals("D", map.get(1));
        assertEquals("R", map.get(2));
    }

    @Test
    void testPutIfKeyAlreadyUsed() {
        assertEquals(0, map.size());
        map.put(1, "D");
        assertEquals(1, map.size());
        map.put(1, "DC");
        assertEquals(1, map.size());
    }

    @Test
    void testGetMethod() {
        assertEquals(0, map.size());
        map.put(1, "D");
        assertEquals("D", map.get(1));
    }

    @Test
    void testGetMethodWithNonExistingData() {
        assertEquals(0, map.size());
        map.put(1, "D");
        Assertions.assertThrows(NoSuchElementException.class, () -> map.get(10));
    }

    @Test
    void testMethodContainsKey() {
        assertEquals(0, map.size());
        map.put(1, "D");
        assertTrue(map.containsKey(1));
    }

    @Test
    void testMethodContainsKeyWithNonExistingData() {
        Assertions.assertThrows(NoSuchElementException.class,
                () -> map.containsKey(11));
    }

    @Test
    void testClearMethod() {
        map.put(1, "D");
        map.put(2, "R");
        assertEquals(2, map.size());
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    void testRemove() {
        map.put(1, "D");
        map.put(2, "R");
        assertEquals(2, map.size());

        map.remove(1);
        assertEquals(1, map.size());
        assertEquals("R", map.get(2));
    }

    @Test
    void testToRemoveElementThatDoesntExist() {
        assertEquals(0, map.size());
        Assertions.assertThrows(NoSuchElementException.class,
                () -> map.remove(1));
    }

    @Test
    void testMapIterator() {
        var iterator = map.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIterator() {
        assertEquals(0, map.size());
        map.put(1, "D");
        var iterator = map.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testIteratorNext() {
        map.put(1, "D");
        var iterator = map.iterator();
        assertTrue(iterator.hasNext());
        var entry = iterator.next();
        assertEquals("D", entry.value());
    }

    @Test
    void testIteratorRemove() {
        map.put(1, "D");
        var iterator = map.iterator();

        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());
        iterator.remove();
        assertEquals(0, map.size());
    }

    @Test
    void testIteratorRemoveWithoutCallingNext() {
        map.put(1, "D");
        var iterator = map.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

}
