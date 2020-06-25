package com.gbb.content.sort;

import java.util.Comparator;
import java.util.List;

/**
 * 冒泡
 * @author gaobinbin
 * @date 2020/06/25
 */
public class BubbleSorter<O> extends Sorter<O>{
    public BubbleSorter(Comparator<O> comparator){
        super(comparator);
    }
    @Override
    void doRun(List<O> source) {
        setList(source);
        int len = source.size();
        int d_len  = len - 1;
        for(int i=0;i<d_len;i++){
            int dd_len = d_len - i;
            boolean flag = true;
            for(int j=0;j<dd_len;j++){
                if(compare(j,j+1)){
                    swap(j,j+1);
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
    }
}
