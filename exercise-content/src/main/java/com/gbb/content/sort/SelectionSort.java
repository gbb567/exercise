package com.gbb.content.sort;

import org.omg.IOP.RMICustomMaxStreamFormat;

import java.util.Comparator;
import java.util.List;

/**
 * 选择排序
 * @author gaobinbin
 * @date 2020/06/25
 */
public class SelectionSort<O> extends Sorter<O>{
    public SelectionSort(Comparator<O> comparator){
        super(comparator);
    }
    @Override
    void doRun(List<O> source) {
        setList(source);
        int len = source.size();
        int right = len - 1;
        for(int i=0;i<right;){
            int minIndex = i;
            int maxIndex = i;
            boolean minF = false;
            boolean maxF = false;
            for(int j=i+1;j < right+1;j++){
                if(compare(minIndex,j)){
                    minIndex = j;
                    minF = true;
                } else if(compare(j,maxIndex)){
                    maxIndex = j;
                    maxF = true;
                }
            }
            if(minF){
                swap(minIndex,i);
                i++;
            }
            if(maxF){
                swap(maxIndex,right);
                right--;
            }
            if(!(minF || maxF)){
                break;
            }
        }
    }
}
