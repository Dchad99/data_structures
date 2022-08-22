package com.ukraine.dc.list.impl;

import com.ukraine.dc.list.List;
import java.util.StringJoiner;

import static com.ukraine.dc.util.Constants.INCORRECT_INDEX;
import static java.lang.String.format;

public abstract class AbstractList<T> implements List<T> {
    protected int size;

    protected void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(format(INCORRECT_INDEX, size));
        }
    }

    protected String toString(List<T> collection) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            joiner.add(collection.get(i).toString());
        }
        return joiner.toString();
    }
}
