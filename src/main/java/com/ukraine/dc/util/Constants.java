package com.ukraine.dc.util;

/**
 * The Constants.
 */
public final class Constants {

    /**
     * Private constructor.
     */
    private Constants() {}

    public static final String INCORRECT_INDEX =
            "The index shouldn't be less than 0 and greater than collection size: %s";
    public static final String ITERATOR_INCORRECT =
            "Incorrect behavior for the iterator, when called remove() previously next() wasn't called";
    public static final String DATA_IS_NOT_PRESENT = "The data is not present by this identifier: %s";
    public static final String HAS_NOT_NEXT_ELEMENT = "There are no more element in the collection.";
}
