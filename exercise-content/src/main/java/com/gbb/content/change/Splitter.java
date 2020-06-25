package com.gbb.content.change;


import com.gbb.content.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简单的根据key拆分成多个list组合的工具类
 * @author gaobinbin
 * @date 2020/06/24
 */
public class Splitter{
    private static final Splitter splitter = new Splitter();
    public static <T,V,K>List<List<V>> out(List<T> source,Builder<T,V,K> builder){
        if(CollectionUtils.isEmpty(source)){
            return null;
        }
        SplitInstance<T,V,K> splitInstance = build(source,builder);
        if(null == splitInstance){
            return null;
        }
        return out(splitInstance);
    }
    private static <T,V,K>List<List<V>> out(SplitInstance<T,V,K> splitInstance){
        List<List<V>> result = new ArrayList<>();
        do{
            List<V> list = new ArrayList<>();
            splitInstance.fillList(list);
            result.add(list);
        }while (splitInstance.next());
        return result;
    }
    private static <T,V,K>SplitInstance<T,V,K> build(List<T> source,Builder<T,V,K> builder){
        if(CollectionUtils.isEmpty(source)){
            return null;
        }
        SplitInstance<T,V,K> root = splitter.new SplitInstance<>(builder);
        root.lastSplitInstance = root;
        Map<K,SplitInstance<T,V,K>> groupMap = new HashMap<>();
        groupMap.put(builder.group(source.get(0)),root);
        for(T t:source){
            K k  = builder.group(t);
            SplitInstance<T,V,K> splitInstance = groupMap.get(k);
            if(null == splitInstance){
                splitInstance = splitter.new SplitInstance<>(builder);
                groupMap.put(k,splitInstance);
                root.lastSplitInstance.nextSplitInstance = splitInstance;
                root.lastSplitInstance = splitInstance;
            }
            splitInstance.add(t);
        }
        return root;
    }
    public interface Builder<T,V,K>{
        V convert(T t);
        List<T> getTChildList(T t);
        List<V> getVChildList(V v);
        K group(T t);
    }
    private class SplitInstance<T,V,K>{
        private List<SplitNode<T,V,K>> splitNodeList = new ArrayList<>();
        private SplitInstance<T,V,K> nextSplitInstance;
        private SplitInstance<T,V,K> lastSplitInstance;
        private Builder<T, V, K> builder;
        private int currentIndex;
        public SplitInstance(Builder<T, V, K> builder) {
            this.builder = builder;
        }
        private void add(T node){
            this.splitNodeList.add(new SplitNode<>(node,this.builder));
        }
        private boolean next(){
            SplitNode<T,V,K> splitNode = splitNodeList.get(currentIndex);
            if(null != splitNode.childSplitInstance && splitNode.childSplitInstance.next()){
                return true;
            }
            if(++currentIndex == splitNodeList.size()){
                currentIndex = 0;
                return null != nextSplitInstance && nextSplitInstance.next();
            }
            return true;
        }
        private void fillList(List<V> list) {
            SplitNode<T,V,K> splitNode = splitNodeList.get(currentIndex);
            V v = this.builder.convert(splitNode.t);
            list.add(v);
            if(null != splitNode.childSplitInstance){
                splitNode.childSplitInstance.fillList(builder.getVChildList(v));
            }
            if(null != nextSplitInstance){
                nextSplitInstance.fillList(list);
            }
        }
    }
    private class SplitNode<T,V,K>{
        private T t;
        private SplitInstance<T,V,K> childSplitInstance;

        SplitNode(T node,Builder<T, V, K> builder) {
            this.t = node;
            this.childSplitInstance = build(builder.getTChildList(node),builder);
        }
    }
}
