package com.gbb.test.change;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gbb.content.change.Splitter;
import com.gbb.content.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaobinbin
 * @date 2020/06/24
 */
public class SplitterTest {
    public static void test(){
        Splitter.Builder<T,V,Integer> builder = new Splitter.Builder<T,V,Integer>() {

            @Override
            public V convert(T t) {
                return new V(t.id);
            }

            @Override
            public List<T> getTChildList(T t) {
                return t.getChildList();
            }

            @Override
            public List<V> getVChildList(V v) {
                if(CollectionUtils.isEmpty(v.getChildList())){
                    v.setChildList(new ArrayList<>());
                }
                return v.getChildList();
            }

            @Override
            public Integer group(T t) {
                return t.groupId;
            }
        };
        System.out.println(JSON.toJSONString(Splitter.out(makeList(),builder), SerializerFeature.PrettyFormat));
    }

    private static List<T> makeList() {
        List<T> l = new ArrayList<>();
        T t1 = new T(1,1);
        List<T> l1c = new ArrayList<>();
        l1c.add(new T(11,1));
        l1c.add(new T(12,1));
        t1.setChildList(l1c);
        l.add(t1);
        l.add(new T(2,1));

        T t3 = new T(3,2);
        List<T> l3c = new ArrayList<>();
        l3c.add(new T(31,1));
        l3c.add(new T(32,1));
        t3.setChildList(l3c);
        l.add(t3);

        l.add(new T(4,2));
        return l;
    }

    public static class T{
        private int id;
        private int groupId;
        private List<T> childList;

        public T(int id, int groupId) {
            this.id = id;
            this.groupId = groupId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public List<T> getChildList() {
            return childList;
        }

        public void setChildList(List<T> childList) {
            this.childList = childList;
        }
    }
    public static class V{
        private int id;
        private List<V> childList;
        public V(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<V> getChildList() {
            return childList;
        }

        public void setChildList(List<V> childList) {
            this.childList = childList;
        }
    }
}
