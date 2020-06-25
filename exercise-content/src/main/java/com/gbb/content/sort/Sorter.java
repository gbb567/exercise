package com.gbb.content.sort;

import com.gbb.content.utils.CollectionUtils;

import java.util.List;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public abstract class Sorter<O> {
    public void run(List<O> source){
        if(CollectionUtils.isEmpty(source)){
            return;
        }
        doRun(source);
    }
    abstract void doRun(List<O> source);
}
