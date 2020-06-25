package com.gbb.content.sort;

import com.gbb.content.utils.CollectionUtils;

import java.util.Comparator;
import java.util.List;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public abstract class Sorter<O> {
    private Comparator<O> comparator;
    private List<O> source;
    Sorter(Comparator<O> comparator){
        this.comparator = comparator;
    }
    public void run(List<O> source){
        if(CollectionUtils.isEmpty(source)){
            return;
        }
        doRun(source);
    }
    boolean compare(int i,int j){
        return comparator.compare(source.get(i),source.get(j)) > 0;
    }
    void swap(int i,int j){
        source.set(i,source.set(j,source.get(i)));
    }
    void setList(List<O> source){
        this.source = source;
    }
    abstract void doRun(List<O> source);
}
