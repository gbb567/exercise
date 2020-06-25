package com.gbb.content.sort;

import java.util.Comparator;
import java.util.List;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public class MergeSort<O> extends Sorter<O> {
    public MergeSort(Comparator<O> comparator) {
        super(comparator);
    }

    @Override
    void doRun(List<O> source) {
        setList(source);
        
    }
}
