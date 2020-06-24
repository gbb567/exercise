package com.gbb.test.collection;

import com.gbb.content.collection.HashInstance;
import com.gbb.content.collection.Pair;

import java.util.HashMap;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public class HashInstanceTest {
    public static class A{
        private Integer i;
        private String b;
    }
    public static void test(){
        HashInstance<Integer,Integer> hi = new HashInstance<>();
        Pair<HashInstance.HashKey<Integer>,Integer> pair = hi.put(1,1);
        Pair<HashInstance.HashKey<Integer>,Integer> pair2 = hi.put(2,2);
        System.out.println(pair.left());
        System.out.println(hi.get(pair.left()));
        System.out.println(hi.get(pair2.left()));

        HashInstance<A,Integer> hi2 = new HashInstance<>();
        Pair<HashInstance.HashKey<A>,Integer> pairA = hi2.put(new A(),30);
        Pair<HashInstance.HashKey<A>,Integer> pairA2 = hi2.put(new A(),20);
        System.out.println(hi2.get(pairA.left()));
        System.out.println(hi2.get(pairA2.left()));

        HashMap<A,Integer> map = new HashMap<>();
        A k1 = new A();
        A k2 = new A();
        map.put(k1,100);
        map.put(k2,300);
        System.out.println(map.get(k1));
        System.out.println(map.get(k2));
    }
}
