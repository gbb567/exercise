package com.gbb.content.sort;

import java.util.Comparator;
import java.util.List;

/**
 * 冒泡
 * @author gaobinbin
 * @date 2020/06/25
 */
public class BubbleSorter<O> extends Sorter<O>{
    private Comparator<O> comparator;
    public BubbleSorter(Comparator<O> comparator){
        this.comparator = comparator;
    }
    @Override
    void doRun(List<O> source) {
        int len = source.size();
        int d_len  = len - 1;
        for(int i=0;i<d_len;i++){
            int dd_len = d_len - i;
            boolean flag = true;
            for(int j=0;j<dd_len;j++){
                if(comparator.compare(source.get(j),source.get(j+1)) > 0){
                    source.set(j,source.set(j+1,source.get(j)));
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
    }
}
