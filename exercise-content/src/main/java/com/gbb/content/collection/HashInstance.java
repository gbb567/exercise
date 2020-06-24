package com.gbb.content.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public class HashInstance<K,V> {
    private int count;
    private Map<HashKey<K>,V> map = new HashMap<>();

    public int size(){
        return map.size();
    }

    public V get(HashKey<K> k){
        return map.get(k);
    }

    public Pair<HashKey<K>,V> put(K k, V v){
        HashKey<K> key= new HashKey<>(++count,k);
        return new Pair<>(key,map.put(key,v));
    }

    public static class HashKey<K>{
        private int currentIndex;
        private K k;
        public HashKey(int i, K k) {
            this.currentIndex = i;
            this.k = k;
        }

        public K getKey(){
            return this.k;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HashKey<?> hashKey = (HashKey<?>) o;
            return currentIndex == hashKey.currentIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(currentIndex);
        }
    }
}
