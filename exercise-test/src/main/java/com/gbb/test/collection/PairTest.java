package com.gbb.test.collection;

import com.gbb.content.collection.Pair;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public class PairTest {
    public static void test(){
        Pair<Integer,Integer> pair = new Pair<>(1,2);
        System.out.println(pair.left());
        System.out.println(pair.right());
    }
}
