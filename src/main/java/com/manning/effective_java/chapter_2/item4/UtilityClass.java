package com.manning.effective_java.chapter_2.item4;

/**
 * @author zhangyoubao
 * @version 2019/11/25
 */
public class UtilityClass {

    /*
        通过包含一个私有构造器来实现类的非实例化
     */

    // Suppress default constructor for noninstantiability
    private UtilityClass() {
        throw new AssertionError();
    }
}
