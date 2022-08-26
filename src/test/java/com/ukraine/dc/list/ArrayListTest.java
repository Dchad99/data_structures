package com.ukraine.dc.list;

import com.ukraine.dc.list.impl.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayListTest<T> extends ListTest<T> {

    @Override
    protected List<String> getList() {
        return new ArrayList<>();
    }

    @Test
    void testExpandArrayMethod() {
        List<Integer> list = new ArrayList<>(10);
        for (int i = 0; i < 25; i++) {
            list.add(i);
        }
        assertEquals(25, list.size());
    }

}