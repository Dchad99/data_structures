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
    @DisplayName("Test get(key) on empty map")
    void shouldReturnNullOnGetCallWhenMapIsEmpty() {
        assertNull(map.get("key1"));
    }

    @Test
    @DisplayName("Test put() method adding data to the map with the same key")
    void shouldAddOneElementToMapByTheSameKey_ValueWillBeOverWrittenTwoTimesTheLastValueWillPresent() {
        map.put("key", "val1");
        map.put("key", "val2");
        map.put("key", "val3");

        assertEquals(1, map.size());
        assertEquals("val3", map.get("key"));
    }

    @Test
    @DisplayName("Test remove() method on empty map")
    void givenEmptyMapWhenRemoveByNullKeyThenSizeShouldBeEqualToZero() {
        map.remove(null);
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Test remove() on non empty map")
    void givenNotEmptyMapWhenRemoveByNullKeyThenSizeShouldBeEqualToZero() {
        map.put(null, "value");
        assertEquals("[null=value]", map.toString());

        map.remove(null);
        assertEquals(0, map.size());
        assertEquals("[]", map.toString());
    }

    @Test
    @DisplayName("Test not empty map with null/notNull key, check the size, content.")
    void givenNotEmptyMapWithNotNullKeyWhenPutWithNullKeyAndRemoveByNullKeyThenSizeShouldDecreaseByOne() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "value");
        map.put("notNullKey", "value");

        assertEquals("[null=value, notNullKey=value]", map.toString());
        assertEquals(2, map.size());
        map.remove(null);
        assertEquals("[notNullKey=value]", map.toString());
        assertEquals(1, map.size());
    }

    @Test
    @DisplayName("Test remove() method by map key.")
    void givenNotEmptyMapWhenRemoveRandomlyThenSizeShouldDecreaseAfterEachRemovalByOne() {
        map.put("key1", "val1");
        map.put("key2", "val2");
        map.put("key3", "val3");

        assertEquals(3, map.size());

        map.remove("key1");
        assertEquals(2, map.size());

        map.remove("key3");
        assertEquals(1, map.size());

        map.remove("key2");
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Test map.containsKey() by null key on empty map")
    void givenEmptyMapWhenContainsNullKeyThenFalseShouldBeReturned() {
        assertFalse(map.containsKey(null));
    }

    @Test
    @DisplayName("Test map.containsKey() by not null key on empty map")
    void givenEmptyMapWhenContainsNotNullKeyThenFalseShouldBeReturned() {
        assertFalse(map.containsKey("key"));
    }

    @Test
    @DisplayName("Test map.containsKey() by null key on not empty map")
    void givenMapWithExistingNullKeyWhenContainsNullKeyThenTrueShouldBeReturned() {
        map.put(null, "value");
        assertTrue(map.containsKey(null));
    }

    @Test
    @DisplayName("Test map.containsKey() by not null key on not empty map")
    void givenMapWithExistingNotNullKeyWhenContainsNullKeyThenTrueShouldBeReturned() {
        map.put("key", "value");
        assertTrue(map.containsKey("key"));
    }

    @Test
    @DisplayName("Test containsKey() on not empty map with non existing key.")
    void givenExistingKeyWhenContainsKeyThenTrueShouldBeReturned() {
        map.put("key", "value");
        assertFalse(map.containsKey("notExistingKey"));
    }

    @Test
    @DisplayName("Test containsKey() on not empty map")
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
    @DisplayName("Test iterator next() on empty map.")
    void givenEmptyMapWhenIteratorNextThenNoSuchElementExceptionShouldBeRaised() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> new HashMap<>().iterator().next());
        assertEquals("There are no more element in the collection.", exception.getMessage());
    }

    @Test
    @DisplayName("Test iterator next() after last element of the map")
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
    @DisplayName("Test iterator hasNex() on empty map")
    void givenEmptyMapWhenIteratorHasNextThenShouldReturnFalse() {
        assertFalse(map.iterator().hasNext());
    }

    @Test
    @DisplayName("Test iterator hasNext(true)/next/hasNext(true) on map with 2 elements")
    void givenMapWithTwoElementsWhenIteratorNextThenIteratorHasNextShouldReturnFalse() {
        map.put("key", "value");
        map.put("key2", "value");

        var iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Test iterator hasNext(true)/next/hasNext(false) on map with 1 element")
    void givenMapWithOneElementWhenIteratorNextThenIteratorHasNextShouldReturnFalse() {
        map.put("key", "value");
        var iterator = map.iterator();

        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Test iterator method, throws IllegalStateException on empty map")
    void givenEmptyMapWhenIteratorRemoveThenNoSuchElementExceptionShouldBeRaised() {
        Exception exception = assertThrows(IllegalStateException.class, () -> new HashMap<>().iterator().remove());
        assertEquals("Incorrect behavior for the iterator, when called remove() previously next() wasn't called", exception.getMessage());
    }

    @Test
    @DisplayName("Test iterator method, throws IllegalStateException on not empty map")
    void givenIteratorWhenRemoveCalledWithoutNextThenIllegalStateExceptionShouldBeRaised() {
        map.put("key", "value");
        assertEquals(1, map.size());
        Exception exception = Assertions.assertThrows(IllegalStateException.class,
                () -> map.iterator().remove());
        assertEquals("Incorrect behavior for the iterator, when called remove() previously next() wasn't called", exception.getMessage());
    }

    @Test
    @DisplayName("Test iterator hasNext/next/remove then check the size of map")
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
    @DisplayName("Test put/get/remove/size methods together with one element.")
    void givenNotEmptyMapPerformRemoveAndCheckSize() {
        map.put(null, "val1");
        assertEquals(1, map.size());
        assertEquals("val1", map.get(null));
        assertEquals("val1", map.remove(null));
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Test put() when we fill our buckets more then on 75%, expected that bucket size should be increased")
    void givenMapIsFullShouldCallExpandAndIncreasedBucketsSize() {
        for (int i = 0; i < 120; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals(120, map.size());
    }

    @Test
    @DisplayName("Test iterator methods on map with collisions.")
    void givenMapWithCollisions_thenUsingIteratorRemoveEverySecondElement() {
        Map<Integer, String> map = new HashMap<>();
        int key = 0;
        for (int i = 0; i < 8; i++) {
            map.put(key, String.valueOf(i));
            key += 4;
        }
        assertEquals("[0=0, 16=4, 4=1, 20=5, 8=2, 24=6, 12=3, 28=7]", map.toString());
        int index = 0;
        var iterator = map.iterator();
        while (iterator.hasNext()) {
            var item = iterator.next();
            if (index % 2 == 0) {
                iterator.remove();
            }
            index++;
        }
        assertEquals("[16=4, 20=5, 24=6, 28=7]", map.toString());
    }

}
