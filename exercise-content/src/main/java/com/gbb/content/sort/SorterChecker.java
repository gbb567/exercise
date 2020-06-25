package com.gbb.content.sort;

import com.gbb.content.exceptions.ExceptionConstantEnum;

import java.util.Comparator;
import java.util.List;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public class SorterChecker {
    public static <O>void check(List<O> source, Comparator<O> comparator){
        for(int i=0;i<source.size()-1;i++){
            if(comparator.compare(source.get(i),source.get(i+1)) > 0){
                throw ExceptionConstantEnum.SORTER_CHECK_NOT_SORT();
            }
        }
        System.out.println("ok");
    }
}
