package com.gbb.content.sort;

import java.util.Comparator;
import java.util.List;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public class ShellSort<O> extends Sorter<O> {

    public ShellSort(Comparator<O> comparator) {
        super(comparator);
    }

    @Override
    void doRun(List<O> source) {
        setList(source);
        int len = source.size();
        for(int gap = len>>1;gap>0;gap = gap>>1){
            for(int i=gap;i<len;i++){
                int tempIndex = i;
                for(int j = i - gap;j >= 0;j-=gap){
                    if(compare(tempIndex,j)){
                        break;
                    }
                    swap(tempIndex,j);
                    tempIndex = j;
                }
            }
        }
    }
}
