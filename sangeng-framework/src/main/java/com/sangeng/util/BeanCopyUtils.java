package com.sangeng.util;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean 拷贝工具类封装
 *
 * @author bing_  @create 2022/2/7-20:20
 */
public class BeanCopyUtils {

    private BeanCopyUtils() {
    }

    /**
     * 拷贝单个实体类
     *
     * @param source 要拷贝的对象
     * @param clazz  拷贝结果对象 字节码方式
     * @param <V>
     * @return
     */
    public static <V> V copyBean(Object source, Class<V> clazz) {
        // 创建目标对象（通过反射）
        V result = null;
        try {
            //反射创建对象，默认使用空参创建
            result = clazz.newInstance();
            // 实现属性 copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回结果
        return result;
    }


    /**
     * 拷贝集合
     *
     * @param list
     * @param clazz
     * @param <O>
     * @param <V>
     * @return
     */
    public static <O, V> List<V> copyBeanList(List<O> list, Class<V> clazz) {
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}

