package com.ukraine.dc.list;

import com.ukraine.dc.list.impl.ArrayList;

class ArrayListTest<T> extends ListTest<T> {

    @Override
    protected List<String> getList() {
        return new ArrayList<>();
    }

}