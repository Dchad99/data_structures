package com.ukraine.dc;

import com.ukraine.dc.list.List;

public abstract class AbstractList<T> implements List<T> {
    protected int size;

    protected void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
