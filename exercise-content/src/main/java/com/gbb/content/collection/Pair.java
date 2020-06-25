package com.gbb.content.collection;

/**
 * 自定义2元组
 * @author gaobinbin
 * @date 2020/06/25
 */
public class Pair<L,R> {
    private L l;
    private R r;
    public Pair(L l,R r) {
        this.l = l;
        this.r = r;
    }
    public L left(){return l;}
    public R right(){return r;}
}
