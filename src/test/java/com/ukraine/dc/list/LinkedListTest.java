package com.ukraine.dc.list;

import com.ukraine.dc.list.impl.LinkedList;

class LinkedListTest<T> extends ListTest<T> {

    @Override
    protected List<String> getList() {
        return new LinkedList<>();
    }

}