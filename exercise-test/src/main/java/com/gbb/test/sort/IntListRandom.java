package com.gbb.test.sort;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public class IntListRandom {
    private static final int LEN = 20;
    private static final int START = 100;
    private static final int END = 500;

    public static List<Integer> random(){
        return random(LEN);
    }

    public static List<Integer> random(int len){
        return random(len,START,END);
    }

    public static List<Integer> random(int len,int start,int end){
        Random random = new Random();
        List<Integer> source = new ArrayList<>(len);
        for(int i=0;i<len;i++){
            source.add(random.nextInt(end-start)+start);
        }
        return source;
    }
}
