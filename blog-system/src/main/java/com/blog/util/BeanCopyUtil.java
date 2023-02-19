package com.blog.util;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtil {

    private BeanCopyUtil() {

    }

    public static <T> T copyBean(Object source, Class<T> clazz) {
        //创建目标对象
        T result = null;
        try {
            result = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //实现属性复制
        BeanUtils.copyProperties(source, result);
        //返回对象
        return result;
    }

    public static <V, T> List<T> copyBeanList(List<V> source, Class<T> clazz) {

        return source.stream().map(o -> copyBean(o, clazz)).collect(Collectors.toList());

    }
}
