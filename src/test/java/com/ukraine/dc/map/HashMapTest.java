package com.ukraine.dc.map;

import org.junit.jupiter.api.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    private Map<String, String> map;

    @BeforeEach
    void setUp() {
        map = new HashMap<>();
    }

    @Test
    @DisplayName("Test put(k,v) new element to the collection with same key, multiply times.")
    void givenNullKeyWhenPutMultipleTimesThenSizeShouldBeEqualToOneAndValueShouldBeOverwrittenWithLast() {
        map.put(null, "value1");
        map.put(null, "value2");
        map.put(null, "value3");

        assertEquals(1, map.size());
        assertEquals("value3", map.get(null));
    }

    @Test
    @DisplayName("Test clear() method on collection.")
    void shouldClearTheCollection() {
        map.put("key1", "val1");
        map.put("key2", "val1");
        map.put("key3", "val1");
        map.put("key4", "val1");
        assertEquals(4, map.size());
        map.clear();
        assertEquals(0, map.size());
        assertNull(map.get(null));
    }

    @Test
    @DisplayName("Test get(null) on empty map.")
    void givenEmptyMapWhenGetByNullKeyThenNullShouldBeReturned() {
        assertNull(map.get(null));
    }

    @Test
    @DisplayName("Test get(key) on empty map.")
    void givenEmptyMapWhenGetByNotNullKeyThenNullShouldBeReturned() {
        assertNull(map.get("key"));
    }

    @Test
    @DisplayName("Test put(null, 'val') then check size and added element.")
    void givenNullKeyWhenPutOnceThenSizeShouldBeEqualToOneAndValueShouldBeEqualToInserted() {
        map.put(null, "test");
        assertEquals(1, map.size());
        assertEquals("test", map.get(null));
    }

    @Test
    @DisplayName("Test put('key', 'val') then check size and added element.")
    void givenNotNullKeyWhenPutThenSizeShouldBeEqualToOneAndValueShouldBeEqualToInserted() {
        map.put("key", "value");
        assertEquals(1, map.size());
        assertEquals("value", map.get("key"));
    }

    @Test
    @DisplayName("Test put() multiple times with non null keys, check size and added elements.")
    void givenMultipleNotNullKeysWhenPutThenSizeShouldBeEqualToSizeOfKeysAndGetByKeyReturnsCorrespondingValue() {
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertEquals(2, map.size());
        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
    }

    @Test
    void shouldReturnNullOnGetByNonExistingKey() {
        map.put("existingKey", "value1");
        assertNull(map.get("notExistingKey"));
    }

    @Test
    void shouldReturnNullOnGetCallWhenMapIsEmpty() {
        assertNull(map.get("key1"));
    }

    @Test
    void shouldAddOneElementToMapByTheSameKey_ValueWillBeOverWrittenTwoTimesTheLastValueWillPresent() {
        map.put("key", "val1");
        map.put("key", "val2");
        map.put("key", "val3");

        assertEquals(1, map.size());
        assertEquals("val3", map.get("key"));
    }

    @Test
    void givenEmptyMapWhenRemoveByNullKeyThenSizeShouldBeEqualToZero() {
        map.remove(null);
        assertEquals(0, map.size());
    }

    @Test
    void givenNotEmptyMapWhenRemoveByNullKeyThenSizeShouldBeEqualToZero() {
        map.put(null, "value");
        map.remove(null);

        assertEquals(0, map.size());
    }

    @Test
    void givenNotEmptyMapWithNotNullKeyWhenPutWithNullKeyAndRemoveByNullKeyThenSizeShouldDecreaseByOne() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "value");
        map.put("notNullKey", "value");

        assertEquals(2, map.size());
        map.remove(null);
        assertEquals(1, map.size());
    }

    @Test
    void givenNotEmptyMapWhenRemoveOneByOneThenSizeShouldDecreaseAfterEachRemovalByOne() {
        map.put("key1", "val1");
        map.put("key2", "val2"); //case: remove first node
        map.put("key3", "val3"); //case: remove last node

        assertEquals(3, map.size());

        map.remove("key1");
        assertEquals(2, map.size());

        map.remove("key2");
        assertEquals(1, map.size());

        map.remove("key3");
        assertEquals(0, map.size());
    }

    @Test
    void givenNotEmptyMapWhenRemoveRandomlyThenSizeShouldDecreaseAfterEachRemovalByOne() {
        map.put("key1", "val1");
        map.put("key2", "val2"); //case: remove first node
        map.put("key3", "val3"); //case: remove last node

        assertEquals(3, map.size());

        map.remove("key1");
        assertEquals(2, map.size());

        map.remove("key3");
        assertEquals(1, map.size());

        map.remove("key2");
        assertEquals(0, map.size());

    }

    @Test
    void givenEmptyMapWhenContainsNullKeyThenFalseShouldBeReturned() {
        assertFalse(map.containsKey(null));
    }

    @Test
    void givenEmptyMapWhenContainsNotNullKeyThenFalseShouldBeReturned() {
        assertFalse(map.containsKey("key"));
    }

    @Test
    void givenMapWithExistingNullKeyWhenContainsNullKeyThenTrueShouldBeReturned() {
        map.put(null, "value");
        assertTrue(map.containsKey(null));
    }

    @Test
    void givenMapWithExistingNotNullKeyWhenContainsNullKeyThenTrueShouldBeReturned() {
        map.put("key", "value");
        assertTrue(map.containsKey("key"));
    }

    @Test
    void givenExistingKeyWhenContainsKeyThenTrueShouldBeReturned() {
        map.put("key", "value");
        assertFalse(map.containsKey("notExistingKey"));
    }

    @Test
    void givenMultipleNodesInSameBucketAndExistingKeyWhenContainsByKeyThenTrueShouldBeReturned() {
        map.put("key1", "val1");
        map.put("key2", "val2");
        map.put("key3", "val3");
        map.put("key4", "val4");

        assertTrue(map.containsKey("key1"));
        assertTrue(map.containsKey("key2"));
        assertTrue(map.containsKey("key3"));
        assertTrue(map.containsKey("key4"));
    }

    @Test
    void givenEmptyMapWhenIteratorNextThenNoSuchElementExceptionShouldBeRaised() {
        Exception exception = Assertions.assertThrows(NoSuchElementException.class,
                () -> new HashMap<>().iterator().next());
        assertEquals("There are no more element in the collection.", exception.getMessage());
    }

    @Test
    void givenIteratorWhenNextAfterLastElementThenNoSuchElementExceptionShouldBeRaised() {
        map.put("key", "value");
        Iterator<Map.Entry<String, String>> iterator = map.iterator();

        Map.Entry<String, String> entry = iterator.next();
        assertEquals("key", entry.getKey());
        assertEquals("value", entry.getValue());
        Exception exception = assertThrows(NoSuchElementException.class, iterator::next);
        assertEquals("There are no more element in the collection.", exception.getMessage());
    }

    @Test
    void givenEmptyMapWhenIteratorHasNextThenShouldReturnFalse() {
        assertFalse(map.iterator().hasNext());
    }

    @Test
    void givenMapWithTwoElementsWhenIteratorNextThenIteratorHasNextShouldReturnFalse() {
        map.put("key", "value");
        map.put("key2", "value");

        var iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    void givenMapWithOneElementWhenIteratorNextThenIteratorHasNextShouldReturnFalse() {
        map.put("key", "value");
        var iterator = map.iterator();

        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    void givenEmptyMapWhenIteratorRemoveThenNoSuchElementExceptionShouldBeRaised() {
        Exception exception = assertThrows(IllegalStateException.class, () -> new HashMap<>().iterator().remove());
        assertEquals("Incorrect behavior for the iterator, when called remove() previously next() wasn't called", exception.getMessage());
    }

    @Test
    void givenIteratorWhenRemoveCalledWithoutNextThenIllegalStateExceptionShouldBeRaised() {
        map.put("key", "value");
        assertEquals(1, map.size());
        Exception exception = Assertions.assertThrows(IllegalStateException.class,
                () -> map.iterator().remove());
        assertEquals("Incorrect behavior for the iterator, when called remove() previously next() wasn't called", exception.getMessage());
    }

    @Test
    void givenIteratorWhenRemoveCalledAfterNextThenSizeShouldBeDecreasedByOneAndMapShouldNotContainKey() {
        map.put("key", "value");
        assertEquals(1, map.size());

        var iterator = map.iterator();
        iterator.next();
        iterator.remove();

        assertEquals(0, map.size());
        assertFalse(map.containsKey("key"));
    }

    @Test
    void givenNotEmptyMapPerformRemoveAndCheckSize() {
        map.put(null, "val1");
        assertEquals(1, map.size());
        assertEquals("val1", map.get(null));
        assertEquals("val1", map.remove(null));
        assertEquals(0, map.size());
    }

    @Test
    void givenMapIsFullShouldCallExpandAndIncreasedBucketsSize() {
        for (int i = 0; i < 120; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        map.put(null, null);
        assertEquals(121, map.size());
    }

}
