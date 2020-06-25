package com.gbb.content.sort;

import java.util.Comparator;
import java.util.List;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public class InsertSort<O> extends Sorter<O> {
    public InsertSort(Comparator<O> comparator) {
        super(comparator);
    }
    @Override
    void doRun(List<O> source) {
        setList(source);
        int len = source.size();
        for(int i=1;i<len;i++){
            int tempIndex = i;
            for(int j = i - 1;j >= 0;j--){
                if(compare(tempIndex,j)){
                    break;
                }
                swap(tempIndex,j);
                tempIndex = j;
            }
        }
    }
}
