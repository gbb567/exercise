package com.gbb.content.utils;

import java.util.List;

/**
 * 简单集合工具类
 * @author gaobinbin
 * @date 2020/06/24
 */
public class CollectionUtils {
    public static boolean isEmpty(List list){return null == list || list.isEmpty();}

    public static boolean isNotEmpty(List list){return null != list && !list.isEmpty();}
}
