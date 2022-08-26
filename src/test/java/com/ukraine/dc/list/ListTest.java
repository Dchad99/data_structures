package com.ukraine.dc.list;

import com.ukraine.dc.list.impl.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class ListTest<T> {
    private List<String> list;

    @BeforeEach
    void setUp() {
        list = getList();
    }

    protected abstract List<String> getList();

    @Test
    @DisplayName("Test add(), then check size, and presence in the collection.")
    void shouldAddElementToCollectionAndIncreasedSize() {
        list.add("1");
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Test add() with collection of null values")
    void shouldAddMultipleNullValuesAndIncreasedTheCollection() {
        list.add(null);
        list.add(null);
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Test add() method when we add data to first position.")
    void shouldAddNewElementOnFirstPositionOfTheList() {
        list.add("1");
        list.add("3");
        list.add( "2", 0);
        assertEquals(3, list.size());
        assertEquals("2", list.get(0));
        assertEquals("1", list.get(1));
        assertEquals("3", list.get(2));
    }

    @Test
    @DisplayName("Test get() with incorrect index.")
    void shouldThrowIndexOutOfBoundExceptionWhenSpecifyingInvalidIndex() {
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(10));
        assertTrue(exception.getMessage().contains("Incorrect index"));
    }

    @Test
    @DisplayName("Test add() by index to the last position of the list.")
    void shouldAddTheLastElementToCollection() {
        list.add("1");
        list.add("2");
        list.add("3", 2);
        assertEquals(3, list.size());
        assertEquals("3", list.get(2));
    }

    @Test
    @DisplayName("Test add() method, then check size and elements that are present in collection.")
    void shouldAddElementByIndexAndShiftOneElementToRight() {
        list.add("2");
        list.add("3");
        list.add("4", 1);
        assertEquals(3, list.size());
        assertEquals("3", list.get(2));
        assertEquals("4", list.get(1));
        assertEquals("2", list.get(0));
    }

    @Test
    @DisplayName("Test add() method, try to add by invalid index.")
    void shouldThrowIndexOutOfBoundExceptionWhenIndexInvalid() {
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.add("10", 10));
        assertTrue(exception.getMessage().contains("Incorrect index"));
    }

    @Test
    @DisplayName("Test remove() method on list with one element.")
    void shouldRemoveElementByIndexMultipleTimesAndDecreasedTheSize() {
        list.add("1");
        assertEquals(1, list.size());
        list.remove(0);
        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("Test remove() on collection, then check collection")
    void shouldRemoveTheLastElementAndDecreasedSize() {
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(3, list.size());
        list.remove(2);
        assertEquals(2, list.size());
        assertEquals("1", list.get(0));
        assertEquals("2", list.get(1));
    }

    @Test
    @DisplayName("Test remove() with non existing element.")
    void shouldThrowIndexOutOfBoundsExceptionWhenInvalidIndex() {
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(10));
        assertTrue(exception.getMessage().contains("Incorrect index"));
    }

    @Test
    @DisplayName("Test remove() first element on collection.")
    void shouldRemoveHeadElementAndDecreasedSize() {
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(3, list.size());
        list.remove(0);
        assertEquals(2, list.size());
        assertEquals("2", list.get(0));
        assertEquals("3", list.get(1));
    }

    @Test
    @DisplayName("Test get() with non existing index.")
    void shouldThrowIndexOutOfBoundsExceptionWhenCallGetWithInvalidIndex() {
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(100));
        assertTrue(exception.getMessage().contains("Incorrect index"));
    }

    @Test
    @DisplayName("Test get() method on collection.")
    void shouldReturnElementByIndex() {
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(3, list.size());
        assertEquals("2", list.get(1));
        assertEquals("1", list.get(0));
        assertEquals("3", list.get(2));
    }

    @Test
    @DisplayName("Test set() then check update element.")
    void testSetMethodThenCheckUpdatedElement() {
        list.add("1");
        list.add("2");
        list.set("22", 1);
        assertEquals("22", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Test set() method by specifying incorrect index.")
    void givenEmptyCollectionCallSetMethodWithInvalidIndex() {
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.set("10", 10));
        assertTrue(exception.getMessage().contains("Incorrect index"));
    }

    @Test
    @DisplayName("Test contains() with valid data.")
    void shouldReturnTrueWhenCallContainsWithValidIndex() {
        list.add("1");
        list.add("2");
        assertEquals(2, list.size());
        assertTrue(list.contains("1"));
    }

    @Test
    @DisplayName("Test contains() with invalid data.")
    void shouldReturnFalseWhenCallContainsWithValidIndex() {
        list.add("1");
        list.add("2");
        assertEquals(2, list.size());
        assertFalse(list.contains("111"));
    }

    @Test
    @DisplayName("Test indexOf() on collection with a few matches.")
    void shouldReturnTheFirstMatchWhenCallIndexOfWithValidIndex() {
        list.add("1");
        list.add("2");
        list.add("2");
        assertEquals(1, list.indexOf("2"));
    }

    @Test
    @DisplayName("Test indexOf() on collection with null values including a few matches.")
    void shouldReturnTheFirstNullMatchWhenCallIndexOfWithValidIndex() {
        list.add("1");
        list.add(null);
        list.add("3");
        list.add(null);
        assertEquals(1, list.indexOf(null));
    }

    @Test
    @DisplayName("Test indexOf() with value that is not present in collection.")
    void shouldReturnExceptionalCaseWhenValueIsNotPresent() {
        list.add("1");
        list.add("2");
        assertEquals(-1, list.indexOf("10"));
    }

    @Test
    @DisplayName("Test lastIndexOf() on collection with a few matches.")
    void shouldReturnTheFirstMatchFromTheEndWhenCallLastIndexOfWithValidIndex() {
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("2");
        assertEquals(3, list.lastIndexOf("2"));
    }

    @Test
    @DisplayName("Test lastIndexOf() on collection with null values including a few matches.")
    void shouldReturnNullFirstNullValueMatchFromTheEndWhenCallLastIndexOfWithValidIndex() {
        list.add("1");
        list.add(null);
        list.add("3");
        list.add(null);
        assertEquals(3, list.lastIndexOf(null));
    }

    @Test
    @DisplayName("Test lastIndexOf() when specify invalid value.")
    void shouldReturnExceptionalCaseWhenCallLastIndexOfWithInValidIndex() {
        list.add("1");
        list.add("2");
        assertEquals(-1, list.lastIndexOf("10"));
    }

    @Test
    @DisplayName("Test clear method.")
    void shouldCleanCollection() {
        list.add("1");
        list.add("2");
        assertEquals(2, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("Test iterator hasNext() on non-empty collection.")
    void shouldReturnTrueWhenCallHasNextOnNonEmptyCollection() {
        list.add("1");
        assertEquals(1, list.size());
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Test hasNext() on empty collection.")
    void shouldReturnFalseWhenCallHasNextOnEmptyCollection() {
        Iterator<String> iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Test iterator next() method multiple times.")
    void shouldTestHashNextInCombinationWithNextOnIterator() {
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
    @DisplayName("Test iterator next() on empty collection.")
    void shouldThrowNoSuchElementExceptionWhenCallNextOnEmptyCollection() {
        Iterator<String> iterator = list.iterator();
        Exception exception = Assertions.assertThrows(NoSuchElementException.class, iterator::next);
        assertEquals("There are no more element in the collection.", exception.getMessage());
    }

    @Test
    @DisplayName("Test iterator hasNext()/next()/remove() in combination.")
    void testRemoveMethodUsingIteratorOnList() {
        list.add("1");
        Iterator<String> iterator = list.iterator();
        assertEquals(1, list.size());
        assertTrue(iterator.hasNext());
        assertEquals("1", iterator.next());
        iterator.remove();
        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("Test iterator remove() method, when called removed() while next() wasn't called.")
    void shouldThrowIllegalStateExceptionWhenCallRemoveAndPreviouslyNextNotCalled() {
        list.add("1");
        Iterator<String> iterator = list.iterator();
        Exception exception = Assertions.assertThrows(IllegalStateException.class, iterator::remove);
        assertEquals("Incorrect behavior for the iterator, when called remove() previously next() wasn't called",
                exception.getMessage());
    }

    @Test
    @DisplayName("Test toString() method with null values.")
    void testToString() {
        list.add("1");
        list.add("2");
        list.add(null);
        list.add(null);
        assertEquals("[1, 2, null, null]", list.toString());
    }

    @Test
    @DisplayName("Test remove() on the iterator, then check the size and remaining elements")
    void shouldRemoveElementsInCollectionByIterator() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        iterator.remove();
        assertEquals(1, list.size());
        assertEquals(2, list.get(0));
    }

    @Test
    @DisplayName("Test iterator next() and remove() multiple times.")
    void should_removeAllObjectsFromList_when_callRemove() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        var iterator = list.iterator();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        assertEquals(0, list.size());
    }
}
