package com.gbb.content.sort;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public class SortFactory {
    public static Sorter<Integer> getIntSorter(Class<? extends Sorter> clazz){
        try{
            Constructor<? extends Sorter<Integer>> constructor = (Constructor<? extends Sorter<Integer>>) clazz.getConstructor(Comparator.class);
            return constructor.newInstance((Comparator<Integer>) Integer::compare);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
