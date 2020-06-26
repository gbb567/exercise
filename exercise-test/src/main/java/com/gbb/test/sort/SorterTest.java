package com.gbb.test.sort;

import com.alibaba.fastjson.JSON;
import com.gbb.content.sort.*;
import jdk.nashorn.tools.Shell;

import java.util.List;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public class SorterTest {
    public static void test(){
        long start = System.currentTimeMillis();
        Sorter<Integer> sorter = SortFactory.getIntSorter(MergeSort.class);
        for(int i=0;i<3000;i++){
            List<Integer> list = IntListRandom.random(400);
            System.out.println(JSON.toJSONString(list));
            sorter.run(list);
            System.out.println(JSON.toJSONString(list));
            SorterChecker.check(list,Integer::compareTo);
        }
        System.out.println(System.currentTimeMillis()-start);
    }
}
