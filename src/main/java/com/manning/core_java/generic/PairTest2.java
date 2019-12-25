package com.manning.core_java.generic;

import java.time.LocalDate;

/**
 * @author zhangyoubao
 * @version 2019/9/23
 */
public class PairTest2 {

    public static void main(String[] args) {
        LocalDate[] birthdays = {
                LocalDate.of(1991, 9, 20),
                LocalDate.of(1993, 5, 23),
                LocalDate.of(1996, 8, 13),
                LocalDate.of(1991, 6, 4)
        };
        Pair<LocalDate> minmax = ArrayAlg2.minmax(birthdays);
        System.out.println("min = " + minmax.getFirst());
        System.out.println("max = " + minmax.getSecond());
    }
}

/**
 * 使用java泛型需要考虑的一些限制 1、不能用基本类型实例化类型参数 2、运行时类型查询只适用于原始类型 3、不能创建参数化类型的数组 4、Varargs警告 5、不能实例化类型变量 6、不能构造泛型数组
 * 7、泛型类的静态上下文中类型变量无效 8、不能抛出或捕获泛型类的实例 9、可以消除对受检异常的检查 10、注意擦除后的冲突
 */
class ArrayAlg2 {

    @SuppressWarnings("unchecked")
    static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) {
                min = a[i];
            }
            if (max.compareTo(a[i]) < 0) {
                max = a[i];
            }
        }
        return new Pair<>(min, max);
    }
}
